package gap.client.bl.table;

import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.TableDataController;
import gap.client.util.ReceiptComparator;
import gap.client.vo.OperatingConditionListVO;
import gap.common.ListInterface.Receipt;
import gap.common.po.BillOrderPO;
import gap.common.po.PaymentListPO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReceiptGetter {
	
	public OperatingConditionListVO getReceipts(Date begin,Date end){
		TableDataController dataController = ControllerFactory.getTableDataController();
		
		Calendar beginCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		beginCal.setTime(begin);
		endCal.setTime(end);
		
		List<BillOrderPO> billOrderPOs = dataController.getPassedBill(beginCal, endCal);
		List<PaymentListPO> paymentListPOs = 
				dataController.getPassedPayment(beginCal, endCal);
		
		double totalIncome = 0;
		double totalPayment = 0;
		
		for(BillOrderPO billOrderPO:billOrderPOs){
			totalIncome+=billOrderPO.getTotal();
		}
		
		for(PaymentListPO paymentListPO:paymentListPOs){
			totalPayment+=paymentListPO.getTotal();
		}
		
		ArrayList<Receipt> receipts = new ArrayList<>
				(billOrderPOs.size()+paymentListPOs.size());
		receipts.addAll(billOrderPOs);
		receipts.addAll(paymentListPOs);
		
		receipts.sort(new ReceiptComparator());
		
		OperatingConditionListVO vo = new OperatingConditionListVO
				(receipts, totalIncome, totalPayment, Calendar.getInstance());
		return vo;
		
	}
	
	
}
