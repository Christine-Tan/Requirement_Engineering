package gap.client.ui.paymentUI.RewardPanels;

import gap.client.exception.MoneyEmptyException;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.paymentUI.PayeeClassPanel;
import gap.client.ui.paymentUI.PaymentDisplayPanel;
import gap.client.ui.paymentUI.PaymentMainPanel;
import gap.client.ui.paymentUI.PaymentTableHeader;
import gap.client.vo.AccountVO;
import gap.client.vo.PayeeVO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

public class PaymentRewardPanel extends PayeeClassPanel{

	ArrayList<PayeeVO> userPayee;
	ArrayList<RewardEditItem> items;
	RewardAddLabel addLabel;
	
	public PaymentRewardPanel(PaymentMainPanel mainPanel, PaymentDisplayPanel displayPanel,
			ArrayList<AccountVO> accountVOs,
			ArrayList<PayeeVO> userPayee) {
		
		super(mainPanel, displayPanel, accountVOs, null, "奖金");
		this.userPayee = userPayee;
		
		setBackground(Color.white);
		items = new ArrayList<>();
		addLabel = new RewardAddLabel(this);
		addLabel.setPreferredSize(new Dimension(500, PaymentTableHeader.height));
		relayout();
		
	}
	
	/**
	 * 重新布局
	 */
	private void relayout(){
		removeAll();
		
		int itemNum = items.size()+1;
		nameLabel.setPreferredSize
		(new Dimension(PaymentTableHeader.leftGap-10,itemNum* PaymentTableHeader.height));
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, nameLabel, 0, 0, 1, itemNum, 0, 1);
		
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		int i=0;
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		for(i=0;i<items.size();i++){
			SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, items.get(i), 1, i, 1, 1, 1, 0.5);
		}
		
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, addLabel, 1, i, 1, 1, 1, 0.5);
		
		super.mainPanel.getJsPanel().validate();

		
	}

	public void deleteItem(RewardEditItem rewardEditItem) {
		// TODO Auto-generated method stub
		items.remove(rewardEditItem);
		relayout();
	}

	public void addItem() {
		// TODO Auto-generated method stub
		
		RewardEditItem newItem = new RewardEditItem(accountVOs, userPayee, this);
		items.add(newItem);
		relayout();
	}

	
	public ArrayList<PayeeVO> getRewardPayees() throws MoneyEmptyException{
		ArrayList<PayeeVO> rewardVOs = new ArrayList<>(items.size());
		for(RewardEditItem item:items){
			rewardVOs.add(item.getRewardPayee());
		}
		return rewardVOs;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);		
		Graphics2D graphics2d = RenderSetter.OpenRender(g);
		g.setColor(Color.gray);
	
		graphics2d.drawLine(0, getHeight()-2, getWidth(), getHeight()-2);	
	}

}
