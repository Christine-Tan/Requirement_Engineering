package gap.client.blservice.orderblservice;

import gap.client.vo.ArrivedOrderVO;
import gap.common.util.ResultMessage;

public interface ArrivedOrderService {

	public ResultMessage save(ArrivedOrderVO order);


}
