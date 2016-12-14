package gap.client.bl.account;

import gap.client.blcontroller.LogController;
import gap.client.util.LocalInfo;
import gap.client.vo.AccountVO;
import gap.common.po.LogPO;
import gap.common.util.ResultMessage;

public class DeleteAccountCmd extends AccountCommond {

	public DeleteAccountCmd(AccountVO vo) {
		super(vo);
	}

	@Override
	public ResultMessage excute() {
		LogPO logPO = new LogPO(LocalInfo.localuser.toUserPO(), "删除账户: "+po.getName());
		LogController.addLog(logPO);
		return accountDateController.deleteAccount(po);

	}

}
