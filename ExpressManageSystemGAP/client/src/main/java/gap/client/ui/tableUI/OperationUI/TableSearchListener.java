package gap.client.ui.tableUI.OperationUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TableSearchListener implements MouseListener{

	TableQueryBar queryBar;
	OperationMainPanel mainPanel;
	
	public TableSearchListener(TableQueryBar queryBar,OperationMainPanel mainPanel){
		this.queryBar = queryBar;
		this.mainPanel = mainPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		String begin = queryBar.getBegin();
		String end = queryBar.getEnd();
		mainPanel.search(begin , end);
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
