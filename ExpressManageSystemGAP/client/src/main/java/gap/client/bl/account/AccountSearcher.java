package gap.client.bl.account;

import gap.client.util.FuzzyQuery;
import gap.client.util.SearchResult;
import gap.client.vo.AccountVO;

import java.util.ArrayList;

public class AccountSearcher {
	ArrayList<AccountVO> originalList;

	public AccountSearcher(ArrayList<AccountVO> originalList) {
		this.originalList = originalList;
	}

	public ArrayList<AccountSearchResult> search(String keyword) {
		String[] names = new String[originalList.size()];

		for (int i = 0; i < names.length; i++) {
			AccountVO vo = originalList.get(i);
			names[i] = vo.getName();
		}

		ArrayList<SearchResult> results = FuzzyQuery.fuzzyQuery(names, keyword);
		ArrayList<AccountSearchResult> accountResults = new ArrayList<>(
				results.size());
		for (SearchResult result : results) {
			AccountVO vo = originalList.get(result.getFormerIndex());
			AccountSearchResult oneResult = new AccountSearchResult(vo, result);
			accountResults.add(oneResult);
		}

		return accountResults;
	}

}
