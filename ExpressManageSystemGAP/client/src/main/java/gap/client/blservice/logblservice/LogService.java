package gap.client.blservice.logblservice;

import gap.client.vo.LogVO;
import gap.common.po.LogPO;

import java.util.List;

public interface LogService {

	public List<LogVO> getLogList();

	public boolean addLog(LogPO logPO);

}
