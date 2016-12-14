package gap.client.ui.BillOrderQueryUI;

import gap.client.blcontroller.AccountorReceiptController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanelWithGird;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.GAPJScrollPane;
import gap.client.util.MessageType;
import gap.client.vo.InstitutionVO;
import gap.common.po.BillOrderPO;
import gap.common.util.InstitutionType;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AccountorBillQueryMainPanel extends MainPanelWithGird{

	AccountorBillQueryBar queryBar;
	AccountorReceiptController receiptController;
	List<InstitutionVO> institutionVOs;
	
	BillOrderTableHeader tableHeader;
	AccountOrderItemListPanel listPanel;
	TotalMoneyPanel totalMoneyPanel;
	ButtonArea buttonArea;
	
	public AccountorBillQueryMainPanel(MainFrame frame) {
		super(frame);
		receiptController = AccountorReceiptController.getInstance();
		tableHeader = new BillOrderTableHeader();
		
		refresh();
	}
	
	

	@Override
	public void refresh() {
		removeAll();
		List<InstitutionVO> allInstitution = receiptController.getAllInstitution();
		institutionVOs = new ArrayList<>();
		
		for(InstitutionVO institutionVO: allInstitution){
			InstitutionType type = InstitutionType.getInsType(institutionVO.getInsId());
			if(type == InstitutionType.BUSSINESS){
				institutionVOs.add(institutionVO);
			}
		}
		
		queryBar = new AccountorBillQueryBar(this, institutionVOs);
		SwingConsole.addComponent(gb, gcons, this, queryBar, 0, 0, 1, 1, 1, 0);
		
		gcons.insets = new Insets(10, 0, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, tableHeader, 0, 1, 1, 1, 1, 0);
		
		gcons.insets = new Insets(0, 0, 0, 0);
		listPanel = new AccountOrderItemListPanel(this);
		GAPJScrollPane billScrollePanel = new GAPJScrollPane(listPanel);
		billScrollePanel.getViewport().setBackground(Color.white);
		gcons.fill = GridBagConstraints.BOTH;
		SwingConsole.addComponent(gb, gcons, this, billScrollePanel, 0, 2, 1, 1, 1, 1);
		
		
		totalMoneyPanel = new TotalMoneyPanel(0, "收款");
		totalMoneyPanel.setVisible(false);
		SwingConsole.addComponent(gb, gcons, this, totalMoneyPanel, 0, 3, 1, 1, 1, 0);
		
		buttonArea = new ButtonArea();
		buttonArea.submit.setText("导出excel");
		SwingConsole.addComponent(gb, gcons, this, buttonArea, 0, 4, 1, 1, 1, 0);
	}

	public void search(String institutionID,String dateString){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Calendar calendar = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			MainFrame.setMessage
			("日期格式不正确，正确格式为：2015-12-21", MessageType.alram, 2000);
			return;
		} catch (NullPointerException e){
			//什么也不做，null视为任意日期的单据
		}
		
		if(dateString!=null){
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			Calendar now = Calendar.getInstance();
			if(calendar.after(now)){
				queryBar.setAlarm();
				MainFrame.setMessage("请输入今天及以前的日期", MessageType.alram, 2000);
				return;
			}
		}
		
		List<BillOrderPO> billOrderPOs = 
				receiptController.getBillOrderByDateOrIns(calendar, institutionID);
		
		if(billOrderPOs.size()==0){
			MainFrame.setMessage("未找到收款单", MessageType.normal, 3000);
			return;
		}else{
			String message = "共找到"+billOrderPOs.size()+"份收款单";
			MainFrame.setMessage(message, MessageType.succeed, 3000);
		}
		
		
		ArrayList<Object> orders = new ArrayList<>(billOrderPOs.size());
		for(BillOrderPO aPO:billOrderPOs){
			orders.add(aPO);
		}
		
		listPanel.refresh(orders);
		double total = computeTotal(billOrderPOs);
		totalMoneyPanel.setMoney(total);
		totalMoneyPanel.setVisible(true);
		validate();
	}
	
	private double computeTotal(List<BillOrderPO> billOrderPOs){
		double total = 0;
		for(BillOrderPO po:billOrderPOs){
			total+=po.getTotal();
		}
		return total;
	}
	
	public AccountOrderItemListPanel getListPanel(){
		return listPanel;
	}
}
