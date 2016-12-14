package gap.client.bl.receipt.payee;

import gap.client.vo.PayeeVO;
import gap.common.po.AccountPO;
import gap.common.po.RentPO;
import gap.common.util.PaymentType;

import java.util.ArrayList;

public class RentPayee extends Payee {

	RentPO rentPO;

	public RentPayee(RentPO rentPO, ArrayList<AccountPO> accountList) {
		super(accountList);
		// TODO Auto-generated constructor stub
		this.rentPO = rentPO;
	}

	@Override
	PayeeVO makePayeeVO() {
		// TODO Auto-generated method stub
		PaymentType type = getType(rentPO);
		String accountName = getRandomAccount();
		String note = getNote(rentPO);
		String entry = type.getEntry();
		double money = rentPO.getMoney();

		PayeeVO vo = new PayeeVO(type, rentPO.getInstitution(),
				rentPO.getInstitution(), null, money, accountName, entry, note);

		return vo;
	}

}
