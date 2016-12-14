package gap.common.po;

import gap.common.ListInterface.Order;

import java.util.List;

public class StockinOrderPO implements Order{
	// 快递编号
	private List<GoodsPO> goods;
	private String inDate, id, ins_id;

	/**
	 * 目的地是最终目的地，先不管是什么，不存入数据库。。。
	 */
	// 到达日期,入库单编号(20位0~9数字),所属中转中心编号

	public StockinOrderPO() {
		// TODO 自动生成的构造函数存根
	}

	public StockinOrderPO(List<GoodsPO> goods, String inDate, String id,
			String ins_id) {
		super();
		this.goods = goods;
		this.inDate = inDate;
		this.id = id;
		this.ins_id = ins_id;
	}

	public List<GoodsPO> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsPO> goods) {
		this.goods = goods;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
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
		return this.goods.size();
	}

}
