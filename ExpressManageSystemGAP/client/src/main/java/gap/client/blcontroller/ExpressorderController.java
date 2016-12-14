package gap.client.blcontroller;

import gap.client.bl.expressorder.ExpressOrder;
import gap.client.exception.InvalidInputException;
import gap.client.util.LocalInfo;
import gap.client.vo.ExpressOrderVO;
import gap.common.po.AllAddressPO;
import gap.common.util.CurrentOrderType;
import gap.common.util.ExpressType;
import gap.common.util.ReceiveInfo;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class ExpressorderController {
	private static ExpressOrder expressorder = new ExpressOrder();

	public static AllAddressPO getAllAddress() {
		return expressorder.getAllAddress();
	}

	public static ExpressOrderVO createOrder(ExpressOrderVO order_info) {
		// TODO 自动生成的方法存根
		return expressorder.createOrder(order_info);
	}

	public static ResultMessage save(ExpressOrderVO order) {
		// TODO 自动生成的方法存根
		return expressorder.save(order);
	}

	public static ResultMessage receiveOrder(ReceiveInfo receiveInfo) {
		// TODO 自动生成的方法存根
		return expressorder.receiveOrder(receiveInfo);
	}

	public static List<ExpressOrderVO> getCurrentOrders(CurrentOrderType type) {
		return expressorder.getCurrentOrders(LocalInfo.ins_id, type);
	}

	public static List<ExpressOrderVO> getByOrderIdList(List<String> orderIds) {
		List<ExpressOrderVO> orders = new ArrayList<ExpressOrderVO>();
		for (String str : orderIds) {
			try {
				orders.add(expressorder.getOrder(str));
			} catch (InvalidInputException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return orders;
	}

	public static String[] getStates(String order_id) {
		List<String> states = expressorder.getState(order_id);
		if (states == null)
			return null;
		String[] str = new String[states.size()];
		for (int i = 0; i < states.size(); i++)
			str[i] = states.get(i);
		return str;
	}

	public static boolean isExisted(String order_id) {
		return expressorder.isExisted(order_id);
	}

	public static double getDeliveryTime(String departure_city,
			String target_city, ExpressType type) {
		return expressorder.getDeliveryTime(departure_city, target_city, type);
	}

}
