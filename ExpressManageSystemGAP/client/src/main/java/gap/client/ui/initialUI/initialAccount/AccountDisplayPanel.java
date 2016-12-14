package gap.client.ui.initialUI.initialAccount;

import gap.client.ui.initialUI.InitialMainPanel;
import gap.client.ui.initialUI.InitialTable.CityTableHeader;
import gap.common.po.AccountPO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class AccountDisplayPanel extends JPanel{

	ArrayList<AccountPO> accounts;

	JComponent viewport;
	FlowLayout flow;

	InitialMainPanel panel;
	
	int currentAccountNum = 0;
	
	final int hGarp = 50;//水平间隙
	final int vGarp = 10;//垂直间隙
	
	HashMap<AccountPO, AccountBox> accountMap;
	public AccountDisplayPanel
	(InitialMainPanel mainPanel,ArrayList<AccountPO> accountPOs,JComponent viewport){
	
		this.panel = mainPanel;
		
		setBackground(Color.white);
		accounts = accountPOs;		
		currentAccountNum = accounts.size();

		accountMap = new HashMap<>(accounts.size());
		
		this.viewport = viewport;
		setOpaque(false);
		viewport.addComponentListener(new MyResizeListener());
		
		flow = new FlowLayout(FlowLayout.LEFT, 50, 10);
		setLayout(flow);
		repaint();
		addAccountBox();
		reSize();

	}
	
	/**
	 * 根据账户数量重新设定大小
	 */
	public void reSize(){
		
		if(viewport==null){
			return;
		}
		int containerWidth = viewport.getWidth() - CityTableHeader.classIdle;
		int containerHeight = viewport.getHeight();

		
		if(accountMap.keySet().isEmpty()){
			return;
		}
		//面板格式不对，还没有被完全new出来
		if(containerWidth<=0 || containerHeight<=0){
			return;
		}
		
		reSizeByNum(accounts.size());
		
	}
	
	
	
	/**
	 * 按数量重新算大小
	 * @param boxNum
	 */
	private void reSizeByNum(int boxNum){
		
		int containerWidth = viewport.getWidth() - CityTableHeader.classIdle;

		
		int boxWidth = AccountBox.width;
		int boxHeight = AccountBox.height;
		//一行最多放几个box
		int numberInRow = containerWidth/(boxWidth +  hGarp);
		//有多少行
		//box的数量是账户数加一个添加图标
		int rowNumber = boxNum/numberInRow;
	
		if(boxNum%numberInRow>0){
			rowNumber++;
		}
		
		int width = numberInRow * (boxWidth +  hGarp);
		int height = rowNumber * (boxHeight +  vGarp);
		
		
		
		if(height<100){
			height = 100;
		}
		
		setPreferredSize(new Dimension(width, height));
		
		flow.layoutContainer(this);

		panel.validate();
		panel.repaint();
		viewport.validate();
		
	}
	
	private void addAccountBox(){
		for(AccountPO po : accounts){
			AccountBox box = new AccountBox(po);
			accountMap.put(po,box);
			add(box);
		}
	}
	
	
	class MyResizeListener implements ComponentListener{

		@Override
		public void componentHidden(ComponentEvent e) {

			
		}

		@Override
		public void componentMoved(ComponentEvent e) {

			
		}

		@Override
		public void componentResized(ComponentEvent e) {
			reSize();
		}

		@Override
		public void componentShown(ComponentEvent e) {

			
		}
		
	}
	
}
