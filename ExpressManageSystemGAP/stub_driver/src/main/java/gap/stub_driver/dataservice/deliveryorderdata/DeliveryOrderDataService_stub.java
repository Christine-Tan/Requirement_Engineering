package gap.stub_driver.dataservice.deliveryorderdata;

import gap.common.dataservice.orderdataservice.DeliveryOrderDataService;
import gap.common.po.DeliveryOrderPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class DeliveryOrderDataService_stub implements DeliveryOrderDataService {

	private List<DeliveryOrderPO> list;

	public DeliveryOrderDataService_stub() {
		list = new ArrayList<DeliveryOrderPO>();
	}

	@Override
	public ResultMessage add(DeliveryOrderPO po) {
		// TODO 自动生成的方法存根
		for (DeliveryOrderPO order : list)
			if (order.getID().equals(po.getID()))
				return ResultMessage.EXITED;
		list.add(po);
		return ResultMessage.SUCCEED;
	}

	@Override
	public DeliveryOrderPO find(String order_id) {
		// TODO 自动生成的方法存根
		for (DeliveryOrderPO order : list)
			if (order.getID().equals(order_id))
				return order;
		return null;
	}

}
