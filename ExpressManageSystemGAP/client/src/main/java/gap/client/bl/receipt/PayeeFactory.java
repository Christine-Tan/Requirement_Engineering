package gap.client.bl.receipt;

import gap.client.bl.receipt.payee.FixedSalaryPayee;
import gap.client.bl.receipt.payee.Payee;
import gap.client.bl.receipt.payee.PercentSalaryPayee;
import gap.client.bl.receipt.payee.RentPayee;
import gap.client.bl.receipt.payee.TransFarePayee;
import gap.client.datacontroller.AccountorReceiptDataController;
import gap.client.vo.PayeeVO;
import gap.common.po.AccountPO;
import gap.common.po.RentPO;
import gap.common.po.SalaryPO;
import gap.common.po.TransFarePO;
import gap.common.po.UserPO;
import gap.common.util.UserType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PayeeFactory {

	private ArrayList<AccountPO> accountList;
	private ArrayList<SalaryPO> salaryList;

	private ArrayList<Payee> payeeList;
	private AccountorReceiptDataController controller;

	public PayeeFactory(ArrayList<AccountPO> accountList,
			ArrayList<SalaryPO> salaryList,
			AccountorReceiptDataController controller) {
		this.accountList = accountList;
		this.salaryList = salaryList;
		this.controller = controller;
	}

	public ArrayList<PayeeVO> getPayeeList(List<UserPO> userList,
			List<TransFarePO> transFareList, List<RentPO> rentList) {
		
		System.out.println(transFareList==null);
		
		int listSize = userList.size() + transFareList.size() + rentList.size();
		payeeList = new ArrayList<>(listSize);

		addUserPayee(userList);
		addTransFarePayee(transFareList);
		addRentPayee(rentList);

		if (payeeList.isEmpty()) {
			System.out.println("未能成功生成付款单");
			return null;
		}

		ArrayList<PayeeVO> payeeVOList = new ArrayList<>(payeeList.size());
		for (Payee payee : payeeList) {
			PayeeVO vo = payee.getVO();
			if(vo!=null){
				payeeVOList.add(payee.getVO());
			}
		}

		return payeeVOList;
	}

	private void addUserPayee(List<UserPO> userList) {

		for (UserPO oneUser : userList) {

			Iterator<SalaryPO> itr = salaryList.iterator();

			if (oneUser.getType() == UserType.DELIVERY) {
				PercentSalaryPayee percentSalaryPayee = new PercentSalaryPayee(
						oneUser, itr, controller, accountList);
				payeeList.add(percentSalaryPayee);
			} else {
				FixedSalaryPayee fixedSalaryPayee = new FixedSalaryPayee(
						oneUser, itr, accountList);
				payeeList.add(fixedSalaryPayee);
			}

		}

	}

	private void addTransFarePayee(List<TransFarePO> transFareList) {

		for (TransFarePO fare : transFareList) {
			TransFarePayee transFarePayee = new TransFarePayee(fare,
					accountList);
			payeeList.add(transFarePayee);
		}
	}

	private void addRentPayee(List<RentPO> rentList) {

		// 房租要判断是不是去年的
		for (RentPO rent : rentList) {
			if (isLastYearRent(rent)) {
				RentPayee rentPayee = new RentPayee(rent, accountList);
				payeeList.add(rentPayee);
			}
		}
	}

	private boolean isLastYearRent(RentPO po) {
		Date date = po.getLastPaidDate();
		Date today = Calendar.getInstance().getTime();
		@SuppressWarnings("deprecation")
		Date firstDay = new Date(today.getYear(), 0, 1);

		if (date.compareTo(firstDay) < 0) {
			return true;
		} else {
			return false;
		}
	}

}
