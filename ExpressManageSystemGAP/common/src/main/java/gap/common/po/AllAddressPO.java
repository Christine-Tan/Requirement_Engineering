package gap.common.po;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AllAddressPO implements Serializable {
	List<String> provinces;
	Map<String, List<String>> province2city;
	Map<String, List<String>> city2district;

	public List<String> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<String> provinces) {
		this.provinces = provinces;
	}

	public Map<String, List<String>> getProvince2city() {
		return province2city;
	}

	public void setProvince2city(Map<String, List<String>> province2city) {
		this.province2city = province2city;
	}

	public Map<String, List<String>> getCity2district() {
		return city2district;
	}

	public void setCity2district(Map<String, List<String>> city2district) {
		this.city2district = city2district;
	}

}
