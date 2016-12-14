package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.accountDataService;
import static gap.client.datacontroller.NetModule.citydataservice;
import static gap.client.datacontroller.NetModule.initialDataService;
import static gap.client.datacontroller.NetModule.institutiondataservice;
import static gap.client.datacontroller.NetModule.inventorydataservice;
import static gap.client.datacontroller.NetModule.userdataservice;
import gap.common.po.AccountPO;
import gap.common.po.CityPO;
import gap.common.po.InitialHistoryPO;
import gap.common.po.InstitutionPO;
import gap.common.po.UserPO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

import java.rmi.RemoteException;
import java.util.List;

public class InitialDataController {
	
	protected InitialDataController(){}
	
	//initialData interface
	public ResultMessage addInitial(InitialHistoryPO initialPO){
		
		try{
			return initialDataService.addInitial(initialPO);
		}catch(RemoteException e){
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
			
	}

	public List<InitialHistoryPO> getHistory(){
		try{
			return initialDataService.getHistory();
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}	
	}
	
	//institution interface
	public List<InstitutionPO> getInititution(){
		
		try {
			return institutiondataservice.getAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//city interface
	public List<CityPO> getAllCity(){
		
		try{
			return citydataservice.getAll();
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//user interface
	public List<UserPO> getAllUser(UserType userType){
		try{
			return userdataservice.getAll(userType);
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public int getPeopleNum(String ins_id,UserType type){
		try{
			return userdataservice.getPeopleNum(ins_id, type);
		}catch(RemoteException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	//account interface
	public List<AccountPO> getAllAccount(){
		try{
			return accountDataService.getAccountList();
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//inventory
	//仓库根据中转中心查，名字叫城市名+仓库,容量是一个枚举*4
	public int getGoodsNum(String ins_id){
		try{
			return inventorydataservice.getTotalNum(ins_id);
		}catch(RemoteException e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
