package gap.stub_driver.dataservice.inventory;

import java.rmi.RemoteException;

import gap.common.dataservice.inventorydataservice.InventoryDataService;
import gap.common.po.GoodsPO;
import gap.common.util.ResultMessage;



public class InventoryDataService_driver {
	private void driver(InventoryDataService inventoryData)
			throws RemoteException {
		GoodsPO goods1 = new GoodsPO("0000000001", "A区甲排1位", null, "car", "20151026", null);
		GoodsPO goods2 = new GoodsPO("0000000002", "A区甲排2位", null, "car", "20151026", null);
		
		if(inventoryData.add(goods1).equals(ResultMessage.SUCCEED)){
			System.out.println("add succeed");
		}
		if(inventoryData.add(goods2).equals(ResultMessage.SUCCEED)){
			System.out.println("add succeed");
		}
		if (inventoryData.add(goods2).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,goods exited");
		}
		GoodsPO get = inventoryData.find("0000000001");
		if (get != null)
			System.out.println("find:Goods"+get.getExpressorder_id());
		
		GoodsPO get1 = inventoryData.find("0000000002");
		if (get1 != null)
			System.out.println("find:Goods"+get.getExpressorder_id());
		get1 = inventoryData.find("0000000003");
		if (get == null)
			System.out.println("find failed,not found");
		if(inventoryData.delete("0000000002").equals(ResultMessage.SUCCEED))
			System.out.println("delete succeed");
		if(inventoryData.delete("0000000002").equals(ResultMessage.NOTFOUND))
			System.out.println("delete failed,not found");
		goods1.setExpressorder_id("0000000003");
		if(inventoryData.modify(goods1).equals(ResultMessage.SUCCEED))
			System.out.println("modify succeed");
		if(inventoryData.modify(goods2).equals(ResultMessage.NOTFOUND))
			System.out.println("modify failed, not found");
		
		if(inventoryData.setAlarm(80, null).equals(ResultMessage.SUCCEED))
			System.out.println("setAlarm succeed");
		System.out.println(inventoryData.getAlarm(null));
	}
	public static void main(String[] args){
		InventoryDataService inventoryData = new InventoryDataService_stub();
		InventoryDataService_driver driver = new InventoryDataService_driver();
		try {
			driver.driver(inventoryData);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	

}
