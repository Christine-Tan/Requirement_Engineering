package gap.client.blcontroller;

import gap.client.bl.inventory.Inventory;
import gap.client.vo.GoodsVO;
import gap.common.util.ResultMessage;

import java.util.List;

public class InventoryController {
	private static Inventory inventory = new Inventory();

	public static ResultMessage setAlarmValue(double alarmValue, String ins_id) {
		return inventory.setAlarm(alarmValue, ins_id);
	}

	public static double getAlarmValue(String ins_id) {
		// System.out.println("InventoryController");
		return inventory.getAlarm(ins_id);
	}

	public static int getTotalNum(String ins_id) {
		return inventory.getTotalNum(ins_id);
	}
	
	public static String getNextLocation(String sector_id,String ins_id){
		return inventory.getNextLocation(ins_id, sector_id);
	}
	
	public static List<GoodsVO> getOneSectorExisted(String sector_id,String ins_id){
		return inventory.getOneSectorExisted(ins_id, sector_id);
	}
	
	public static List<GoodsVO> getOneSector(String sector_id,String ins_id){
		return inventory.getOneSector(ins_id, sector_id);
	}
	
	public static List<GoodsVO> getOneTypeSector(String sector_id,String ins_id){
		return inventory.getOneTypeSector(sector_id,ins_id);
	}
	
	public static ResultMessage Stockin(GoodsVO vo){
		return inventory.stockIn(vo);
	}
	
	public static ResultMessage Stockout(GoodsVO vo){
		return inventory.stockOut(vo.getExpressorder_id());
	}
	
	public static void InitialModify(GoodsVO vo){
		inventory.initialmodify(vo);
	}
	
	public static void InitialAdd(GoodsVO vo){
		inventory.initialadd(vo);
	}
	
	public static void InitialDelete(String id){
		inventory.initialdelete(id);
	}
	
	public static ResultMessage InitialFlush(){
		return inventory.initialflush();
	}
	
	public static double getOneShelfRatio(String position, String sector_id){
		return inventory.getOneShelfRatio(position,sector_id);
	}
	
	public static String alarm(String sector_id,String ins_id){
		return inventory.Alarm(sector_id, ins_id);
	}
	
	public static ResultMessage exportExcel(String path){
		return inventory.exportExcel(path);
	}
}
