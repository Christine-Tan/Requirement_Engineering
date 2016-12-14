package gap.testers;

import static org.junit.Assert.*;
import gap.client.bl.expressorder.ExpressOrder;
import gap.client.blservice.expressorderblservice.PriceCal;
import gap.client.vo.ExpressOrderVO;
import gap.common.util.ExpressType;
import gap.mockobject.MockPriceCal;

import org.junit.*;

public class CreateExpressorderTest {

	@Test
	public void test() {
		PriceCal priceCal = new MockPriceCal();
		ExpressOrder expressOrder = new ExpressOrder(priceCal);
		ExpressOrderVO vo1 = new ExpressOrderVO("yyf", "nju", "software",
				"110", "txy", "nju", "software", "119", ExpressType.ECONOMIC,
				null, false, "0000000001", "0010001", "0011001");
		ExpressOrderVO vo2 = new ExpressOrderVO("yyf", "nju", "software",
				"110", "txy", "nju", "software", "119", ExpressType.EXPRESS,
				null, false, "0000000001", "0010001", "0011001");
		ExpressOrderVO order1 = expressOrder.createOrder(vo1);
		ExpressOrderVO order2 = expressOrder.createOrder(vo2);
		assertEquals(order1.getPrice(), 10, 0.01);
		assertEquals(order2.getPrice(), 20, 0.01);
	}
}
