package gap.server.data.receiptdata;

import gap.common.po.PayeePO;
import gap.common.po.PaymentListPO;
import gap.common.util.PaymentType;
import gap.server.initial.NetInitial;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PaymentSubmitterTest {
	PaymentListPO list;
	PaymentSubmitter submitter;

	@Before
	public void setUp() throws Exception {
		NetInitial.initial();

		submitter = new PaymentSubmitter();

		list = new PaymentListPO("201512022219", "小春", 1380,
				Calendar.getInstance());
		list.paymentList
				.add(new PayeePO(PaymentType.BUSSINESSCLERK, "000000041", "小李",
						Calendar.getInstance(), 0, 2000, "账户1", "2月"));
		list.paymentList.add(new PayeePO(PaymentType.DELIVERY, "000000021",
				"小王", Calendar.getInstance(), 10, 2000, "账户1", "2月"));
		list.paymentList.add(new PayeePO(PaymentType.RENT, "南京房租", "南京房租",
				Calendar.getInstance(), 0, 2000, "账户1", "2月"));

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSubmit() {
		System.out.println(submitter.submit(list));
	}

}
