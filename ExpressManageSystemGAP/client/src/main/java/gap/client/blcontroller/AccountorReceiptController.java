package gap.client.blcontroller;

import gap.client.bl.receipt.BillOrderHandler;
import gap.client.bl.receipt.PaymentHandler;
import gap.client.bl.receipt.PaymentList;
import gap.client.blservice.accountorReceiptblservice.AccountorReceiptService;
import gap.client.datacontroller.AccountorReceiptDataController;
import gap.client.datacontroller.ControllerFactory;
import gap.client.util.LocalInfo;
import gap.client.vo.AccountVO;
import gap.client.vo.InstitutionVO;
import gap.client.vo.PayeeVO;
import gap.client.vo.PaymentListVO;
import gap.common.po.AccountPO;
import gap.common.po.BillOrderPO;
import gap.common.po.InstitutionPO;
import gap.common.po.LogPO;
import gap.common.po.PaymentListPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * 和财务人员相关的收付款单的逻辑层接口，支持的需求有：制定付款单，处理收款单、付款单，按天按营业厅查收款单。
 * @author 申彬
 *
 */
public class AccountorReceiptController implements AccountorReceiptService {

	static AccountorReceiptDataController receiptDataController = null;
	private static AccountorReceiptController receiptController = null;
	PaymentList paymentList;

	public static AccountorReceiptController getInstance() {
		if (receiptController == null) {
			receiptController = new AccountorReceiptController();
		}
		return receiptController;
	}

	private AccountorReceiptController() {
		receiptDataController = ControllerFactory.getReceiptDataController();
		paymentList = new PaymentList(receiptDataController);
	}

	@Override
	public PaymentListVO getPaymentList() {

		return paymentList.creatPaymentList();
	}

	@Override
	public ResultMessage submitPaymentList(PaymentListVO paymentListVO) {
		
		LogPO logPO = new LogPO
				(LocalInfo.localuser.toUserPO(), "创建付款单"+paymentListVO.getPaymentID());
		LogController.addLog(logPO);
		
		
		PaymentListPO po = paymentListVO.toPO();
		return receiptDataController.submitPayment(po);
	}

	@Override
	public ResultMessage handlePaymentList(PaymentListPO paymentListPO) {
		// TODO Auto-generated method stub
		PaymentHandler paymentHandler = new PaymentHandler(paymentListPO);
		return paymentHandler.handle();
	}

	public ArrayList<AccountVO> getAccoutList(){
		ArrayList<AccountPO> accoutPOs = receiptDataController.getAccountList();
		ArrayList<AccountVO> accountVOs = new ArrayList<>(accoutPOs.size());
		
		for(AccountPO po:accoutPOs){
			accountVOs.add(new AccountVO(po));	
		}
		
		return accountVOs;
		
	}
	
	@Override
	public ResultMessage addPayee(PayeeVO payeeVO) {

		return paymentList.addPayee(payeeVO);
	}

	@Override
	public ResultMessage deletePayee(PayeeVO payeeVO) {

		return paymentList.deletePayee(payeeVO);
	}

	@Override
	public ResultMessage modifyPayee(PayeeVO payeeVO) {

		return paymentList.modifyPayee(payeeVO);
	}

	@Override
	public ResultMessage paymentExcel(PaymentListVO paymentListVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillOrderPO> getBillOrderByDateOrIns(Calendar oneDay,
			String instituteID) {
		List<BillOrderPO> billOrderPOs = 
				receiptDataController.getPassedBill(oneDay, instituteID);
		
		return billOrderPOs;	
	}

	@Override
	public ResultMessage handleBillOrder(BillOrderPO billOrderPO) {
	
		BillOrderHandler handler = new BillOrderHandler(billOrderPO);
		return handler.handle();
	}

	public ArrayList<InstitutionVO> getAllInstitution(){
		List<InstitutionPO> POs = receiptDataController.getAllInstitute();
		ArrayList<InstitutionVO> VOs = new ArrayList<>(POs.size());
		
		for(InstitutionPO po:POs){
			InstitutionVO aVO = new InstitutionVO(po);
			VOs.add(aVO);
		}
		
		return VOs;
	}
}
