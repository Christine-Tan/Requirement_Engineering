package gap.server.data.inventorydata;

import static org.junit.Assert.fail;
import gap.common.po.GoodsPO;
import gap.common.util.SectorType;
import gap.server.initial.NetInitial;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryDataServiceImplTest {
	InventoryDataServiceImpl inventory;
	GoodsPO[] po = new GoodsPO[17];

	@Before
	public void setUp() throws Exception {
		NetInitial.initial();
		inventory = new InventoryDataServiceImpl();
		po[0] = null;
		po[1] = new GoodsPO("0000000001", "A,A,1", SectorType.CAR,
				"2015-11-25", "00110011", "00110011", "nanjing");
		po[2] = new GoodsPO("0000000001", "A,A,1", SectorType.CAR,
				"2015-11-25", "00210011", "00210011", "nanjing");
		po[3] = new GoodsPO("0000000003", "A,A,3", SectorType.TRAIN,
				"2015-11-25", "00110012", "00000012", "nanjing");
		po[4] = new GoodsPO("0000000004", "A,A,4", SectorType.PLANE,
				"2015-11-25", "00110013", "00000013", "nanjing");
		// po[5] = new GoodsPO("0000000005", "A,A,5", SectorType.FLEX,
		// "2015-11-24", "00110010", "00000011", "nanjing");
		// po[6] = new GoodsPO("0000000006", "A,A,6", SectorType.CAR,
		// "2015-11-24", "00000011", "00000011", "nanjing");
		// po[7] = new GoodsPO("0000000007", "A,A,7", SectorType.TRAIN,
		// "2015-11-23", "00000012", "00000012", "nanjing");
		// po[8] = new GoodsPO("0000000008", "A,A,8", SectorType.PLANE,
		// "2015-11-23", "00000013", "00000013", "nanjing");
		// po[9] = new GoodsPO("0000000009", "A,A,9", SectorType.FLEX,
		// "2015-11-23", "00000010", "00000011", "nanjing");
		// po[10] = new GoodsPO("0000000010", "A,A,10", SectorType.CAR,
		// "2015-11-23", "00000011", "00000011", "nanjing");
		// po[11] = new GoodsPO("0000000011", "A,A,11", SectorType.TRAIN,
		// "2015-11-22", "00000012", "00000012", "nanjing");
		// po[12] = new GoodsPO("0000000012", "A,A,12", SectorType.PLANE,
		// "2015-11-22", "00000013", "00000013", "nanjing");
		// po[13] = new GoodsPO("0000000013", "A,A,13", SectorType.FLEX,
		// "2015-11-22", "00000020", "00000021", "nanjing");
		// po[14] = new GoodsPO("0000000014", "A,A,14", SectorType.CAR,
		// "2015-11-22", "00000021", "00000021", "nanjing");
		// po[15] = new GoodsPO("0000000015", "A,A,15", SectorType.TRAIN,
		// "2015-11-21", "00000022", "00000022", "nanjing");
		// po[16] = new GoodsPO("0000000016", "A,A,16", SectorType.PLANE,
		// "2015-11-21", "00000023", "00000023", "nanjing");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test 
	public void testGetOneTypeSector(){
		try {
			List<GoodsPO> list = inventory.getOneTypeSector("00110011");
			if(list==null){
				System.out.println("gg");
			}else{
				for(GoodsPO po:list){
					System.out.println(po.getExpressorder_id());
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testGetOneSector() {
		try {
			List<GoodsPO> list = inventory.getOneSector("00000010", "0000001");
			System.out.println(list.isEmpty());
			for (GoodsPO po : list) {
				System.out
						.println("expressorder_id:" + po.getExpressorder_id());
				System.out.println("location:" + po.getLocation());
				System.out.println("SectorType:" + po.getSector());
				System.out.println("Date:" + po.getDate());
				System.out.println("Sector_id:" + po.getSector_id());
				System.out.println("Belong_sector_id:"
						+ po.getBelong_sector_id());
				System.out.println("Destination:" + po.getDestination());
				System.out.println();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTotalNum(){
		try {
			int num = inventory.getTotalNum("0011001");
			System.out.println(num);
			
			num = inventory.getTotalNum("0011004");
			System.out.println(num);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSetExisted(){
		inventory.setExisted("0000000001");
	}
	
	@Test 
	public void test(){
		try {
			List<GoodsPO> list = inventory.getOneSectorExisted("00110010", "0011001");
			System.out.println(list==null);
			for (GoodsPO po : list) {
				System.out
						.println("expressorder_id:" + po.getExpressorder_id());
				System.out.println("location:" + po.getLocation());
				System.out.println("SectorType:" + po.getSector());
				System.out.println("Date:" + po.getDate());
				System.out.println("Sector_id:" + po.getSector_id());
				System.out.println("Belong_sector_id:"
						+ po.getBelong_sector_id());
				System.out.println("Destination:" + po.getDestination());
				System.out.println();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	
	public void testgetOneShelfNum(){
		System.out.println(inventory.getOneShelfNum("A,B", "00110011"));
	}

	@Test
	public void testAddGoodsPO() {

		try {
			inventory.add(po[1]);
			inventory.add(po[2]);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void testAddListOfGoodsPO() {

	}

	@Test
	public void testDeleteString() {
		try {
			for (int i = 1; i <= 16; i++) {
				inventory.delete(po[i].getExpressorder_id());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @Test
	public void testDeleteListOfString() {
		fail("Not yet implemented");
	}

	// @Test
	public void testModifyGoodsPO() {
		GoodsPO po = new GoodsPO("0000000016", "A,A,16", SectorType.FLEX,
				"2015-11-21", "00000020", "00000023", "nanjing");
		GoodsPO po1 = new GoodsPO("0000000016", "A,A,16", SectorType.PLANE,
				"2015-11-21", "00000023", "00000023", "nanjing");
		try {
			System.out.println(inventory.modify(po1));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void testModifyListOfGoodsPO() {
		fail("Not yet implemented");
	}

	// @Test
	public void testFind() {
		GoodsPO po = new GoodsPO();
		try {
			po = inventory.find("0000000016");
			System.out.println("expressorder_id:" + po.getExpressorder_id());
			System.out.println("location:" + po.getLocation());
			System.out.println("SectorType:" + po.getSector());
			System.out.println("Date:" + po.getDate());
			System.out.println("Sector_id:" + po.getSector_id());
			System.out.println("Belong_sector_id:" + po.getBelong_sector_id());
			System.out.println("Destination:" + po.getDestination());

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void testSetAlarm() {
		double a = 90;
		try {
			System.out.println(inventory.setAlarm(a, "0000002"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void testGetAlarm() {
		try {
			System.out.println(inventory.getAlarm("0000002") + "%");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
