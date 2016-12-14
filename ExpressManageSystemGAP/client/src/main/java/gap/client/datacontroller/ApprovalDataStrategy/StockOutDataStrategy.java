package gap.client.datacontroller.ApprovalDataStrategy;


import gap.client.datacontroller.AppDataStrategyFactory;
import gap.common.ListInterface.Order;
import gap.common.dataservice.orderdataservice.StockoutOrderDataService;
import gap.common.po.StockoutOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;

public class StockOutDataStrategy extends AppStrategy{

	public StockOutDataStrategy(AppDataStrategyFactory factory) {
		super(factory);
	}

	@Override
	public ResultMessage setPassed(Order order) {
		ResultMessage rm = null;
		StockoutOrderDataService stockoutOrderDataService = factory.getStockOutData();
		try {
			rm = stockoutOrderDataService.setPassed(((StockoutOrderPO) order).getID(), "");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rm;
	}

}
