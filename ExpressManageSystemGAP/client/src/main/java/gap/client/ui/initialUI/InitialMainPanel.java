package gap.client.ui.initialUI;

import gap.client.blcontroller.InitialController;
import gap.client.blservice.initialblservice.InitialBlService;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanelWithGird;
import gap.client.ui.UITools.SwingConsole;
import gap.client.util.MessageType;
import gap.common.po.InitialHistoryPO;
import gap.common.util.ResultMessage;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JPanel;

public class InitialMainPanel extends MainPanelWithGird{

	InitialBlService initialBl;
	CreatInitialPanel creatInitialPanel;
	InitialHistoryPanel historyPanel;
	HorizontalNavi horizontalNavi;
	
	JPanel currentPanel;
	
	public InitialMainPanel(MainFrame frame) {
		super(frame);
		initialBl = InitialController.getInstance();
		refresh();
	}

	public void refresh() {
		
		removeAll();
		
		InitialHistoryPO historyPO = initialBl.getCurrentInitial();
		List<InitialHistoryPO> historyPOs = initialBl.getInitialHistory();
		
		creatInitialPanel = new CreatInitialPanel(this, historyPO);
		historyPanel = new InitialHistoryPanel(this, historyPOs);
		horizontalNavi = new HorizontalNavi(this, historyPanel, creatInitialPanel);
		
		horizontalNavi.setPreferredSize(new Dimension(500, 50));
		
		currentPanel = historyPanel;
		
		gcons.fill = GridBagConstraints.BOTH;
		SwingConsole.addComponent(gb, gcons, this, horizontalNavi, 0, 0, 1, 1, 1, 0);
		
		SwingConsole.addComponent(gb, gcons, this, currentPanel, 0, 1, 1, 1, 1, 1);
		
	}

	public void jumpTo(JPanel panel) {
		
		if(currentPanel == panel){
			return;
		}
		else{
			gb.removeLayoutComponent(currentPanel);
			remove(currentPanel);
			currentPanel = panel;
			SwingConsole.addComponent(gb, gcons, this, panel, 0, 1, 1, 1, 1, 1);
			
			validate();
			repaint();
		}
	
	}

	public void confirm(InitialHistoryPO historyPO) {
		ResultMessage message = initialBl.submitInitial(historyPO);
		if(message.equals(ResultMessage.SUCCEED)){
			MainFrame.setMessage("建账成功", MessageType.succeed, 2000);
		}else{
			MainFrame.setMessage("建账失败", MessageType.alram, 2000);
		}
	}

	
}
