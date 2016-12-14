package gap.server.data.receiptdata;

import gap.common.po.PayeePO;
import gap.common.po.PaymentListPO;
import gap.common.util.ResultMessage;
import gap.server.data.util.SQLBuilder;

public class PaymentSubmitter {

	SQLBuilder builder = new SQLBuilder();

	public ResultMessage submit(PaymentListPO list) {

		builder.InsertInto(PaymentListTable.tableName,
				PaymentListTable.paymentListID_col, PaymentListTable.total_col,
				PaymentListTable.payer_col, PaymentListTable.date_col,
				PaymentListTable.passed_col).Values(list.getID(),
				list.getTotal(), list.getPayer(), list.getDate(), 0);

		boolean pass = builder.excute();
		if (!pass) {
			return ResultMessage.FAILED;
		}

		for (PayeePO payee : list.paymentList) {
			builder.InsertInto(PayeeTable.tableName, PayeeTable.userID_col,
					PayeeTable.account_col, PayeeTable.money_col,
					PayeeTable.type_col, PayeeTable.comment_col,
					PayeeTable.paymentList_col).Values(payee.getUserID(),
					payee.getAccountName(), payee.getMoney(), payee.getType(),
					payee.getNote(), list.getID());

			System.out.println(builder.toString());

			pass = builder.excute();
			if (!pass) {
				return ResultMessage.FAILED;
			}
		}

		return ResultMessage.SUCCEED;
	}
}
