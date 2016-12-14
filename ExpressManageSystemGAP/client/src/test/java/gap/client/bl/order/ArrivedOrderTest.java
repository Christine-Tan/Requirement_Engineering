package gap.client.bl.order;

import gap.client.datacontroller.NetModule;
import gap.client.vo.ArrivedOrderVO;

import org.junit.Before;
import org.junit.Test;

public class ArrivedOrderTest {
	ArrivedOrder order;

	@Before
	public void setUp() throws Exception {
		NetModule.connect();
		order = new ArrivedOrder();
	}

	@Test
	public void testArrivedOrder() {
	}

	@Test
	public void testSave() {
		ArrivedOrderVO vo=new ArrivedOrderVO();
		vo.time="2015-03-01";
		order.save(vo);
	}

}
