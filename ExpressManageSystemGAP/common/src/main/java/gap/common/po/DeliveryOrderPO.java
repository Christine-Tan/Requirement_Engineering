package gap.common.po;

import gap.common.ListInterface.Order;

import java.util.List;
import java.util.Map;

public class DeliveryOrderPO implements Order {
	// 派件信息，记录派件快递员和对应派件快递
	private Map<String, List<String>> deliveryInfo;
	// 派件单生成时间
	private String time;
	// 派件单编号
	private String id;
	// 备注
	private String comment;

	public DeliveryOrderPO() {

	}

	public DeliveryOrderPO(Map<String, List<String>> deliveryInfo, String time,
			String id, String comment) {
		super();
		this.deliveryInfo = deliveryInfo;
		this.time = time;
		this.id = id;
		this.comment = comment;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public Map<String, List<String>> getDeliveryInfo() {
		return deliveryInfo;
	}

	public final void setDeliveryInfo(Map<String, List<String>> deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}



}
