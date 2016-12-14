package gap.server.data.receiptdata;

import gap.common.po.PayeePO;
import gap.common.po.PaymentListPO;
import gap.common.util.PaymentType;
import gap.server.data.util.ListMaker;
import gap.server.data.util.SQLBuilder;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * 
 * 这是个麻烦的类，其中两个内部类，第一个是制作付款单列表的，第二个是制作一个付款单中条目的列表的
 * @author 申彬
 *
 */
public class PaymentGetter {
	SQLBuilder builder = new SQLBuilder();

	public ArrayList<PaymentListPO> getPassedPayment(Calendar start,
			Calendar end) {
		// calendar比较小于0，代表时间靠前，大于0代表时间靠后
		if (start.compareTo(end) > 0) {
			System.out.println("开始时间比结束时间靠前");
			return null;
		}
		builder = new SQLBuilder();
		// 获得这个时间段内已经通过的付款单
		builder.Select("*").From(PaymentListTable.tableName)
				.Where(PaymentListTable.passed_col).EQUALS(1)
				.AND(PaymentListTable.date_col).Between(start).AND(end);

		return getPaymentBySQL(builder);
	}

	public ArrayList<PaymentListPO> getNotPassedPayment() {
		// 获得所有未审批通过的单据
		builder = new SQLBuilder();
		builder.Select("*").From(PaymentListTable.tableName)
				.Where(PaymentListTable.passed_col).EQUALS(0);

		return getPaymentBySQL(builder);
	}

	private ArrayList<PaymentListPO> getPaymentBySQL(SQLBuilder myBuilder) {
		PaymentListMaker paymentListMaker = new PaymentListMaker();
		return paymentListMaker.getList(myBuilder);
	}

	class PaymentListMaker extends ListMaker<PaymentListPO> {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		public PaymentListPO getPO(ResultSet resultSet) {
			// TODO Auto-generated method stub

			PaymentListPO po = null;
			try {
				String ID = resultSet
						.getString(PaymentListTable.paymentListID_col);
				double total = resultSet.getDouble(PaymentListTable.total_col);
				String payer = resultSet.getString(PaymentListTable.payer_col);
				Date date = resultSet.getDate(PaymentListTable.date_col);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);

				po = new PaymentListPO(ID, payer, total, calendar);
				po.paymentList = getPayeeList(ID);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return po;
		}

		public ArrayList<PayeePO> getPayeeList(String paymentListID) {
			SQLBuilder builder = new SQLBuilder();
			// 获得属于该付款单的条目
			builder.Select("*").From(PayeeTable.tableName)
					.Where(PayeeTable.paymentList_col).EQUALS(paymentListID);
			String sql = builder.getSQL();

			PayeeMaker payeeMaker = new PayeeMaker();
			return payeeMaker.getList(sql);
		}

	}

	class PayeeMaker extends ListMaker<PayeePO> {

		@Override
		public PayeePO getPO(ResultSet resultSet) {
			// TODO Auto-generated method stub
			PayeePO payee = null;

			try {
				String userID = resultSet.getString(PayeeTable.userID_col);
				String account = resultSet.getString(PayeeTable.account_col);
				double money = resultSet.getDouble(PayeeTable.money_col);
				String stringType = resultSet.getString(PayeeTable.type_col);
				// string转枚举
				PaymentType type = PaymentType.valueOf(stringType);
				int expressNumber = resultSet.getInt(PayeeTable.expressNum_col);
				String note = resultSet.getString(PayeeTable.comment_col);
				String userName = null;

				// PaymentType:
				// INVENTORY,CENTERCLERK,BUSSINESSCLERK,DELIVERY,RENT,TRANSFARE,REWARD
				switch (type) {
				case DELIVERY:
				case CENTERCLERK:
				case INVENTORY:
				case BUSSINESSCLERK:

					// 根据人员ID获得名字
					SQLBuilder builder = new SQLBuilder();
					builder.Select("username").From("user").Where("id")
							.EQUALS(userID);
					ResultSet nameSet = builder.excuteQuery();
					
					if(nameSet.next()){
						userName = nameSet.getString("username");
					}else{
						userName = "未知";
					}
				}

				payee = new PayeePO(type, userID, userName, null,
						expressNumber, money, account, note);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return payee;
		}

	}

}
