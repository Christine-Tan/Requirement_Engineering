package gap.client.vo;

import gap.common.po.DeliveryOrderPO;

import java.util.List;
import java.util.Map;

public class DeliveryOrderVO {
	// 派件信息，记录派件快递员和对应派件快递
	public Map<String, List<String>> deliveryInfo;
	// 派件单生成时间
	public String time;
	// 派件单编号
	public String id;
	// 备注
	public String comment;

	public DeliveryOrderVO() {

	}

	public DeliveryOrderPO toPO() {
		return new DeliveryOrderPO(deliveryInfo, time, id, comment);
	}

	public DeliveryOrderVO(Map<String, List<String>> deliveryInfo, String time,
			String id, String comment) {
		super();
		this.deliveryInfo = deliveryInfo;
		this.time = time;
		this.id = id;
		this.comment = comment;
	}

	public boolean equals(Object cob) {
		return false;
	}
}
