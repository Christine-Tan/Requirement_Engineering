package gap.client.vo;

import gap.common.po.GoodsPO;
import gap.common.po.StockinOrderPO;

import java.util.ArrayList;
import java.util.List;

public class StockinOrderVO {
	// 快递编号
	private List<GoodsVO> goods;
	private String inDate, id, ins_id;

	// 到达日期,入库单编号(20位0~9数字),所属中转中心编号

	public StockinOrderVO() {
		// TODO 自动生成的构造函数存根
	}

	public StockinOrderVO(StockinOrderPO po) {
		List<GoodsPO> list = po.getGoods();
		List<GoodsVO> goods = new ArrayList<GoodsVO>();
		for (GoodsPO go : list) {
			goods.add(new GoodsVO(go));
		}
		this.goods = goods;
		this.inDate = po.getInDate();
		this.id = po.getID();
		this.ins_id = po.getIns_id();
	}

	public StockinOrderVO(List<GoodsVO> goods, String inDate, String id,
			String ins_id) {
		super();
		this.goods = goods;
		this.inDate = inDate;
		this.id = id;
		this.ins_id = ins_id;
	}

	public List<GoodsVO> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsVO> goods) {
		this.goods = goods;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getId() {
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
		return goods.size();
	}

	public StockinOrderPO toPO() {
		List<GoodsPO> goods = new ArrayList<GoodsPO>();
		for (GoodsVO vo : this.goods) {
			goods.add(vo.toPO());
		}
		StockinOrderPO po = new StockinOrderPO(goods, inDate, id, ins_id);
		return po;

	}

	public static List<StockinOrderVO> toVOList(List<StockinOrderPO> list) {
		List<StockinOrderVO> voList = new ArrayList<StockinOrderVO>();
		for (StockinOrderPO po : list) {
			StockinOrderVO vo = new StockinOrderVO(po);
			voList.add(vo);
		}
		return voList;

	}

}
