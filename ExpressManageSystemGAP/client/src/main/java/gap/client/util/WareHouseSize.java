package gap.client.util;

public enum WareHouseSize {
	ROW(10), SHELF(10), UNIT(10), TOTAL(1000);
	private int size;

	private WareHouseSize(int number) {
		this.size = number;
	}

	public int getSize() {
		return size;
	}

	// public void setSize(int number){
	// if(number>0){
	// this.size = number;
	// }
	// }
}
