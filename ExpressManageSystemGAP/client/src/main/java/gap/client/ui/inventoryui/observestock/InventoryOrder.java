package gap.client.ui.inventoryui.observestock;

import gap.client.vo.StockinOrderVO;
import gap.client.vo.StockoutOrderVO;

import java.util.ArrayList;
import java.util.List;

public class InventoryOrder {
	String id;
	String num;
	String date;
	
	public InventoryOrder(StockinOrderVO vo){
		id = vo.getId();
		num = vo.getNum()+"";
		date = vo.getInDate();
	}
	
	public InventoryOrder(StockoutOrderVO vo){
		id = vo.getId();
		num = vo.getNum()+"";
		date = vo.getOutDate();
	}

	public String getId() {
		return id;
	}

	public String getNum() {
		return num;
	}

	public String getDate() {
		return date;
	}
	
	public static List<InventoryOrder> transformInOrder(List<StockinOrderVO> list){
		List<InventoryOrder> orders = new ArrayList<InventoryOrder>();
		for(StockinOrderVO vo: list){
			orders.add(new InventoryOrder(vo));
		}
		return orders;
	}
	
	public static List<InventoryOrder> transformOutOrder(List<StockoutOrderVO> list){
		List<InventoryOrder> orders = new ArrayList<InventoryOrder>();
		for(StockoutOrderVO vo: list){
			orders.add(new InventoryOrder(vo));
		}
		return orders;
	}
	
	
}
