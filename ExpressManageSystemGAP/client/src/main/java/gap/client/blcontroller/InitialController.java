package gap.client.blcontroller;

import gap.client.bl.initial.InitialCmd;
import gap.client.bl.initial.InitialPOCreater;
import gap.client.blservice.initialblservice.InitialBlService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.InitialDataController;
import gap.common.po.InitialHistoryPO;
import gap.common.util.ResultMessage;

import java.util.List;

public class InitialController implements InitialBlService{

	private static InitialController initialController;
	
	private InitialDataController dataController;
	
	public static InitialController getInstance(){
		if(initialController == null){
			initialController = new InitialController();
		}
		return initialController;
	}
	
	private InitialController() {
		dataController = ControllerFactory.getInitialDataController();
	}
	
	@Override
	public InitialHistoryPO getCurrentInitial() {
		
		InitialPOCreater creater = new InitialPOCreater(dataController);
		return creater.creatPO();
	}

	@Override
	public ResultMessage submitInitial(InitialHistoryPO initialPO) {
		InitialCmd initialCmd = new InitialCmd();
		return initialCmd.excute(initialPO);
	}

	@Override
	public List<InitialHistoryPO> getInitialHistory() {
		return dataController.getHistory();
	}

}
