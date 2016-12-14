package gap.common.po;

import java.io.Serializable;

public class Cost_profitPO implements Serializable{

	private String accountName;
	private double income;
	private double payment;

	public Cost_profitPO(String accountName, double income, double payment) {
		this.setAccountName(accountName);
		this.setIncome(income);
		this.setPayment(payment);
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

}
