package gap.client.bl.payment;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import gap.client.blcontroller.AccountorReceiptController;
import gap.client.datacontroller.AccountorReceiptDataController;
import gap.client.datacontroller.ApprovalDataController;
import gap.client.datacontroller.ControllerFactory;
import gap.client.vo.PayeeVO;
import gap.client.vo.PaymentListVO;
import gap.common.po.PaymentListPO;
import gap.common.po.TransFarePO;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReceiptTest {
	AccountorReceiptController receiptController;
	ApprovalDataController controller;
	@Before
	public void setUp() throws Exception {
		
		receiptController = AccountorReceiptController.getInstance();
		controller = ControllerFactory.getApprovalDataController();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPaymentList() {
		PaymentListVO list = receiptController.getPaymentList();
		
		for(PayeeVO vo:list.getPayeeList()){	
			System.out.println(vo.getUserName()+"  "+vo.getAccountName());
		}
	}
	@Test
	public void testGetTransFare(){
		AccountorReceiptDataController dataController = ControllerFactory.getReceiptDataController();
		
		List<TransFarePO> list = dataController.getTransFare();
		
		for(TransFarePO po : list){
			System.out.println(po.getOrderID()+" "+po.getFare());
		}
		
	}

	@Test
	public void testSubmitPaymentList() {
		fail("Not yet implemented");
	}

	@Test
	public void testHandlePaymentList() {
		List<PaymentListPO> paymentLists= controller.getUnpassedPaymentListOrder();
		System.out.println(paymentLists.size());
		
		PaymentListPO testPO = null;
		for(PaymentListPO aPO:paymentLists){
			if(aPO.getID().equals("151220121025")){
				testPO = aPO;
				break;
			}
		}
		
		assertNotEquals(testPO, null);
		System.out.println( receiptController.handlePaymentList(testPO));
	}

	@Test
	public void testGetAccoutList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPayee() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

}
