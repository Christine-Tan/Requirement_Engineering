package gap.common.util;

import java.io.Serializable;

public class PeopleInfo implements Serializable {
	private String name, depart, cellphone;
	private Address address;

	public PeopleInfo(String name, Address address, String depart,
			String cellphone) {
		super();
		this.name = name;
		this.address = address;
		this.depart = depart;
		this.cellphone = cellphone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

}
