package gap.client.bl.initial;


import gap.client.blcontroller.LogController;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.InitialDataController;
import gap.client.util.LocalInfo;
import gap.common.po.InitialHistoryPO;
import gap.common.po.LogPO;
import gap.common.util.ResultMessage;

public class InitialCmd {
	InitialDataController dataController;
	public InitialCmd(){
		dataController = ControllerFactory.getInitialDataController();
	}
	
	public ResultMessage excute(InitialHistoryPO historyPO){
		LogPO logPO = new LogPO(LocalInfo.localuser.toUserPO(), "期初建账");
		LogController.addLog(logPO);
		return dataController.addInitial(historyPO);
	}
	
}
