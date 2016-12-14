package gap.common.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InitialHistoryPO implements Serializable{
	private Calendar date;
	private int totalBusinessHall;
	private int totalCenter;
	private int totalCourier;
	private int totalBusinessClerk;
	private int totalCenterClerk;
	private int totalStockman;
	private int totalWarehouse;
	private int totalStock;
	private int totalAccount;  
	private double totalBalance;
	
	public ArrayList<AccountPO> accountPOs;
	public ArrayList<InitialPeoplePO> initialPeoplePOs;
	public ArrayList<InitialStockPO> initialStockPOs;
	
	public InitialHistoryPO(ArrayList<AccountPO> accountPOs,
			ArrayList<InitialPeoplePO> initialPeoplePOs,
			ArrayList<InitialStockPO> initialStockPOs)
	{
		
		date = Calendar.getInstance();
		
		for(InitialPeoplePO peoplePO:initialPeoplePOs){
			totalBusinessHall += peoplePO.getBusinessHallNum();
			totalCenter 	  += peoplePO.getCenterNum();
			totalCourier	  += peoplePO.getCourierNum();
			totalBusinessClerk+= peoplePO.getBusinessClerkNum();
			totalCenterClerk  += peoplePO.getCenterClerkNum();
			totalStockman	  += peoplePO.getStockmanNum();
		}
		
		totalWarehouse = initialStockPOs.size();
		totalAccount = accountPOs.size();
		
		for(InitialStockPO stockPO:initialStockPOs){
			totalStock += stockPO.getGoodsNum();
		}
		
		for(AccountPO accountPO:accountPOs){
			totalBalance += accountPO.getBalance();
		}
		
		this.accountPOs = accountPOs;
		this.initialPeoplePOs = initialPeoplePOs;
		this.initialStockPOs = initialStockPOs;
		
	}
	
	public InitialHistoryPO(Date date,ArrayList<AccountPO> accountPOs,
			ArrayList<InitialPeoplePO> initialPeoplePOs,
			ArrayList<InitialStockPO> initialStockPOs)
	{
		this(accountPOs, initialPeoplePOs, initialStockPOs);
		this.date.setTime(date);
		
	}
	
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public int getTotalBusinessHall() {
		return totalBusinessHall;
	}
	public void setTotalBusinessHall(int totalBusinessHall) {
		this.totalBusinessHall = totalBusinessHall;
	}
	public int getTotalCenter() {
		return totalCenter;
	}
	public void setTotalCenter(int totalCenter) {
		this.totalCenter = totalCenter;
	}
	public int getTotalCourier() {
		return totalCourier;
	}
	public void setTotalCourier(int totalCourier) {
		this.totalCourier = totalCourier;
	}
	public int getTotalBusinessClerk() {
		return totalBusinessClerk;
	}
	public void setTotalBusinessClerk(int totalBusinessClerk) {
		this.totalBusinessClerk = totalBusinessClerk;
	}
	public int getTotalCenterClerk() {
		return totalCenterClerk;
	}
	public void setTotalCenterClerk(int totalCenterClerk) {
		this.totalCenterClerk = totalCenterClerk;
	}
	public int getTotalStockman() {
		return totalStockman;
	}
	public void setTotalStockman(int totalStockman) {
		this.totalStockman = totalStockman;
	}
	public int getTotalWarehouse() {
		return totalWarehouse;
	}
	public void setTotalWarehouse(int totalWarehouse) {
		this.totalWarehouse = totalWarehouse;
	}
	public int getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}
	public int getTotalAccount() {
		return totalAccount;
	}
	public void setTotalAccount(int totalAccount) {
		this.totalAccount = totalAccount;
	}
	public double getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	
	public int getTotalPeopleNum(){
		return totalCourier+totalBusinessClerk+totalCenterClerk+totalStockman;
	}
	
	public int getTotalInstitution(){
		return totalBusinessHall+totalCenter;
	}
	
}
