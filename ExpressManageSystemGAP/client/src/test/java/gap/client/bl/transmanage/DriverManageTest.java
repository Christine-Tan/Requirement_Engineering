package gap.client.bl.transmanage;

import static org.junit.Assert.fail;
import gap.client.util.Driver;
import gap.common.util.Gender;

import org.junit.Before;
import org.junit.Test;

public class DriverManageTest {
	DriverManage driverManage;

	@Before
	public void setUp() throws Exception {
		driverManage = new DriverManage();
	}

	@Test
	public void testDriverManage() {
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
		Driver driver = new Driver("0010001002", "0010001", "zjs",
				"1996-03-01", "500113199603013932", "15520065137",
				"2016-02-03", Gender.MALE);
		Driver driver1 = new Driver("0010001001", "0010001", "dwa",
				"1996-03-04", "xxxxxxxxxxxxxxxxxx", "xxxxxxxxxxx",
				"2016-02-03", Gender.MALE);
		driverManage.add(driver);
		driverManage.modify(driver1);
		driverManage.flush();
	}

}
