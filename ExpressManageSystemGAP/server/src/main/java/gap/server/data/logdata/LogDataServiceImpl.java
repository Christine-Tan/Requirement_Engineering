package gap.server.data.logdata;

import gap.common.dataservice.logdataservice.LogDataService;
import gap.common.dataservice.userdataservice.UserDataService;
import gap.common.po.LogPO;
import gap.common.po.UserPO;
import gap.server.data.userdata.UserDataServiceImpl;
import gap.server.data.util.InsertSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDataServiceImpl extends UnicastRemoteObject implements LogDataService {

	private String tableName = "log";
	private String timef = "time", user_idf = "user_id", operatef = "operate";
	private InsertSQL insertSQL;

	public static LogDataService instance;

	private LogDataServiceImpl() throws RemoteException {
		super();
		insertSQL = new InsertSQL(tableName);
	}

	public static LogDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new LogDataServiceImpl();
		return instance;
	}

	public List<LogPO> getLogList() throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			List<LogPO> logs = new ArrayList<LogPO>();
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM " + tableName + " ORDER BY " + timef + " DESC ;");
			UserDataService userdata = UserDataServiceImpl.getInstance();
			while (re.next()) {
				UserPO user = userdata.findById(re.getString(user_idf));
				String date = re.getString(timef);
				String operate = re.getString(operatef);
				logs.add(new LogPO(user, date, operate));
			}
			return logs;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return null;
	}

	public boolean addLog(LogPO logPO) throws RemoteException {
		// TODO 自动生成的方法存根

		try {
			insertSQL.clear();
			insertSQL.add(user_idf, logPO.getUserPO().getUserId());
			insertSQL.add(operatef, logPO.getOperate());
			String sql = insertSQL.createSQL();
			NetModule.excutor.excute(sql);
			return true;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
}
