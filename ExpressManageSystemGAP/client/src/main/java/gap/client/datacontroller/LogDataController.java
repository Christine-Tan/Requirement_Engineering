package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.logdataservice;
import gap.common.po.LogPO;

import java.rmi.RemoteException;
import java.util.List;

public class LogDataController {
	protected LogDataController() {
	}

	public List<LogPO> getLogList() {
		try {
			List<LogPO> logPOs = logdataservice.getLogList();
			return logPOs;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addLog(LogPO logPO) {
		try {
			return logdataservice.addLog(logPO);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
}
