package gap.client.vo;

public class Cost_ProfitListVO {

	private double income;
	private double payment;
	private double rate;
	
	//净收入
	private double netIncome;
	
	public Cost_ProfitListVO(double income,double payment,double rate,double netIncome) {
		this.setIncome(income);
		this.setPayment(payment);
		this.setRate(rate);
		this.setNetIncome(netIncome);
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}
}
