package gap.stub_driver.dataservice.arrivedorderdata;

import java.rmi.RemoteException;

import gap.common.dataservice.orderdataservice.ArrivedOrderDataService;
import gap.common.po.ArrivedOrderPO;
import gap.common.util.ResultMessage;

public class ArrivedOrderDataService_driver {
	public void drive(ArrivedOrderDataService arrivedOrder)
			throws RemoteException {
		ArrivedOrderPO po = new ArrivedOrderPO(null, "19700101",
				"00100011970010100001");
		if (arrivedOrder.add(po).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed!");
		}
		if (arrivedOrder.add(po).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,order exited");
		}
		ArrivedOrderPO get = arrivedOrder.find("00100011970010100001");
		if (get != null)
			System.out.println("find:id=" + get.getID() + ",date="
					+ get.getTime());
		get = arrivedOrder.find("001100119700101000001");
		if (get == null)
			System.out.println("not found");
	}

	public static void main(String[] args) {
		ArrivedOrderDataService_driver driver = new ArrivedOrderDataService_driver();
		ArrivedOrderDataService arrivedOrder = new ArrivedOrderDataService_stub();
		try {
			driver.drive(arrivedOrder);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
