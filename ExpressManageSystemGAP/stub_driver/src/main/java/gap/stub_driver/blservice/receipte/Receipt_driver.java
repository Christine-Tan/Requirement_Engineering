package gap.stub_driver.blservice.receipte;

import gap.client.blservice.accountorReceiptblservice.AccountorReceiptService;
import gap.client.vo.BillOrderVO;
import gap.client.vo.PaymentListVO;

public class Receipt_driver {

	public void drive(AccountorReceiptService stub){
		BillOrderVO billOrderVO = stub.getBillOrder();
		System.out.println("get bill order!");
		PaymentListVO paymentListVO = stub.getPaymentList();
		System.out.println("get payment list");
		System.out.println(stub.submitBillOrder(billOrderVO));
		System.out.println(stub.submitPaymentList(paymentListVO));
		
	
	}
	
	public static void main(String[] args) {
		new Receipt_driver().drive(new Receipt_stub());
	}
	
}
