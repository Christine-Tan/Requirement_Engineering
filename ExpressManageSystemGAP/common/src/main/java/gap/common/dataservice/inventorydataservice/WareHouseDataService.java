package gap.common.dataservice.inventorydataservice;

import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WareHouseDataService extends Remote {
	public ResultMessage add(String ins_id, String sector_id)
			throws RemoteException;

	public ResultMessage delete(String ins_id) throws RemoteException;
}
