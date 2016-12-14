package gap.client.bl.initial;

import gap.client.datacontroller.InitialDataController;
import gap.client.util.WareHouseSize;
import gap.common.po.AccountPO;
import gap.common.po.CityPO;
import gap.common.po.InitialHistoryPO;
import gap.common.po.InitialPeoplePO;
import gap.common.po.InitialStockPO;
import gap.common.po.InstitutionPO;
import gap.common.util.InstitutionType;
import gap.common.util.UserType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * 读取当前信息创建 期初信息
 * @author 申彬
 *
 */
public class InitialPOCreater {
	InitialDataController dataController;
	
	ArrayList<AccountPO> accountPOs;
	HashMap<String, InitialPeoplePO> cityMap;
	List<InstitutionPO> institutionPOs;
	
	public InitialPOCreater(InitialDataController dataController){
		this.dataController = dataController;
	}
	
	public InitialHistoryPO creatPO(){
		//获得account
		accountPOs = new ArrayList<>(dataController.getAllAccount());
		List<CityPO> cityPOs = dataController.getAllCity();
		
		cityMap = new HashMap<>(cityPOs.size());
		
		for(CityPO cityPO:cityPOs){
			String cityName = cityPO.getCity();
			InitialPeoplePO peoplePO = new InitialPeoplePO(cityName);
			cityMap.put(cityName, peoplePO);
		}
		
		institutionPOs = dataController.getInititution();
		
		//计算城市对应的机构数和人员数
		calculatePeopleAndIns();
		
		//获得库存
		ArrayList<InitialStockPO> stockPOs = calculateStockPOs();
		
		//创建history
		ArrayList<InitialPeoplePO> peoplePOs = new ArrayList<>(cityMap.values());
		
		InitialHistoryPO historyPO = new InitialHistoryPO
					(accountPOs, peoplePOs, stockPOs);
		
		return historyPO;
		
	}
	
	private ArrayList<InitialStockPO> calculateStockPOs(){
	
		ArrayList<InstitutionPO> centerPOs = new ArrayList<>();
		for(InstitutionPO institution : institutionPOs){
			if(institution.getInsType().equals(InstitutionType.CENTER)){
				centerPOs.add(institution);
			}
		}
		
		ArrayList<InitialStockPO> stockPOs = new ArrayList<>(centerPOs.size());
		
		for(InstitutionPO institutionPO : centerPOs){
			String centerName = institutionPO.getInsName();
			String inventoryName = centerName.replace("中转中心", "仓库");
			
			int capacity = WareHouseSize.TOTAL.getSize() * 4;
			int goodsNum = dataController.getGoodsNum(institutionPO.getInsId());
			double occupiedRate = goodsNum*1.0/capacity;
			
			InitialStockPO stockPO = new InitialStockPO(inventoryName, goodsNum, occupiedRate);
			stockPOs.add(stockPO);
		}
		
		return stockPOs;
		
	}

	private void calculatePeopleAndIns() {
		for(InstitutionPO institution : institutionPOs){
			//判断机构类型，增加对应机构数，增加对应的人员数
			addInstituteNum(institution);
		}
	}



	private void addInstituteNum(InstitutionPO institution) {
		String cityName = institution.getInsCity();
		String ins_id = institution.getInsId();
		InstitutionType type = institution.getInsType();
		InitialPeoplePO peoplePO = cityMap.get(cityName);
		
		switch(type){
			
			case BUSSINESS:
			{
				peoplePO.businessHallNum++;
				
				int courierNum = dataController.getPeopleNum(ins_id, UserType.DELIVERY);
				int busClerk = dataController.getPeopleNum(ins_id, UserType.BUSSINESSCLERK);
				
				peoplePO.courierNum += courierNum;
				peoplePO.businessClerkNum += busClerk;
				break;
			}
			
			case CENTER:
			{
				peoplePO.centerNum++;
	
				int centerClerk = dataController.getPeopleNum(ins_id, UserType.CENTERCLERK);
				int stockManNum = dataController.getPeopleNum(ins_id, UserType.INVENTORY);
				
				peoplePO.centerClerkNum += centerClerk;
				peoplePO.stockmanNum += stockManNum;
				break;
			}
		
		}
		
	}
	
	
}
