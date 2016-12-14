package gap.stub_driver.blservice.inventory;

import gap.client.blservice.inventoryblservice.InventoryService;
import gap.client.vo.ExpressOrderVO;
import gap.client.vo.GoodsVO;
import gap.client.vo.StockCountVO;
import gap.client.vo.StockObVO;
import gap.client.vo.StockinOrderVO;
import gap.client.vo.StockoutOrderVO;
import gap.common.util.ExpressType;
import gap.common.util.ResultMessage;

import java.util.ArrayList;

public class InventoryService_driver {
	private void driver(InventoryService inventory) {
		ExpressOrderVO order1 = new ExpressOrderVO("yyf", "nju", "software",
				"15520065137", "txy", "nju", "software", "110",
				ExpressType.STANDARD, null, false, "0000000001", "0011001",
				null);
		ExpressOrderVO order2 = new ExpressOrderVO("shenbin", "nju",
				"software", "119", "plw", "nju", "software", "120",
				ExpressType.EXPRESS, null, false, "0000000002", null, "0010001");
		
		
		System.out.println(inventory.stockIn(order1, null));
		System.out.println(inventory.stockIn(order2, null));
		if(inventory.setAlarm(80).equals(ResultMessage.SUCCEED))
			System.out.println("setAlarm succeed");
		System.out.println(inventory.getAlarm());
		
		StockCountVO stockCheck =  inventory.countStock();
		StockObVO stockOb = inventory.observeStock("20151025","20151026");
		ArrayList<String> list = new ArrayList<String>();
		list.add(order1.getID());
		list.add(order2.getID());
		StockinOrderVO stockinOrder = inventory.createStockinOrder(list);
		
		GoodsVO goods1 = new GoodsVO(order1.getID(), "A区甲排1位", "car", "20151026");
		ExpressOrderVO goods3 = inventory.getSingleExpressOrder(order1.getID());
		if(inventory.initialadd(goods1).equals(ResultMessage.SUCCEED))
			System.out.println("add succeed");
		goods1.setSector("plane");
		
		if(inventory.initialmodify(goods1).equals(ResultMessage.SUCCEED))
			System.out.println("modefy succeed");
		if(inventory.initialdelete(goods1.getExpressorder_id()).equals(ResultMessage.SUCCEED))
			System.out.println("delete succeed");
		
		if(inventory.initialmodify(goods1).equals(ResultMessage.NOTFOUND))
			System.out.println("modefy failed,not found");
		if(inventory.initialdelete(goods1.getExpressorder_id()).equals(ResultMessage.NOTFOUND))
			System.out.println("delete failed,not found");
		
		
		
		
		ArrayList<ExpressOrderVO> orderlist = new ArrayList<ExpressOrderVO>();
		inventory.stockOut("上海", "car", order1.getID(), null);
		inventory.stockOut("上海", "car", order2.getID(), null);
		StockoutOrderVO stockoutOrder = inventory.createStockoutOrder(orderlist);
		
		System.out.println(inventory.Alarm());
		
		inventory.distributeSector("甲排", "乙排", "plane");
	}
	public static void main(String[] args){
		InventoryService inventory = new InventoryService_stub();
		InventoryService_driver driver = new InventoryService_driver();
		driver.driver(inventory);
	}
}
