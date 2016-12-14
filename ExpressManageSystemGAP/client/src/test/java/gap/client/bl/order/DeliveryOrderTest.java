package gap.client.bl.order;

import gap.client.datacontroller.NetModule;
import gap.client.vo.DeliveryOrderVO;

import org.junit.Before;
import org.junit.Test;

public class DeliveryOrderTest {
	DeliveryOrder delivery;

	@Before
	public void setUp() throws Exception {
		NetModule.connect();
		delivery=new DeliveryOrder();
	}

	@Test
	public void testDeliveryOrder() {
	}

	@Test
	public void testSave() {
		DeliveryOrderVO vo=new DeliveryOrderVO();
		delivery.save(vo);
	}

}
