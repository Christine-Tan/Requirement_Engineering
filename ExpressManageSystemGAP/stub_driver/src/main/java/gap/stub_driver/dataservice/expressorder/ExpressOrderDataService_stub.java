package gap.stub_driver.dataservice.expressorder;

import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.po.ExpressOrderPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class ExpressOrderDataService_stub implements ExpressOrderDataService {
	private List<ExpressOrderPO> list;

	public ExpressOrderDataService_stub() {
		list = new ArrayList<ExpressOrderPO>();
	}

	@Override
	public ResultMessage add(ExpressOrderPO po) {
		// TODO 自动生成的方法存根
		for (ExpressOrderPO order : list)
			if (order.getID().equals(po.getID()))
				return ResultMessage.EXITED;
		list.add(po);
		return ResultMessage.SUCCEED;
	}

	@Override
	public ExpressOrderPO find(String order_id) {
		// TODO 自动生成的方法存根
		for (ExpressOrderPO order : list)
			if (order.getID().equals(order_id))
				return order;
		return null;

	}

	@Override
	public ResultMessage modify(ExpressOrderPO po) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getID().equals(po.getID())) {
				list.remove(i);
				list.add(po);
				return ResultMessage.SUCCEED;
			}
		}
		return ResultMessage.NOTFOUND;
	}

	@Override
	public List<ExpressOrderPO> findArrivingOrders(String ins_id) {
		// TODO 自动生成的方法存根
		ArrayList<ExpressOrderPO> re = new ArrayList<>();
		for (ExpressOrderPO order : list)
			if (order.getTargetins_id() != null
					&& order.getTargetins_id().equals(ins_id))
				re.add(order);
		return re;
	}

	@Override
	public List<ExpressOrderPO> findCurrentOrders(String ins_id) {
		// TODO 自动生成的方法存根
		ArrayList<ExpressOrderPO> re = new ArrayList<>();
		for (ExpressOrderPO order : list)
			if (order.getCurrentins_id() != null
					&& order.getCurrentins_id().equals(ins_id))
				re.add(order);
		return re;
	}

	@Override
	public List<ExpressOrderPO> findLoadOrders(String loadorder_id) {
		// TODO 自动生成的方法存根
		ArrayList<ExpressOrderPO> re = new ArrayList<>();
		for (ExpressOrderPO order : list)
			if (order.getClerkLoadOrder_id() != null
					&& order.getClerkLoadOrder_id().equals(loadorder_id))
				re.add(order);
		return re;
	}

	@Override
	public List<ExpressOrderPO> findDeliveryOrders(String deliveryorder_id) {
		// TODO 自动生成的方法存根
		ArrayList<ExpressOrderPO> re = new ArrayList<>();
		for (ExpressOrderPO order : list)
			if (order.getDeliveryOrder_id() != null
					&& order.getDeliveryOrder_id().equals(deliveryorder_id))
				re.add(order);
		return re;
	}

	@Override
	public List<ExpressOrderPO> findArrivedOrders(String arrivedorder_id) {
		// TODO 自动生成的方法存根
		ArrayList<ExpressOrderPO> re = new ArrayList<>();
		for (ExpressOrderPO order : list)
			if (order.getClerkArrivedOrder_id().equals(arrivedorder_id))
				re.add(order);
		return re;
	}

}
