package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.loadorderdataservice;
import gap.common.po.LoadOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

public class LoadOrderDataController {

	protected LoadOrderDataController() {
	}

	public ResultMessage add(LoadOrderPO po) {
		try {
			return loadorderdataservice.add(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public int nextId(String ins_id) {
		try {
			return loadorderdataservice.getMaxId(ins_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}

	public List<LoadOrderPO> getArrivingLoadOrder(String ins_id) {
		try {
			return loadorderdataservice.getArrivingLoadOrder(ins_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
}
