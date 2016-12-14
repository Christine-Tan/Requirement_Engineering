package gap.client.ui.initialUI;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ChooseButton;
import gap.client.ui.gapcomponents.PanelWithGrid;

import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class HorizontalNavi extends PanelWithGrid{
	
	ChooseButton historyButton;
	ChooseButton initialButton;
	
	InitialMainPanel mainPanel;
	InitialHistoryPanel historyPanel;
	CreatInitialPanel creatInitialPanel;
	
	InitialPanelListener listener;
	
	public HorizontalNavi
	(InitialMainPanel mainPanel,
			InitialHistoryPanel historyPanel,CreatInitialPanel creatInitialPanel){
		this.mainPanel = mainPanel;
		this.historyPanel = historyPanel;
		this.creatInitialPanel = creatInitialPanel;
		
		
		listener = new InitialPanelListener();
		
		historyButton = new ChooseButton("查看期初信息", historyPanel);
		initialButton = new ChooseButton("新建期初信息", creatInitialPanel);
		
		historyButton.addMouseListener(listener);
		initialButton.addMouseListener(listener);
		
		historyButton.setSelected();
		
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, historyButton, 0, 0, 1, 1, 0, 1);
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints ,this, initialButton, 1, 0, 1, 1, 0, 1);
	}
	
	class InitialPanelListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			ChooseButton button = (ChooseButton)e.getSource();
			JPanel panel = button.getPanel();
			mainPanel.jumpTo(panel);
			
			if(panel == historyPanel){
				initialButton.toNomal();
			}else{
				historyButton.toNomal();
			}
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
	}
	
}
