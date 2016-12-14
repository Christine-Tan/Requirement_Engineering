package gap.client.blcontroller;

import gap.client.bl.order.ArrivedOrder;
import gap.client.vo.ArrivedOrderVO;
import gap.common.util.ResultMessage;

public class ArrivedOrderController {
	public static ArrivedOrder arrivedOrder = new ArrivedOrder();

	public static ResultMessage save(ArrivedOrderVO order) {
		return arrivedOrder.save(order);
	}
}
