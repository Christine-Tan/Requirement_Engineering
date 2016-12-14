package gap.stub_driver.dataservice.inventory;

import gap.common.dataservice.inventorydataservice.InventoryDataService;
import gap.common.po.GoodsPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDataService_stub implements InventoryDataService{
	private List<GoodsPO> list;
	private double alarmValue;
	
	public InventoryDataService_stub(){
		list = new ArrayList<GoodsPO>();
	}

	@Override
	public ResultMessage add(GoodsPO expressorder) {
		// TODO 自动生成的方法存根
		for (GoodsPO order : list)
			if (order.getExpressorder_id().equals(expressorder.getExpressorder_id()))
				return ResultMessage.EXITED;
		list.add(expressorder);
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage delete(String expressorder_id) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getExpressorder_id().equals(expressorder_id)) {
				list.remove(i);
				return ResultMessage.SUCCEED;
			}
		}
		return ResultMessage.NOTFOUND;
	}

	@Override
	public ResultMessage modify(GoodsPO expressorder) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getExpressorder_id().equals(expressorder.getExpressorder_id())) {
				list.get(i).setLocation(expressorder.getLocation());
				return ResultMessage.SUCCEED;
			}
		}
		return ResultMessage.NOTFOUND;
	}

	@Override
	public GoodsPO find(String expressorder_id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getExpressorder_id().equals(expressorder_id)) 
				return list.get(i);
		}
		return null;
	}


	@Override
	public ResultMessage setAlarm(double alarmValue,String ins_id) {
		// TODO Auto-generated method stub
		this.alarmValue = alarmValue;
		return ResultMessage.SUCCEED;
	}

	@Override
	public double getAlarm(String ins_id) {
		// TODO Auto-generated method stub
		return this.alarmValue;
	}

	@Override
	public ResultMessage add(List<GoodsPO> expressorders)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(List<String> expressorders_id)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modify(List<GoodsPO> expressorders)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GoodsPO> getOneSector(String ins_id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GoodsPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
