package gap.server.data.transdata;

import gap.common.dataservice.transdataservice.DriverDataService;
import gap.common.po.DriverPO;
import gap.common.util.Gender;
import gap.common.util.ResultMessage;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDataServiceImpl extends UnicastRemoteObject implements
		DriverDataService {

	public static DriverDataService instance;

	private DriverDataServiceImpl() throws RemoteException {
		super();
		// TODO 自动生成的构造函数存根
	}

	public static DriverDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new DriverDataServiceImpl();
		return instance;
	}

	public List<DriverPO> getAll(String localins_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			List<DriverPO> drivers = new ArrayList<DriverPO>();
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM driver WHERE institution='"
							+ localins_id + "';");
			while (re.next()) {
				String order_id = re.getString("id"), name = re
						.getString("name"), ins_id = re
						.getString("institution"), birth = re
						.getString("birth"), driving_licence_due = re
						.getString("driving_license_due"), phone = re
						.getString("phone"), identity_number = re
						.getString("identity_num");
				Gender gender = Gender.valueOf(re.getString("gender")
						.toUpperCase());
				DriverPO po = new DriverPO(order_id, ins_id, name, birth,
						identity_number, phone, driving_licence_due, gender);
				drivers.add(po);
			}
			return drivers;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage add(DriverPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		String id = "'" + po.getId() + "'", name = "'" + po.getName() + "'", ins_id = "'"
				+ po.getIns_id() + "'", birth = "'" + po.getBirth() + "'", phone = "'"
				+ po.getPhone() + "'", driving_lice_due = "'"
				+ po.getDriving_license_due() + "'", gender = "'"
				+ po.getGender() + "'", identity_number = "'"
				+ po.getIdentity_number() + "'";
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM driver WHERE id=" + id + ";");
			if (re.next()) {
				System.out.println("司机存在 司机名字为:" + re.getString("name"));
				return ResultMessage.EXISTED;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		try {
			String sql = "INSERT INTO driver (id,name,institution,gender,birth,phone,identity_num,driving_license_due) VALUES ("
					+ id
					+ ","
					+ name
					+ ","
					+ ins_id
					+ ","
					+ gender
					+ ","
					+ birth
					+ ","
					+ phone
					+ ","
					+ identity_number
					+ ","
					+ driving_lice_due + ");";
			NetModule.excutor.excute(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

	public DriverPO find(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT *FROM driver WHERE id='" + id + "';");
			re.next();
			String name = re.getString("name"), ins_id = re
					.getString("institution"), birth = re.getString("birth"), driving_licence_due = re
					.getString("driving_license_due"), phone = re
					.getString("phone"), identity_number = re
					.getString("identity_num");
			Gender gender = Gender
					.valueOf(re.getString("gender").toUpperCase());
			DriverPO po = new DriverPO(ins_id, ins_id, name, birth,
					identity_number, phone, driving_licence_due, gender);
			return po;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage modify(DriverPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		String id = "'" + po.getId() + "'", name = "'" + po.getName() + "'", ins_id = "'"
				+ po.getIns_id() + "'", birth = "'" + po.getBirth() + "'", phone = "'"
				+ po.getPhone() + "'", driving_lice_due = "'"
				+ po.getDriving_license_due() + "'", gender = "'"
				+ po.getGender() + "'", identity_number = "'"
				+ po.getIdentity_number() + "'";
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT *FROM driver WHERE id=" + id + ";");
			if (!re.next()) {
				return ResultMessage.NOTFOUND;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		try {
			String sql = "UPDATE driver set name=" + name + ",institution="
					+ ins_id + ",gender=" + gender + ",birth=" + birth
					+ ",phone=" + phone + ",driving_license_due="
					+ driving_lice_due + ",identity_num=" + identity_number
					+ " WHERE id=" + id + ";";
			NetModule.excutor.excute(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

	public ResultMessage delete(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			NetModule.excutor.excute("DELETE FROM driver WHERE id='" + id
					+ "';");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

}
