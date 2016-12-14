package gap.client.vo;

import gap.common.po.AccountPO;

public class AccountVO {

	private String name;
	private String newName;
	private double balance;

	public AccountVO(String name, double balance) {
		this.setName(name);
		this.setBalance(balance);
	}

	public AccountVO(AccountPO po) {
		this.name = po.getName();
		this.balance = po.getBalance();
	}

	public AccountPO toPO() {

		AccountPO po = new AccountPO(name, balance);
		po.setNewName(newName);
		return po;

	}

	public AccountVO(String oldName, String newName) {
		this.name = oldName;
		this.setNewName(newName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public boolean equals(Object object) {
		if (object instanceof AccountVO) {
			AccountVO o = (AccountVO) object;
			return name.equals(o.name);
		}

		return false;
	}
	
	public String toString(){
		return name;
	}

}
