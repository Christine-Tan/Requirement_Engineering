package gap.client.bl.account;

import gap.client.util.SearchResult;
import gap.client.vo.AccountVO;

public class AccountSearchResult {
	private SearchResult searchResult;
	private AccountVO vo;

	public AccountSearchResult(AccountVO vo, SearchResult result) {
		this.vo = vo;
		this.searchResult = result;
	}

	public AccountVO getAccountVO() {
		return vo;
	}

	public int[] getMatchIndex() {
		return searchResult.getMatchIndex();
	}
	
}
