package gap.client.vo;

import gap.common.ListInterface.Receipt;

import java.util.Calendar;
import java.util.List;

public class OperatingConditionListVO {
	private List<Receipt> receipts;
	private double totalIncome;
	private double totalPayment;
	private Calendar calendar;
	
	public OperatingConditionListVO
	(List<Receipt> receipts,double totalIncome,double totalPayment,Calendar calendar) {
		this.setReceipts(receipts);
		this.setTotalIncome(totalIncome);
		this.setTotalPayment(totalPayment);
		this.setCalendar(calendar);
	}

	public List<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(List<Receipt> receipts) {
		this.receipts = receipts;
	}

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
}
