package gap.client.bl.account;

import gap.client.blcontroller.LogController;
import gap.client.util.LocalInfo;
import gap.client.vo.AccountVO;
import gap.common.po.LogPO;
import gap.common.util.ResultMessage;

public class ModifyAccountCmd extends AccountCommond {

	public ModifyAccountCmd(AccountVO vo) {
		super(vo);
	}

	@Override
	public ResultMessage excute() {
		LogPO logPO = new LogPO(LocalInfo.localuser.toUserPO(), 
				"修改账户 "+po.getName()+" 为 "+po.getNewName());
		LogController.addLog(logPO);
		return accountDateController.modifyAccount(po);
	}
}
