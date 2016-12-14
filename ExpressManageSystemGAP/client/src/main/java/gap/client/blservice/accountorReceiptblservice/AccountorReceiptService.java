package gap.client.blservice.accountorReceiptblservice;

import gap.client.vo.PayeeVO;
import gap.client.vo.PaymentListVO;
import gap.common.po.BillOrderPO;
import gap.common.po.PaymentListPO;
import gap.common.util.ResultMessage;

import java.util.Calendar;
import java.util.List;

/**
 * 和财务人员相关的收付款单的逻辑层接口，支持的需求有：制定付款单，处理收款单、付款单，按天按营业厅查收款单。
 * @author 申彬
 *
 */
public interface AccountorReceiptService {

	/**
	 * 获得付款单草稿
	 * @return
	 */
	public PaymentListVO getPaymentList();

	/**
	 * 提交制定完毕的收款单，等待总经理审批
	 * @param paymentListVO
	 * @return
	 */
	public ResultMessage submitPaymentList(PaymentListVO paymentListVO);

	/**
	 * 供总经理调用的接口，用来处理从数据库读出的，通过审批的付款单
	 * @param paymentListPO
	 * @return
	 */
	public ResultMessage handlePaymentList(PaymentListPO paymentListPO);

	/**
	 * 增加一个收款人/收款项
	 * @param payeeVO
	 * @return
	 */
	public ResultMessage addPayee(PayeeVO payeeVO);

	/**
	 * 删除
	 * @param payeeVO
	 * @return
	 */
	public ResultMessage deletePayee(PayeeVO payeeVO);

	/**
	 * 修改
	 * @param payeeVO
	 * @return
	 */
	public ResultMessage modifyPayee(PayeeVO payeeVO);

	/**
	 * 导出excel
	 * @param paymentListVO
	 * @return
	 */
	public ResultMessage paymentExcel(PaymentListVO paymentListVO);

	/**
	 * 按天、按营业厅获得收款单
	 * @return
	 */
	public List<BillOrderPO> getBillOrderByDateOrIns(Calendar oneDay,
			String insitituteID);

	/**
	 * 处理收款单
	 * @param billOrderPO
	 * @return
	 */
	public ResultMessage handleBillOrder(BillOrderPO billOrderPO);
}
