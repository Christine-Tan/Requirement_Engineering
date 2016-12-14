package gap.common.dataservice.orderdataservice;

import gap.common.po.ArrivedOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ArrivedOrderDataService extends Remote {
	public ResultMessage add(ArrivedOrderPO po) throws RemoteException;

	public ArrivedOrderPO find(String order_id) throws RemoteException;

	public ResultMessage setPassed(String order_id, String state_info)
			throws RemoteException;

	public List<ArrivedOrderPO> getUnpassedOrders() throws RemoteException;

	public int getMaxId(String cons) throws RemoteException;

	public List<ArrivedOrderPO> getStockinginArrivedOrder(String ins_id) throws RemoteException;

	public ResultMessage setStockIn(String order_id) throws RemoteException;



}
