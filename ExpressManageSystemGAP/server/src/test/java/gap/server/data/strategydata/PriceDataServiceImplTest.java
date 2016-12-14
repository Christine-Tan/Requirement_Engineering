package gap.server.data.strategydata;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.strategydataservice.PriceDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.po.PricePO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

public class PriceDataServiceImplTest {
	@Before
	public void setup() throws Exception {
		// NetInitial.initial();
	}

	// String city, int express, int standard, int economic, double base
	@Test
	public void test() throws RemoteException, MalformedURLException,
			NotBoundException {
		PriceDataService pricedata = (PriceDataService) Naming
				.lookup(RMIConfig.url + ServiceName.PRICE_DATA_SERVICE);
		PricePO po1 = new PricePO("南京市", 32, 25, 18, 1);
		PricePO po2 = new PricePO("北京市", 32, 25, 18, 1);
		PricePO po3 = new PricePO("上海市", 32, 25, 18, 1);
		PricePO po4 = new PricePO("广州市", 32, 25, 18, 1);
		// System.out.println(pricedata.add(po1).getMessage());
		// System.out.println(pricedata.add(po2).getMessage());
		// System.out.println(pricedata.add(po3).getMessage());
		// System.out.println(pricedata.add(po4).getMessage());
		//
		// PricePO find = pricedata.find("广州市");
		// System.out.println(find.getCity() + find.getExpress() + ":"
		// + find.getStandard() + ":" + find.getEconomic() + " "
		// + find.getBase());
		PricePO po5 = new PricePO("广州市", 3, 2, 1, 2.0);
		System.out.println(pricedata.modify(po5).getMessage());

		System.out.println("GET ALL");
		for (PricePO get : pricedata.getAll()) {
			System.out.println(get.getCity() + get.getExpress() + ":"
					+ get.getStandard() + ":" + get.getEconomic() + " "
					+ get.getBase());
		}
	}

}
