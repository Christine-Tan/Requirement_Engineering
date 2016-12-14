package gap.client.bl.order;

import gap.client.datacontroller.NetModule;
import gap.client.vo.LoadOrderVO;

import org.junit.Before;
import org.junit.Test;

public class LoadOrderTest {
	LoadOrder load;

	@Before
	public void setUp() throws Exception {
		NetModule.connect();
		load = new LoadOrder();
	}

	@Test
	public void testSave() {
		LoadOrderVO vo = new LoadOrderVO();
		vo.date = "2015-03-01";
		load.save(vo);
	}

	@Test
	public void testGetArrivingLoadOrder() {
		load.getArrivingLoadOrder("0010001");
	}

}
