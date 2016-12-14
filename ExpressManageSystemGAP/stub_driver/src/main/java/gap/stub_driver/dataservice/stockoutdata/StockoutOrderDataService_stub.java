package gap.stub_driver.dataservice.stockoutdata;

import gap.common.dataservice.orderdataservice.StockoutOrderDataService;
import gap.common.po.StockoutOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

public class StockoutOrderDataService_stub implements StockoutOrderDataService {
	public List<StockoutOrderPO> list;

	@Override
	public ResultMessage add(StockoutOrderPO po) {
		// TODO 自动生成的方法存根
		for (StockoutOrderPO order : list)
			if (order.getIdentifier().equals(po.getIdentifier()))
				return ResultMessage.EXITED;
		list.add(po);
		return ResultMessage.SUCCEED;
	}

	@Override
	public StockoutOrderPO find(String identifier) {
		// TODO 自动生成的方法存根
		for(StockoutOrderPO order:list){
			if(order.getExpressorder_ids().contains(identifier))
				return order;
		}
		return null;
	}


	@Override
	public List<StockoutOrderPO> get(String date) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockoutOrderPO> getRequired(String beginDate, String endDate)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
