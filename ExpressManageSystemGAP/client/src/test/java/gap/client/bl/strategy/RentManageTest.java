package gap.client.bl.strategy;

import gap.client.datacontroller.NetModule;
import gap.client.vo.RentVO;

import org.junit.Before;
import org.junit.Test;

public class RentManageTest {
     RentManage rent;
	@Before
	public void setUp() throws Exception {
	     NetModule.connect();
	     rent=new RentManage();
	}

	@Test
	public void testGetAll() {
		rent.getAll();
	}

	@Test
	public void testAddRent() {
		RentVO vo=new RentVO();
		rent.addRent(vo);
	}

	@Test
	public void testModifyRent() {
		RentVO vo=new RentVO();
		rent.modifyRent(vo);
	}

}
