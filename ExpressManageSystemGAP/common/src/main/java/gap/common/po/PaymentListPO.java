package gap.common.po;

import gap.common.ListInterface.Receipt;

import java.util.ArrayList;
import java.util.Calendar;

public class PaymentListPO implements Receipt {
	public ArrayList<PayeePO> paymentList;
	private String paymentID;
	private Calendar payDate;
	private String payer;
	private double total = 0;

	public PaymentListPO(String ID, String payer, double total,
			Calendar calendar) {
		setPaymentID(ID);
		this.setPayer(payer);
		this.setTotal(total);
		this.payDate = calendar;
		paymentList = new ArrayList<>();
	}

	public Calendar getDate() {
		return payDate;
	}

	public void setPayDate(Calendar payDate) {
		this.payDate = payDate;
	}

	public String getID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
