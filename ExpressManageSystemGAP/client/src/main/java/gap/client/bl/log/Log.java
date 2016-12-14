package gap.client.bl.log;

import gap.client.blservice.logblservice.LogService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.LogDataController;
import gap.client.vo.LogVO;
import gap.common.po.LogPO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Log implements LogService {
	LogDataController controller;
	List<LogVO> logs;
	TreeMap<String, List<LogVO>> logByDate;

	public Log() {
		// TODO Auto-generated constructor stub
		controller = ControllerFactory.getLogDataController();
	}

	@Override
	public List<LogVO> getLogList() {
		// TODO Auto-generated method stub
		logs = new ArrayList<>();
		for (LogPO po : controller.getLogList()) {
			logs.add(new LogVO(po));
		}
		return logs;
	}

	public TreeMap<String, List<LogVO>> getLogByDate() {
		logByDate = new TreeMap<String, List<LogVO>>();
		logs = this.getLogList();
		for (int i = logs.size() - 1; i >= 0; i--) {
			String date = logs.get(i).getDate().substring(0, 11);
			if (logByDate.containsKey(date)) {
				logByDate.get(date).add(logs.get(i));
			} else {
				List<LogVO> log = new ArrayList<>();
				logByDate.put(date, log);
				logByDate.get(date).add(logs.get(i));
			}
		}

		return logByDate;
	}

	@Override
	public boolean addLog(LogPO logPO) {
		// TODO Auto-generated method stub
		return controller.addLog(logPO);
	}

}
