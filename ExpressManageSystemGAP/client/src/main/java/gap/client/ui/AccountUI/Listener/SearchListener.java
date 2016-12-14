package gap.client.ui.AccountUI.Listener;

import gap.client.ui.AccountUI.AccountManagePanel;
import gap.client.ui.AccountUI.AccountQueryPanel;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.util.MessageType;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class SearchListener implements MouseListener{

	AccountManagePanel managePanel;
	AccountQueryPanel queryPanel;
	
	public SearchListener(AccountManagePanel managePanel,AccountQueryPanel queryPanel){
		this.managePanel = managePanel;
		this.queryPanel = queryPanel;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String key = queryPanel.getKey();
		
		//假如关键字为空
		if(key==null || key.equals("")){
			//queryPanel.setAlarm();
			MainFrame.setMessage("请输入关键字", MessageType.alram, 2000);
			return;
		}
		
		
		managePanel.searchAccount(key);
		queryPanel.setCancelButton();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
