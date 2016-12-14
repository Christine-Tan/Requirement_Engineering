package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.accountDataService;
import static gap.client.datacontroller.NetModule.billorderdataservice;
import static gap.client.datacontroller.NetModule.expressorderdataservice;
import static gap.client.datacontroller.NetModule.institutiondataservice;
import static gap.client.datacontroller.NetModule.logdataservice;
import static gap.client.datacontroller.NetModule.paymentdataService;
import static gap.client.datacontroller.NetModule.rentdataservice;
import static gap.client.datacontroller.NetModule.salarydataservice;
import static gap.client.datacontroller.NetModule.transFareDataService;
import static gap.client.datacontroller.NetModule.userdataservice;
import gap.common.po.AccountPO;
import gap.common.po.BillOrderPO;
import gap.common.po.InstitutionPO;
import gap.common.po.LogPO;
import gap.common.po.PaymentListPO;
import gap.common.po.RentPO;
import gap.common.po.SalaryPO;
import gap.common.po.TradePO;
import gap.common.po.TransFarePO;
import gap.common.po.UserPO;
import gap.common.util.OrderState;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 和财务人员相关的收付款单的data层接口，支持的需求有：制定付款单，处理收款单、付款单，按天按营业厅查收款单。
 * @author 申彬
 *
 */
public class AccountorReceiptDataController {

	protected AccountorReceiptDataController() {

	}

	// accountData的接口
	public boolean trade(TradePO tradePO) {
		try {
			return accountDataService.trade(tradePO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<AccountPO> getAccountList() {
		try {
			return accountDataService.getAccountList();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// PaymentData的接口
	public ResultMessage submitPayment(PaymentListPO paymentListPO) {
		try {
			return paymentdataService.submitPayment(paymentListPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
	}

	public ArrayList<PaymentListPO> getPassedPayment(Calendar begin,
			Calendar end) {
		try {
			return paymentdataService.getPassedPayment(begin, end);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public OrderState isPaymentPassed(String paymentID) {
		try {
			return paymentdataService.isPaymentPassed(paymentID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage setPaymentPassed(String paymentID) {
		try {
			return paymentdataService.setPassed(paymentID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// BillOrder的接口
	public ResultMessage setBillOrderPassed(String order_id) {
		try {
			return billorderdataservice.setPassed(order_id, null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<BillOrderPO> getPassedBill(Calendar oneDay, String institutionID) {
		try {
			return billorderdataservice.getPassedOrder(oneDay, institutionID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public OrderState isBillPassed(String orderID) {
		try {
			return billorderdataservice.isOrderPassed(orderID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// expressOrderData的接口
	public double getDeliveryMoney(String date, String delivery_id) {
		try {
			return expressorderdataservice.getDeliveryMoney(date, delivery_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	// userdate的接口
	public List<UserPO> findUnpaidUser(Date date) {
		try {
			return userdataservice.findUnpaidUser(date);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public UserPO getUserByID(String id){
		try {
			return userdataservice.findById(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage setUserPaid(String user_id) {
		try {
			return userdataservice.setPaid(user_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// transFareData
	public List<TransFarePO> getTransFare() {
		System.out.println("get TransFare!");
		try {
			return transFareDataService.getTransFare();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteTransFare(List<TransFarePO> transFareList) {
		try {
			return transFareDataService.deleteTransFare(transFareList);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// rentData
	public List<RentPO> getAllRentPO() {
		try {
			return rentdataservice.getAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage setInstitePaid(String institution) {
		try {
			return rentdataservice.setPaid(institution);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// Salary
	public List<SalaryPO> getAllSalaryPO() {
		if(salarydataservice==null){
			System.out.println("salaryDataServoce == null");
			return null;
		}
		
		try {
			return salarydataservice.getAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// Institution
	public List<InstitutionPO> getAllInstitute(){
		try {
			return institutiondataservice.getAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//Log
	public ResultMessage addLog(LogPO logPO){
		boolean isSucceed = false;
		try {
			isSucceed = logdataservice.addLog(logPO);
			
			if(isSucceed){
				return ResultMessage.SUCCEED;
			}else{
				return ResultMessage.FAILED;
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		
	}
	
}
