package gap.client.vo;

import gap.common.po.ExpressOrderPO;
import gap.common.util.CargoInfo;
import gap.common.util.ExpressType;
import gap.common.util.PeopleInfo;

public class ExpressOrderVO {
	// 寄件人收件人信息姓名、地址、单位、电话
	public PeopleInfo sender_info, receiver_info;
	// 快递类型
	public ExpressType expressType;
	// 快递价格
	public double price;
	// 货物信息
	public CargoInfo cargoInfo;
	// 是否接收
	public boolean received;
	// 快递单号
	public String order_id;
	// 快递所在机构编号和即将发往的机构编号
	public String currentins_id, targetins_id;

	public String time;

	public ExpressOrderVO() {

	}

	public ExpressOrderVO(ExpressOrderPO po) {
		this.sender_info = po.getSenderInfo();
		receiver_info = po.getReceiverInfo();
		price = po.getPrice();
		expressType = po.getExpressType();
		cargoInfo = po.getCargoInfo();
		received = po.isReceived();
		order_id = po.getID();
		currentins_id = po.getCurrentins_id();
		targetins_id = po.getTargetins_id();
		time = po.getTime();
	}

	public ExpressOrderVO(PeopleInfo sender_info, PeopleInfo receiver_info,
			ExpressType expressType, double price, CargoInfo cargoInfo,
			boolean received, String order_id, String currentins_id,
			String targetins_id, String time) {
		super();
		this.sender_info = sender_info;
		this.receiver_info = receiver_info;
		this.expressType = expressType;
		this.price = price;
		this.cargoInfo = cargoInfo;
		this.received = received;
		this.order_id = order_id;
		this.currentins_id = currentins_id;
		this.targetins_id = targetins_id;
		this.time = time;
	}

	public ExpressOrderPO toPO() {
		ExpressOrderPO po = new ExpressOrderPO(sender_info, receiver_info,
				expressType, cargoInfo, order_id, price, currentins_id,
				targetins_id, time);
		return po;

	}
}
