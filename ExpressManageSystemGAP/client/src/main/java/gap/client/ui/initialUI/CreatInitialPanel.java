package gap.client.ui.initialUI;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.common.po.InitialHistoryPO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatInitialPanel extends PanelWithGrid{

	InitialMainPanel mainPanel;
	InitialHistoryPO historyPO;
	InitialTablePanel tablePanel;
	ButtonArea buttonArea;
	
	public CreatInitialPanel(InitialMainPanel mainPanel,InitialHistoryPO historyPO){
		
		this.mainPanel = mainPanel;
		this.historyPO = historyPO;
		tablePanel = new InitialTablePanel(mainPanel,historyPO);
		buttonArea = new ButtonArea();
		buttonArea.submit.setText("确认建账");
		buttonArea.submit.addActionListener(new ConfirmListener());
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(3, 0, 3, 0);
		
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, tablePanel, 0, 0, 1, 1, 1, 1);
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, buttonArea, 0, 1, 1, 1, 1, 0);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.gray);
		g.drawLine(0, 1, getWidth(), 1);
		
		g.drawLine(0,getHeight()-1, 0, getHeight()-1);
	}
	
	public class ConfirmListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.confirm(historyPO);
		}
		
	}
	
}
