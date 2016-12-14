package gap.client.bl.order;

import gap.client.blservice.orderblservice.DeliveryOrderService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.DeliveryOrderDataController;
import gap.client.util.LocalInfo;
import gap.client.vo.DeliveryOrderVO;
import gap.common.util.ResultMessage;

import java.sql.Date;

public class DeliveryOrder implements DeliveryOrderService {
	DeliveryOrderDataController deliveryOrderData;

	public DeliveryOrder() {
		deliveryOrderData = ControllerFactory.getDeliveryOrderDataController();
	}

	@Override
	public ResultMessage save(DeliveryOrderVO order) {
		// TODO 自动生成的方法存根
		order.time = (new Date(System.currentTimeMillis())).toString()
				.replaceAll("-", "");
		String pre = LocalInfo.ins_id + order.time;
		String id = deliveryOrderData.getNextId(pre) + "";
		while (id.length() < 5)
			id = "0" + id;
		order.id = pre + id;
		System.out.println(pre + id);
		return deliveryOrderData.add(order.toPO());
	}

}
