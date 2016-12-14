package gap.client.bl.account;

import gap.client.blcontroller.LogController;
import gap.client.util.LocalInfo;
import gap.client.vo.AccountVO;
import gap.common.po.LogPO;
import gap.common.util.ResultMessage;

public class AddAccountCmd extends AccountCommond {

	public AddAccountCmd(AccountVO vo) {
		super(vo);
	}

	@Override
	public ResultMessage excute() {
		// TODO Auto-generated method stub
		LogPO logPO = new LogPO(LocalInfo.localuser.toUserPO(), "增加账户："+po.getName());
		LogController.addLog(logPO);
		return accountDateController.addAccount(po);
	}

}
