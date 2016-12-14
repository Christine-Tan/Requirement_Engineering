package gap.common.dataservice.userdataservice;

import gap.common.po.UserPO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

public interface UserDataService extends Remote {
	public List<UserPO> getAll(UserType userType) throws RemoteException;

	public ResultMessage add(UserPO po) throws RemoteException;

	public UserPO findById(String user_id) throws RemoteException;

	public UserPO findByUsername(String username) throws RemoteException;

	public ResultMessage modify(UserPO po) throws RemoteException;

	public ResultMessage delete(String user_id) throws RemoteException;

	public List<UserPO> findUnpaidUser(Date date) throws RemoteException;

	public ResultMessage setPaid(String user_id) throws RemoteException;

	public List<UserPO> getDilivery(String ins_id) throws RemoteException;
	
	public int getPeopleNum(String ins_id,UserType type) throws RemoteException;

}
