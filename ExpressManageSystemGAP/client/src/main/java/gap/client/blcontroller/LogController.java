package gap.client.blcontroller;

import java.util.List;
import java.util.TreeMap;

import gap.client.bl.log.Log;
import gap.client.vo.LogVO;
import gap.common.po.LogPO;

public class LogController {
	private static Log logManage = new Log();

	public LogController() {
		// TODO Auto-generated constructor stub
	}

	public static List<LogVO> getLogList() {
		return logManage.getLogList();
	}

	public static TreeMap<String, List<LogVO>> getLogByDate() {
		return logManage.getLogByDate();
	}

	public static boolean addLog(LogPO logPO) {
		return logManage.addLog(logPO);

	}
}
