package gap.client.vo;

import gap.common.po.GoodsPO;

import java.util.List;

public class StockCountVO {
	private List<GoodsPO> carList;
	private List<GoodsPO> trainList;
	private List<GoodsPO> planeList;
	private List<GoodsPO> flexList;

	// 库存盘点信息列表

	public StockCountVO(List<GoodsPO> carList, List<GoodsPO> trainList,
			List<GoodsPO> planeList, List<GoodsPO> flexList) {
		super();
		this.carList = carList;
		this.trainList = trainList;
		this.planeList = planeList;
		this.flexList = flexList;
	}

	public StockCountVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCarNum() {
		return this.carList.size();
	}

	public int getTrainNum() {
		return this.carList.size();
	}

	public int getPlaneNum() {
		return this.carList.size();
	}

	public int getFlexNum() {
		return this.carList.size();
	}

	public void setCarList(List<GoodsPO> carList) {
		this.carList = carList;
	}

	public void setTrainList(List<GoodsPO> trainList) {
		this.trainList = trainList;
	}

	public void setPlaneList(List<GoodsPO> planeList) {
		this.planeList = planeList;
	}

	public void setFlexList(List<GoodsPO> flexList) {
		this.flexList = flexList;
	}

}
