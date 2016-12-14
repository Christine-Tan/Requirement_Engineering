package gap.client.bl.receipt.payee;

import gap.client.vo.PayeeVO;
import gap.common.po.AccountPO;
import gap.common.po.SalaryPO;
import gap.common.po.UserPO;
import gap.common.util.PaymentType;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * 固定工资付款人
 * @author 申彬
 *
 */
public class FixedSalaryPayee extends Payee {
	UserPO userPO;
	Iterator<SalaryPO> salaryItr;

	public FixedSalaryPayee(UserPO userPO, Iterator<SalaryPO> salaryItr,
			ArrayList<AccountPO> accountList) {
		super(accountList);

		this.userPO = userPO;
		this.salaryItr = salaryItr;
	}

	@Override
	PayeeVO makePayeeVO() {
		SalaryPO salaryPO = getSalaryPO(salaryItr, userPO);
		PaymentType type = getType(userPO);
		
		//类型为空，说明是管理员、总经理或者财务人员
		if(type==null){
			return null;
		}
		
		double salary = salaryPO.getSalary();

		String accountName = getRandomAccount();

		// entry是条目的意思
		PayeeVO vo = new PayeeVO(type, userPO.getUserId(), userPO.getName(),
				null, salary, accountName, type.getEntry(), getNote(userPO));
		return vo;

	}

}
