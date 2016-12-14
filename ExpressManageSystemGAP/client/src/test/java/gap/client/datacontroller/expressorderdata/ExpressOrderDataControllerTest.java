package gap.client.datacontroller.expressorderdata;

import static org.junit.Assert.fail;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.ExpressOrderDataController;
import gap.common.po.PricePO;

import org.junit.Before;
import org.junit.Test;

public class ExpressOrderDataControllerTest {
	ExpressOrderDataController expressorderdata;

	@Before
	public void setUp() throws Exception {
		expressorderdata = ControllerFactory.getExpressOrderDataController();
	}

	@Test
	public void testExpressOrderDataController() {
		fail("尚未实现");
	}

	@Test
	public void testAdd() {
		fail("尚未实现");
	}

	@Test
	public void testFindPrice() {
		PricePO find = expressorderdata.findPrice("南京市");
		System.out.println(find.getCity() + find.getExpress() + ":"
				+ find.getStandard() + ":" + find.getEconomic() + " "
				+ find.getBase());
	}

	@Test
	public void testFindExpressOrder() {
		fail("尚未实现");
	}

	@Test
	public void testModify() {
		fail("尚未实现");
	}

	@Test
	public void testFindArrivingOrders() {
		fail("尚未实现");
	}

	@Test
	public void testFindCurrentOrders() {
		fail("尚未实现");
	}

	@Test
	public void testGetState() {
		fail("尚未实现");
	}

	@Test
	public void testSetReceived() {
		fail("尚未实现");
	}

}
