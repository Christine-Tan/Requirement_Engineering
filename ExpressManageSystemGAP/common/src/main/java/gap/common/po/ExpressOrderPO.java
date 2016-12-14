package gap.common.po;

import gap.common.ListInterface.Order;
import gap.common.util.CargoInfo;
import gap.common.util.ExpressType;
import gap.common.util.PeopleInfo;

public class ExpressOrderPO implements Order {
	// 寄件人姓名、地址、单位、电话
	private PeopleInfo senderInfo;
	// 收件人姓名、地址、单位、电话
	private PeopleInfo receiverInfo;
	// 快递类型
	private ExpressType expressType;
	// 货物信息
	private CargoInfo cargoInfo;
	// 是否接收
	private boolean received;
	// 快递单号
	private String order_id;
	// 快递所在机构编号和即将发往的机构编号
	private String currentins_id, targetins_id;

	// 创建时间
	private String time;

	private double price;

	public ExpressOrderPO() {

	}

	public ExpressOrderPO(PeopleInfo senderInfo, PeopleInfo receiverInfo,
			ExpressType expressType, CargoInfo cargoInfo, String order_id,
			double price, String currentins_id, String targetins_id, String time) {
		super();
		this.senderInfo = senderInfo;
		this.receiverInfo = receiverInfo;
		this.expressType = expressType;
		this.cargoInfo = cargoInfo;
		this.order_id = order_id;
		this.price = price;
		this.currentins_id = currentins_id;
		this.targetins_id = targetins_id;
		this.time = time;
	}

	public ExpressOrderPO(PeopleInfo senderInfo, PeopleInfo receiverInfo,
			ExpressType expressType, CargoInfo cargoInfo, boolean received,
			String order_id, String currentins_id, String targetins_id,
			String clerkLoadOrder_id, String clerkArrivedOrder_id,
			String centerLoadOrder_id, String centerArrived_id,
			String deliveryOrder_id, String time) {
		super();
		this.senderInfo = senderInfo;
		this.receiverInfo = receiverInfo;
		this.expressType = expressType;
		this.cargoInfo = cargoInfo;
		this.received = received;
		this.order_id = order_id;
		this.currentins_id = currentins_id;
		this.targetins_id = targetins_id;
		this.time = time;
	}

	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean received) {
		this.received = received;
	}

	public ExpressType getExpressType() {
		return expressType;
	}

	public void setExpressType(ExpressType expressType) {
		this.expressType = expressType;
	}

	public CargoInfo getCargoInfo() {
		return cargoInfo;
	}

	public void setCargoInfo(CargoInfo cargoInfo) {
		this.cargoInfo = cargoInfo;
	}

	public String getCurrentins_id() {
		return currentins_id;
	}

	public void setCurrentins_id(String currentins_id) {
		if (currentins_id == null)
			return;
		targetins_id = null;// currentins_id与targetins_id不能同时被设置为非null值
		this.currentins_id = currentins_id;
	}

	public String getTargetins_id() {
		return targetins_id;
	}

	public void setTargetins_id(String targetins_id) {
		if (targetins_id == null)
			return;
		currentins_id = null;
		this.targetins_id = targetins_id;
	}

	public PeopleInfo getSenderInfo() {
		return senderInfo;
	}

	public void setSenderInfo(PeopleInfo senderInfo) {
		this.senderInfo = senderInfo;
	}

	public PeopleInfo getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(PeopleInfo receiverInfo) {
		this.receiverInfo = receiverInfo;
	}

	public String getID() {
		return order_id;
	}

	public void setID(String order_id) {
		this.order_id = order_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
