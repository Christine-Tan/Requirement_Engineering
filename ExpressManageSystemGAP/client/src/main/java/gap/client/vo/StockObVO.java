package gap.client.vo;

import gap.common.po.StockinOrderPO;
import gap.common.po.StockoutOrderPO;

import java.util.List;

public class StockObVO {
	private List<StockinOrderPO> inList;

	private List<StockoutOrderPO> outList;

	public StockObVO() {
		super();
	}

	public StockObVO(List<StockinOrderPO> inList, List<StockoutOrderPO> outList) {
		super();
		this.inList = inList;
		this.outList = outList;
	}

	public int getInNum() {
		return this.inList.size();
	}

	public int getOutNum() {
		return this.outList.size();
	}

	public void setInList(List<StockinOrderPO> inList) {
		this.inList = inList;
	}

	public void setOutList(List<StockoutOrderPO> outList) {
		this.outList = outList;
	}

}
