package gap.server.data.order;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.orderdataservice.DeliveryOrderDataService;
import gap.common.netconfig.RMIConfig;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

public class DeliveryOrderDataServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		try {
			DeliveryOrderDataService deliveryData = (DeliveryOrderDataService) Naming
					.lookup(RMIConfig.url
							+ ServiceName.DELIVERYPORDER_DATA_SERVICE);
			// Map<String, List<String>> orders = new HashMap<String,
			// List<String>>();
			// List<String> expressorders = new ArrayList<>();
			// expressorders.add("0000000001");
			// expressorders.add("0000000002");
			// orders.put("000000001", expressorders);
			// DeliveryOrderPO po = new DeliveryOrderPO(orders, "2015-03-02",
			// "00100012015030100001", "测试");
			// deliveryData.add(po);
			// deliveryData.setPassed("00100022015112500001", "xxx正在派件");
			// DeliveryOrderPO po = deliveryData.find("00100012015030100001");
			// System.out.println(po.getId() + "," + po.getTime() + ","
			// + po.getComment());
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
