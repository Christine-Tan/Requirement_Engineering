package gap.common.dataservice.receiptdataservice;

import gap.common.po.PaymentListPO;
import gap.common.util.OrderState;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

public interface PaymentdataService extends Remote {

	public ResultMessage submitPayment(PaymentListPO paymentListPO)
			throws RemoteException;

	public ArrayList<PaymentListPO> getNotPassedPayment()
			throws RemoteException;

	public ArrayList<PaymentListPO> getPassedPayment(Calendar begin,
			Calendar end) throws RemoteException;

	public OrderState isPaymentPassed(String paymentID) throws RemoteException;

	public ResultMessage setPassed(String paymentID) throws RemoteException;

}
