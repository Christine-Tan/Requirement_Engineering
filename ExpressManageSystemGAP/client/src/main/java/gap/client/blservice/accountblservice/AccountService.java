package gap.client.blservice.accountblservice;

import gap.client.bl.account.AccountSearchResult;
import gap.client.vo.AccountVO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.Iterator;

public interface AccountService {

	public Iterator<AccountVO> getAccountManageList();

	public ResultMessage addAccount(AccountVO vo);

	public ResultMessage deleteAccount(AccountVO vo);

	public ResultMessage modifyAccount(AccountVO vo);

	public ArrayList<AccountSearchResult> searchAccount(String keyword);

	public ResultMessage confirm();

}
