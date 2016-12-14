package gap.common.po;

import gap.common.ListInterface.Order;

import java.util.List;

public class LoadOrderPO implements Order{
	//
	private String order_id, date, car_number, departureins_id, targetins_id,
			driver_id, guard_id, comment;
	private List<String> orders;

	private double price;

	public LoadOrderPO(String order_id, String date, String car_number,
			String departureins_id, String targetins_id, String driver_id,
			String guard_id, List<String> orders, String comment) {
		super();
		this.order_id = order_id;
		this.date = date;
		this.car_number = car_number;
		this.departureins_id = departureins_id;
		this.targetins_id = targetins_id;
		this.driver_id = driver_id;
		this.guard_id = guard_id;
		this.orders = orders;
		this.comment = comment;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}

	public String getDepartureins_id() {
		return departureins_id;
	}

	public void setDepartureins_id(String departureins_id) {
		this.departureins_id = departureins_id;
	}

	public String getTargetins_id() {
		return targetins_id;
	}

	public void setTargetins_id(String targetins_id) {
		this.targetins_id = targetins_id;
	}

	public String getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}

	public String getGuard_id() {
		return guard_id;
	}

	public void setGuard_id(String guard_id) {
		this.guard_id = guard_id;
	}

	public String getID() {
		return order_id;
	}

	public void setID(String order_id) {
		this.order_id = order_id;
	}

	public List<String> getOrders() {
		return orders;
	}

	public void setOrders(List<String> orders) {
		this.orders = orders;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}