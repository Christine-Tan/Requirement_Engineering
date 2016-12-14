package gap.client.util;

import gap.client.vo.CityVO;
import gap.common.po.CityPO;

public class City {
	// 城市名称
	String cityName;
	// 城市所在省份
	String province;
	// 城市经纬度 lat 纬度 longi 经度
	double latitude, longitude;

	public City(String cityName, String province, double latitude,
			double longitude) {
		super();
		this.cityName = cityName;
		this.province = province;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public City(CityPO po) {
		this.cityName = po.getCity();
		this.province = po.getProvince();
		this.latitude = po.getLatitude();
		this.longitude = po.getLongitude();
	}

	public City(CityVO vo) {
		this.cityName = vo.getCityName();
		this.province = vo.getProvince();
		this.latitude = vo.getLatitude();
		this.longitude = vo.getLongitude();
	}

	public CityPO toCityPO() {
		return new CityPO(cityName, province, latitude, longitude);
	}

	public CityVO toCityVO() {
		return new CityVO(cityName, province, latitude, longitude);
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
