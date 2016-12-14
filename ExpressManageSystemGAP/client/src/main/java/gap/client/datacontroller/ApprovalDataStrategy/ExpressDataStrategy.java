package gap.client.datacontroller.ApprovalDataStrategy;

import gap.client.datacontroller.AppDataStrategyFactory;
import gap.common.ListInterface.Order;
import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.dataservice.managedataservice.InstitutionDataService;
import gap.common.po.ExpressOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;

public class ExpressDataStrategy extends AppStrategy {

	public ExpressDataStrategy(AppDataStrategyFactory factory) {
		super(factory);
	}

	@Override
	public ResultMessage setPassed(Order order) {
		String targetInsId = ((ExpressOrderPO) order).getCurrentins_id();
		InstitutionDataService institutiondataservice = factory.getInstitutionData();
		ExpressOrderDataService expressorderdataservice = factory.getExpressData();
		ResultMessage rm= null;
		try {
			String insname = institutiondataservice.findById(targetInsId).getInsName();
			String state = insname + "已收件";
			rm = expressorderdataservice.setPassed(((ExpressOrderPO) order).getID(), state);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rm;
	}

}
