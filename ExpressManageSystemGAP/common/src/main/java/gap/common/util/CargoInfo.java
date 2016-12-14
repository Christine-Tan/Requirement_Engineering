package gap.common.util;

import java.io.Serializable;

public class CargoInfo implements Serializable {
	// 原件数量、重量、体积
	private int cargoNum;
	// 原件重量、体积
	private double weight, volume;
	// 内件名称
	private String name;

	public CargoInfo() {

	}

	public CargoInfo(int cargoNum, double weight, double volume, String name) {
		this.cargoNum = cargoNum;
		this.weight = weight;
		this.volume = volume;
		this.name = name;
	}

	public int getCargoNum() {
		return cargoNum;
	}

	public void setCargoNum(int cargoNum) {
		this.cargoNum = cargoNum;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
