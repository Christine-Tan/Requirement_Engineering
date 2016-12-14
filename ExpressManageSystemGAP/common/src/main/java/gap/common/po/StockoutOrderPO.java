package gap.common.po;

import gap.common.ListInterface.Order;

import java.util.List;

public class StockoutOrderPO implements Order {
	// 快递编号
	private List<String> expressorder_ids;
	// 出库日期，目标机构编号,中转单编号（单据编号）
	private String outDate, target_ins, id;
	// 货运方式
	private String transport;
	// 所属中转中心id
	private String ins_id;

	// 出库日期，目标机构，货运方式，中转单编号或装车单编号(20位0~9数字)

	public List<String> getExpressorder_ids() {
		return expressorder_ids;
	}

	public StockoutOrderPO(List<String> expressorder_ids, String outDate,
			String target_ins, String id, String transport, String ins_id) {
		super();
		this.expressorder_ids = expressorder_ids;
		this.outDate = outDate;
		this.target_ins = target_ins;
		this.id = id;
		this.transport = transport;
		this.ins_id = ins_id;
	}

	public void setExpressorder_ids(List<String> expressorder_ids) {
		this.expressorder_ids = expressorder_ids;
	}

	public void deleteExpressorder_id(String expressorder_id) {
		int a = this.expressorder_ids.indexOf(expressorder_id);
		this.expressorder_ids.remove(a);
	}

	public void addExpressorder_id(String expressorder_id) {
		this.expressorder_ids.add(expressorder_id);
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getTarget_ins() {
		return target_ins;
	}

	public void setTarget_ins(String target_ins) {
		this.target_ins = target_ins;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getID() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIns_id() {
		return ins_id;
	}

	public void setIns_id(String ins_id) {
		this.ins_id = ins_id;
	}

	public int getNum() {
		return this.expressorder_ids.size();
	}

}
