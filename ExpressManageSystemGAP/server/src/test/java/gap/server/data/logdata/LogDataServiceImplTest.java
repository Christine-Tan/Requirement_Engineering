package gap.server.data.logdata;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.logdataservice.LogDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.po.LogPO;
import gap.common.po.UserPO;
import gap.common.util.Gender;
import gap.common.util.UserType;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

public class LogDataServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		try {
			LogDataService logdata = (LogDataService) Naming
					.lookup(RMIConfig.url + ServiceName.LOG_DATA_SERVICE);
			UserPO user = new UserPO("000000001", "yyf", "123456", "杨雁飞",
					UserType.ADMINISTRATOR, Gender.MALE, "0010001");
			LogPO log1 = new LogPO(user,
					new Date(System.currentTimeMillis()).toString(), "test1"), log2 = new LogPO(
					user, new Date(System.currentTimeMillis()).toString(),
					"test2");
			logdata.addLog(log1);
			logdata.addLog(log2);
			for (LogPO log : logdata.getLogList()) {
				System.out.println("姓名：" + log.getUserPO().getUserName()
						+ ",日期:" + log.getDate() + ",操作：" + log.getOperate());
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
