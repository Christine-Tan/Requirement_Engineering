package gap.stub_driver.dataservice.loadorder;

import gap.common.dataservice.orderdataservice.LoadOrderDataService;
import gap.common.po.LoadOrderPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class LoadOrderDataService_stub implements LoadOrderDataService {

	public List<LoadOrderPO> list;

	public LoadOrderDataService_stub() {
		list = new ArrayList<LoadOrderPO>();
	}

	@Override
	public ResultMessage add(LoadOrderPO po) {
		// TODO 自动生成的方法存根
		for (LoadOrderPO order : list)
			if (order.getNumber().equals(po.getNumber()))
				return ResultMessage.EXITED;
		list.add(po);
		return ResultMessage.SUCCEED;
	}

	@Override
	public LoadOrderPO find(String order_id) {
		// TODO 自动生成的方法存根
		for (LoadOrderPO order : list)
			if (order.getNumber().equals(order_id))
				return order;
		return null;
	}

}
