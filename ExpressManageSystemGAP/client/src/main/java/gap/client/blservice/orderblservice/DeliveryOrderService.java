package gap.client.blservice.orderblservice;

import gap.client.vo.DeliveryOrderVO;
import gap.common.util.ResultMessage;

public interface DeliveryOrderService {

	public ResultMessage save(DeliveryOrderVO order);

}
