package gap.client.bl.accountbl;

import gap.client.blcontroller.AccountBlController;
import gap.client.datacontroller.NetModule;
import gap.client.vo.AccountVO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountBlTest {
	AccountBlController account;

	@Before
	public void setUp() throws Exception {
		NetModule.connect();
		account = AccountBlController.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitial() {
		account.initial();
	}

	@Test
	public void testGetAccountManageList() {
		account.getAccountManageList();
	}

	@Test
	public void testAddAccount() {
		account.addAccount(new AccountVO("账户1", 100));
	}

	@Test
	public void testDeleteAccount() {
		account.deleteAccount(new AccountVO("账户1", 100));
	}

	@Test
	public void testModifyAccount() {
		account.modifyAccount(new AccountVO("账户1", 100));
	}

	@Test
	public void testSearchAccount() {
		account.searchAccount("daw");
	}

	@Test
	public void testConfirm() {
		account.confirm();
	}

}
