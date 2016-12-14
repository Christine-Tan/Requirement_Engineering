package gap.common.po;

import java.io.Serializable;

public class InitialPeoplePO implements Serializable {
	private String cityName;
	public int businessHallNum;
	public int centerNum;
	public int courierNum;
	public int businessClerkNum;
	public int centerClerkNum;
	public int stockmanNum;
	
	

	/**
	 * businessHall,center,courier,businessClerk,centerClerk,stockMan
	 * @param cityName
	 * @param numbers
	 */
	public InitialPeoplePO(String cityName,int[] numbers){
		this.setCityName(cityName);
		
		if(numbers==null){
			numbers = new int[6];
		}
		
		setBusinessHallNum(numbers[0]);
		setCenterNum(numbers[1]);
		setCourierNum(numbers[2]);
		setBusinessClerkNum(numbers[3]);
		setCenterNum(numbers[4]);
		setStockmanNum(numbers[5]);
		
	}

	public InitialPeoplePO(String cityName){
		this(cityName,null);
	}


	public String getCityName() {
		return cityName;
	}



	public void setCityName(String cityName) {
		this.cityName = cityName;
	}



	public int getBusinessHallNum() {
		return businessHallNum;
	}



	public void setBusinessHallNum(int businessHallNum) {
		this.businessHallNum = businessHallNum;
	}



	public int getCenterNum() {
		return centerNum;
	}



	public void setCenterNum(int centerNum) {
		this.centerNum = centerNum;
	}



	public int getCourierNum() {
		return courierNum;
	}



	public void setCourierNum(int courierNum) {
		this.courierNum = courierNum;
	}



	public int getBusinessClerkNum() {
		return businessClerkNum;
	}



	public void setBusinessClerkNum(int businessClerkNum) {
		this.businessClerkNum = businessClerkNum;
	}



	public int getCenterClerkNum() {
		return centerClerkNum;
	}



	public void setCenterClerkNum(int centerClerkNum) {
		this.centerClerkNum = centerClerkNum;
	}



	public int getStockmanNum() {
		return stockmanNum;
	}



	public void setStockmanNum(int stockmanNum) {
		this.stockmanNum = stockmanNum;
	}
	
	public int[] getNums(){
		int[] nums = new int[6];
		
		nums[0] = businessHallNum;
		nums[1] = centerNum;
		nums[2] = courierNum;
		nums[3] = businessClerkNum;
		nums[4] = centerClerkNum;
		nums[5] = stockmanNum;
		
		return nums;
		
	}
	
}
