package gap.common.dataservice.accountdataservice;

import gap.common.po.AccountPO;
import gap.common.po.Cost_profitPO;
import gap.common.po.TradePO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AccountDataService extends Remote {

	public boolean add(AccountPO accountPO) throws RemoteException;

	public boolean delete(AccountPO accountPO) throws RemoteException;

	public boolean modify(AccountPO accountPO) throws RemoteException;

	public boolean trade(TradePO tradePO) throws RemoteException;

	public ArrayList<Cost_profitPO> getCost_Profit() throws RemoteException;

	public ArrayList<AccountPO> getAccountList() throws RemoteException;

}
