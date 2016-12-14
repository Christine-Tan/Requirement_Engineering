package gap.client.ui.tableUI.OperationUI;

import gap.client.bl.table.TableBlController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanelWithGird;
import gap.client.ui.BillOrderQueryUI.AccountOrderItemListPanel;
import gap.client.ui.BillOrderQueryUI.BillOrderTableHeader;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.excelUI.FileChooser;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.GAPJScrollPane;
import gap.client.util.MessageType;
import gap.client.vo.OperatingConditionListVO;
import gap.common.ListInterface.Receipt;
import gap.common.util.ResultMessage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OperationMainPanel extends MainPanelWithGird{


	TableBlController tableController;
	TableQueryBar queryBar;
	BillOrderTableHeader tableHeader;
	AccountOrderItemListPanel listPanel;
	TableTotalMoneyPanel totalMoneyPanel;
	ButtonArea buttonArea;
	
	OperatingConditionListVO operatingConditionListVO;
	
	public OperationMainPanel(MainFrame frame) {
		super(frame);
		tableController = TableBlController.getInstance();
		tableHeader = new BillOrderTableHeader();
		
		refresh();
	}
	
	

	@Override
	public void refresh() {
		removeAll();
		
		queryBar = new TableQueryBar(this);
		
		SwingConsole.addComponent(gb, gcons, this, queryBar, 0, 0, 1, 1, 1, 0);
		
		gcons.insets = new Insets(10, 0, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, tableHeader, 0, 1, 1, 1, 1, 0);
		
		gcons.insets = new Insets(0, 0, 0, 0);
		
		listPanel = new AccountOrderItemListPanel(null);
		
		GAPJScrollPane billScrollePanel = new GAPJScrollPane(listPanel);
		billScrollePanel.getViewport().setBackground(Color.white);
		gcons.fill = GridBagConstraints.BOTH;
		SwingConsole.addComponent(gb, gcons, this, billScrollePanel, 0, 2, 1, 1, 1, 1);
		
		
		totalMoneyPanel = new TableTotalMoneyPanel();
		totalMoneyPanel.setVisible(false);
		SwingConsole.addComponent(gb, gcons, this, totalMoneyPanel, 0, 3, 1, 1, 1, 0);
		
		buttonArea = new ButtonArea();
		buttonArea.submit.setText("导出excel");
		buttonArea.submit.addActionListener(new ExcelListener());
		SwingConsole.addComponent(gb, gcons, this, buttonArea, 0, 4, 1, 1, 1, 0);
	}

	public void search(String beginString,String endString){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Date begin = null;
		Date end = null;
		
		//各种判断
		try {
			begin = format.parse(beginString);
		} catch (ParseException e) {
			MainFrame.setMessage
			("开始日期格式不正确，正确格式为：2015-12-21", MessageType.alram, 2000);
			return;
		}
		
		try {
			end = format.parse(endString);
		} catch (ParseException e) {
			MainFrame.setMessage
			("结束日期格式不正确，正确格式为：2015-12-21", MessageType.alram, 2000);
			return;
		}
		
		if(begin!=null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(begin);
			Calendar now = Calendar.getInstance();
			if(calendar.after(now)){
				queryBar.setAlarm();
				MainFrame.setMessage("请输入今天及以前的开始日期", MessageType.alram, 2000);
				return;
			}
		}
		
		if(begin.after(end)){
			MainFrame.setMessage("结束日期必须在开始日期之后", MessageType.alram, 2000);
			return;
		}
		
		
		operatingConditionListVO = 
				tableController.getOperatingConditionList(begin, end);
		List<Receipt> receipts = operatingConditionListVO.getReceipts();
		
		if(receipts.size()==0){
			MainFrame.setMessage("未找到单据", MessageType.normal, 3000);
			return;
		}else{
			String message = "共找到"+receipts.size()+"份单据";
			MainFrame.setMessage(message, MessageType.succeed, 3000);
		}
		
		
		ArrayList<Object> orders = new ArrayList<>(receipts.size());
		for(Receipt aPO:receipts){
			orders.add(aPO);
		}
		
		listPanel.refresh(orders);
		
		double income = operatingConditionListVO.getTotalIncome();
		double payment = operatingConditionListVO.getTotalPayment();
		double total = income-payment;
		
		totalMoneyPanel.setMoney(income, payment, total);
		totalMoneyPanel.setVisible(true);
		validate();
	}
	
	
	public AccountOrderItemListPanel getListPanel(){
		return listPanel;
	}
	
	public class ExcelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String filePath = FileChooser.chooseFile(mainFrame);
			System.out.println(filePath);
			ResultMessage resultMessage = 
					tableController.OperatingConditionExcel
						(operatingConditionListVO, filePath);
			if(resultMessage == ResultMessage.NOTFOUND){
				MainFrame.setMessage("列表为空", MessageType.alram, 2000);
			}else if(resultMessage == ResultMessage.FAILED){
				MainFrame.setMessage("导出失败", MessageType.alram, 2000);
			}else if (resultMessage == ResultMessage.SUCCEED) {
				MainFrame.setMessage("导出成功", MessageType.succeed, 2000);
			}
		}
		
	}
	
}
