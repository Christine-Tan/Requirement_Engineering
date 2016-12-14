package gap.stub_driver.dataservice.loadorder;

import java.rmi.RemoteException;

import gap.common.dataservice.orderdataservice.LoadOrderDataService;
import gap.common.po.LoadOrderPO;
import gap.common.util.ResultMessage;

public class LoadOrderDataService_driver {
	public void drive(LoadOrderDataService arrivedOrder) throws RemoteException {
		LoadOrderPO po = new LoadOrderPO("19700101", "00100011970010100001",
				"0010001", "0011001", "", "", null);
		if (arrivedOrder.add(po).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed!");
		}
		if (arrivedOrder.add(po).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,order exited");
		}
		LoadOrderPO get = arrivedOrder.find("00100011970010100001");
		if (get != null)
			System.out.println("find:id=" + get.getNumber() + ",date="
					+ get.getDate());
		get = arrivedOrder.find("001100119700101000001");
		if (get == null)
			System.out.println("not found");
	}

	public static void main(String[] args) {
		LoadOrderDataService_driver driver = new LoadOrderDataService_driver();
		LoadOrderDataService arrivedOrder = new LoadOrderDataService_stub();
		try {
			driver.drive(arrivedOrder);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
