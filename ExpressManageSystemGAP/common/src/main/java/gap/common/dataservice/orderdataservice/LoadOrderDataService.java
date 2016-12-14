package gap.common.dataservice.orderdataservice;

import gap.common.po.LoadOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LoadOrderDataService extends Remote {
	public ResultMessage add(LoadOrderPO po) throws RemoteException;

	public LoadOrderPO find(String order_id) throws RemoteException;

	public ResultMessage setPassed(String order_id, String state_info)
			throws RemoteException;

	public List<LoadOrderPO> getUnpassedOrders() throws RemoteException;

	public int getMaxId(String cons) throws RemoteException;

	public List<LoadOrderPO> getArrivingLoadOrder(String ins_id)
			throws RemoteException;

	public ResultMessage setArrived(String ins_id) throws RemoteException;

}
