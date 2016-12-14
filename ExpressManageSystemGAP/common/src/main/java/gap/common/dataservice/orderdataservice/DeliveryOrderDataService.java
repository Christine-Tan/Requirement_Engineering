package gap.common.dataservice.orderdataservice;

import gap.common.po.DeliveryOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DeliveryOrderDataService extends Remote {
	public ResultMessage add(DeliveryOrderPO po) throws RemoteException;

	public DeliveryOrderPO find(String order_id) throws RemoteException;

	public ResultMessage setPassed(String order_id, String state_info)
			throws RemoteException;

	public List<DeliveryOrderPO> getUnpassedOrders() throws RemoteException;

	public int getMaxId(String cons) throws RemoteException;
}
