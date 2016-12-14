package gap.client.bl.receipt.payee;

import gap.client.vo.PayeeVO;
import gap.common.po.AccountPO;
import gap.common.po.TransFarePO;
import gap.common.util.PaymentType;

import java.util.ArrayList;

public class TransFarePayee extends Payee {

	TransFarePO transFarePO;

	public TransFarePayee(TransFarePO transFarePO,
			ArrayList<AccountPO> accountList) {
		super(accountList);
		// TODO Auto-generated constructor stub
		this.transFarePO = transFarePO;
	}

	@Override
	PayeeVO makePayeeVO() {
		// TODO Auto-generated method stub
		PaymentType type = getType(transFarePO);
		String accountName = getRandomAccount();
		String note = getNote(transFarePO);
		String entry = type.getEntry();
		double money = transFarePO.getFare();

		PayeeVO vo = new PayeeVO(type, transFarePO.getOrderID(),
				transFarePO.getDriverID(), null, money, accountName, entry, note);

		return vo;
	}

}
