package gap.stub_driver.blservice.initial;

import gap.client.blservice.initialblservice.InitialBlService;
import gap.client.vo.InitialHistoryVO;
import gap.client.vo.InitialVO;
import gap.common.util.ResultMessage;

public class Initial_stub implements InitialBlService {

	@Override
	public InitialVO getInitialList() {
		// TODO Auto-generated method stub
		return new InitialVO();
	}

	@Override
	public ResultMessage submitInitialList(InitialVO initialVO) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCEED;
	}

	@Override
	public InitialHistoryVO getInitialHistory() {
		// TODO Auto-generated method stub
		return new InitialHistoryVO();
	}

}
