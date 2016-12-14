package gap.client.util;

public class SearchResult {
	private String originalString;
	private String keyword;
	private int[] matchIndex;
	private int formerIndex = 0;

	public SearchResult(String name, String keyword, int[] matchIndex,
			int formerIndex) {
		this.originalString = name;
		this.keyword = keyword;
		this.matchIndex = matchIndex;
		this.formerIndex = formerIndex;
	}

	public String getString() {
		return originalString;
	}

	public String getKeyword() {
		return keyword;
	}

	public int[] getMatchIndex() {
		return matchIndex;
	}

	public int getFormerIndex() {
		return formerIndex;
	}

}
