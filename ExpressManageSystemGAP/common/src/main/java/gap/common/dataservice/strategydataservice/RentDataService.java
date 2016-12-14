package gap.common.dataservice.strategydataservice;

import gap.common.po.RentPO;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RentDataService extends Remote {

	public List<RentPO> getAll() throws RemoteException;

	public ResultMessage add(RentPO po) throws RemoteException;

	public ResultMessage modify(RentPO po) throws RemoteException;

	public ResultMessage setPaid(String institution) throws RemoteException;
}
