package gap.server.data.inventorydata;

import gap.server.initial.NetInitial;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WareHouseDataServiceImplTest {
	WareHouseDataServiceImpl wareHouse;

	@Before
	public void setUp() throws Exception {
		NetInitial.initial();
		wareHouse = new WareHouseDataServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		try {
			wareHouse.add("0011001", "00110010");
			wareHouse.add("0011001", "00110011");
			wareHouse.add("0011001", "00110012");
			wareHouse.add("0011001", "00110013");
			// wareHouse.add("0000002", "00000020");
			// wareHouse.add("0000002", "00000021");
			// wareHouse.add("0000002", "00000022");
			// wareHouse.add("0000002", "00000023");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			wareHouse.delete("0000001");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
