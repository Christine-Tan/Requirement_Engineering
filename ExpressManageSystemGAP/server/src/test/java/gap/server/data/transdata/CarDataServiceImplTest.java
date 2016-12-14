package gap.server.data.transdata;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.transdataservice.CarDataService;
import gap.common.netconfig.RMIConfig;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

public class CarDataServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		try {
			CarDataService cardata = (CarDataService) Naming
					.lookup(RMIConfig.url + ServiceName.CAR_DATA_SERVICE);
			cardata.delete("0010001003");
			// CarPO po = new CarPO("0010001001", "88888", "0010001", 5);
			// CarPO po1 = new CarPO("0010001002", "44444", "0010001", 3);
			// CarPO po2 = new CarPO("0010001003", "12345", "0010001", 3);
			// cardata.add(po);
			// cardata.add(po1);
			// cardata.add(po2);
			//
			// for (CarPO car : cardata.getAll("00100001")) {
			// System.out.println("car_id=" + car.getCar_id() + ",car_num="
			// + car.getCar_num());
			// }
			//
			// po2.setCar_num("12345");
			// System.out.println(cardata.modify(po2).getMessage());
			// for (CarPO car : cardata.getAll("0010001")) {
			// System.out.println("car_id=" + car.getCar_id() + ",car_num="
			// + car.getCar_num());
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
