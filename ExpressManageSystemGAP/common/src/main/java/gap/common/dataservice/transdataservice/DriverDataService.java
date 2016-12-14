package gap.common.dataservice.transdataservice;

import gap.common.po.DriverPO;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DriverDataService extends Remote {
	public List<DriverPO> getAll(String local_ins) throws RemoteException;

	public ResultMessage add(DriverPO po) throws RemoteException;

	public DriverPO find(String ins_id) throws RemoteException;

	public ResultMessage modify(DriverPO po) throws RemoteException;

	public ResultMessage delete(String ins_id) throws RemoteException;
}
