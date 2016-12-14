package gap.client.vo;

public class CityVO {
	// 城市名称
	String cityName;
	// 城市所在省份
	String province;
	// 城市经纬度
	double latitude, longitude;

	public CityVO(String cityName, String province, double latitude,
			double longitude) {
		this.cityName = cityName;
		this.province = province;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
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

}
