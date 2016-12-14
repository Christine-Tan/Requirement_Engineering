package gap.client.datacontroller.ApprovalDataStrategy;

import gap.client.blcontroller.AccountorReceiptController;
import gap.client.datacontroller.AppDataStrategyFactory;
import gap.common.ListInterface.Order;
import gap.common.po.PaymentListPO;
import gap.common.util.ResultMessage;

public class PaymentDataStrategy extends AppStrategy{

	public PaymentDataStrategy(AppDataStrategyFactory factory) {
		super(factory);
	}

	@Override
	public ResultMessage setPassed(Order order) {
		PaymentListPO paymentListPO = (PaymentListPO)order;
		ResultMessage rm = null;
		
		AccountorReceiptController accountorReceiptController =
				AccountorReceiptController.getInstance();
	
		rm = accountorReceiptController.handlePaymentList(paymentListPO);
		return rm;
	}

}
