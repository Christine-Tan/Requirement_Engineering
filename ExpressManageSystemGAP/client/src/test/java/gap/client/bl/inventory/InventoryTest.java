package gap.client.bl.inventory;

import static org.junit.Assert.fail;
import gap.client.datacontroller.NetModule;
import gap.client.vo.GoodsVO;
import gap.common.util.SectorType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {
	Inventory inventory;
	@Before
	public void setUp() throws Exception {
		NetModule.connect();
		inventory = new Inventory();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetTotalNum() {
		inventory.getTotalNum("0011001");
	}

	@Test
	public void testGetOneSector() {
		inventory.getOneSector("0011001", "00110011");
	}

	@Test
	public void testGetOneSectorExisted() {
		inventory.getOneSectorExisted("0011001", "00110011");
	}

	@Test
	public void testGetOneShelfRatio() {
		inventory.getOneShelfRatio("A,A", "00110011");
	}

	@Test
	public void testSetAlarm() {
		inventory.setAlarm(85, "0011001");
	}

	@Test
	public void testGetAlarm() {
		inventory.getAlarm("0011001");
	}

	@Test
	public void testDistributeSector() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitialadd() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitialdelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitialmodify() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitialflush() {
		fail("Not yet implemented");
	}

	@Test
	public void testStockOut() {
		inventory.stockOut("0000000001");
	}

	@Test
	public void testStockIn() {
		inventory.stockIn(new GoodsVO("0000000001", "A,A,1", SectorType.CAR, "2015-12-20", "00110011", "00110011", "江苏省南京市栖霞区"));
	}

	@Test
	public void testGetNextLocation() {
		inventory.getNextLocation("0011001", "00110011");
	}

	@Test
	public void testAlarm() {
		fail("Not yet implemented");
	}

}
