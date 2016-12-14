package gap.client.bl.expressorder;

import gap.client.bl.strategy.CityManage;
import gap.client.blservice.expressorderblservice.ExpressOrderService;
import gap.client.blservice.expressorderblservice.PriceCal;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.ExpressOrderDataController;
import gap.client.exception.InvalidInputException;
import gap.client.util.LocalInfo;
import gap.client.vo.ExpressOrderVO;
import gap.common.po.AllAddressPO;
import gap.common.po.ExpressOrderPO;
import gap.common.util.CurrentOrderType;
import gap.common.util.ExpressType;
import gap.common.util.ReceiveInfo;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class ExpressOrder implements ExpressOrderService {

	PriceCal priceCal;
	ExpressOrderDataController expressorderData;
	AllAddressPO allAddress;

	public ExpressOrder() {
		// TODO 自动生成的构造函数存根
		expressorderData = ControllerFactory.getExpressOrderDataController();
		priceCal = new PriceCalImpl(new CityManage());
	}

	public ExpressOrder(PriceCal p) {
		expressorderData = ControllerFactory.getExpressOrderDataController();
		priceCal = p;
	}

	@Override
	public List<String> getState(String order_id) {
		// TODO 自动生成的方法存根
		return expressorderData.getState(order_id);
	}

	@Override
	public ExpressOrderVO getOrder(String order_id)
			throws InvalidInputException {
		// TODO 自动生成的方法存根
		if (order_id.length() != 10)
			throw new InvalidInputException("快件单号位数错误");
		ExpressOrderPO po = expressorderData.findExpressOrder(order_id);
		if (po != null)
			return new ExpressOrderVO(po);
		return null;
	}

	@Override
	public List<ExpressOrderVO> getArrivingOrders(String ins_id) {
		// TODO 自动生成的方法存根
		List<ExpressOrderPO> orders = expressorderData
				.findArrivingOrders(ins_id);
		if (orders == null)
			return null;
		List<ExpressOrderVO> ordersVO = new ArrayList<ExpressOrderVO>();
		for (ExpressOrderPO po : orders)
			ordersVO.add(new ExpressOrderVO(po));
		return ordersVO;
	}

	@Override
	public List<ExpressOrderVO> getCurrentOrders(String ins_id,
			CurrentOrderType type) {
		// TODO 自动生成的方法存根
		List<ExpressOrderPO> orders = expressorderData.findCurrentOrders(
				ins_id, type);
		if (orders == null)
			return null;
		List<ExpressOrderVO> ordersVO = new ArrayList<ExpressOrderVO>();
		for (ExpressOrderPO po : orders)
			ordersVO.add(new ExpressOrderVO(po));
		return ordersVO;
	}

	@Override
	public ExpressOrderVO createOrder(ExpressOrderVO order_info) {
		// TODO 自动生成的方法存根
		order_info.currentins_id = LocalInfo.ins_id;
		order_info.price = priceCal.getPrice(order_info);
		String id = expressorderData.nextID() + "";
		while (id.length() < 10)
			id = "0" + id;
		order_info.order_id = id;
		return order_info;
	}

	@Override
	public ResultMessage save(ExpressOrderVO order) {
		// TODO 自动生成的方法存根
		return expressorderData.add(order.toPO());
	}

	@Override
	public ResultMessage receiveOrder(ReceiveInfo receiveInfo) {
		// TODO 自动生成的方法存根
		return expressorderData.setReceived(receiveInfo);
	}

	@Override
	public AllAddressPO getAllAddress() {
		// TODO 自动生成的方法存根
		if (allAddress == null)
			allAddress = expressorderData.getAllAddress();
		return allAddress;
	}

	@Override
	public boolean isExisted(String order_id) {
		// TODO 自动生成的方法存根
		return expressorderData.isExisted(order_id);
	}

	@Override
	public double getDeliveryTime(String departure_city, String target_city,
			ExpressType type) {
		// TODO 自动生成的方法存根
		return expressorderData.getDeliveryTime(departure_city, target_city,
				type);
	}

}
