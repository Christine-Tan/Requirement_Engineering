package gap.common.po;

import gap.common.util.SectorType;

import java.util.ArrayList;

public class SectorFlexPO {
	final public SectorType type = SectorType.FLEX;

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

	// 分配给航运区的架数
	private int pshelves;

	// 分配给铁运区的架数
	private int tshelves;

	// 分配给汽运区的架数
	private int cshelves;

	// 各架分配情况
	private SectorType[][] usedState;

	// 分配给航运区的区域中存放的快递
	private ArrayList<GoodsPO> toPlane;
	// 分配给铁运区的区域中存放的快递
	private ArrayList<GoodsPO> toTrain;
	// 分配给汽运区的区域中存放的快递
	private ArrayList<GoodsPO> toCar;

	// 分区编号
	private String id;

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

	public SectorType[][] getUsedState() {
		return usedState;
	}

	public void setUsedState(SectorType[][] usedState) {
		this.usedState = usedState;
	}

	public int getPshelves() {
		return pshelves;
	}

	public void setPshelves(int pshelves) {
		this.pshelves = pshelves;
	}

	public int getTshelves() {
		return tshelves;
	}

	public void setTshelves(int tshelves) {
		this.tshelves = tshelves;
	}

	public int getCshelves() {
		return cshelves;
	}

	public void setCshelves(int cshelves) {
		this.cshelves = cshelves;
	}

	public ArrayList<GoodsPO> getToPlane() {
		return toPlane;
	}

	public void setToPlane(ArrayList<GoodsPO> toPlane) {
		this.toPlane = toPlane;
	}

	public ArrayList<GoodsPO> getToTrain() {
		return toTrain;
	}

	public void setToTrain(ArrayList<GoodsPO> toTrain) {
		this.toTrain = toTrain;
	}

	public ArrayList<GoodsPO> getToCar() {
		return toCar;
	}

	public void setToCar(ArrayList<GoodsPO> toCar) {
		this.toCar = toCar;
	}

	// 获得快递数量
	public int getCarNum() {
		return this.toCar.size();
	}

	public int getPlaneNum() {
		return this.toPlane.size();
	}

	public int getTrainNum() {
		return this.toTrain.size();
	}

	public int getNum() {
		return getCarNum() + getPlaneNum() + getTrainNum();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SectorType getType() {
		return type;
	}

}
