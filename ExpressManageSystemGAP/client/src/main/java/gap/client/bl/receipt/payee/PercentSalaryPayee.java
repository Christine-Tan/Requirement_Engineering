package gap.client.bl.receipt.payee;

import gap.client.datacontroller.AccountorReceiptDataController;
import gap.client.vo.PayeeVO;
import gap.common.po.AccountPO;
import gap.common.po.SalaryPO;
import gap.common.po.UserPO;
import gap.common.util.PaymentType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 * 提成人员payee
 * @author 申彬
 *
 */
public class PercentSalaryPayee extends Payee {
	UserPO userPO;
	Iterator<SalaryPO> salaryItr;
	AccountorReceiptDataController controller;

	public PercentSalaryPayee(UserPO userPO, Iterator<SalaryPO> salaryItr,
			AccountorReceiptDataController controller,
			ArrayList<AccountPO> accountList) {
		super(accountList);
		this.userPO = userPO;
		this.salaryItr = salaryItr;
		this.controller = controller;
	}

	@Override
	PayeeVO makePayeeVO() {
		// 快递员提成比
		SalaryPO percent = getSalaryPO(salaryItr, userPO);

		double sumMoney = computeDeliveryMoney(userPO.getUserId());
		double salary = sumMoney * percent.getSalary();
		PaymentType type = getType(userPO);

		String accountName = getRandomAccount();

		// (PaymentType type, String userID, String userName,
		// Calendar lastPaydate,double money,
		// String accountName,String entry,String note)
		PayeeVO vo = new PayeeVO(type, userPO.getUserId(), userPO.getName(),
				null, salary, accountName, type.getEntry(), getNote(userPO));
		return vo;

	}

	/**
	 * 根据快递员编号算他从上月到现在的快递费
	 * @param userID
	 * @return
	 */
	double computeDeliveryMoney(String userID) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		double sumMoney = 0;

		Calendar start = Calendar.getInstance();
		start.setTime(userPO.getLastPayDate());

		Calendar end = Calendar.getInstance();

		// 当start的时间比end的时间靠前
		while (start.compareTo(end) < 0) {
			String stringDate = format.format(start.getTime());
			double oneDayMoney = controller
					.getDeliveryMoney(stringDate, userID);
			sumMoney += oneDayMoney;

			start.add(Calendar.DATE, 1);
		}

		return sumMoney;
	}

}
