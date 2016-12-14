package gap.common.dataservice.inventorydataservice;

import gap.common.po.GoodsPO;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface InventoryDataService extends Remote {
	public ResultMessage add(GoodsPO expressorder) throws RemoteException;

	public ResultMessage add(List<GoodsPO> expressorders)
			throws RemoteException;

	public ResultMessage delete(String expressorder_id) throws RemoteException;

	public ResultMessage delete(List<String> ids)
			throws RemoteException;

	public ResultMessage modify(GoodsPO expressorder) throws RemoteException;

	public ResultMessage modify(List<GoodsPO> expressorders)
			throws RemoteException;

	public GoodsPO find(String expressorder_id) throws RemoteException;

	public List<GoodsPO> getOneSector(String sector_id, String ins_id)
			throws RemoteException;
	
	public List<GoodsPO> getOneSectorExisted(String sector_id,String ins_id) throws RemoteException;
	
	public List<GoodsPO> getOneTypeSector(String sector_id) throws RemoteException;

	public int getFlexNum(String ins_id) throws RemoteException;

	public ResultMessage setAlarm(double alarmValue, String ins_id)
			throws RemoteException;

	public double getAlarm(String ins_id) throws RemoteException;

	// 从机动区获得某一货运方式的快递
	public List<GoodsPO> getOneTypeInFlex(String ins_id, String belong_sec_id)
			throws RemoteException;

	public ResultMessage setExisted(String id) throws RemoteException;

	public ResultMessage setlistExisted(List<GoodsPO> list) throws RemoteException;

	public int getOneShelfNum(String position, String sector_id) throws RemoteException;

	public ResultMessage setUnexisted(String id) throws RemoteException;

	public ResultMessage setlistUnexisted(List<String> list) throws RemoteException;

	public int getTotalNum(String ins_id) throws RemoteException;
}
