package gap.client.blcontroller;

import gap.client.bl.account.AccountCmdBuffer;
import gap.client.bl.account.AccountSearchResult;
import gap.client.bl.account.AccountSearcher;
import gap.client.bl.account.AddAccountCmd;
import gap.client.bl.account.DeleteAccountCmd;
import gap.client.bl.account.ModifyAccountCmd;
import gap.client.blservice.accountblservice.AccountService;
import gap.client.datacontroller.AccountDateController;
import gap.client.datacontroller.ControllerFactory;
import gap.client.vo.AccountVO;
import gap.common.po.AccountPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.Iterator;

public class AccountBlController implements AccountService {

	AccountCmdBuffer buffer;
	ArrayList<AccountVO> accounts;
	private static AccountBlController controller = null;
	private AccountDateController dateController;

	private AccountBlController() {
		buffer = new AccountCmdBuffer();
		dateController = ControllerFactory.getAccountDataController();
	}

	public static AccountBlController getInstance() {

		if (controller == null) {
			controller = new AccountBlController();
		}
		return controller;
	}

	public void initial() {
		buffer.clear();
		//getAccountManageList();
	}

	@Override
	public Iterator<AccountVO> getAccountManageList() {
		// TODO Auto-generated method stub
		ArrayList<AccountPO> pos = dateController.getAccountList();
		accounts = new ArrayList<>(pos.size());
		for (AccountPO po : pos) {
			accounts.add(new AccountVO(po));
		}
		return accounts.iterator();

	}

	@Override
	public ResultMessage addAccount(AccountVO vo) {
		// 名字相同的账户视为相同
		if (accounts.contains(vo)) {
			return ResultMessage.EXISTED;
		}
		accounts.add(vo);
		return buffer.addCommond(new AddAccountCmd(vo));
	}

	@Override
	public ResultMessage deleteAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		accounts.remove(vo);
		return buffer.addCommond(new DeleteAccountCmd(vo));
	}

	@Override
	public ResultMessage modifyAccount(AccountVO vo) {
		// accountVO在名字相同的情况下视为相同
		int index = accounts.indexOf(vo);
		// 该旧名字不存在
		if (index == -1) {
			return ResultMessage.FAILED;
		} else {

			AccountVO newVO = new AccountVO(vo.getNewName(), vo.getBalance());

			if (accounts.contains(newVO)) {
				// 名字已经存在
				return ResultMessage.EXISTED;
			}

			accounts.set(index, newVO);

			buffer.addCommond(new ModifyAccountCmd(vo));
			return ResultMessage.SUCCEED;
		}
	}

	@Override
	public ArrayList<AccountSearchResult> searchAccount(String keyword) {
		// TODO Auto-generated method stub
		AccountSearcher searcher = new AccountSearcher(accounts);
		return searcher.search(keyword);

	}

	@Override
	public ResultMessage confirm() {
		// TODO Auto-generated method stub
		return buffer.flush();
	}

}
