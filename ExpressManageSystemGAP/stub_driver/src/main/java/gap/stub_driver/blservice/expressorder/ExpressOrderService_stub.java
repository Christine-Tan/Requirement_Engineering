package gap.stub_driver.blservice.expressorder;

import gap.client.blservice.expressorderblservice.ExpressOrderService;
import gap.client.vo.ExpressOrderVO;
import gap.client.vo.StateVO;
import gap.common.po.ExpressOrderPO;
import gap.common.util.ReceiveInfo;
import gap.common.util.ResultMessage;
import gap.stub_driver.dataservice.expressorder.ExpressOrderDataService_stub;

import java.util.ArrayList;
import java.util.List;

public class ExpressOrderService_stub implements ExpressOrderService {

	private ExpressOrderDataService_stub datastub;

	public ExpressOrderService_stub() {
		datastub = new ExpressOrderDataService_stub();
	}

	@Override
	public StateVO find(String order_id) {
		// TODO 自动生成的方法存根
		return new StateVO();
	}

	@Override
	public ExpressOrderVO getOrder(String order_id) {
		// TODO 自动生成的方法存根
		return getVO(datastub.find(order_id));
	}

	@Override
	public ResultMessage modify(ExpressOrderVO vo) {
		// TODO 自动生成的方法存根
		return datastub.modify(getPO(vo));
	}

	@Override
	public List<ExpressOrderVO> getArrivingOrders(String ins_id) {
		// TODO 自动生成的方法存根
		List<ExpressOrderVO> re = new ArrayList<ExpressOrderVO>();
		for (ExpressOrderPO order : datastub.findArrivingOrders(ins_id))
			re.add(getVO(order));
		return re;
	}

	@Override
	public List<ExpressOrderVO> getCurrentOrders(String ins_id) {
		// TODO 自动生成的方法存根
		List<ExpressOrderVO> re = new ArrayList<ExpressOrderVO>();
		for (ExpressOrderPO order : datastub.findCurrentOrders(ins_id))
			re.add(getVO(order));
		return re;
	}

	@Override
	public ResultMessage setArrivedOrders(List<ExpressOrderVO> orders,
			String ins_id) {
		// TODO 自动生成的方法存根
		for (ExpressOrderVO vo : orders) {
			ExpressOrderPO po = datastub.find(vo.getID());
			if (po == null)
				return ResultMessage.NOTFOUND;
			po.setCurrentins_id(ins_id);
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage setOffOders(List<ExpressOrderVO> orders, String ins_id) {
		// TODO 自动生成的方法存根
		for (ExpressOrderVO vo : orders) {
			ExpressOrderPO po = datastub.find(vo.getID());
			if (po == null)
				return ResultMessage.NOTFOUND;
			po.setTargetins_id(ins_id);
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public ExpressOrderVO createOrder(ExpressOrderVO order_info) {
		// TODO 自动生成的方法存根
		return order_info;
	}

	@Override
	public ResultMessage save(ExpressOrderVO order) {
		// TODO 自动生成的方法存根
		return datastub.add(getPO(order));
	}

	@Override
	public ResultMessage receiveOrder(String order_id, ReceiveInfo receiveInfo) {
		// TODO 自动生成的方法存根
		ExpressOrderPO po = datastub.find(order_id);
		if (po == null)
			return ResultMessage.NOTFOUND;
		if (po.isReceived())
			return ResultMessage.RECEIVED;
		po.setReceived(true);
		return ResultMessage.SUCCEED;
	}

	private ExpressOrderVO getVO(ExpressOrderPO po) {
		if (po == null)
			return null;

		return new ExpressOrderVO();
	}

	private ExpressOrderPO getPO(ExpressOrderVO vo) {
		if (vo == null)
			return null;
		return new ExpressOrderPO();
	}

}
