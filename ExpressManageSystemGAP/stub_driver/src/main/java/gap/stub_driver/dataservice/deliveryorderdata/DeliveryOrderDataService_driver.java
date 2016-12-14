package gap.stub_driver.dataservice.deliveryorderdata;

import java.rmi.RemoteException;

import gap.common.dataservice.orderdataservice.DeliveryOrderDataService;
import gap.common.po.DeliveryOrderPO;
import gap.common.util.ResultMessage;

public class DeliveryOrderDataService_driver {
	public void driver(DeliveryOrderDataService deliveryOrder)
			throws RemoteException {
		DeliveryOrderPO po = new DeliveryOrderPO(null, "19700101",
				"00100011970010100001");
		if (deliveryOrder.add(po).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed!");
		}
		if (deliveryOrder.add(po).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,order exited");
		}
		DeliveryOrderPO get = deliveryOrder.find("00100011970010100001");
		if (get != null)
			System.out.println("find:id=" + get.getID() + ",date="
					+ get.getTime());
		get = deliveryOrder.find("001100119700101000001");
		if (get == null)
			System.out.println("not found");
	}

	public static void main(String[] args) {
		DeliveryOrderDataService deliveryOrder = new DeliveryOrderDataService_stub();
		DeliveryOrderDataService_driver driver = new DeliveryOrderDataService_driver();
		try {
			driver.driver(deliveryOrder);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
