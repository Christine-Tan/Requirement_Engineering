package gap.client.bl.receipt;

import gap.client.datacontroller.AccountorReceiptDataController;
import gap.client.datacontroller.ControllerFactory;
import gap.common.po.PayeePO;
import gap.common.po.PaymentListPO;
import gap.common.po.TradePO;
import gap.common.po.TransFarePO;
import gap.common.po.UserPO;
import gap.common.util.OrderState;
import gap.common.util.PaymentType;
import gap.common.util.ResultMessage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class PaymentHandler {
	AccountorReceiptDataController dataController;
	PaymentListPO paymentListPO;
	public PaymentHandler(PaymentListPO paymentListPO){
		this.paymentListPO = paymentListPO;	
		dataController = 
				ControllerFactory.getReceiptDataController();
	}
	
	public ResultMessage handle(){
		
		if(paymentListPO==null){
			return ResultMessage.FAILED;
		}
		
		OrderState state = dataController.isPaymentPassed(paymentListPO.getID());
		
		//如果单据已经被审批通过，就不执行
		if(state.equals(OrderState.PASSED)){
			return ResultMessage.EXISTED;
		}else{
			dataController.setPaymentPassed(paymentListPO.getID());
		}
		
		ArrayList<PayeePO> payeePOs = paymentListPO.paymentList;
		
		if(!testPayment(payeePOs)){
			return ResultMessage.FAILED;
		}
		
		ArrayList<TradePO> tradePOs = new ArrayList<>(payeePOs.size());
		for(PayeePO po:payeePOs){
			TradePO tradePO = new TradePO(po.getAccountName(), po.getMoney()*(-1));
			tradePOs.add(tradePO);
		}
		
		
		trade(tradePOs);
		setUserPaid(payeePOs);
		setRentPaid(payeePOs);
		deleteTransfare(payeePOs);
		
		
		return ResultMessage.SUCCEED;
	}
	
	private void setRentPaid(ArrayList<PayeePO> payeePOs) {

		for(PayeePO payeePO:payeePOs){
			if(isRent(payeePO)){
				dataController.setInstitePaid(payeePO.getUserID());
			}
		}
	}
	
	private boolean isRent(PayeePO payeePO){
		if(payeePO.getType()==null){
			return false;
		}
		if(payeePO.getType().equals(PaymentType.RENT)){
			return true;
		}else{
			return false;
		}
		
	}

	private void setUserPaid(ArrayList<PayeePO> payeePOs){
		for(PayeePO payeePO:payeePOs){
			if(isUser(payeePO)){
				dataController.setUserPaid(payeePO.getUserID());
			}
		}
	}
	
	private void deleteTransfare(ArrayList<PayeePO> payeePOs){
		ArrayList<TransFarePO> transFareList = new ArrayList<>();
		for(PayeePO payeePO:payeePOs){
			if(isTransfare(payeePO)){
				String orderID = payeePO.getUserID();
				double fare = payeePO.getMoney();
				String carID = payeePO.getUserName();
				TransFarePO transFarePO = new TransFarePO(fare, orderID, carID);
				transFareList.add(transFarePO);
			}
		}
		
		dataController.deleteTransFare(transFareList);
		
	}
	
	private boolean isTransfare(PayeePO payeePO){
		if(payeePO.getType().equals(PaymentType.TRANSFARE)){
			return true;
		}else{
			return false;
		}
	}
	
	private void trade(ArrayList<TradePO> tradePOs){
		if(tradePOs==null || tradePOs.isEmpty()){
			return;
		}
		
		for(TradePO po:tradePOs){
			if(po.getTradeNum()>0){
				System.out.println("付款金额不能为负");
			}
			dataController.trade(po);
		}
		
	}
	
	
	/**
	 * 测试一个用户的付款日期，假如是本月付款的，那么这个付款单不被执行
	 * @return
	 */
	private boolean testPayment(ArrayList<PayeePO> payeePOs){
		PayeePO po = null;
		
		//找一个工资付款人，没找到视为可以执行
label:	for(PayeePO aPO :payeePOs){
			if(isUser(aPO)){
				po = aPO; break label;
			}
		}
		
		if(po==null){
			return true;
		}
		
		UserPO user = dataController.getUserByID(po.getUserID());
		if(isThisMonth(user.getLastPayDate())){
			return false;
		}else{
			return true;
		}
		
	}
	
	private boolean isUser(PayeePO aPayeePO){
		PaymentType type = aPayeePO.getType();
		switch (type) {

		case DELIVERY:
		case BUSSINESSCLERK:
		case CENTERCLERK:
		case INVENTORY:
			return true;
		default:
			return false;
		}
		
	}

	private boolean isThisMonth(Date lastPayDate) {
		// TODO Auto-generated method stub
		
		java.util.Date currentDate = Calendar.getInstance().getTime();
		currentDate.setDate(1);
		
		//将时间设为本月1号，若是上次付款时间在1号之后，说明本月已经付过款
		if(lastPayDate.after(currentDate)){
			return true;
		}else{
			return false;
		}
	}

}
