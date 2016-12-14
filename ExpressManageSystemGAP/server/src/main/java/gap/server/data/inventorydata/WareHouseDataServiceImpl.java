package gap.server.data.inventorydata;

import gap.common.dataservice.inventorydataservice.WareHouseDataService;
import gap.common.util.ResultMessage;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.SQLBuilder;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WareHouseDataServiceImpl implements WareHouseDataService {
	private String sectorTable = "sector";
	private String sector_id_f = "sector_id", type_f = "type",
			ins_id_f = "ins_id", alarmValue_f = "alarmValue", rows_f = "rows",
			shelves_f = "shelves", units_f = "units";

	private String sectorItemTable = "sector_item";

	private InsertSQL sectorInsert;
	private double defaultAlarm = 80;
	private int rows = 10, shelves = 10, units = 10;
	public static WareHouseDataService instance;

	private SQLBuilder sql;

	public WareHouseDataServiceImpl() {
		sectorInsert = new InsertSQL(sectorTable);
		sql = new SQLBuilder();
	}

	public WareHouseDataService getInstance() {
		if (instance == null) {
			instance = new WareHouseDataServiceImpl();
		}
		return instance;
	}

	@Override
	public ResultMessage add(String ins_id, String sector_id)
			throws RemoteException {
		// TODO Auto-generated method stub

		// System.out.println("开始了");
		try {

			ResultSet re = sql.Select("*").From(sectorTable).Where(ins_id_f)
					.EQUALS(ins_id).AND(sector_id_f).EQUALS(sector_id)
					.excuteQuery();
			if (re.next()) {
				System.out.println("已经存在了");
				return ResultMessage.EXISTED;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String type;
		switch (sector_id.charAt(sector_id.length() - 1)) {
		case '0':
			type = "FLEX";
			break;
		case '1':
			type = "CAR";
			break;
		case '2':
			type = "TRAIN";
			break;
		case '3':
			type = "PLANE";
			break;
		default:
			System.out.println("WRONG sector_id !!!!!!!!");
			return ResultMessage.FAILED;
		}

		try {
			sectorInsert.clear();
			sectorInsert.add(sector_id_f, sector_id);
			sectorInsert.add(type_f, type);
			sectorInsert.add(ins_id_f, ins_id);
			sectorInsert.add(alarmValue_f, defaultAlarm);
			sectorInsert.add(rows_f, rows);
			sectorInsert.add(shelves_f, shelves);
			sectorInsert.add(units_f, units);
			String sql = sectorInsert.createSQL();
			NetModule.excutor.excute(sql);
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

		return ResultMessage.FAILED;
	}

	@Override
	public ResultMessage delete(String ins_id) throws RemoteException {
		// TODO Auto-generated method stub
		sql.DeleteFrom(sectorTable).Where(ins_id).EQUALS(ins_id).excute();

		sql.DeleteFrom(sectorItemTable).Where(ins_id).EQUALS(ins_id + "0")
				.excute();
		sql.DeleteFrom(sectorItemTable).Where(ins_id).EQUALS(ins_id + "1")
				.excute();
		sql.DeleteFrom(sectorItemTable).Where(ins_id).EQUALS(ins_id + "2")
				.excute();
		sql.DeleteFrom(sectorItemTable).Where(ins_id).EQUALS(ins_id + "3")
				.excute();
		return null;
	}

}
