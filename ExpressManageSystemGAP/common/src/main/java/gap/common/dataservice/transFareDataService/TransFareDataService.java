package gap.common.dataservice.transFareDataService;

import gap.common.po.TransFarePO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TransFareDataService extends Remote {

	public List<TransFarePO> getTransFare() throws RemoteException;

	public boolean deleteTransFare(List<TransFarePO> transFareList)
			throws RemoteException;

	public boolean addTransFare(TransFarePO po) throws RemoteException;

}
