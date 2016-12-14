package gap.client.datacontroller.ApprovalDataStrategy;


import gap.client.datacontroller.AppDataStrategyFactory;
import gap.common.ListInterface.Order;
import gap.common.dataservice.orderdataservice.StockinOrderDataService;
import gap.common.po.StockinOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;

public class StockInDataStrategy extends AppStrategy{

	public StockInDataStrategy(AppDataStrategyFactory factory) {
		super(factory);
	}

	@Override
	public ResultMessage setPassed(Order order) {
		ResultMessage rm = null;
		StockinOrderDataService stockinOrderDataService = factory.getStockinData();
		try {
			rm = stockinOrderDataService.setPassed(((StockinOrderPO) order).getID(), "");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rm;
	}

}
