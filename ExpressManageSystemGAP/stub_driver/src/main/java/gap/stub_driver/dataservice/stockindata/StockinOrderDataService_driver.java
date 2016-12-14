package gap.stub_driver.dataservice.stockindata;

import java.rmi.RemoteException;

import gap.common.dataservice.orderdataservice.StockinOrderDataService;
import gap.common.po.StockinOrderPO;
import gap.common.util.ResultMessage;

public class StockinOrderDataService_driver {
	private void driver(StockinOrderDataService stockinData)
			throws RemoteException {
		StockinOrderPO stockinOrder1 = new StockinOrderPO(null, "20151026",
				"北京", "plane", "A区甲排1位");
		StockinOrderPO stockinOrder2 = new StockinOrderPO(null, "20151026",
				"上海", "car", "A区甲排2位");

		if (stockinData.add(stockinOrder1).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (stockinData.add(stockinOrder2).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (stockinData.add(stockinOrder1).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,stockinOrder exited");
		}
		StockinOrderPO get = stockinData.find("00100011970010100002");
		if (get != null)
			System.out.println("find:StockoutOrder" + get.getID());

		StockinOrderPO get1 = stockinData.find("00100011970010100001");
		if (get1 != null)
			System.out.println("find:StockoutOrder" + get.getID());
		get1 = stockinData.find("00100011970010100003");
		if (get == null)
			System.out.println("find failed,not found");
	}

	public static void main(String[] args) {
		StockinOrderDataService stockinData = new StockinOrderDataService_stub();
		StockinOrderDataService_driver driver = new StockinOrderDataService_driver();
		try {
			driver.driver(stockinData);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
