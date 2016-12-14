package gap.server.data.accountdata;

import gap.common.po.AccountPO;
import gap.common.po.TradePO;
import gap.server.initial.NetInitial;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class accountTest {
	AccountDataServiceImpl accountServiceImpl;

	@Before
	public void setUp() throws Exception {
		NetInitial.initial();
		accountServiceImpl = new AccountDataServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		AccountPO accountPO = new AccountPO("test account", 1200);
		try {
			accountServiceImpl.add(accountPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		// AccountPO accountPO = new AccountPO("test account", 1200);
		// try {
		// accountServiceImpl.delete(accountPO);
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@Test
	public void testModify() {
		// AccountPO accountPO = new AccountPO("测试账户", "新的测试账户");
		// try {
		// accountServiceImpl.modify(accountPO);
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@Test
	public void testTrade() {
		TradePO tradePO = new TradePO("test account", -100);
		try {
			accountServiceImpl.trade(tradePO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetCost_Profit() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetAccountList() {
		// fail("Not yet implemented");
	}

}
