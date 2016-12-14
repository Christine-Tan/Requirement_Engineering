package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.expressorderdataservice;
import static gap.client.datacontroller.NetModule.pricedataservice;
import gap.client.util.LocalInfo;
import gap.common.po.AllAddressPO;
import gap.common.po.ExpressOrderModifyPO;
import gap.common.po.ExpressOrderPO;
import gap.common.po.PricePO;
import gap.common.util.CurrentOrderType;
import gap.common.util.ExpressType;
import gap.common.util.ReceiveInfo;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

public class ExpressOrderDataController {

	protected ExpressOrderDataController() {
	}

	public ResultMessage add(ExpressOrderPO po) {
		// TODO 自动生成的方法存根
		try {
			return expressorderdataservice.add(po,
					LocalInfo.localuser.getUserId());
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public boolean isExisted(String order_id) {
		try {
			return expressorderdataservice.isExisted(order_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}

	public PricePO findPrice(String city) {
		try {
			return pricedataservice.find(city);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public ExpressOrderPO findExpressOrder(String order_id) {
		// TODO 自动生成的方法存根
		try {
			return expressorderdataservice.find(order_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage modify(ExpressOrderModifyPO po) {
		// TODO 自动生成的方法存根
		try {
			return expressorderdataservice.modify(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public List<ExpressOrderPO> findArrivingOrders(String ins_id) {
		// TODO 自动生成的方法存根
		try {
			return expressorderdataservice.findArrivingOrders(ins_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public List<ExpressOrderPO> findCurrentOrders(String ins_id,
			CurrentOrderType type) {
		// TODO 自动生成的方法存根
		try {
			return expressorderdataservice.findCurrentOrders(ins_id, type);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getState(String order_id) {
		try {
			return expressorderdataservice.getState(order_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage setReceived(ReceiveInfo info) {
		try {
			return expressorderdataservice.setRecieved(info);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public int nextID() {
		try {
			return expressorderdataservice.nextId();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}

	public AllAddressPO getAllAddress() {
		try {
			return expressorderdataservice.getAllAddress();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public double getDeliveryTime(String departure_city, String target_city,
			ExpressType type) {
		try {
			return expressorderdataservice.getDeliveryTime(departure_city,
					target_city, type);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}

}
