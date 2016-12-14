package gap.client.datacontroller.ApprovalDataStrategy;

import gap.client.datacontroller.AppDataStrategyFactory;
import gap.common.ListInterface.Order;
import gap.common.dataservice.managedataservice.InstitutionDataService;
import gap.common.dataservice.orderdataservice.ArrivedOrderDataService;
import gap.common.po.ArrivedOrderPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;

public class ArriveDataStrategy extends AppStrategy{

	public  ArriveDataStrategy(AppDataStrategyFactory factory) {
		super(factory);
	}

	@Override
	public ResultMessage setPassed(Order order) {
		ResultMessage rm = null;
		InstitutionDataService institutiondataservice = factory.getInstitutionData();
		ArrivedOrderDataService arrivedOrderDataService = factory.getArriveData();
		String targetInsId = ((ArrivedOrderPO) order).getDes_ins_id();
		try {
			String insname = institutiondataservice.findById(targetInsId).getInsName();
			String state = insname + "已收件";
			rm = arrivedOrderDataService.setPassed(((ArrivedOrderPO) order).getID(), state);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rm;

	}
	
	
}
