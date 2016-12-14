package gap.client.ui.BillOrderQueryUI.Listener;

import gap.client.ui.BillOrderQueryUI.AccountorBillQueryBar;
import gap.client.ui.BillOrderQueryUI.AccountorBillQueryMainPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BillCancelListener implements MouseListener{
	
	AccountorBillQueryBar queryBar;
	AccountorBillQueryMainPanel mainPanel;
	
	public BillCancelListener
	(AccountorBillQueryBar queryBar,AccountorBillQueryMainPanel mainPanel) {
		this.queryBar = queryBar;
		this.mainPanel = mainPanel;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		queryBar.setSearchButton();
		mainPanel.getListPanel().cancel();
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
