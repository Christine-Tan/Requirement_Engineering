package gap.client.ui.paymentUI;

import gap.client.blcontroller.AccountorReceiptController;
import gap.client.exception.MoneyEmptyException;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanelWithGird;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.paymentUI.Listener.PaymentSubmitListener;
import gap.client.util.MessageType;
import gap.client.vo.AccountVO;
import gap.client.vo.PayeeVO;
import gap.client.vo.PaymentListVO;

import java.awt.GridBagConstraints;
import java.util.ArrayList;

public class PaymentMainPanel extends MainPanelWithGird{

	AccountorReceiptController receiptController;
	
	HeadPanel headPanel;
	PaymentDisplayPanel displayPanel;
	
	ArrayList<AccountVO> accountVOs;
	PaymentListVO paymentListVO;
	
	ButtonArea buttonArea;
	PaymentSubmitListener submitListener;
	
	public PaymentMainPanel(MainFrame frame) {
		super(frame);
		receiptController = AccountorReceiptController.getInstance();
		
		headPanel = new HeadPanel();
		refresh();
	}
	
	public void refresh() {
		// TODO Auto-generated method stub
		removeAll();
		accountVOs = receiptController.getAccoutList();
		paymentListVO = receiptController.getPaymentList();
		
		displayPanel = new PaymentDisplayPanel
				(this, accountVOs, paymentListVO.getPayeeList());
		buttonArea = new ButtonArea();
		buttonArea.submit.setText("提交付款单");
		
		submitListener = new PaymentSubmitListener(this);
		
		buttonArea.submit.addActionListener(submitListener);
		
		gcons.fill = GridBagConstraints.BOTH;
		SwingConsole.addComponent(gb, gcons, this, headPanel	, 0, 0, 1, 1, 1, 0.2);
		gcons.fill = GridBagConstraints.HORIZONTAL;
		gcons.anchor = GridBagConstraints.NORTH;
		
		SwingConsole.addComponent(gb, gcons, this, displayPanel , 0, 1, 1, 1, 1, 1);
		SwingConsole.addComponent(gb, gcons, this, buttonArea	, 0, 2, 1, 1, 1, 0);
	}
	
	public void moneyEmpty() {
		// TODO Auto-generated method stub
		MainFrame.setMessage("奖金金额不能为空", MessageType.alram, 2000);
	}

	public void submit() {
		ArrayList<PayeeVO> payeeVOs = paymentListVO.getPayeeList();
		try{
			payeeVOs.addAll(displayPanel.getRewardPayees());
			
		}catch(MoneyEmptyException e){
			MainFrame.setMessage("付款金额不能为空", MessageType.alram, 2000);
			return;
		}
		
		double sum = calculateTotal(payeeVOs);
		paymentListVO.setTotal(sum);
		receiptController.submitPaymentList(paymentListVO);
		MainFrame.setMessage("提交成功", MessageType.succeed, 2000);
	}
	
	private double calculateTotal(ArrayList<PayeeVO> payeeVOs){
		double sum=0;
		if(payeeVOs==null){
			return 0;
		}
		
		for(PayeeVO vo:payeeVOs){
			sum+=vo.getMoney();
		}
		return sum;
	}
	
}
