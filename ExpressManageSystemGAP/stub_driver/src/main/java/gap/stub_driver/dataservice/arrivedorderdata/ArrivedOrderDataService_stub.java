package gap.stub_driver.dataservice.arrivedorderdata;

import gap.common.dataservice.orderdataservice.ArrivedOrderDataService;
import gap.common.po.ArrivedOrderPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class ArrivedOrderDataService_stub implements ArrivedOrderDataService {

	private List<ArrivedOrderPO> list;

	public ArrivedOrderDataService_stub() {
		list = new ArrayList<ArrivedOrderPO>();
	}

	@Override
	public ResultMessage add(ArrivedOrderPO po) {
		// TODO 自动生成的方法存根
		for (ArrivedOrderPO order : list)
			if (po.getID().equals(order.getID()))
				return ResultMessage.EXITED;
		list.add(po);
		return ResultMessage.SUCCEED;
	}

	@Override
	public ArrivedOrderPO find(String order_id) {
		// TODO 自动生成的方法存根
		for (ArrivedOrderPO order : list)
			if (order_id.equals(order.getID()))
				return order;
		return null;
	}
}
