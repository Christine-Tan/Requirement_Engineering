package gap.server.data.order;

import gap.common.po.GoodsPO;
import gap.common.po.StockoutOrderPO;
import gap.common.util.SectorType;
import gap.server.initial.NetInitial;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StockoutOrderDataServiceImplTest {
	StockoutOrderDataServiceImpl stockout;

	GoodsPO[] gpo = new GoodsPO[17];
	ArrayList<String> list1 = new ArrayList<String>();
	ArrayList<String> list2 = new ArrayList<String>();
	ArrayList<String> list3 = new ArrayList<String>();
	ArrayList<String> list4 = new ArrayList<String>();
	ArrayList<String> list5 = new ArrayList<String>();
	ArrayList<String> list6 = new ArrayList<String>();

	StockoutOrderPO[] or = new StockoutOrderPO[6];

	@Before
	public void setUp() throws Exception {
		NetInitial.initial();
		stockout = new StockoutOrderDataServiceImpl();

		gpo[0] = null;
		gpo[1] = new GoodsPO("0000000001", "A,A,1", SectorType.FLEX,
				"2015-11-25", "00000010", "00000011", "nanjing");
		gpo[2] = new GoodsPO("0000000002", "A,A,2", SectorType.CAR,
				"2015-11-25", "00000011", "00000011", "nanjing");
		gpo[3] = new GoodsPO("0000000003", "A,A,3", SectorType.TRAIN,
				"2015-11-25", "00000012", "00000012", "nanjing");
		gpo[4] = new GoodsPO("0000000004", "A,A,4", SectorType.PLANE,
				"2015-11-24", "00000013", "00000013", "nanjing");
		gpo[5] = new GoodsPO("0000000005", "A,A,5", SectorType.FLEX,
				"2015-11-24", "00000010", "00000011", "nanjing");
		gpo[6] = new GoodsPO("0000000006", "A,A,6", SectorType.CAR,
				"2015-11-24", "00000011", "00000011", "nanjing");
		gpo[7] = new GoodsPO("0000000007", "A,A,7", SectorType.TRAIN,
				"2015-11-23", "00000012", "00000012", "nanjing");
		gpo[8] = new GoodsPO("0000000008", "A,A,8", SectorType.PLANE,
				"2015-11-23", "00000013", "00000013", "nanjing");
		gpo[9] = new GoodsPO("0000000009", "A,A,9", SectorType.FLEX,
				"2015-11-23", "00000010", "00000021", "nanjing");
		gpo[10] = new GoodsPO("0000000010", "A,A,10", SectorType.CAR,
				"2015-11-23", "00000011", "00000021", "nanjing");
		gpo[11] = new GoodsPO("0000000011", "A,A,11", SectorType.TRAIN,
				"2015-11-22", "00000012", "00000022", "nanjing");
		gpo[12] = new GoodsPO("0000000012", "A,A,12", SectorType.PLANE,
				"2015-11-22", "00000013", "00000023", "nanjing");
		gpo[13] = new GoodsPO("0000000013", "A,A,13", SectorType.FLEX,
				"2015-11-22", "00000020", "00000021", "nanjing");
		gpo[14] = new GoodsPO("0000000014", "A,A,14", SectorType.CAR,
				"2015-11-22", "00000021", "00000021", "nanjing");
		gpo[15] = new GoodsPO("0000000015", "A,A,15", SectorType.TRAIN,
				"2015-11-21", "00000022", "00000022", "nanjing");
		gpo[16] = new GoodsPO("0000000016", "A,A,16", SectorType.PLANE,
				"2015-11-21", "00000023", "00000023", "nanjing");

		list1.add(gpo[1].getExpressorder_id());
		list1.add(gpo[2].getExpressorder_id());
		list1.add(gpo[5].getExpressorder_id());
		list1.add(gpo[6].getExpressorder_id());
		list4.add(gpo[9].getExpressorder_id());
		list4.add(gpo[10].getExpressorder_id());
		list4.add(gpo[13].getExpressorder_id());
		list4.add(gpo[14].getExpressorder_id());
		list2.add(gpo[3].getExpressorder_id());
		list2.add(gpo[7].getExpressorder_id());
		list5.add(gpo[11].getExpressorder_id());
		list5.add(gpo[15].getExpressorder_id());
		list3.add(gpo[4].getExpressorder_id());
		list3.add(gpo[8].getExpressorder_id());
		list6.add(gpo[12].getExpressorder_id());
		list6.add(gpo[16].getExpressorder_id());

		or[0] = new StockoutOrderPO(list1, "2015-11-25", "0000008",
				"00000000000000000001", "CAR", "0000001");
		or[1] = new StockoutOrderPO(list2, "2015-11-26", "0000009",
				"00000000000000000002", "TRAIN", "0000001");
		or[2] = new StockoutOrderPO(list3, "2015-11-27", "0000010",
				"00000000000000000003", "PLANE", "0000001");
		or[3] = new StockoutOrderPO(list4, "2015-11-28", "0000011",
				"00000000000000000004", "CAR", "0000002");
		or[4] = new StockoutOrderPO(list5, "2015-11-29", "0000012",
				"00000000000000000005", "TRAIN", "0000002");
		or[5] = new StockoutOrderPO(list6, "2015-11-30", "0000013",
				"00000000000000000006", "PLANE", "0000002");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		try {
			for (int i = 0; i <= 5; i++) {
				stockout.add(or[i]);
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void testDelete() {
		for (int i = 0; i <= 5; i++) {
			stockout.delete(or[i].getID());
		}
	}

	@Test
	public void testFind() {
		try {
			StockoutOrderPO po = stockout.find("00000000000000000005",
					"0000002");
			System.out.println(po.getID());
			System.out.println(po.getOutDate() + "   "
					+ po.getExpressorder_ids().size());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetOneDay() {
		try {
			List<StockoutOrderPO> list = stockout.getOneDay("2015-11-28",
					"0000002");
			System.out.println(list.isEmpty());
			if (!list.isEmpty()) {
				for (StockoutOrderPO po : list) {
					System.out.println(po.getID());
					System.out.println(po.getIns_id() + "     "
							+ po.getOutDate() + "   "
							+ po.getExpressorder_ids().size());
					System.out.println();
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetRequired() {
		try {
			List<StockoutOrderPO> list = stockout.getRequired("2015-11-26",
					"2015-11-29", "0000002");
			System.out.println(list.isEmpty());
			if (!list.isEmpty()) {
				for (StockoutOrderPO po : list) {
					System.out.println(po.getID());
					System.out.println(po.getIns_id() + "     "
							+ po.getOutDate() + "   "
							+ po.getExpressorder_ids().size());
					System.out.println();
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSetPassed() {
		for (int i = 0; i < 6; i++) {
			try {
				stockout.setPassed(or[i].getID(), "lalala并没有什么用");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * @Test public void testUnPassed(){ for(int i = 0;i<6;i++){ try {
	 * stockout.setUnPassed(or[i].getId(), "lalala并没有什么用"); } catch
	 * (RemoteException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } } }
	 */

	@Test
	public void testGetUnpassedOrders() {
		try {
			List<StockoutOrderPO> list = stockout.getUnpassedOrders();
			System.out.println(list.isEmpty());
			if (!list.isEmpty()) {
				for (StockoutOrderPO po : list) {
					System.out.println(po.getID());
					System.out.println(po.getIns_id() + "     "
							+ po.getOutDate() + "   "
							+ po.getExpressorder_ids().size());
					System.out.println();
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUnLoadedOrder(){
		try {
			List<StockoutOrderPO> list = stockout.getUnLoadedOrders();
			if(list.isEmpty()){
				System.out.println("0");
			}else{
				System.out.println(list.size());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSetLoaded(){
		System.out.println(stockout.setLoaded("00110012015120800001"));
	}

}
