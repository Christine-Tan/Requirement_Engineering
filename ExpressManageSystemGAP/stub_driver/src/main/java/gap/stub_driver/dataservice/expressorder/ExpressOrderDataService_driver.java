package gap.stub_driver.dataservice.expressorder;

import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.po.ExpressOrderPO;
import gap.common.util.ExpressType;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

public class ExpressOrderDataService_driver {
	public void driver(ExpressOrderDataService expressOrder)
			throws RemoteException {
		ExpressOrderPO order1 = new ExpressOrderPO();
		ExpressOrderPO order2 = new ExpressOrderPO();
		// test add
		if (expressOrder.add(order1).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (expressOrder.add(order2).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (expressOrder.add(order1).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,order exited");
		}
		// test find
		ExpressOrderPO get = expressOrder.find("0000000001");
		if (get != null)
			System.out.println("find:id=" + get.getID() + ",sender_name="
					+ get.getSenderInfo().getName() + ",receiver_name="
					+ get.getReceiverInfo().getName());
		get = expressOrder.find("0000000003");
		if (get == null)
			System.out.println("find failed,not found");
		// test modify
		order1.setReceived(true);
		if (expressOrder.modify(order1).equals(ResultMessage.SUCCEED))
			System.out.println("modify succeed");
		// test findArrivingOrders
		List<ExpressOrderPO> listGet = expressOrder
				.findArrivingOrders("0010001");
		if (!listGet.isEmpty()) {
			System.out.println("find succeed:");
			for (ExpressOrderPO po : listGet) {
				System.out.println("id=" + po.getID() + ",sender_name="
						+ po.getSenderInfo().getName() + ",receiver_name="
						+ po.getReceiverInfo().getName());
			}
		}
		// test findCurrentOrders
		listGet = expressOrder.findCurrentOrders("0011001");
		if (!listGet.isEmpty()) {
			System.out.println("find succeed:");
			for (ExpressOrderPO po : listGet) {
				System.out.println("id=" + po.getID() + ",sender_name="
						+ po.getSenderInfo().getName() + ",receiver_name="
						+ po.getReceiverInfo().getName());
			}
		}
	}

	public static void main(String[] args) {
		ExpressOrderDataService expressOrder = new ExpressOrderDataService_stub();
		ExpressOrderDataService_driver driver = new ExpressOrderDataService_driver();
		try {
			driver.driver(expressOrder);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
