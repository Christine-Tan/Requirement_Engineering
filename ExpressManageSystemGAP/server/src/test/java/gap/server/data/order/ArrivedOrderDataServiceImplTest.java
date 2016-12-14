package gap.server.data.order;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.orderdataservice.ArrivedOrderDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.po.ArrivedOrderPO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

public class ArrivedOrderDataServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		try {
			ArrivedOrderDataService arrivedOrderDataService = (ArrivedOrderDataService) Naming
					.lookup(RMIConfig.url
							+ ServiceName.ARRIVEDORDER_DATA_SERVICE);
			// Map<String, String> orders = new HashMap<>();
			// orders.put("0000000003", "完好");
			// orders.put("0000000002", "损坏");
			// ArrivedOrderPO po = new ArrivedOrderPO(orders, "2015-03-01",
			// "00100011996030100002", "0010001", "001002", "测试");
			// arrivedOrderDataService.add(po);
			// arrivedOrderDataService.setPassed("00100012015112500001",
			// "南京玄武营业厅已收件");
			// ArrivedOrderPO po = arrivedOrderDataService
			// .find("00100011996030100001");
			// System.out.println(po.getId() + "," + po.getDes_ins_id() + ","
			// + po.getFrom_ins_id() + "," + po.getComment() + ","
			// + po.getTime());
			for (ArrivedOrderPO po : arrivedOrderDataService
					.getStockinginArrivedOrder("0011001")) {
				System.out.println(po.getID());
				arrivedOrderDataService.setStockIn(po.getID());
			}
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
