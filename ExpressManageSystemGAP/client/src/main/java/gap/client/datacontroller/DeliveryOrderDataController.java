package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.deliveryorderdataservice;
import gap.common.po.DeliveryOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;

public class DeliveryOrderDataController {

	protected DeliveryOrderDataController() {
	}

	public ResultMessage add(DeliveryOrderPO po) {
		try {
			return deliveryorderdataservice.add(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public int getNextId(String cons) {
		try {
			return deliveryorderdataservice.getMaxId(cons);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}
}
