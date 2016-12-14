package gap.server.data.transdata;

import gap.common.dataservice.transdataservice.CarDataService;
import gap.common.po.CarPO;
import gap.common.util.ResultMessage;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDataServiceImpl extends UnicastRemoteObject implements
		CarDataService {
	private String car_id_f = "car_id", car_num_f = "car_num",
			institution_id_f = "institution_id", server_time_f = "server_time";
	private String tableName = "car";
	private InsertSQL insertSQL;
	private UpdateSQL updateSQL;

	public static CarDataService instance;

	private CarDataServiceImpl() throws RemoteException {
		super();
		insertSQL = new InsertSQL(tableName);
		updateSQL = new UpdateSQL(tableName);
	}

	public static CarDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new CarDataServiceImpl();
		return instance;
	}

	public List<CarPO> getAll(String localins_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			List<CarPO> cars = new ArrayList<CarPO>();
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM car WHERE " + institution_id_f
							+ " = " + localins_id + ";");
			while (re.next()) {
				String id = re.getString(car_id_f), car_num = re
						.getString(car_num_f), ins_id = re
						.getString(institution_id_f);
				int server_time = re.getInt(server_time_f);
				CarPO po = new CarPO(id, car_num, ins_id, server_time);
				cars.add(po);
			}
			return cars;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage add(CarPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		String car_id = po.getCar_id(), car_num = po.getCar_num(), ins_id = po
				.getIns_id();
		int server_time = po.getServe_time();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT *FROM car WHERE car_id=" + car_id
							+ ";");
			if (re.next()) {
				System.out.println("车辆存在 车牌号为:" + re.getString(car_num_f));
				return ResultMessage.EXISTED;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		try {
			insertSQL.clear();
			insertSQL.add(car_id_f, car_id);
			insertSQL.add(car_num_f, car_num);
			insertSQL.add(institution_id_f, ins_id);
			insertSQL.add(server_time_f, server_time);
			String sql = insertSQL.createSQL();
			NetModule.excutor.excute(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

	public CarPO find(String car_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT *FROM car WHERE car_id=" + car_id
							+ ";");
			re.next();
			String id = re.getString(car_id_f), car_num = re
					.getString(car_num_f), ins_id = re
					.getString(institution_id_f);
			int server_time = re.getInt(server_time_f);
			CarPO po = new CarPO(id, car_num, ins_id, server_time);
			return po;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage modify(CarPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		String car_id = "'" + po.getCar_id() + "'", car_num = "'"
				+ po.getCar_num() + "'", ins_id = "'" + po.getIns_id() + "'";
		int server_time = po.getServe_time();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT *FROM car WHERE car_id=" + car_id
							+ ";");
			if (!re.next()) {
				return ResultMessage.NOTFOUND;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		try {
			String sql = "UPDATE car set car_num=" + car_num
					+ ",institution_id=" + ins_id + ",server_time="
					+ server_time + " WHERE car_id=" + car_id + ";";
			NetModule.excutor.excute(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

	public ResultMessage delete(String car_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			NetModule.excutor.excute("DELETE FROM car WHERE car_id = '"
					+ car_id + "' ;");
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();

		}
		return ResultMessage.FAILED;

	}

}
