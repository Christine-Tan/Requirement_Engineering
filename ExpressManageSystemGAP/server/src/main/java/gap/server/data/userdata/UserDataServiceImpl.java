package gap.server.data.userdata;

import gap.common.dataservice.userdataservice.UserDataService;
import gap.common.po.UserPO;
import gap.common.util.Gender;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.SQLBuilder;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author seven
 */
public class UserDataServiceImpl extends UnicastRemoteObject implements
		UserDataService {
	// 表名
	private String tableName = "user";
	// 字段
	private String id_f = "id", username_f = "username",
			password_f = "password", institution_f = "institution",
			rank_f = "rank", userType_f = "userType", name_f = "name",
			gender_f = "gender", lastpaid_f = "lastpaid";
	private InsertSQL insertSQL;
	private UpdateSQL updateSQL;

	public static UserDataService instance;

	private UserDataServiceImpl() throws RemoteException {
		super();
		insertSQL = new InsertSQL(tableName);
		updateSQL = new UpdateSQL(tableName);
	}

	public static UserDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new UserDataServiceImpl();
		return instance;

	}

	public List<UserPO> getAll(UserType userType) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			List<UserPO> users = new ArrayList<UserPO>();
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM user WHERE userType='"
							+ userType + "';");
			while (re.next()) {
				users.add(getByResultSet(re));
			}
			return users;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage add(UserPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		String user_id = po.getUserId(), username = po.getUserName(), password = po
				.getPassword(), ins_id = po.getIns_id(), name = po.getName(), gender = po
				.getGender().toString();
		UserType usertype = po.getType();
		// int rank = po.getRank();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT *FROM user WHERE id='" + user_id
							+ "';");
			if (re.next()) {
				System.out.println(re.getString("username"));
				return ResultMessage.EXISTED;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		try {
			insertSQL.clear();
			insertSQL.add(id_f, user_id);
			insertSQL.add(userType_f, usertype);
			insertSQL.add(username_f, username);
			insertSQL.add(password_f, password);
			insertSQL.add(institution_f, ins_id);
			// insertSQL.add(rank_f, rank);
			insertSQL.add(name_f, name);
			insertSQL.add(gender_f, gender);
			String sql = insertSQL.createSQL();
			NetModule.excutor.excute(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

	public UserPO findById(String user_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT *FROM user WHERE id= '" + user_id
							+ "';");
			re.next();
			return getByResultSet(re);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage modify(UserPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		String user_id = po.getUserId(), username = po.getUserName(), password = po
				.getPassword(), ins_id = po.getIns_id(), name = po.getName(), gender = po
				.getGender().toString();
		UserType usertype = po.getType();
		// int rank = po.getRank();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT *FROM user WHERE id='" + user_id
							+ "';");
			if (!re.next()) {
				return ResultMessage.NOTFOUND;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		try {
			updateSQL.clear();
			updateSQL.add(institution_f, ins_id);
			updateSQL.add(username_f, username);
			updateSQL.add(password_f, password);
			// updateSQL.add(rank_f, rank);
			updateSQL.add(userType_f, usertype);
			updateSQL.add(name_f, name);
			updateSQL.add(gender_f, gender);
			updateSQL.setKey(id_f, user_id);
			String sql = updateSQL.createSQL();
			NetModule.excutor.excute(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.SUCCEED;
	}

	public ResultMessage delete(String user_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM user WHERE id=" + user_id + ";");
			if (!re.next()) {
				return ResultMessage.NOTFOUND;
			} else
				NetModule.excutor.excute("DELETE FROM user WHERE id='"
						+ user_id + "';");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public UserPO findByUsername(String username) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM user WHERE username ='"
							+ username + "';");
			re.next();
			return getByResultSet(re);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserPO> findUnpaidUser(Date date) throws RemoteException {
		// TODO 自动生成的方法存根

		// 把日期的天改成1，这样能获得上个月的未付款人
		@SuppressWarnings("deprecation")
		Date dateForCompute = new Date(date.getYear()-1900, date.getMonth(), 1);
		System.out.println(dateForCompute.toString());
		try {
			List<UserPO> users = new ArrayList<UserPO>();
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM user WHERE " + lastpaid_f
							+ "<'" + dateForCompute.toString() + "';");
			while (re.next()) {
				users.add(getByResultSet(re));
			}
			return users;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage setPaid(String user_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			updateSQL.clear();
		//	updateSQL.add(lastpaid_f, new Date(System.currentTimeMillis()).toString());
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			updateSQL.add(lastpaid_f, format.format(calendar.getTime()));
			updateSQL.setKey(id_f, user_id);
			NetModule.excutor.excute(updateSQL.createSQL());
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
	public List<UserPO> getDilivery(String ins_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE "
					+ institution_f + " = " + ins_id + " AND " + userType_f
					+ " = '" + UserType.DELIVERY + "';";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<UserPO> orders = new ArrayList<UserPO>();
			while (re.next()) {
				orders.add(getByResultSet(re));
			}
			return orders;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	// 通过结果集获得单一userPO对象
	private UserPO getByResultSet(ResultSet re) {
		try {
			String id, password, ins_id, name, username;
			id = re.getString(id_f);
			password = re.getString(password_f);
			ins_id = re.getString(institution_f);
			name = re.getString(name_f);
			username = re.getString(username_f);
			int rank = re.getInt(rank_f);
			Gender gender = Gender.valueOf(re.getString(gender_f));
			UserType type = UserType.getUserType(re.getString(userType_f));
			UserPO po = new UserPO(id, username, password, name, type, gender,
					ins_id);

			// 设置上次付款日期，这个属性只有财务人员用到，所以特意标注一下
			Date lastPayDate = re.getDate(lastpaid_f);
			po.setLastPayDate(lastPayDate);

			return po;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getPeopleNum(String ins_id, UserType type) throws RemoteException {
		SQLBuilder builder = new SQLBuilder();
		
		builder.Select("COUNT(*)").From(tableName).Where(institution_f).EQUALS(ins_id)
			.AND(userType_f).EQUALS(type);
		ResultSet resultSet = builder.excuteQuery();
		try {
			resultSet.next();
			int num = resultSet.getInt("COUNT(*)");
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

}
