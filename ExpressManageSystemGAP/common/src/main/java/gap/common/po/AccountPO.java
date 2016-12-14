package gap.common.po;

import java.io.Serializable;

public class AccountPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String newName;
	private double balance;

	public AccountPO(String name, double balance) {
		this.setName(name);
		this.setBalance(balance);
	}

	public AccountPO(String oldName, String newName) {
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

}
