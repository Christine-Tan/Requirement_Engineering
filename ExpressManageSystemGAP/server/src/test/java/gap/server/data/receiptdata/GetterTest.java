package gap.server.data.receiptdata;

import static org.junit.Assert.assertEquals;
import gap.common.po.PaymentListPO;
import gap.server.initial.NetInitial;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetterTest {
	PaymentGetter getter;
	Calendar start;
	Calendar end;

	@Before
	public void setUp() throws Exception {
		NetInitial.initial();
		getter = new PaymentGetter();

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
	public void testGetPassedPayment() {
		ArrayList<PaymentListPO> lists;
		lists = getter.getPassedPayment(end, start);

		assertEquals(lists, null);

		lists = getter.getPassedPayment(start, end);
		System.out.println(lists);

	}

}
