package gap.stub_driver.blservice.receipte;

import gap.client.blservice.accountorReceiptblservice.AccountorReceiptService;
import gap.client.vo.BillOrderVO;
import gap.client.vo.PaymentListVO;
import gap.common.util.ResultMessage;

public class Receipt_stub implements AccountorReceiptService {

	@Override
	public PaymentListVO getPaymentList() {
		// TODO Auto-generated method stub
		return new PaymentListVO();
	}




	@Override
	public ResultMessage paymetnExcel(PaymentListVO paymentListVO) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCEED;
	}

	@Override
	public BillOrderVO getBillOrder() {
		// TODO Auto-generated method stub
		return new BillOrderVO();
	}



	@Override
	public ResultMessage submitPaymentList(PaymentListVO paymentListVO) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage submitBillOrder(BillOrderVO billOrderVO) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCEED;
	}

}
