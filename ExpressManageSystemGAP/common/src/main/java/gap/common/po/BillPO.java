package gap.common.po;

import java.io.Serializable;

public class BillPO implements Serializable {

	private double money;
	private String courierID;

	public BillPO(double money, String courierID) {
		super();
		this.money = money;
		this.courierID = courierID;
	}

	public String getCourierID() {
		return courierID;
	}

	public void setCourierID(String courierID) {
		this.courierID = courierID;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

}
