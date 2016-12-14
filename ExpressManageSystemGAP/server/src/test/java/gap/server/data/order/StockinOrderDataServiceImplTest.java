package gap.server.data.order;

import gap.common.po.GoodsPO;
import gap.common.po.StockinOrderPO;
import gap.common.util.SectorType;
import gap.server.initial.NetInitial;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StockinOrderDataServiceImplTest {
	StockinOrderDataServiceImpl stockin;
	GoodsPO[] gpo = new GoodsPO[17];
	ArrayList<GoodsPO> list1 = new ArrayList<GoodsPO>();
	ArrayList<GoodsPO> list2 = new ArrayList<GoodsPO>();
	ArrayList<GoodsPO> list3 = new ArrayList<GoodsPO>();
	ArrayList<GoodsPO> list4 = new ArrayList<GoodsPO>();
	ArrayList<GoodsPO> list5 = new ArrayList<GoodsPO>();
	ArrayList<GoodsPO> list6 = new ArrayList<GoodsPO>();

	StockinOrderPO[] or = new StockinOrderPO[6];

	@Before
	public void setUp() throws Exception {
		NetInitial.initial();
		stockin = new StockinOrderDataServiceImpl();
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
				"2015-11-23", "00000010", "00000011", "nanjing");
		gpo[10] = new GoodsPO("0000000010", "A,A,10", SectorType.CAR,
				"2015-11-23", "00000011", "00000011", "nanjing");
		gpo[11] = new GoodsPO("0000000011", "A,A,11", SectorType.TRAIN,
				"2015-11-22", "00000012", "00000012", "nanjing");
		gpo[12] = new GoodsPO("0000000012", "A,A,12", SectorType.PLANE,
				"2015-11-22", "00000013", "00000013", "nanjing");
		// gpo[13] = new GoodsPO("0000000013", "A,A,13", SectorType.FLEX,
		// "2015-11-22", "00000020", "00000021", "nanjing");
		// gpo[14] = new GoodsPO("0000000014", "A,A,14", SectorType.CAR,
		// "2015-11-22", "00000021", "00000021", "nanjing");
		// gpo[15] = new GoodsPO("0000000015", "A,A,15", SectorType.TRAIN,
		// "2015-11-21", "00000022", "00000022", "nanjing");
		// gpo[16] = new GoodsPO("0000000016", "A,A,16", SectorType.PLANE,
		// "2015-11-21", "00000023", "00000023", "nanjing");

		list1.add(gpo[1]);
		list1.add(gpo[2]);
		list1.add(gpo[3]);
		list2.add(gpo[4]);
		list2.add(gpo[5]);
		list2.add(gpo[6]);
		list3.add(gpo[7]);
		list3.add(gpo[8]);
		list3.add(gpo[9]);
		list3.add(gpo[10]);
		list4.add(gpo[11]);
		list4.add(gpo[12]);
		// list5.add(gpo[13]);
		// list5.add(gpo[14]);
		// list6.add(gpo[15]);
		// list6.add(gpo[16]);

		or[0] = new StockinOrderPO(list1, "2015-11-25", "00000000000000000001",
				"0000001");
		or[1] = new StockinOrderPO(list2, "2015-11-24", "00000000000000000002",
				"0000001");
		or[2] = new StockinOrderPO(list3, "2015-11-23", "00000000000000000003",
				"0000001");
		or[3] = new StockinOrderPO(list4, "2015-11-22", "00000000000000000004",
				"0000001");
		// or[4] = new StockinOrderPO(list5, "2015-11-22",
		// "00000000000000000005", "0000002");
		// or[5] = new StockinOrderPO(list6, "2015-11-21",
		// "00000000000000000006", "0000002");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		try {
			for (int i = 0; i <= 3; i++) {
				stockin.add(or[i]);
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFind() {
		try {
			StockinOrderPO po = stockin.find("00000000000000000004", "0000001");
			System.out.println(po.getInDate() + "   " + po.getGoods().size());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		for (int i = 0; i < 6; i++) {
			stockin.delete(or[i].getID());
		}
	}

	@Test
	public void testGetOneDay() {

		try {
			List<StockinOrderPO> list = stockin.getOneDay("2015-11-22",
					"0000002");
			if (!list.isEmpty()) {
				for (StockinOrderPO po : list) {
					System.out.println(po.getID());
					System.out.println(po.getIns_id() + "     "
							+ po.getInDate() + "   " + po.getGoods().size());
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
			List<StockinOrderPO> list = stockin.getRequired("2015-11-21",
					"2015-11-25", "0000002");
			System.out.println(list.isEmpty());
			if (!list.isEmpty()) {
				for (StockinOrderPO po : list) {
					System.out.println(po.getID());
					System.out.println(po.getIns_id() + "     "
							+ po.getInDate() + "   " + po.getGoods().size());
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
				stockin.setPassed(or[i].getID(), "lalala并没有什么用");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testGetUnpassedOrders() {
		try {
			List<StockinOrderPO> list = stockin.getUnpassedOrders();
			System.out.println(list.isEmpty());
			if (!list.isEmpty()) {
				for (StockinOrderPO po : list) {
					System.out.println(po.getID());
					System.out.println(po.getIns_id() + "     "
							+ po.getInDate() + "   " + po.getGoods().size());
					System.out.println();
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
