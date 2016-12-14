package gap.server.data.strategydata;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.strategydataservice.RentDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.po.RentPO;
import gap.server.initial.NetInitial;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

public class RentDataServiceImplTest {
	@Before
	public void setup() throws Exception {
		NetInitial.initial();
	}

	@Test
	public void test() throws RemoteException, MalformedURLException,
			NotBoundException {
		RentDataService rentdata = (RentDataService) Naming
				.lookup(RMIConfig.url + ServiceName.RENT_DATA_SERVICE);
		Date date = new Date(System.currentTimeMillis());
		RentPO po1 = new RentPO("南京市栖霞区营业厅", 1000, date);
		RentPO po2 = new RentPO("南京市玄武区中转中心", 1000, date);
		RentPO po3 = new RentPO("北京市栖霞区营业厅", 1000, date);
		RentPO po4 = new RentPO("北京市栖霞区中转中心", 1000, date);
		RentPO po5 = new RentPO("广州市栖霞区营业厅", 1000, date);
		RentPO po6 = new RentPO("广州市栖霞区中转中心", 1000, date);
		RentPO po7 = new RentPO("上海市静安区营业厅", 1000, date);
		RentPO po8 = new RentPO("上海市静安区中转中心", 1000, date);
		RentPO po9 = new RentPO("上海市静安区中转中心", 3000, date);
		System.out.println(rentdata.add(po1).getMessage());
		System.out.println(rentdata.add(po2).getMessage());
		System.out.println(rentdata.add(po3).getMessage());
		System.out.println(rentdata.add(po4).getMessage());
		System.out.println(rentdata.add(po5).getMessage());
		System.out.println(rentdata.add(po6).getMessage());
		System.out.println(rentdata.add(po7).getMessage());
		System.out.println(rentdata.add(po8).getMessage());
		System.out.println(rentdata.modify(po9).getMessage());

		for (RentPO get : rentdata.getAll()) {
			System.out.println(get.getInstitution() + get.getMoney());
		}

	}

}
