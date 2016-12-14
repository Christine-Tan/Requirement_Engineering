package gap.server.data.inventorydata;

import gap.common.dataservice.inventorydataservice.InventoryDataService;
import gap.common.po.GoodsPO;
import gap.common.util.ResultMessage;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.SQLBuilder;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDataServiceImpl extends UnicastRemoteObject implements
		InventoryDataService {

	// 表名
	private String sectorTable = "sector";
	// 字段
	private String ins_id_f = "ins_id", alarmValue_f = "alarmValue";
	// 表名
	private String sectorItemTable = "sector_item";
	// 字段
	private String location_f = "location", sectorId_f = "sector_id",
			expressorder_id_f = "expressorder_id", time_f = "time",
			belong_sec_f = "belong_sec", destination_f = "destination",
			existed_f = "existed";
	// // 表名
	// private String sector_flexTable = "sector_flex";
	// // 字段
	// private String flex_sector_id_f = "sector_id", beginshelf_f =
	// "beginshelf",
	// endshelf_f = "endshelf", belong_sec_id_f = "belong_sec_id",
	// used_f = "used";

	private InsertSQL sectorItemInsert;
	private UpdateSQL sectorUpdate, sectorItemUpdate;

	private SQLBuilder sql;

	public static InventoryDataService instance;

	public InventoryDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub

		sectorUpdate = new UpdateSQL(sectorTable);
		sectorItemInsert = new InsertSQL(sectorItemTable);
		sectorItemUpdate = new UpdateSQL(sectorItemTable);

	}

	public static InventoryDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new InventoryDataServiceImpl();
		return instance;
	}

	@Override
	public List<GoodsPO> getOneSector(String sector_id, String ins_id)
			throws RemoteException {
		try {
			String sql1 = "SELECT * FROM sector_item WHERE " + belong_sec_f
					+ " = '" + sector_id + "';";
			ResultSet re = NetModule.excutor.excuteQuery(sql1);
			if (!re.next()) {
				System.out.println("分区中无快递");
				return null;
			}
			List<GoodsPO> goodsPOs = getListByResultSet(re);
			return goodsPOs;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int getTotalNum(String ins_id) throws RemoteException{
		SQLBuilder builder = new SQLBuilder();
		builder.Select(sectorId_f).From(sectorTable).Where(ins_id_f).EQUALS(ins_id);
		try {
			ResultSet re = builder.excuteQuery();
//			ArrayList<String> sectors = new ArrayList<String>();
//			while(re.next()){
//				sectors.add(re.getString(sectorId_f))
//			}
			int num = 0;
			
			while(re.next()){
				String sectorID = re.getString(sectorId_f);
				builder.Select("COUNT("+sectorId_f+")").From(sectorItemTable).Where(sectorId_f).EQUALS(sectorID);
				ResultSet aSet = builder.excuteQuery();
				aSet.next();
				int aSectorCount = aSet.getInt("COUNT("+sectorId_f+")");
				num+=aSectorCount;
			}
			
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<GoodsPO> getOneTypeSector(String sector_id)
			throws RemoteException {
		try {
			String sql1 = "SELECT * FROM sector_item WHERE " + sectorId_f
					+ " = '" + sector_id + "';";
			ResultSet re = NetModule.excutor.excuteQuery(sql1);
			if (!re.next()) {
				System.out.println("分区中无快递");
				return null;
			}
			List<GoodsPO> goodsPOs = getListByResultSet(re);
			return goodsPOs;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getOneShelfNum(String position, String sector_id) {
		String sql1 = "SELECT * FROM " + sectorItemTable
				+ " WHERE LEFT(location,3) = '" + position + "' AND "
				+ sectorId_f + " = '" + sector_id + "' AND " + existed_f
				+ " = " + true + ";";
		int num = 0;
		try {
			ResultSet re = NetModule.excutor.excuteQuery(sql1);
			while (re.next()) {
				num++;
			}

			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<GoodsPO> getOneSectorExisted(String sector_id, String ins_id)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			String sql1 = "SELECT * FROM " + sectorItemTable + " WHERE "
					+ belong_sec_f + " = '" + sector_id + "' AND " + existed_f
					+ " = " + true + ";";
			ResultSet re = NetModule.excutor.excuteQuery(sql1);
			if (!re.next()) {
				// System.out.println("错误的分区编号");
				return null;
			}
			List<GoodsPO> goodsPOs = getListByResultSet(re);
			return goodsPOs;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<GoodsPO> getListByResultSet(ResultSet re) {
		List<GoodsPO> goodsPOs = new ArrayList<GoodsPO>();
		try {
			do {
				String location = re.getString(location_f), expressorder_id = re
						.getString(expressorder_id_f), time = re
						.getString(time_f), blong_sector = re
						.getString(belong_sec_f), destination = re
						.getString(destination_f), sector_id = re
						.getString(sectorId_f);

				GoodsPO po = new GoodsPO(expressorder_id, location, null, time,
						sector_id, blong_sector, destination);
				goodsPOs.add(po);

			} while (re.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodsPOs;
	}

	@Override
	public ResultMessage add(GoodsPO po) throws RemoteException {
		// TODO Auto-generated method stub
		String expressorder_id = po.getExpressorder_id(), location = po
				.getLocation(), belong_sector = po.getBelong_sector_id(), date = po
				.getDate(), sector_id = po.getSector_id(), destination = po
				.getDestination();

		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM sector_item WHERE expressorder_id='"
							+ expressorder_id + "AND "+sectorId_f+" = "+sector_id+ "';");
			if (re.next()) {
				System.out.println("订单号为" + expressorder_id + "的订单已经存在");
				return ResultMessage.EXISTED;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}

		try {
			// System.out.println("插入啦");
			sectorItemInsert.clear();
			sectorItemInsert.add(location_f, location);
			sectorItemInsert.add(sectorId_f, sector_id);
			sectorItemInsert.add(expressorder_id_f, expressorder_id);
			sectorItemInsert.add(time_f, date);
			sectorItemInsert.add(belong_sec_f, belong_sector);
			sectorItemInsert.add(destination_f, destination);
			sectorItemInsert.add(existed_f, false);
			String sql = sectorItemInsert.createSQL();
			NetModule.excutor.excute(sql);
			// System.out.println("插入好啦");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("报警");
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage add(List<GoodsPO> expressorders)
			throws RemoteException {
		// TODO Auto-generated method stub
		for (GoodsPO po : expressorders) {
			return add(po);
		}
		return ResultMessage.SUCCEED;

	}

	@Override
	public ResultMessage delete(String expressorder_id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			NetModule.excutor
					.excute("DELETE FROM sector_item WHERE expressorder_id='"
							+ expressorder_id + "';");
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("删除：快递编号不存在");

		}
		return ResultMessage.NOTFOUND;
	}

	@Override
	public ResultMessage delete(List<String> ids) throws RemoteException {
		// TODO Auto-generated method stub
		for (String id : ids) {
			return delete(id);
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage modify(GoodsPO po) throws RemoteException {
		// TODO Auto-generated method stubString expressorder_id =
		// po.getExpressorder_id(),
		String expressorder_id = po.getExpressorder_id(), location = po
				.getLocation(), belong_sector = po.getBelong_sector_id(), date = po
				.getDate(), sector_id = po.getSector_id(), destination = po
				.getDestination();

		try {
			ResultSet re = NetModule.excutor.excuteQuery("SELECT * FROM "
					+ sectorItemTable + " WHERE " + expressorder_id_f + "='"
					+ expressorder_id + "';");
			if (!re.next()) {
				return ResultMessage.NOTFOUND;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}

		try {
			System.out.println("found");
			sectorItemUpdate.clear();
			sectorItemUpdate.add(location_f, location);
			sectorItemUpdate.add(sectorId_f, sector_id);
			sectorItemUpdate.add(time_f, date);
			sectorItemUpdate.add(belong_sec_f, belong_sector);
			sectorItemUpdate.add(destination_f, destination);
			sectorItemUpdate.setKey(expressorder_id_f, expressorder_id);
			String sql = sectorItemUpdate.createSQL();
			NetModule.excutor.excute(sql);
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;

	}

	@Override
	public ResultMessage modify(List<GoodsPO> expressorders)
			throws RemoteException {
		// TODO Auto-generated method stub

		for (GoodsPO po : expressorders) {
			return modify(po);
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public GoodsPO find(String expressorder_id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM sector_item WHERE expressorder_id='"
							+ expressorder_id + "';");
			if (re.next()) {
				String location = re.getString(location_f), sector_id = re
						.getString(sectorId_f), time = re.getString(time_f), blong_sector = re
						.getString(belong_sec_f), destination = re
						.getString(destination_f);
				// SectorType type =
				// getSectorType(sector_id.charAt(sector_id.length()-1));

				GoodsPO po = new GoodsPO(expressorder_id, location, null, time,
						sector_id, blong_sector, destination);
				return po;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("查找:错误的快递编号");
		}
		return null;
	}

	@Override
	public ResultMessage setAlarm(double alarmValue, String ins_id)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			sectorUpdate.clear();
			sectorUpdate.add(alarmValue_f, alarmValue);
			sectorUpdate.setKey(ins_id_f, ins_id);
			String sql = sectorUpdate.createSQL();
			NetModule.excutor.excute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}

		return ResultMessage.SUCCEED;
	}

	@Override
	public double getAlarm(String ins_id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM sector WHERE ins_id ='"
							+ ins_id + "';");
			if (re.next()) {
				String alarm = re.getString(alarmValue_f);
				return Double.parseDouble(alarm);
			} else {
				System.out.println("错误的机构编号");
				return 0;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getFlexNum(String ins_id) throws RemoteException {
		// TODO Auto-generated method stub

		// try {
		// ResultSet re = NetModule.excutor.excuteQuery("");
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return 0;
	}

	@Override
	public List<GoodsPO> getOneTypeInFlex(String ins_id, String belong_sec_id) {
		// TODO Auto-generated method stub
		ResultSet re = sql.Select("*").From(sectorItemTable).Where(sectorId_f)
				.EQUALS(ins_id + "0").AND(belong_sec_f).EQUALS(belong_sec_id)
				.excuteQuery();
		try {
			if (!re.next()) {
				System.out.println("错误的分区编号或者错误的所属分区编号");
				return null;
			}
			List<GoodsPO> list = new ArrayList<GoodsPO>();
			list = getListByResultSet(re);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public ResultMessage setExisted(String id) {
		try {
			sectorItemUpdate.clear();
			sectorItemUpdate.add(existed_f, true);
			sectorItemUpdate.setKey(expressorder_id_f, id);
			String sql = sectorItemUpdate.createSQL();
			NetModule.excutor.excute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;

	}

	@Override
	public ResultMessage setUnexisted(String id) {
		try {
			sectorItemUpdate.clear();
			sectorItemUpdate.add(existed_f, false);
			sectorItemUpdate.setKey(expressorder_id_f, id);
			String sql = sectorItemUpdate.createSQL();
			NetModule.excutor.excute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage setlistExisted(List<GoodsPO> list) {
		if (list != null && list.size() > 0) {
			for (GoodsPO po : list) {
				return setExisted(po.getExpressorder_id());
			}
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage setlistUnexisted(List<String> list) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
				return setUnexisted(id);
			}
		}
		return ResultMessage.SUCCEED;
	}

}
