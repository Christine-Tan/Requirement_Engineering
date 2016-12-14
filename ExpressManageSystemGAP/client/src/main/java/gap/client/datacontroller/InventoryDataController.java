package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.inventorydataservice;
import gap.common.po.GoodsPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

public class InventoryDataController {

	protected InventoryDataController() {
	}

	public ResultMessage add(GoodsPO goodsPO) {
		// TODO Auto-generated method stub
		try {
			return inventorydataservice.add(goodsPO);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage add(List<GoodsPO> goodsPOs) {
		// TODO Auto-generated method stub
		try {
			return inventorydataservice.add(goodsPOs);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage delete(String goods_id) {
		// TODO Auto-generated method stub
		try {
			return inventorydataservice.delete(goods_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage delete(List<String> goods_ids) {
		// TODO Auto-generated method stub
		try {
			return inventorydataservice.delete(goods_ids);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage modify(GoodsPO goods) {
		// TODO Auto-generated method stub
		try {
			return inventorydataservice.modify(goods);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage modify(List<GoodsPO> goodsPOs) {
		// TODO Auto-generated method stub
		try {
			return inventorydataservice.modify(goodsPOs);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public GoodsPO find(String goods_id) {
		// TODO Auto-generated method stub
		try {
			return inventorydataservice.find(goods_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<GoodsPO> getOneSector(String sector_id, String ins_id) {
		// TODO Auto-generated method stub
		try {
			return inventorydataservice.getOneSector(sector_id, ins_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<GoodsPO> getOneTypeSector(String sector_id,String ins_id){
		try {
			return inventorydataservice.getOneTypeSector(sector_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<GoodsPO> getOneSectorExisted(String sector_id,String ins_id){
		try {
			return inventorydataservice.getOneSectorExisted(sector_id, ins_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getOneShelfNum(String position, String sector_id){
		try {
			return inventorydataservice.getOneShelfNum(position, sector_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public ResultMessage setAlarm(double alarmValue, String ins_id) {
		// TODO Auto-generated method stub
		try {
			return inventorydataservice.setAlarm(alarmValue, ins_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public double getAlarm(String ins_id) {
		// TODO Auto-generated method stub
		// System.out.println("dataController");
		try {
			return inventorydataservice.getAlarm(ins_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<GoodsPO> getOneTypeInFlex(String ins_id, String belong_sec_id) {
		try {
			return inventorydataservice.getOneTypeInFlex(ins_id, belong_sec_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getTotalNum(String ins_id){
		try {
			return inventorydataservice.getTotalNum(ins_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}
