package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.arrivedOrderdataservice;
import gap.common.po.ArrivedOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

public class ArrivedOrderDataController {

	protected ArrivedOrderDataController() {
	}

	public ResultMessage add(ArrivedOrderPO po) {
		try {
			return arrivedOrderdataservice.add(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}
	
	public List<ArrivedOrderPO> getStockinginArrivedOrder(String ins_id){
		try {
			return arrivedOrderdataservice.getStockinginArrivedOrder(ins_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int nextId(String cons) {
		try {
			return arrivedOrderdataservice.getMaxId(cons);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}
	
	public ResultMessage setOrderStockin(String order_id){
		try {
			return arrivedOrderdataservice.setStockIn(order_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

}
