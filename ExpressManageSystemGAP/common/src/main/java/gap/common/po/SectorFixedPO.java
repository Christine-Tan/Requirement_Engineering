package gap.common.po;

import gap.common.util.ResultMessage;
import gap.common.util.SectorType;

import java.util.ArrayList;
import java.util.List;

public class SectorFixedPO {
	// 分区类型
	public SectorType type;

	public String Stype;
	// 警戒值，百分比
	private double alarmVal;

	// 分区存放快递最大件数
	private int maxCapacity;

	// 分区占用比
	private double used;

	// 排数
	private int rows;

	// 一排中架子个数
	private int shelves;

	// 一架中位置个数
	private int units;

	// 机动区分配给的大小
	private int addedsize;

	// 快递list
	private ArrayList<GoodsPO> goodsList;

	// 分区编号
	private String id;

	public SectorFixedPO(SectorType type, double alarmVal, int maxCapacity,
			double used, int rows, int shelves, int units, int addedsize,
			ArrayList<GoodsPO> goodsList, String id) {
		super();
		this.type = type;
		this.alarmVal = alarmVal;
		this.maxCapacity = maxCapacity;
		this.used = used;
		this.rows = rows;
		this.shelves = shelves;
		this.units = units;
		this.addedsize = addedsize;
		this.goodsList = goodsList;
		this.id = id;
	}

	public ResultMessage add(GoodsPO goods) {
		for (GoodsPO g : this.goodsList) {
			if (g.getExpressorder_id().equals(goods.getExpressorder_id())) {
				return ResultMessage.EXISTED;
			}
		}
		this.goodsList.add(goods);
		return ResultMessage.SUCCEED;
	}

	public ResultMessage add(List<GoodsPO> goodsList) {
		for (GoodsPO g : goodsList) {
			this.goodsList.add(g);
		}
		return ResultMessage.SUCCEED;
	}

	public ResultMessage delete(String id) {
		for (GoodsPO g : this.goodsList) {
			if (g.getExpressorder_id().equals(id)) {
				this.goodsList.remove(g);
				return ResultMessage.SUCCEED;
			}
		}
		return ResultMessage.NOTFOUND;
	}

	public ResultMessage delete(List<String> ids) {
		for (String id : ids) {
			delete(id);
			ids.remove(id);
		}
		if (ids.isEmpty()) {
			return ResultMessage.SUCCEED;
		} else {
			return ResultMessage.NOTFOUND;
		}
	}

	public ResultMessage modify(GoodsPO goods) {
		for (GoodsPO g : this.goodsList) {
			if (g.getExpressorder_id().equals(goods.getExpressorder_id())) {
				this.goodsList.remove(g);
				this.goodsList.add(goods);
				return ResultMessage.SUCCEED;
			}
		}
		return ResultMessage.NOTFOUND;
	}

	public ResultMessage modify(List<GoodsPO> goodsList) {
		for (GoodsPO g : goodsList) {
			modify(g);
		}
		return ResultMessage.SUCCEED;
	}

	public int getIndex(String location) {
		return 0;
	}

	public double getAlarmVal() {
		return alarmVal;
	}

	public void setAlarmVal(double alarmVal) {
		this.alarmVal = alarmVal;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public double getUsed() {
		return used;
	}

	public void setUsed(double used) {
		this.used = used;
	}

	public ArrayList<GoodsPO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(ArrayList<GoodsPO> goodsList) {
		this.goodsList = goodsList;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getShelves() {
		return shelves;
	}

	public void setShelves(int shelves) {
		this.shelves = shelves;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public SectorType getType() {
		return type;
	}

	public void setType(SectorType type) {
		this.type = type;
	}

	public int getAddedsize() {
		return addedsize;
	}

	public void setAddedsize(int addedsize) {
		this.addedsize = addedsize;
	}

	public int getNum() {
		return this.goodsList.size();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
