package gap.common.dataservice.strategydataservice;

import gap.common.po.CityPO;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CityDataService extends Remote {

	public CityPO find(String name) throws RemoteException;

	public ResultMessage add(CityPO po) throws RemoteException;

	public List<CityPO> getAll() throws RemoteException;
}
