package gap.client.bl.order;

import gap.client.datacontroller.NetModule;
import gap.client.vo.StockoutOrderVO;

import org.junit.Before;
import org.junit.Test;

public class StockoutOrderTest {
	StockoutOrder stockoutOrder;

	@Before
	public void setUp() throws Exception {
		NetModule.connect();
		stockoutOrder = new StockoutOrder();
	}

	@Test
	public void testSave() {
		StockoutOrderVO vo = new StockoutOrderVO(null, "2015-12-20", "北京市栖霞区中转中心", "0011001201512200001", "plane", "0011001");
		stockoutOrder.save(vo);
	}

//	@Test
//	public void testFind() {
//		fail("尚未实现");
//	}
//
//	@Test
//	public void testGetLocation() {
//		fail("尚未实现");
//	}

	@Test
	public void testGetRequired() {
		stockoutOrder.getRequired("2015-12-08", "2015-12-20", "0011001");
	}

	@Test
	public void testGetTotalNum() {
		stockoutOrder.getTotalNum(stockoutOrder.getRequired("2015-12-08", "2015-12-20", "0011001"));
	}

	@Test
	public void testGetNextId() {
		stockoutOrder.getNextId("001100120151220");
	}

}
