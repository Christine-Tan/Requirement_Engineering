package gap.common.dataservice.strategydataservice;

import gap.common.po.PricePO;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PriceDataService extends Remote {
	public PricePO find(String city) throws RemoteException;

	public ResultMessage add(PricePO po) throws RemoteException;

	public ResultMessage modify(PricePO po) throws RemoteException;

	public List<PricePO> getAll() throws RemoteException;
}
