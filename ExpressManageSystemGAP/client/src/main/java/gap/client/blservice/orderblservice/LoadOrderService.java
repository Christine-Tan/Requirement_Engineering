package gap.client.blservice.orderblservice;

import gap.client.vo.LoadOrderVO;
import gap.common.util.ResultMessage;

import java.util.List;

public interface LoadOrderService {

	public ResultMessage save(LoadOrderVO order);

	public List<LoadOrderVO> getArrivingLoadOrder(String ins_id);

}
