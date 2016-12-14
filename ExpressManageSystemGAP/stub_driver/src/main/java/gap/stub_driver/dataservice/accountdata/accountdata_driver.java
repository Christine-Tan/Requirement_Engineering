package gap.stub_driver.dataservice.accountdata;

import java.rmi.RemoteException;

import gap.common.dataservice.accountdataservice.AccountDataService;
import gap.common.po.AccountPO;
import gap.common.po.TradePO;

public class accountdata_driver {

	public void drive(AccountDataService stub) throws RemoteException {
		stub.getAccountList();
		stub.add(new AccountPO("master", 1000));
		stub.trade(new TradePO("aaa", 20));

	}

	public static void main(String[] args) {
		try {
			new accountdata_driver().drive(new accountdata_stub());
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
