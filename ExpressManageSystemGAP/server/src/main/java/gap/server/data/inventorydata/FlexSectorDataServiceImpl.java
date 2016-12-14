package gap.server.data.inventorydata;

import gap.common.dataservice.inventorydataservice.FlexSectorDataService;
import gap.common.util.ResultMessage;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.SQLBuilder;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlexSectorDataServiceImpl implements FlexSectorDataService {
	private String tableName = "sector_flex";
	private String sector_id_f = "sector_id", beginshelf_f = "beginshelf",
			endshelf_f = "endshelf", belong_sec_id_f = "belong_sec_id",
			used_f = "used";

	private InsertSQL insert;
	private UpdateSQL update;

	private SQLBuilder sql;

	public static FlexSectorDataService instance;

	public FlexSectorDataServiceImpl() {
		super();
		insert = new InsertSQL(tableName);
		update = new UpdateSQL(tableName);
	}

	public static FlexSectorDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new FlexSectorDataServiceImpl();
		return instance;
	}

	@Override
	public ResultMessage add(String beginShelf, String endShelf,
			String belong_sec_id, String ins_id) {
		// TODO Auto-generated method stub

		// String belong = getIdfromType(type, ins_id);
		// if (belong.equals("null")) {
		// System.out.println("分配给的分区类型填写错误");
		// return ResultMessage.FAILED;
		// }

		try {
			ResultSet re = sql.Select("*").From(tableName).Where(beginshelf_f)
					.EQUALS(beginShelf).AND(endshelf_f).EQUALS(endShelf)
					.excuteQuery();
			if (re.next()) {
				return ResultMessage.EXISTED;
			} else {
				insert.clear();
				insert.add(sector_id_f, ins_id + "0");
				insert.add(endshelf_f, endShelf);
				insert.add(beginshelf_f, beginShelf);
				insert.add(belong_sec_id_f, belong_sec_id);
				insert.add(used_f, "false");
				String sql = insert.createSQL();
				NetModule.excutor.excute(sql);
				return ResultMessage.SUCCEED;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return ResultMessage.FAILED;
	}

	// public String getIdfromType(String type,String ins_id){
	// String belong;
	// switch (type) {
	// case "CAR":
	// belong = ins_id + "1";
	// break;
	// case "TRAIN":
	// belong = ins_id + "2";
	// break;
	// case "PLANE":
	// belong = ins_id + "3";
	// break;
	// default:
	// belong = "null";
	// break;
	// }
	// return belong;
	// }

	@Override
	public ResultMessage delete(String beginShelf, String endShelf,
			String ins_id) {
		// TODO Auto-generated method stub

		try {
			ResultSet re = sql.Select("*").From(tableName).Where(sector_id_f)
					.EQUALS(ins_id + "0").AND(beginshelf_f).EQUALS(beginShelf)
					.AND(endshelf_f).EQUALS(endShelf).excuteQuery();
			if (re.next()) {
				sql.DeleteFrom(tableName).Where(sector_id_f)
						.EQUALS(ins_id + "0").AND(beginshelf_f)
						.EQUALS(beginShelf).AND(endshelf_f).EQUALS(endShelf)
						.excute();
			} else {
				System.out.println("恢复的区域选择不正确");
				return ResultMessage.FAILED;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage modify(String beginShelf, String endShelf,
			String belong_sec_id, String ins_id) {
		// TODO Auto-generated method stub

		// String belong = getIdfromType(type, ins_id);
		// if(belong.equals("null")){
		// System.out.println("分配给的分区类型填写错误");
		// return ResultMessage.FAILED;
		// }

		try {
			ResultSet re = sql.Select("*").From(tableName).Where(beginshelf_f)
					.EQUALS(beginShelf).AND(endshelf_f).EQUALS(endShelf)
					.AND(sector_id_f).EQUALS(ins_id + "0").excuteQuery();

			if (!re.next()) {
				System.out.println("错误的区域选择");
				return ResultMessage.FAILED;
			} else {
				if (re.getString(belong_sec_id_f).equals(belong_sec_id)) {
					System.out.println("已经分配过");
					return ResultMessage.SUCCEED;
				} else if (re.getString(used_f).equals("true")) {
					System.out.println("所选区域已被使用");
					return ResultMessage.FAILED;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			update.clear();
			update.add(belong_sec_id_f, belong_sec_id);
			update.setKey(sector_id_f, ins_id + "0");
			String sql = update.createSQL();
			NetModule.excutor.excute(sql);
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	@Override
	public String getBelongSecId(String beginShelf, String endShelf,
			String ins_id) {
		// TODO Auto-generated method stub
		ResultSet re = sql.Select("*").From(tableName).Where(beginshelf_f)
				.EQUALS(beginShelf).AND(endshelf_f).EQUALS(endShelf)
				.AND(sector_id_f).EQUALS(ins_id + "0").excuteQuery();
		try {
			if (re.next()) {
				String belong = re.getString(belong_sec_id_f);
				return belong;
			} else {
				System.out.println("获取类型：错误的区域");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public String isUsed(String beginShelf, String endShelf, String ins_id) {
		// TODO Auto-generated method stub
		ResultSet re = sql.Select("*").From(tableName).Where(beginshelf_f)
				.EQUALS(beginShelf).AND(endshelf_f).EQUALS(endShelf)
				.AND(sector_id_f).EQUALS(ins_id + "0").excuteQuery();
		try {
			if (re.next()) {
				String used = re.getString(used_f);
				return used;
			} else {
				System.out.println("获取类型：错误的区域");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
