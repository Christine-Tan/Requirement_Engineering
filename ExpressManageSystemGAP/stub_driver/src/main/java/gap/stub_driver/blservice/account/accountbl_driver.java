package gap.stub_driver.blservice.account;

import gap.client.blservice.accountblservice.AccountService;
import gap.client.vo.AccountListVO;




public class accountbl_driver {
	public void drive(AccountService stub) {
		AccountListVO accountListVO = stub.getAccountManageList();
		System.out.println(stub.setAccountManageList(accountListVO));
	}
	
	public static void main(String[] args) {
		new accountbl_driver().drive(new accountbl_stub());
	}
	
}
