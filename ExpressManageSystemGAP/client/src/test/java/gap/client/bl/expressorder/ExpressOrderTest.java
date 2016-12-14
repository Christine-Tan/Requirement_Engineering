package gap.client.bl.expressorder;

import static org.junit.Assert.fail;
import gap.client.exception.InvalidInputException;
import gap.client.vo.ExpressOrderVO;
import gap.common.util.Address;
import gap.common.util.CargoInfo;
import gap.common.util.CurrentOrderType;
import gap.common.util.ExpressType;
import gap.common.util.PeopleInfo;
import gap.common.util.ReceiveInfo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ExpressOrderTest {
	ExpressOrder expressorder;

	@Before
	public void setUp() throws Exception {
		expressorder = new ExpressOrder();
		// User user = new User(UserType.DELIVERY, "xiaoming", "000000005",
		// Gender.MALE);
		// User user = new User(UserType.DELIVERY, "小明", "000000005", "123456",
		// Gender.MALE);
		// LocalInfo.localuser = user;
	}

	@Test
	public void testSave() {
		Address add1 = new Address("江苏省", "南京市", "玄武区");
		Address add2 = new Address("北京市", "北京市", "东城区");
		PeopleInfo sender = new PeopleInfo("杨雁飞", add1, "南京大学", "15520065137");
		PeopleInfo receiver = new PeopleInfo("张家盛", add2, "北京大学", "119");
		CargoInfo cargo = new CargoInfo(2, 2.5, 3.5, "测试");
		ExpressOrderVO vo = new ExpressOrderVO(sender, receiver,
				ExpressType.EXPRESS, 20.5, cargo, false, "0000000003",
				"0010001", null,null);
	}

	@Test
	public void testExpressOrder() {
	}

	@Test
	public void testExpressOrderPriceCal() {
		fail("尚未实现");
	}

	@Test
	public void testGetState() throws InvalidInputException {
		List<String> states = expressorder.getState("0000000003");
		for (String state : states) {
			System.out.println(state);
		}
	}

	@Test
	public void testGetOrder() throws InvalidInputException {
		ExpressOrderVO vo = expressorder.getOrder("0000000002");
		System.out.println(vo.order_id + "," + vo.expressType + "," + vo.price);
	}

	@Test
	public void testModify() {
		fail("尚未实现");
	}

	@Test
	public void testGetArrivingOrders() {
		List<ExpressOrderVO> orders = expressorder.getArrivingOrders("0010002");
		for (ExpressOrderVO vo : orders) {
			System.out.println(vo.order_id + "," + vo.expressType + ","
					+ vo.price);
		}
	}

	@Test
	public void testGetCurrentOrders() {
		List<ExpressOrderVO> orders = expressorder.getCurrentOrders("0010001",
				CurrentOrderType.ALL);
		for (ExpressOrderVO vo : orders) {
			System.out.println(vo.order_id + "," + vo.expressType + ","
					+ vo.price);
		}
	}

	@Test
	public void testSetArrivedOrders() {
		fail("尚未实现");
	}

	@Test
	public void testSetOffOders() {
		fail("尚未实现");
	}

	@Test
	public void testCreateOrder() {
		fail("尚未实现");
	}

	@Test
	public void testReceiveOrder() {
		expressorder.receiveOrder(new ReceiveInfo("0000000001", "小明",
				"2015-11-26", "000000005", "测试1"));
		// fail("尚未实现");
	}

}
