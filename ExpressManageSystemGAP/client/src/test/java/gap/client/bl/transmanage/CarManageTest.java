package gap.client.bl.transmanage;

import static org.junit.Assert.fail;
import gap.client.util.Car;
import gap.client.vo.CarVO;

import org.junit.Before;
import org.junit.Test;

public class CarManageTest {
	CarManage carManage;

	@Before
	public void setUp() throws Exception {
		carManage = new CarManage();
	}

	@Test
	public void testCarManage() {
		fail("尚未实现");
	}

	@Test
	public void testGetAll() {
		fail("尚未实现");
	}

	@Test
	public void testGetSingle() {
		fail("尚未实现");
	}

	@Test
	public void testModify() {
		fail("尚未实现");
	}

	@Test
	public void testDelete() {
		fail("尚未实现");
	}

	@Test
	public void testAdd() {
		fail("尚未实现");
	}

	@Test
	public void testFlush() {
		CarVO vo1 = new CarVO("0010001004", "123456", "0010001", 5);
		carManage.add(new Car(vo1));
		carManage.delete("0010001002");
		carManage.flush();
	}

}
