package gap.stub_driver.blservice.expressorder;

import gap.client.blservice.expressorderblservice.ExpressOrderService;
import gap.client.vo.ExpressOrderVO;
import gap.common.util.ExpressType;
import gap.common.util.ResultMessage;

import java.util.List;

public class ExpressOrderService_driver {
	public void driver(ExpressOrderService expressOrder) {
		ExpressOrderVO order1 = new ExpressOrderVO("yyf", "nju", "software",
				"15520065137", "txy", "nju", "software", "110",
				ExpressType.STANDARD, null, false, "0000000001", "0011001",
				null);
		ExpressOrderVO order2 = new ExpressOrderVO("shenbin", "nju",
				"software", "119", "plw", "nju", "software", "120",
				ExpressType.EXPRESS, null, false, "0000000002", null, "0010001");
		// test save&create
		ExpressOrderVO orderCom1 = expressOrder.createOrder(order1);
		ExpressOrderVO orderCom2 = expressOrder.createOrder(order2);
		if (expressOrder.save(orderCom1).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (expressOrder.save(orderCom2).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (expressOrder.save(orderCom1).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,order exited");
		}
		// test find
		ExpressOrderVO get = expressOrder.getOrder("0000000001");
		if (get != null)
			System.out.println("find:id=" + get.getID() + ",sender_name="
					+ get.getSender_name() + ",receiver_name="
					+ get.getReceiver_name());
		get = expressOrder.getOrder("0000000003");
		if (get == null)
			System.out.println("find failed,not found");
		// test modify
		order1.setReceived(true);
		if (expressOrder.modify(order1).equals(ResultMessage.SUCCEED))
			System.out.println("modify succeed");
		// test getArrivingOrders
		List<ExpressOrderVO> listGet = expressOrder
				.getArrivingOrders("0010001");
		if (!listGet.isEmpty()) {
			System.out.println("find succeed:");
			for (ExpressOrderVO vo : listGet) {
				System.out.println("id=" + vo.getID() + ",sender_name="
						+ vo.getSender_name() + ",receiver_name="
						+ vo.getReceiver_name());
			}
		}
		// test getCurrentOrders
		listGet = expressOrder.getCurrentOrders("0011001");
		if (!listGet.isEmpty()) {
			System.out.println("find succeed:");
			for (ExpressOrderVO vo : listGet) {
				System.out.println("id=" + vo.getID() + ",sender_name="
						+ vo.getSender_name() + ",receiver_name="
						+ vo.getReceiver_name());
			}
		}
	}

	public static void main(String[] args) {
		ExpressOrderService expressOrder = new ExpressOrderService_stub();
		ExpressOrderService_driver driver = new ExpressOrderService_driver();
		driver.driver(expressOrder);

	}
}
