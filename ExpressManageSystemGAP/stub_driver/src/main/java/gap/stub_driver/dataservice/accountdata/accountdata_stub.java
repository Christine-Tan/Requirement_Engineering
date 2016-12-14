package gap.stub_driver.dataservice.accountdata;

import gap.common.dataservice.accountdataservice.AccountDataService;
import gap.common.po.AccountPO;
import gap.common.po.Cost_profitPO;
import gap.common.po.TradePO;

import java.util.ArrayList;

public class accountdata_stub implements AccountDataService {

	public boolean add(AccountPO accountPO) {
		// TODO Auto-generated method stub
		System.out.println("added " + accountPO.getName());

		return true;
	}

	public boolean delete(AccountPO accountPO) {
		// TODO Auto-generated method stub

		System.out.println("deleted " + accountPO.getName());

		return true;
	}

	public boolean modify(AccountPO accountPO) {
		// TODO Auto-generated method stub
		System.out.println("modified " + accountPO.getName() + " from " + 100
				+ " to " + accountPO.getBalance());

		return true;
	}

	public boolean trade(TradePO tradePO) {
		// TODO Auto-generated method stub
		if (tradePO.getTradeNum() < 0) {
			int a = -tradePO.getTradeNum();
			System.out.println("payed " + a + "yuan");
		} else {

			System.out.println("payed " + tradePO.getTradeNum() + "yuan");
		}

		return true;
	}

	public ArrayList<Cost_profitPO> getCost_Profit() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	public ArrayList<AccountPO> getAccountList() {
		// TODO Auto-generated method stub
		return new ArrayList<AccountPO>();
	}

}
