package gap.common.dataservice.receiptdataservice;

import gap.common.po.BillOrderPO;
import gap.common.util.OrderState;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

public interface BillOrderDataService extends Remote {
	public ResultMessage add(BillOrderPO po) throws RemoteException;

	public BillOrderPO find(String order_id) throws RemoteException;

	public ResultMessage setPassed(String order_id, String state_info)
			throws RemoteException;

	public List<BillOrderPO> getUnpassedOrders() throws RemoteException;

	public int getMaxId(String cons) throws RemoteException;

	/**
	 * 
	 * 获得这个时间段内所有通过的收款单。假如没有找到单据就返回一个空的list，不要返回null
	 * @param start
	 * @param end
	 * @return
	 */
	public List<BillOrderPO> getPassedOrder(Calendar start, Calendar end)
			throws RemoteException;

	/**
	 * 
	 * 根据一个具体日期和一个机构ID获得收款单list。<br/>
	 * 假如calendar是null，就返回该机构所有收款单.<br/>
	 * 假如institutionID是null，就返回这一天当中所有的收款单。<br/>
	 * 都不是null就返回该机构当天的list。没找到返回空list，不要返回null。
	 * 
	 * @param orderID
	 * @param institutionID
	 * @return
	 * @throws RemoteException
	 */
	public List<BillOrderPO> getPassedOrder(Calendar oneDay,
			String institutionID) throws RemoteException;

	/**
	 * 
	 * 根据ID查询收款单是不是通过。<br/>
	 * 通过就返回OrderState.PASSED，已经提交但没通过就返回OrderState.SUBMITTED<br/>
	 * 没找到就返回null.
	 * 
	 * @param orderID
	 * @return
	 * @throws RemoteException
	 */
	public OrderState isOrderPassed(String orderID) throws RemoteException;

}
