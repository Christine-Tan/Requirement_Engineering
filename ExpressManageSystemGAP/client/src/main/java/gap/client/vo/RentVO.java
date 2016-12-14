package gap.client.vo;

import gap.common.po.RentPO;

import java.sql.Date;

public class RentVO {

	private double money;
	private String institution;

	public RentVO() {

	}

	public RentVO(RentPO po) {
		this.money = po.getMoney();
		this.institution = po.getInstitution();
	}

	public RentVO(String institution, double money) {
		this.setMoney(money);
		this.setInstitution(institution);
	}

	public RentPO toRentPO() {
		return new RentPO(institution, money, new Date(
				System.currentTimeMillis()));
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

}
