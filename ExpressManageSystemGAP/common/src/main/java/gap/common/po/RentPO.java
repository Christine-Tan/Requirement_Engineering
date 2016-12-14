package gap.common.po;

import java.io.Serializable;
import java.sql.Date;

public class RentPO implements Serializable {

	private double money;
	private Date lastPaidDate;
	private String institution;

	public RentPO(String institution, double money, Date lastPaidDate) {
		this.setMoney(money);
		this.setLastPaidDate(lastPaidDate);
		this.setInstitution(institution);
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Date getLastPaidDate() {
		return lastPaidDate;
	}

	public void setLastPaidDate(Date lastPaidDate) {
		this.lastPaidDate = lastPaidDate;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

}
