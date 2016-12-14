package gap.client.ui.AccountUI;

import gap.client.bl.account.AccountSearchResult;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.util.MessageType;
import gap.client.vo.AccountVO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JViewport;

public class AccountDisplayPanel extends JPanel{

	ArrayList<AccountVO> accounts;
	AccountManagePanel accountManagePanel;
	
	AddBox addBox;
	JViewport viewport;
	FlowLayout flow;
	
	//这个变量用来记录现在界面上的box数量，因为要增删改查，所以数量是变动的
	int currentAccountNum = 0;
	
	final int hGarp = 50;//水平间隙
	final int vGarp = 10;//垂直间隙
	
	HashMap<AccountVO, AccountBox> accountMap;
	public AccountDisplayPanel
	(AccountManagePanel accountManagePanel, ArrayList<AccountVO> accountVOs,
			JViewport viewport){
		
		setBackground(Color.white);
		accounts = accountVOs;		
		currentAccountNum = accounts.size() + 1;
		
		this.accountManagePanel = accountManagePanel;
		accountMap = new HashMap<>(accounts.size());
		
		addBox = new AddBox(accountManagePanel,this);
		
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
		int containerWidth = viewport.getWidth();
		int containerHeight = viewport.getHeight();


		
		if(accountMap.keySet().isEmpty()){
			return;
		}
		//面板格式不对，还没有被完全new出来
		if(containerWidth<=0 || containerHeight<=0){
			return;
		}
		
		reSizeByNum(accounts.size()+1);
		
	}
	
	/**
	 * 用于取消search之后重新显示所有账户
	 * 
	 */
	public void showAllAccount(){
		
		currentAccountNum = accounts.size()+1;
		
		removeAll();
		addAccountBox();
		reSize();
		accountManagePanel.validate();
	}
	
	/**
	 * 
	 * 显示查找到的账户
	 * @param results
	 */
	public void showSearchAccount(ArrayList<AccountSearchResult> results){
		removeAll();
		int resultNum = results.size();
		for(AccountSearchResult oneResult: results){
			AccountVO matchVO = oneResult.getAccountVO();
			AccountBox matchBox = getBoxByVO(matchVO);
			add(matchBox);
		}
		
		
		currentAccountNum = resultNum;
		
		reSizeByNum(currentAccountNum);
		
		MainFrame.setMessage("共找到"+resultNum+"个账户", MessageType.normal, 2000);
	}
	
	private AccountBox getBoxByVO(AccountVO matchVO){
		int index = accounts.indexOf(matchVO);
		AccountVO localVO = accounts.get(index);
		return accountMap.get(localVO);
	}
	
	
	/**
	 * 按数量重新算大小
	 * @param boxNum
	 */
	private void reSizeByNum(int boxNum){
		
		int containerWidth = viewport.getWidth();

		
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
		
		if(height<200){
			height = 200;
		}
		
		setPreferredSize(new Dimension(width, height));
		
		flow.layoutContainer(this);
		accountManagePanel.revalidate();
		accountManagePanel.repaint();
//		validate();
		
	}
	
	private void addAccountBox(){
		for(AccountVO vo : accounts){
			AccountBox box = new AccountBox(this, vo,accountManagePanel);
			accountMap.put(vo,box);
			add(box);
		}
		
		add(addBox);
	}
	
	public void addOneAccount(AccountVO vo){
		addBox.cancel();
		remove(addBox);
		AccountBox box = new AccountBox(this, vo,accountManagePanel);
		
		//map中加一个
		accountMap.put(vo,box);
		//accountList中加一个
		accounts.add(vo);
		//面板中添加
		add(box);	
		add(addBox);
		reSize();
		accountManagePanel.validate();
	}
	
	public void removeOneAccount(AccountVO vo){
		if(accounts.size()==1){
			MainFrame.setMessage("至少需要一个账户", MessageType.alram, 2000);
			return;
		}
		accountManagePanel.deleteAccount(vo);
		remove(accountMap.get(vo));
		
		//map和list中删除
		accountMap.remove(vo);
		accounts.remove(vo);
		reSize();
		accountManagePanel.validate();
		
	}
	
	class MyResizeListener implements ComponentListener{

		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentResized(ComponentEvent e) {
			// TODO Auto-generated method stub
			reSizeByNum(currentAccountNum);
		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
