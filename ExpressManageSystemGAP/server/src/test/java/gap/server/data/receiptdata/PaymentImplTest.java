package gap.server.data.receiptdata;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import gap.common.dataservice.ServiceName;
import gap.common.dataservice.receiptdataservice.PaymentdataService;
import gap.common.netconfig.RMIConfig;
import gap.common.po.PaymentListPO;
import gap.common.util.OrderState;
import gap.common.util.ResultMessage;
import gap.server.initial.NetInitial;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PaymentImplTest {
	PaymentdataService payment;
	Calendar start;
	Calendar end;

	@Before
	public void setUp() throws Exception {
		NetInitial.main(null);

		payment = (PaymentdataService) Naming.lookup(RMIConfig.url
				+ ServiceName.PAYMENT_DATA_SERVICE);

		assertNotEquals(payment, null);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startString = "2015-12-01";
		String endString = "2015-12-03";

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
	public void testSubmitPayment() {

	}

	@Test
	public void testGetNotPassedPayment() {
		try {
			ArrayList<PaymentListPO> lists = payment.getNotPassedPayment();
			System.out.println(lists);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			fail("exception");
			e.printStackTrace();
		}
	}

	@Test
	public void testGetPassedPayment() {
		try {
			ArrayList<PaymentListPO> lists = payment.getPassedPayment(start,
					end);
			System.out.println(lists);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testIsPaymentPassed() {
		try {
			OrderState state = payment.isPaymentPassed("201512022219");
			System.out.println(state);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSetPassed() {
		try {
			ResultMessage message = payment.setPassed("201512031703"); // failed
			System.out.println(message);

			message = payment.setPassed("211512031703"); // not found
			System.out.println(message);

			message = payment.setPassed("201512022219"); // succeed
			System.out.println(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
