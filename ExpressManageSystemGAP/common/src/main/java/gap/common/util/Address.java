package gap.common.util;

import java.io.Serializable;

public class Address implements Serializable {
	String province_name, city_name, district_name;

	public Address(String province_name, String city_name, String district_name) {
		super();
		this.province_name = province_name;
		this.city_name = city_name;
		this.district_name = district_name;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

}
