package gap.client.bl.receipt.payee;

import gap.client.vo.PayeeVO;
import gap.common.po.AccountPO;
import gap.common.po.RentPO;
import gap.common.po.SalaryPO;
import gap.common.po.TransFarePO;
import gap.common.po.UserPO;
import gap.common.util.PaymentType;
import gap.common.util.UserType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 * 被付款人的逻辑层对象
 * @author 申彬
 *
 */
public abstract class Payee {
	protected PayeeVO payeeVO;
	ArrayList<AccountPO> accountList;
	private static SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
	private static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	private static int accountIndex = 0;
	private static double increaseRate = 0.25;

	public Payee(ArrayList<AccountPO> accountList) {
		this.accountList = accountList;
	}

	// factory method
	abstract PayeeVO makePayeeVO();

	public PayeeVO getVO() {
		if (payeeVO == null) {
			payeeVO = makePayeeVO();
		}
		
		return payeeVO;
	}

	public PaymentType getType(Object o) {
		if (o instanceof UserPO) {
			UserPO userPO = (UserPO) o;

			// DELIVERY, BUSSINESSCLERK, CENTERCLERK, INVENTORY, ACCOUNTER,
			// MANAGER, ADMINISTRATOR;
			switch (userPO.getType()) {

			case DELIVERY:
				return PaymentType.DELIVERY;
			case BUSSINESSCLERK:
				return PaymentType.BUSSINESSCLERK;
			case CENTERCLERK:
				return PaymentType.CENTERCLERK;
			case INVENTORY:
				return PaymentType.INVENTORY;
			default:
				return null;
			}

		} else if (o instanceof RentPO) {
			return PaymentType.RENT;
		} else if (o instanceof TransFarePO) {
			return PaymentType.TRANSFARE;
		}
		return null;
	}

	public String getNote(Object o) {
		PaymentType type = getType(o);
		if (type == null) {
			return null;
		}
		Calendar now = Calendar.getInstance();

		switch (type) {

		case DELIVERY:
		case INVENTORY:
		case CENTERCLERK:
		case BUSSINESSCLERK: {
			// 工资月份
			String month = monthFormat.format(now.getTime());
			return month + "月份工资";
		}

		case RENT:
			// 房租年份
			String year = yearFormat.format(now.getTime());
			return year + "年房租";
		case TRANSFARE:
			// 运费单号
			TransFarePO transFarePO = (TransFarePO) o;
			return transFarePO.getOrderID();
		}
		return null;

	}

	protected SalaryPO getSalaryPO(Iterator<SalaryPO> salaryItr, UserPO userPO) {
		SalaryPO salaryPO = null;
		UserType userType = userPO.getType();
		while (salaryItr.hasNext()) {
			SalaryPO aPO = salaryItr.next();
			
			if(aPO.getType() == null){
				continue;
			}
			
			if (aPO.getType().equals(userType)) {
				salaryPO = aPO;
				break;
			}
		}

		if (salaryPO == null) {
			System.out.println("未找到" + userType + "的策略");
			return null;
		}
		return salaryPO;
	}

	protected String getRandomAccount() {
		// 按一定概率将银行账户序号增加1.
		if (Math.random() < increaseRate) {
			accountIndex = (accountIndex + 1) % accountList.size();
		}

		AccountPO account = accountList.get(accountIndex);
		return account.getName();
	}

}
