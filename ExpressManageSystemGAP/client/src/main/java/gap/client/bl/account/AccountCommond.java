package gap.client.bl.account;

import gap.client.datacontroller.AccountDateController;
import gap.client.datacontroller.ControllerFactory;
import gap.client.vo.AccountVO;
import gap.common.po.AccountPO;
import gap.common.util.ResultMessage;

public abstract class AccountCommond {
	protected AccountDateController accountDateController = ControllerFactory
			.getAccountDataController();
	protected AccountPO po;

	public AccountCommond(AccountVO vo) {
		po = vo.toPO();
	}

	public abstract ResultMessage excute();
}
