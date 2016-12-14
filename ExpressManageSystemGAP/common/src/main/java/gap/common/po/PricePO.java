package gap.common.po;

import java.io.Serializable;

/**
 * 
 * @author seven
 *
 */
public class PricePO implements Serializable {
	// 经济：标准：特快的比例式
	private int economic, standard, express;
	// 比例基准
	private double base;
	// 城市名
	private String city;

	public PricePO() {

	}

	public PricePO(String city, int express, int standard, int economic,
			double base) {
		this.base = base;
		this.economic = economic;
		this.standard = standard;
		this.express = express;
		this.city = city;
	}

	public int getEconomic() {
		return economic;
	}

	public void setEconomic(int economic) {
		this.economic = economic;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public int getExpress() {
		return express;
	}

	public void setExpress(int express) {
		this.express = express;
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
