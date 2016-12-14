package gap.client.bl.receipt;

import gap.client.datacontroller.AccountorReceiptDataController;
import gap.client.datacontroller.ControllerFactory;
import gap.common.po.AccountPO;
import gap.common.po.BillOrderPO;
import gap.common.po.BillPO;
import gap.common.po.TradePO;
import gap.common.util.OrderState;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class BillOrderHandler {
	AccountorReceiptDataController controller;
	BillOrderPO billOrderPO;
	public BillOrderHandler(BillOrderPO billOrderPO){
		this.billOrderPO = billOrderPO;
		controller = ControllerFactory.getReceiptDataController();
	}
	
	public ResultMessage handle(){
		if(billOrderPO==null){
			return ResultMessage.FAILED;
		}
		
		String orderID = billOrderPO.getID();
		OrderState state = controller.isBillPassed(orderID);
		
		if(state==null || state.equals(OrderState.PASSED)){
			System.out.println("收款单不存在或已审批");
			return ResultMessage.FAILED;
		}
	
		controller.setBillOrderPassed(orderID);
		ResultMessage result = trade();
		return result;
	}

	private ResultMessage trade() {
		List<BillPO> billPOs = billOrderPO.getBills();
		if(billPOs==null){
			System.out.println("收款单list为null");
			return ResultMessage.FAILED;
		}
		
		ArrayList<TradePO> tradePOs = new ArrayList<>(billPOs.size());
		ArrayList<AccountPO> accountPOs = controller.getAccountList();
		
		if(accountPOs==null || accountPOs.size()==0){
			System.out.println("银行账户列表为空");
		}
		
		String firstAccount = accountPOs.get(0).getName();
		
		for(BillPO billPO:billPOs){
			double money = billPO.getMoney();
			if(money<0){
				System.out.println(billPO.getCourierID()+"的交易额为负："+money+"元");
			}
			TradePO aTradePO = new TradePO(firstAccount, money);
			controller.trade(aTradePO);
		}
		
		return ResultMessage.SUCCEED;
		
	}
	
}
