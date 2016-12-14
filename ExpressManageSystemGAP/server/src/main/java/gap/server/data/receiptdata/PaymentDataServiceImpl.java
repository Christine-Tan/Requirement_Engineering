package gap.server.data.receiptdata;

import gap.common.dataservice.receiptdataservice.PaymentdataService;
import gap.common.po.PaymentListPO;
import gap.common.util.OrderState;
import gap.common.util.ResultMessage;
import gap.server.data.util.SQLBuilder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class PaymentDataServiceImpl extends UnicastRemoteObject implements
		PaymentdataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static PaymentdataService receiptdataService;
	PaymentSubmitter paymentSubmitter = new PaymentSubmitter();
	PaymentGetter paymentGetter = new PaymentGetter();

	private PaymentDataServiceImpl() throws RemoteException {
		super();
	}

	public static PaymentdataService getInstance() {
		if (receiptdataService == null) {
			try {
				receiptdataService = new PaymentDataServiceImpl();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return receiptdataService;
	}

	public ResultMessage submitPayment(PaymentListPO paymentListPO)
			throws RemoteException {
		// TODO Auto-generated method stub
		ResultMessage result = paymentSubmitter.submit(paymentListPO);

		return result;
	}

	public ArrayList<PaymentListPO> getNotPassedPayment() {
		ArrayList<PaymentListPO> lists = paymentGetter.getNotPassedPayment();
		return lists;
	}

	public ArrayList<PaymentListPO> getPassedPayment(Calendar begin,
			Calendar end) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PaymentListPO> lists = paymentGetter.getPassedPayment(begin,
				end);
		return lists;
	}

	public OrderState isPaymentPassed(String paymentID) {
		SQLBuilder builder = new SQLBuilder();
		builder.Select(PaymentListTable.passed_col)
				.From(PaymentListTable.tableName)
				.Where(PaymentListTable.paymentListID_col).EQUALS(paymentID);
		ResultSet set = builder.excuteQuery();
		try {
			if (!set.next()) {
				return null;
			}
			boolean passed = set.getBoolean(PaymentListTable.passed_col);
			if (passed) {
				return OrderState.PASSED;
			} else {
				return OrderState.SUBMITTED;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public ResultMessage setPassed(String paymentID) {
		OrderState state = isPaymentPassed(paymentID);

		if (state == null) {
			return ResultMessage.NOTFOUND;
		}
		if (state == OrderState.PASSED) {
			return ResultMessage.FAILED;
		} else {
			SQLBuilder builder = new SQLBuilder();
			builder.Update(PaymentListTable.tableName)
					.Set(PaymentListTable.passed_col).Assign(1)
					.Where(PaymentListTable.paymentListID_col)
					.EQUALS(paymentID);
			builder.excute();
			return ResultMessage.SUCCEED;
		}
	}
}
