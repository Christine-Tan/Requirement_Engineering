package gap.common.po;

import gap.common.ListInterface.Order;

import java.util.Map;

public class ArrivedOrderPO implements Order {
	// 到达单订单
	private Map<String, String> orders;
	// 生成时间
	private String time;
	// 对应的装车单编号
	private String load_id;
	// 到达单编号
	private String id;
	// 发货机构编号
	private String from_ins_id;
	// 到达机构编号
	private String des_ins_id;
	// 备注
	String comment;

	public ArrivedOrderPO(Map<String, String> orders, String time, String id,
			String from_ins_id, String des_ins_id, String comment,
			String load_id) {
		super();
		this.orders = orders;
		this.time = time;
		this.id = id;
		this.from_ins_id = from_ins_id;
		this.des_ins_id = des_ins_id;
		this.comment = comment;
		this.load_id = load_id;
	}

	public ArrivedOrderPO() {
		// TODO 自动生成的构造函数存根
	}

	public final Map<String, String> getOrders() {
		return orders;
	}

	public final void setOrders(Map<String, String> orders) {
		this.orders = orders;
	}

	public final String getTime() {
		return time;
	}

	public final void setTime(String time) {
		this.time = time;
	}

	public final String getID() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public String getFrom_ins_id() {
		return from_ins_id;
	}

	public void setFrom_ins_id(String from_ins_id) {
		this.from_ins_id = from_ins_id;
	}

	public String getDes_ins_id() {
		return des_ins_id;
	}

	public void setDes_ins_id(String des_ins_id) {
		this.des_ins_id = des_ins_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLoad_id() {
		return load_id;
	}

	public void setLoad_id(String load_id) {
		this.load_id = load_id;
	}

}
