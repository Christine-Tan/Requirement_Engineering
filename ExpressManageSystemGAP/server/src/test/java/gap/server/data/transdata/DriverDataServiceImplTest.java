package gap.server.data.transdata;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.transdataservice.DriverDataService;
import gap.common.netconfig.RMIConfig;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

public class DriverDataServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		try {
			DriverDataService driverData = (DriverDataService) Naming
					.lookup(RMIConfig.url + ServiceName.DRIVER_DATA_SERVICE);
			driverData.delete("0010001002");
			// DriverPO po = new DriverPO("0010001001", "0010001", "yyf",
			// "1996-03-01", "500113199603013932", "15520065137",
			// "2020-01-01", Gender.MALE);
			// DriverPO po1 = new DriverPO("0010001002", "0010002", "sss",
			// "1996-03-01", "111111111111111111", "12345678987",
			// "2020-01-01", Gender.MALE);
			// driverData.add(po);
			// driverData.add(po1);
			// for (DriverPO driver : driverData.getAll("0010001")) {
			// System.out.println("编号：" + driver.getId() + ",姓名："
			// + driver.getName() + "，身份证号："
			// + driver.getIdentity_number());
			// }
			// po1.setName("wgf");
			// System.out.println(driverData.modify(po1));
			// for (DriverPO driver : driverData.getAll("0010001")) {
			// System.out.println("编号：" + driver.getId() + ",姓名："
			// + driver.getName() + "，身份证号："
			// + driver.getIdentity_number());
			// }
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
