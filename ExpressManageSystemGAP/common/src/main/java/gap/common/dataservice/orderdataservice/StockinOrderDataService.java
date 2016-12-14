package gap.common.dataservice.orderdataservice;

import gap.common.po.StockinOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StockinOrderDataService extends Remote {
	public ResultMessage add(StockinOrderPO po) throws RemoteException;

	public StockinOrderPO find(String order_id, String ins_id)
			throws RemoteException;

	public List<StockinOrderPO> getOneDay(String date, String ins_id)
			throws RemoteException;

	public List<StockinOrderPO> getRequired(String beginDate, String endDate,
			String ins_id) throws RemoteException;

	public ResultMessage setPassed(String order_id, String state_info)
			throws RemoteException;

	public List<StockinOrderPO> getUnpassedOrders() throws RemoteException;
	
	public int getNextId(String cons) throws RemoteException;

}
