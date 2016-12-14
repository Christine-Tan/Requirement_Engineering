package gap.common.po;

import gap.common.util.Gender;

import java.io.Serializable;

public class DriverPO implements Serializable {
	// 司机编号、所在机构编号、姓名、生日、身份证号、电话、行驶证期限
	private String id, ins_id, name, birth, identity_number, phone,
			driving_license_due;
	// 性别
	private Gender gender;

	public DriverPO() {

	}

	public DriverPO(String id, String ins_id, String name, String birth,
			String identity_number, String phone, String driving_license_due,
			Gender gender) {
		super();
		this.id = id;
		this.ins_id = ins_id;
		this.name = name;
		this.birth = birth;
		this.identity_number = identity_number;
		this.phone = phone;
		this.driving_license_due = driving_license_due;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getIdentity_number() {
		return identity_number;
	}

	public void setIdentity_number(String identity_number) {
		this.identity_number = identity_number;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIns_id() {
		return ins_id;
	}

	public void setIns_id(String ins_id) {
		this.ins_id = ins_id;
	}

	public String getDriving_license_due() {
		return driving_license_due;
	}

	public void setDriving_license_due(String driving_license_due) {
		this.driving_license_due = driving_license_due;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
