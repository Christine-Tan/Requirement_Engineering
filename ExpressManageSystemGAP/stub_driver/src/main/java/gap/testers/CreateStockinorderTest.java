package gap.testers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gap.client.bl.stockinorder.LocationCal;
import gap.client.bl.stockinorder.StockinOrder;
import gap.client.vo.GoodsVO;
import gap.mockobject.MockLocationCal;



public class CreateStockinorderTest {
	@Test
	public void test(){
		LocationCal locationcal = new MockLocationCal();
		StockinOrder stockinOrder = new StockinOrder(locationcal);
		GoodsVO goods1 = new GoodsVO("0000000001", null, "car", "2015-11-16");
		GoodsVO goods2 = new GoodsVO("0000000002", null, "car", "2015-11-16");
		assertEquals(stockinOrder.getLocation(goods1),"1");
		assertEquals(stockinOrder.getLocation(goods2),"2");
		
	}

}
