package gap.common.po;


public class InventoryPO {

	// 警戒值，百分比
	private double alarmValue;
	// 属于的中转中心的编号
	private String ins_id;
	private SectorFixedPO carSector;
	private SectorFixedPO planeSector;
	private SectorFixedPO trainSector;
	private SectorFlexPO flexSector;

	public InventoryPO(double alarmValue, String ins_id,
			SectorFixedPO carSector, SectorFixedPO planeSector,
			SectorFixedPO trainSector, SectorFlexPO flexSector) {
		super();
		this.alarmValue = alarmValue;
		this.ins_id = ins_id;
		this.carSector = carSector;
		this.planeSector = planeSector;
		this.trainSector = trainSector;
		this.flexSector = flexSector;
		this.carSector.setId(ins_id + "1");
		this.planeSector.setId(ins_id + "3");
		this.trainSector.setId(ins_id + "2");
		this.flexSector.setId(ins_id + "0");
	}

	public double getAlarmValue() {
		return alarmValue;
	}

	public void setAlarmValue(double alarmValue) {
		this.alarmValue = alarmValue;
		this.carSector.setAlarmVal(alarmValue);
		this.planeSector.setAlarmVal(alarmValue);
		this.trainSector.setAlarmVal(alarmValue);
		this.planeSector.setAlarmVal(alarmValue);
	}

	public String getIns_id() {
		return ins_id;
	}

	public void setIns_id(String ins_id) {
		this.ins_id = ins_id;
	}

	/*
	 * private double alarmValue = 70; //各分区可存放快递件数，整数 static int planeMax =
	 * 100; static int trainMax = 100; static int carMax = 100; final static int
	 * flexMax = 100; //各分区快递列表 private ArrayList<GoodsPO> planeSector; private
	 * ArrayList<GoodsPO> trainSector; private ArrayList<GoodsPO> carSector;
	 * private static ArrayList<String> flexibleSector; // 各分区占用比，百分比 double
	 * planeSused, trainSused, carSused, flexSused;
	 * 
	 * 
	 * public InventoryPO(ArrayList<GoodsPO> planeSector, ArrayList<GoodsPO>
	 * trainSector, ArrayList<GoodsPO> carSector, ArrayList<String>
	 * flexibleSector, double planeSused, double trainSused, double carSused,
	 * double flexSused) { this.planeSector = planeSector; this.trainSector =
	 * trainSector; this.carSector = carSector; this.planeSused = (double)
	 * planeSector.size() / planeMax; this.trainSused = (double)
	 * trainSector.size() / trainMax; this.carSused = (double) carSector.size()
	 * / carMax; this.flexSused = (double) flexibleSector.size() / flexMax; }
	 * 
	 * public ArrayList<GoodsPO> getPlaneSector() { return planeSector; }
	 * 
	 * public void setPlaneSector(ArrayList<GoodsPO> planeSector) {
	 * this.planeSector = planeSector; }
	 * 
	 * public ArrayList<GoodsPO> getTrainSector() { return trainSector; }
	 * 
	 * public void setTrainSector(ArrayList<GoodsPO> trainSector) {
	 * this.trainSector = trainSector; }
	 * 
	 * public ArrayList<GoodsPO> getCarSector() { return carSector; }
	 * 
	 * public void setCarSector(ArrayList<GoodsPO> carSector) { this.carSector =
	 * carSector; }
	 * 
	 * public static int getPlaneMax() { return planeMax; }
	 * 
	 * public static void setPlaneMax() { int a = 0; for (int i = 0; i <
	 * flexibleSector.size(); i++) { if
	 * (flexibleSector.get(i).equals("planeSector")) { a++; } }
	 * InventoryPO.planeMax = planeMax + a; }
	 * 
	 * public static int getTrainMax() { return trainMax; }
	 * 
	 * public static void setTrainMax() { int a = 0; for (int i = 0; i <
	 * flexibleSector.size(); i++) { if
	 * (flexibleSector.get(i).equals("trainSector")) { a++; } }
	 * InventoryPO.trainMax = trainMax + a; }
	 * 
	 * public static int getCarMax() { return carMax; }
	 * 
	 * public static void setCarMax(int carMax) { int a = 0; for (int i = 0; i <
	 * flexibleSector.size(); i++) { if
	 * (flexibleSector.get(i).equals("carSector")) { a++; } } InventoryPO.carMax
	 * = carMax + a; }
	 * 
	 * public double getPlaneSused() {
	 * 
	 * return planeSused; }
	 * 
	 * public void setPlaneSused() { this.planeSused = (double)
	 * planeSector.size() / planeMax; }
	 * 
	 * public double getTrainSused() { return trainSused; }
	 * 
	 * public void setTrainSused() { this.trainSused = (double)
	 * trainSector.size() / trainMax; }
	 * 
	 * public double getCarSused() { return trainSused; }
	 * 
	 * public void setCarSused() { this.carSused = (double) carSector.size() /
	 * carMax; }
	 * 
	 * public double getFlexSused() { return flexSused; }
	 * 
	 * public void setFlexSused() { this.flexSused = (double)
	 * flexibleSector.size() / flexMax; }
	 * 
	 * public double getAlarmValue() { return alarmValue; }
	 * 
	 * public void setAlarmValue(double alarmValue) { this.alarmValue =
	 * alarmValue; }
	 */

}
