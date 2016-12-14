package gap.common.dataservice.managedataservice;

import gap.common.po.InstitutionPO;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface InstitutionDataService extends Remote {
	public ResultMessage add(InstitutionPO po) throws RemoteException;

	public ResultMessage modify(InstitutionPO po) throws RemoteException;

	public ResultMessage delete(String ins_id) throws RemoteException;

	public List<InstitutionPO> getAll() throws RemoteException;

	public List<InstitutionPO> findByCity(String city) throws RemoteException;

	public InstitutionPO findById(String ins_id) throws RemoteException;

	public InstitutionPO findByName(String ins_name) throws RemoteException;
}
