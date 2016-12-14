package gap.client.datacontroller.ApprovalDataStrategy;

import gap.client.datacontroller.AppDataStrategyFactory;
import gap.common.ListInterface.Order;
import gap.common.dataservice.managedataservice.InstitutionDataService;
import gap.common.dataservice.orderdataservice.LoadOrderDataService;
import gap.common.po.LoadOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;

public class LoadDataStrategy extends AppStrategy{

	public LoadDataStrategy(AppDataStrategyFactory factory) {
		super(factory);
	}

	@Override
	public ResultMessage setPassed(Order order) {
		ResultMessage rm = null;
		InstitutionDataService institutionDataService = factory.getInstitutionData();
		LoadOrderDataService loadOrderDataService = factory.getLoadDataService();
		String targetInsId = ((LoadOrderPO) order).getTargetins_id();
		try {
			String insname = institutionDataService.findById(targetInsId).getInsName();
			String state = "正在发往" + insname;
			rm = loadOrderDataService.setPassed(((LoadOrderPO) order).getID(), state);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rm;
	}

}
