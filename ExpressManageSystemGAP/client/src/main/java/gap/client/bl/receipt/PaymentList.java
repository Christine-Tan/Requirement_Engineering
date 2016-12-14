package gap.client.bl.receipt;

import gap.client.datacontroller.AccountorReceiptDataController;
import gap.client.util.LocalInfo;
import gap.client.vo.PayeeVO;
import gap.client.vo.PaymentListVO;
import gap.common.po.AccountPO;
import gap.common.po.RentPO;
import gap.common.po.SalaryPO;
import gap.common.po.TransFarePO;
import gap.common.po.UserPO;
import gap.common.util.ResultMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 
 * 逻辑层的付款单对象，创建时会读取数据库信息
 * @author 申彬
 *
 */
public class PaymentList {

	ArrayList<PayeeVO> payeeList;
	AccountorReceiptDataController dataController;
	private ArrayList<AccountPO> accounts;

	List<SalaryPO> salaryPOs;

	PaymentListVO listVO;

	public PaymentList(AccountorReceiptDataController dataController) {
		// payeeList = new ArrayList<>();
		this.dataController = dataController;
		salaryPOs = dataController.getAllSalaryPO();

		if (accounts == null) {
			accounts = dataController.getAccountList();
		}
	}

	/**
	 * 读取数据库，创建付款单草稿
	 * @return
	 */
	public PaymentListVO creatPaymentList() {
		// ArrayList<AccountPO> accountList,ArrayList<SalaryPO> salaryList,
		// AccountorReceiptDataController controller
		ArrayList<SalaryPO> salaryList = (ArrayList<SalaryPO>) dataController
				.getAllSalaryPO();

		PayeeFactory factory = new PayeeFactory(accounts, salaryList,
				dataController);

		Calendar today = Calendar.getInstance();

		@SuppressWarnings("deprecation")
		java.sql.Date date = new java.sql.Date(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH), today.get(Calendar.DATE));

		ArrayList<UserPO> userList = (ArrayList<UserPO>) dataController
				.findUnpaidUser(date);
		ArrayList<TransFarePO> transFareList = (ArrayList<TransFarePO>) dataController
				.getTransFare();
		ArrayList<RentPO> rentList = (ArrayList<RentPO>) dataController
				.getAllRentPO();

		payeeList = factory.getPayeeList(userList, transFareList, rentList);
		double sum = computeTotal(payeeList);

		listVO = new PaymentListVO(getPaymentID(), LocalInfo.getName(),
				sum, today);
		listVO.setPayeeList(payeeList);

		return listVO;

	}
	
	//根据当前时间生成付款单ID
	private String getPaymentID(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		return format.format(calendar.getTime());
	}

	private double computeTotal(ArrayList<PayeeVO> list) {
		if (list == null || list.isEmpty()) {
			System.out.println("付款单未能成功生成");
			return 0;
		}

		double sum = 0;
		for (PayeeVO payeeVO : list) {
			sum += payeeVO.getMoney();
		}

		return sum;
	}

	public ResultMessage addPayee(PayeeVO payeeVO) {
		String accountName = payeeVO.getAccountName();
		AccountPO account = null;
		for (AccountPO oneAccount : accounts) {
			if (oneAccount.getName().equals(accountName)) {
				account = oneAccount;
				break;
			}
		}

		if (account == null) {
			System.out.println("该账户不存在");
			return ResultMessage.NOTFOUND;
		} else {
			if (isBalanceEnough(payeeVO, account)) {
				payeeList.add(payeeVO);
				return ResultMessage.SUCCEED;
			} else {
				return ResultMessage.FAILED;
			}
		}

	}

	private boolean isBalanceEnough(PayeeVO vo, AccountPO accountPO) {
		if (accountPO.getBalance() > vo.getMoney()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 删除
	 * @param payeeVO
	 * @return
	 */
	public ResultMessage deletePayee(PayeeVO payeeVO) {
		if (payeeList.remove(payeeVO)) {
			return ResultMessage.SUCCEED;
		} else {
			return ResultMessage.NOTFOUND;
		}

	}

	//
	/**
	 * 修改
	 * @param payeeVO
	 * @return
	 */
	public ResultMessage modifyPayee(PayeeVO payeeVO) {
		int index = payeeList.indexOf(payeeVO);
		payeeList.set(index, payeeVO);

		return ResultMessage.SUCCEED;
	}

}
