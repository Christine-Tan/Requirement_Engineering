package gap.common.po;

import gap.common.util.PaymentType;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 
 * 对于transFare，userID是运单号，userName是车辆ID
 * @author 申彬
 *
 */
public class PayeePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PaymentType type;
	private String userID;
	private String userName;
	private Calendar lastPaydate;
	private int expressOrderNum = 0;
	private double money = 0;
	private String AccountName;
	private String note;

	public PayeePO(PaymentType type, String userID, String userName,
			Calendar lastPaydate, int expressOrderNum, double money,
			String accountName, String note) {
		this.setType(type);
		this.setUserID(userID);
		this.setUserName(userName);
		this.setLastPaydate(lastPaydate);
		this.setExpressOrderNum(expressOrderNum);
		setMoney(money);
		setAccountName(accountName);
		this.note = note;

		if (type != PaymentType.DELIVERY) {
			expressOrderNum = 0;
		}

	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Calendar getLastPaydate() {
		return lastPaydate;
	}

	public void setLastPaydate(Calendar lastPaydate) {
		this.lastPaydate = lastPaydate;
	}

	public int getExpressOrderNum() {
		return expressOrderNum;
	}

	public void setExpressOrderNum(int expressOrderNum) {
		this.expressOrderNum = expressOrderNum;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getAccountName() {
		return AccountName;
	}

	public void setAccountName(String accountName) {
		AccountName = accountName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
