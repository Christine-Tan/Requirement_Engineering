package gap.client.ui.paymentUI.RewardPanels;

import gap.client.exception.MoneyEmptyException;
import gap.client.ui.AccountUI.DefaultText_Field;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.client.ui.paymentUI.PayeeItem;
import gap.client.ui.paymentUI.PaymentTableHeader;
import gap.client.vo.AccountVO;
import gap.client.vo.PayeeVO;
import gap.common.util.PaymentType;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;

public class RewardEditItem extends PanelWithGrid{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel nameLabel;
	GAPLabel dateLabel;
	GAPComboBox<AccountVO> accountBox;
	GAPComboBox<PayeeVO> receiver;
	GAPLabel item;
	DefaultText_Field moneyField;
	DefaultText_Field commentField;
	GAPButton deleteButton;
	
	Component[] components;
	
	PaymentRewardPanel rewardPanel;
	
	ArrayList<AccountVO> accountVOs;
	ArrayList<PayeeVO> payees;
	
	private static String dateString;
	
	public RewardEditItem
	(ArrayList<AccountVO> accountVOs,ArrayList<PayeeVO> payees,PaymentRewardPanel rewardPanel){
		this.accountVOs = accountVOs;
		this.payees = payees;
		this.rewardPanel = rewardPanel;
		
		setBackground(Color.white);
		
		nameLabel = new JLabel("奖金");
		nameLabel.setOpaque(true);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setFont(ColorAndFonts.getChinese(20));
		nameLabel.setBackground(Color.white);
		
		accountBox = new GAPComboBox<>();
		
		for(AccountVO accountVO: accountVOs){
			accountBox.addItem(accountVO);
		}
		
		receiver = new GAPComboBox<>();
		
		for(PayeeVO payeeVO: payees){
			receiver.addItem(payeeVO);
		}
		
		if(dateString==null){
			dateString = PayeeItem.getDateString();
		}
		
		dateLabel = new GAPLabel();
		dateLabel.setHorizontalAlignment(JLabel.CENTER);
		dateLabel.setText(dateString);
		
		item = new GAPLabel("奖金");
		item.setHorizontalAlignment(JLabel.CENTER);
		moneyField = new DefaultText_Field("请输入金额");
		moneyField.setControl("[^(\\d+(\\.\\d+)?)]", 1, 20);
		commentField = new DefaultText_Field("请输入备注");
		
		deleteButton = new GAPButton("x");
		deleteButton.addActionListener(new DeleteListener());
		
		components = new Component[7];
		
		components[0] = dateLabel;
		components[1] = receiver;
		components[2] = accountBox;
		components[3] = item;
		components[4] = moneyField;
		components[5] = commentField;
		components[6] = deleteButton;
		

		
	
		for(int i=0;i<PaymentTableHeader.widthArgs.length;i++){
			int width = PaymentTableHeader.widthArgs[i];
			if(components[i] instanceof GAPComboBox){
				components[i].setPreferredSize(new Dimension(width,25));
			}
			else if(components[i] instanceof DefaultText_Field){
				components[i].setPreferredSize(new Dimension(width,25));
			}	
			else{
				components[i].setPreferredSize(new Dimension(width,PaymentTableHeader.height));
			}
		}
		
		deleteButton.setPreferredSize(new Dimension(40, 40));
		
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		for(int i=0;i< components.length;i++){
			Component aComponent = components[i];
			SwingConsole.addComponent(gridBagLayout, gridBagConstraints, this, aComponent,
						i, 0, 1, 1, 1, 1);
		}
		
	}
	
	public PayeeVO getRewardPayee() throws MoneyEmptyException{
		PayeeVO selectedUser = (PayeeVO) receiver.getSelectedItem();
		String rewardMoney = moneyField.getText();
		
		if(rewardMoney.equals("")){
			moneyField.toAlarm();
			throw new MoneyEmptyException();
		}
		double money = Double.parseDouble(rewardMoney);
		AccountVO accountVO = (AccountVO) accountBox.getSelectedItem();
		String accountName = accountVO.getName();
		String entry = "奖金";
		String note = commentField.getText();
		
		PayeeVO rewardPayee = new PayeeVO
				(PaymentType.REWARD,selectedUser.getUserID(), 
				selectedUser.getUserName(),
				null, money, accountName, entry, note);
	
		return rewardPayee;
		
	}
	
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			rewardPanel.deleteItem(RewardEditItem.this);
		}
	}

}
