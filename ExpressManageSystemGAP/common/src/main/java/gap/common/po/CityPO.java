package gap.common.po;

import java.io.Serializable;

public class CityPO implements Serializable {
	// 城市名称
	String city;
	// 城市所在省份
	String province;
	// 城市经纬度,规定正的为东经，北纬，负的为西经，南纬
	double longitude, latitude;

	public CityPO(String name, String province, double latitude,
			double longitude) {
		city = name;
		this.province = province;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
