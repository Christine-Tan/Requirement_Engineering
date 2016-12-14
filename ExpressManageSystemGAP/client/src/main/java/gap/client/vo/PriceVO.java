package gap.client.vo;

import gap.common.po.PricePO;

public class PriceVO {
	// 经济：标准：特快的比例式
	private int express, standard, economic;
	// 比例基准
	private double base;
	// 城市名
	private String city;

	public PriceVO() {

	}

	public PriceVO(String city, int express, int standard, int economic,
			double base) {
		this.express = express;
		this.standard = standard;
		this.economic = economic;
		this.base = base;
		this.city = city;
	}

	public PriceVO(PricePO po) {
		this.express = po.getExpress();
		this.standard = po.getStandard();
		this.economic = po.getEconomic();
		this.base = po.getBase();
		this.city = po.getCity();
	}

	public PricePO toPricePO() {
		return new PricePO(city, express, standard, economic, base);
	}

	public int getExpress() {
		return express;
	}

	public void setExpress(int express) {
		this.express = express;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public int getEconomic() {
		return economic;
	}

	public void setEconomic(int economic) {
		this.economic = economic;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return base
	 */
	public double getBase() {
		return base;
	}

	/**
	 * @param base Ҫ���õ� base
	 */
	public void setBase(double base) {
		this.base = base;
	}

}
