package gap.client.blservice.initialblservice;

import gap.common.po.InitialHistoryPO;
import gap.common.util.ResultMessage;

import java.util.List;

public interface InitialBlService {

	public InitialHistoryPO getCurrentInitial();

	public ResultMessage submitInitial(InitialHistoryPO initialPO);

	public List<InitialHistoryPO> getInitialHistory();

}
