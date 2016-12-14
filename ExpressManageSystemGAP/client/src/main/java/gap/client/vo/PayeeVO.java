package gap.client.vo;

import gap.common.po.PayeePO;
import gap.common.util.PaymentType;

import java.util.Calendar;

public class PayeeVO {

	private PaymentType type;
	private String userID;
	private String userName;
	private Calendar lastPaydate;
	private double money = 0;
	private String accountName;
	private String entry;
	private String note;

	public PayeeVO(PaymentType type, String userID, String userName,
			Calendar lastPaydate, double money, String accountName,
			String entry, String note) {
		this.type = type;
		this.userID = userID;
		this.userName = userName;
		this.lastPaydate = lastPaydate;
		this.money = money;
		this.accountName = accountName;
		this.setEntry(entry);
		this.setNote(note);

	}

	public PayeeVO(PayeePO po) {
		this.type = po.getType();
		this.userID = po.getUserID();
		this.lastPaydate = po.getLastPaydate();
		this.userName = po.getUserName();
		this.money = po.getMoney();
		if(po.getAccountName()!=null){
			accountName = po.getAccountName();
		}

		if(po.getNote()!=null){
			note = po.getNote();
		}
	}

	public PayeePO toPO() {
		PayeePO po = new PayeePO(type, userID, userName, lastPaydate, 0, money,
				accountName, note);

		return po;
	}

	public PaymentType getType() {
		return type;
	}

	public double getMoney() {
		return money;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserID() {
		return userID;
	}

	public Calendar getLastPaydate() {
		return lastPaydate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}

	public boolean equals(Object o) {
		PayeeVO other = null;
		if (o instanceof PayeeVO) {
			other = (PayeeVO) o;
		} else {
			return false;
		}

		if (other.userID.equals(userID) && other.userName.equals(userName)) {
			return true;
		} else {
			return false;
		}

	}
	
	public String toString(){
		return userName;
	}

}
