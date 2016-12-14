package gap.server.data.strategydata;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.strategydataservice.CityDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.po.CityPO;
import gap.server.initial.NetInitial;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

public class CityDataServiceImplTest {
	@Before
	public void setup() throws Exception {
		NetInitial.initial();
	}

	@Test
	public void test() throws RemoteException, MalformedURLException,
			NotBoundException {
		CityDataService citydata = (CityDataService) Naming
				.lookup(RMIConfig.url + ServiceName.CITY_DATA_SERVICE);
		CityPO po1 = new CityPO("无锡市", "江苏省", 39.10, 117.10);
		System.out.println(citydata.add(po1).getMessage());
		CityPO po2 = citydata.find("南京市");
		System.out.println(po2.getCity() + po2.getProvince()
				+ po2.getLatitude() + po2.getLongitude());
		System.out.println("GET ALL!");
		for (CityPO get : citydata.getAll()) {
			System.out.println(get.getCity() + get.getProvince()
					+ get.getLatitude() + get.getLongitude());
		}
	}

}
