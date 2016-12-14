package gap.client.datacontroller.ApprovalDataStrategy;

import gap.client.blcontroller.AccountorReceiptController;
import gap.client.datacontroller.AppDataStrategyFactory;
import gap.common.ListInterface.Order;
import gap.common.po.BillOrderPO;
import gap.common.util.ResultMessage;

public class BillDataStrategy extends AppStrategy{

	public BillDataStrategy(AppDataStrategyFactory factory) {
		super(factory);

	}

	@Override
	public ResultMessage setPassed(Order order) {
		ResultMessage rm = null;
		AccountorReceiptController accountDataService = AccountorReceiptController.getInstance();
		BillOrderPO aBillOrderPO = (BillOrderPO)order;
		rm = accountDataService.handleBillOrder(aBillOrderPO);
		return rm;
	}

}
