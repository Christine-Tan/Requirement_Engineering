package gap.stub_driver.blservice.log;

import gap.client.blservice.logblservice.LogService;
import gap.client.vo.LogListVO;

public class Log_stub implements LogService {

	@Override
	public LogListVO getLog() {
		// TODO Auto-generated method stub
		return new LogListVO();
	}

}
