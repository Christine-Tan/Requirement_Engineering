package gap.client.bl.account;

import gap.common.util.ResultMessage;

import java.util.ArrayList;

public class AccountCmdBuffer {
	ArrayList<AccountCommond> commonds = new ArrayList<>(20);

	public ResultMessage addCommond(AccountCommond commond) {
		commonds.add(commond);
		return ResultMessage.SUCCEED;
	}

	public ResultMessage flush() {

		for (AccountCommond commond : commonds) {
			commond.excute();
		}

		commonds.clear();
		return ResultMessage.SUCCEED;
	}

	public ResultMessage clear() {
		commonds.clear();
		return ResultMessage.SUCCEED;
	}

}
