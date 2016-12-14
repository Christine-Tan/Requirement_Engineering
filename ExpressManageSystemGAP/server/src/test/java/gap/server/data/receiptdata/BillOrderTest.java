package gap.server.data.receiptdata;

import gap.common.dataservice.receiptdataservice.BillOrderDataService;
import gap.common.po.BillOrderPO;
import gap.common.util.OrderState;
import gap.server.initial.NetInitial;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BillOrderTest {
	BillOrderDataService billOrderData;
	Calendar start;
	Calendar end;

	@Before
	public void setUp() throws Exception {
		NetInitial.initial();
		billOrderData = BillOrderDataServiceImpl.getInstance();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startString = "2015-11-24";
		String endString = "2015-11-26";

		start = Calendar.getInstance();
		start.setTime(format.parse(startString));
		;

		end = Calendar.getInstance();
		end.setTime(format.parse(endString));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPassedOrderCalendarCalendar() throws RemoteException {
		List<BillOrderPO> orders = billOrderData.getPassedOrder(start, end);
		System.out.println(orders);
	}

	@Test
	public void testGetPassedOrderCalendarString() throws RemoteException {
		List<BillOrderPO> orders = billOrderData.getPassedOrder(end, "101");
		System.out.println(orders);
		orders = billOrderData.getPassedOrder(end, "0010001");
		System.out.println(orders);
		orders = billOrderData.getPassedOrder(null, "0010001");
		System.out.println(orders);

	}

	@Test
	public void testIsOrderPassed() throws RemoteException {
		OrderState state = billOrderData.isOrderPassed("00100012015112600001");
		System.out.println(state);
		state = billOrderData.isOrderPassed("00100012015112600002");
		System.out.println(state);
	}

}
