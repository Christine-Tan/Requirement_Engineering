package gap.server.data.strategydata;

import gap.common.dataservice.strategydataservice.SalaryDataService;
import gap.common.po.SalaryPO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author seven
 */
public class SalaryDataServiceImpl extends UnicastRemoteObject implements
		SalaryDataService {
	// 表名
	private String tablename = "salary";
	// 字段
	private String id_f = "id", usertype_f = "userType", salary_f = "salary";
	private InsertSQL insertSQL;
	private UpdateSQL updateSQL;
	public static SalaryDataService instance;

	public static SalaryDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new SalaryDataServiceImpl();
		return instance;
	}

	public SalaryDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		insertSQL = new InsertSQL(tablename);
		updateSQL = new UpdateSQL(tablename);
	}

	@Override
	public SalaryPO find(UserType type) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM salary WHERE userType='"
							+ type.toString() + "';");
			re.next();
			double salary = re.getDouble(salary_f);
			SalaryPO po = new SalaryPO(type, salary);
			return po;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage add(SalaryPO po) throws RemoteException {
		// TODO Auto-generated method stub
		UserType usertype = po.getType();
		double salary = po.getSalary();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM salary WHERE userType='"
							+ usertype.toString() + "';");
			if (re.next())
				return ResultMessage.EXISTED;
			insertSQL.clear();
			insertSQL.add(usertype_f, usertype.toString());
			insertSQL.add(salary_f, salary);
			String sql = insertSQL.createSQL();
			NetModule.excutor.excute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage modify(SalaryPO po) throws RemoteException {
		// TODO Auto-generated method stub
		UserType usertype = po.getType();
		double salary = po.getSalary();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM salary WHERE userType='"
							+ usertype.toString() + "';");
			if (!re.next())
				return ResultMessage.NOTFOUND;
			int id = re.getInt(id_f);
			updateSQL.clear();
			updateSQL.setKey(id_f, id);
			updateSQL.add(usertype_f, usertype.toString());
			updateSQL.add(salary_f, salary);
			String sql = updateSQL.createSQL();
			NetModule.excutor.excute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public List<SalaryPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		List<SalaryPO> salaries = new ArrayList<SalaryPO>();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM salary;");
			while (re.next()) {
				UserType usertype = UserType.getUserType(re
						.getString(usertype_f));
				double salary = re.getDouble(salary_f);
				SalaryPO po = new SalaryPO(usertype, salary);
				salaries.add(po);
			}
			return salaries;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
