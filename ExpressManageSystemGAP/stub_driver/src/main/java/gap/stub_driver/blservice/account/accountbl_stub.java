package gap.stub_driver.blservice.account;

import gap.client.blservice.accountblservice.AccountService;
import gap.client.vo.AccountListVO;
import gap.common.util.ResultMessage;

public class accountbl_stub implements AccountService {

	@Override
	public AccountListVO getAccountManageList() {
		// TODO Auto-generated method stub
		return new AccountListVO();
	
	}

	@Override
	public ResultMessage setAccountManageList(AccountListVO accountListVO) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCEED;
	}

}
