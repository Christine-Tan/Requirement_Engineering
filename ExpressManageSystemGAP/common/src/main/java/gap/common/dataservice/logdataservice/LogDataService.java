package gap.common.dataservice.logdataservice;

import gap.common.po.LogPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LogDataService extends Remote {

	public List<LogPO> getLogList() throws RemoteException;

	public boolean addLog(LogPO logPO) throws RemoteException;

}
