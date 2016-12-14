package gap.client.datacontroller.ApprovalDataStrategy;

import gap.client.datacontroller.AppDataStrategyFactory;
import gap.common.ListInterface.Order;
import gap.common.dataservice.orderdataservice.DeliveryOrderDataService;
import gap.common.po.DeliveryOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;

public class DeliveryDataStrategy extends AppStrategy{

	public DeliveryDataStrategy(AppDataStrategyFactory factory) {
		super(factory);
	}

	@Override
	public ResultMessage setPassed(Order order) {
		ResultMessage rm = null;
		DeliveryOrderDataService deliveryOrderDataService = factory.getDeliveryData();
		try {
			rm = deliveryOrderDataService.setPassed(((DeliveryOrderPO) order).getID(), "");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rm;
	}

}
