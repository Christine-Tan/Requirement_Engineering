package gap.server.data.order;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.receiptdataservice.BillOrderDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.po.BillOrderPO;
import gap.common.po.BillPO;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BillOrderDataServiceImplTest {
	BillOrderDataService billorder;

	@Before
	public void setUp() throws Exception {
		billorder = (BillOrderDataService) Naming.lookup(RMIConfig.url
				+ ServiceName.BILLORDER_DATA_SERVICE);
	}

	@Test
	public void testBillOrderDataServiceImpl() {
	}

	@Test
	public void testAdd() {
		BillPO po1 = new BillPO(10.5, "000000005");
		BillPO po2 = new BillPO(20.5, "000000006");
		List<BillPO> bills = new ArrayList<>();
		bills.add(po1);
		bills.add(po2);
		BillOrderPO po = new BillOrderPO(bills, "00100012015112600001",
				Date.valueOf("2015-11-26"));
		try {
			billorder.add(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Test
	public void testFind() {
		BillOrderPO bill;
		try {
			bill = billorder.find("00100012015112600001");
			System.out.println(bill.getID() + "," + bill.getTotal());
			for (BillPO po : bill.getBills()) {
				System.out.println(po.getCourierID() + "," + po.getMoney());
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	@Test
	public void testSetPassed() {
		try {
			billorder.setPassed("00100012015112600001", "");
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUnpassedOrders() {
		List<BillOrderPO> orders;
		try {
			orders = billorder.getUnpassedOrders();
			for (BillOrderPO bill : orders) {
				System.out.println(bill.getID() + "," + bill.getTotal());
				for (BillPO po : bill.getBills()) {
					System.out.println(po.getCourierID() + "," + po.getMoney());
				}
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Test
	public void testGetMax() throws RemoteException {
		int result = billorder.getMaxId("001000120151126");
		System.out.println(result);
	}

}
