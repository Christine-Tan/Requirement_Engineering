package gap.common.po;

import java.io.Serializable;

public class TransFarePO implements Serializable {

	private double fare;
	private String orderID;
	private String driverID;

	public TransFarePO(double fare, String orderID, String carID) {
		this.fare = fare;
		this.orderID = orderID;
		this.driverID = carID;

	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public String getDriverID() {
		return driverID;
	}

	public void setDriverID(String carID) {
		this.driverID = carID;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

}
