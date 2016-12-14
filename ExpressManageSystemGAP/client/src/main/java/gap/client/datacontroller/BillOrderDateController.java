package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.billorderdataservice;
import static gap.client.datacontroller.NetModule.expressorderdataservice;
import static gap.client.datacontroller.NetModule.userdataservice;
import gap.common.po.BillOrderPO;
import gap.common.po.UserPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

public class BillOrderDateController {

	protected BillOrderDateController() {
	}

	public double getDeliveryMoney(String date, String delivery_id) {
		try {
			return expressorderdataservice.getDeliveryMoney(date, delivery_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}

	public ResultMessage add(BillOrderPO po) {
		try {
			return billorderdataservice.add(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public List<UserPO> getDelivery(String ins_id) {
		try {
			return userdataservice.getDilivery(ins_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public int getNextID(String cons) {
		try {
			return billorderdataservice.getMaxId(cons);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}
}
