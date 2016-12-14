package gap.client.bl.table;

import gap.client.blservice.tableblservice.TableService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.TableDataController;
import gap.client.ui.UITools.ConvertString;
import gap.client.util.ExcelOutput;
import gap.client.vo.Cost_ProfitListVO;
import gap.client.vo.OperatingConditionListVO;
import gap.common.ListInterface.Receipt;
import gap.common.po.Cost_profitPO;
import gap.common.po.PaymentListPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

public class TableBlController implements TableService {

	static TableBlController tableBlController;
	TableDataController dataController;

	private TableBlController() {
		dataController = ControllerFactory.getTableDataController();
	}

	public static TableBlController getInstance() {
		if (tableBlController == null) {
			tableBlController = new TableBlController();
		}
		return tableBlController;
	}

	@Override
	public Cost_ProfitListVO getCost_ProfitList() {

		ArrayList<Cost_profitPO> list = dataController.getCost_Profit();
		CostProfitComputer computer = new CostProfitComputer();
	 	return computer.compute(list);
		
	}

	@Override
	public OperatingConditionListVO getOperatingConditionList(Date begin,
			Date end) {
		ReceiptGetter getter = new ReceiptGetter();
		return getter.getReceipts(begin, end);
	}

	@Override
	public ResultMessage Cost_ProfitExcel(Cost_ProfitListVO costProfitListVO,String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage OperatingConditionExcel(
			OperatingConditionListVO operatingConditionListVO,String path) {
		
		if(operatingConditionListVO == null){
			return ResultMessage.NOTFOUND;
		}
		String[] titles ={"单据编号","单据类型","生成日期","金额"};
		ExcelOutput excelOutput = new ExcelOutput(titles);
		for(Receipt receipt:operatingConditionListVO.getReceipts()){
				String id = receipt.getID();
				String type = null;
				if(receipt instanceof PaymentListPO){
					type = "付款单";
				}else{
					type = "收款单";
				}
				String date = ConvertString.getString(receipt.getDate());
				String money = ConvertString.getString(receipt.getTotal());
				excelOutput.appendRow(id,type,date,money);
			}
			excelOutput.appendRow("");
			String totalIncome = "总收入";
			String incomeMoney = ConvertString.getString
						(operatingConditionListVO.getTotalIncome());
			excelOutput.appendRow("","",totalIncome,incomeMoney);
			
			String totalPayment = "总支出";
			String paymentMoney = ConvertString.getString(
					operatingConditionListVO.getTotalPayment());
			excelOutput.appendRow("","",totalPayment,paymentMoney);
			
			excelOutput.export(path);
			return ResultMessage.SUCCEED;
		
		
		
	}

}
