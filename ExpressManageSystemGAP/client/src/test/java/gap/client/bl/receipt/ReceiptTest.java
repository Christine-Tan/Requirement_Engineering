package gap.client.bl.receipt;

import static org.junit.Assert.fail;
import gap.client.blcontroller.AccountorReceiptController;
import gap.client.datacontroller.ApprovalDataController;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.NetModule;
import gap.client.vo.PayeeVO;
import gap.common.po.BillOrderPO;
import gap.common.util.PaymentType;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReceiptTest {
	AccountorReceiptController receiptController;
	ApprovalDataController approvalController;

	@Before
	public void setUp() throws Exception {
		NetModule.connect();
		receiptController = AccountorReceiptController.getInstance();
		approvalController = ControllerFactory.getApprovalDataController();
	}

	@Test
	public void testGetPaymentList() {
		receiptController.getPaymentList();
	}

	@Test
	public void testSubmitPaymentList() {
	}

	@Test
	public void testHandlePaymentList() {
	}

	@Test
	public void testAddPayee() {
		receiptController.addPayee(new PayeeVO(PaymentType.CENTERCLERK, "000000001",
				"yyf", Calendar.getInstance(), 100.0, "账户1", "", ""));
	}

	@Test
	public void testDeletePayee() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyPayee() {
		fail("Not yet implemented");
	}

	@Test
	public void testPaymentExcel() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBillOrderByDateOrIns() {
		fail("Not yet implemented");
	}

	@Test
	public void testHandleBillOrder() {
		List<BillOrderPO> billOrderPOs = approvalController.getUnpassedBillOrder();
		for(BillOrderPO aOrder:billOrderPOs){
			System.out.println(aOrder.getID());
		}
		
		receiptController.handleBillOrder(billOrderPOs.get(0));
		
	}

}
