package gap.common.po;

import java.io.Serializable;

public class TradePO  implements Serializable{
	private String accountName;
	private double tradeNum = 0;

	public TradePO(String accountName, double tradeNum) {
		this.setAccountName(accountName);
		this.setTradeNum(tradeNum);
	}

	public double getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(double tradeNum) {
		this.tradeNum = tradeNum;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}
