package gap.client.ui.initialUI;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.client.ui.initialUI.InitialHistory.HistoryItem;
import gap.client.ui.initialUI.InitialHistory.HistoryTableHeader;
import gap.common.po.InitialHistoryPO;

import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

public class InitialHistoryPanel extends PanelWithGrid{

	InitialMainPanel mainPanel;
	List<InitialHistoryPO> historyPOs;
	HistoryTableHeader tableHeader;
	ArrayList<HistoryItem> items;
	GAPLabel empty = new GAPLabel();
	
	public InitialHistoryPanel(InitialMainPanel mainPanel,List<InitialHistoryPO> historyPOs){
		this.mainPanel = mainPanel;
		this.historyPOs = historyPOs;
		
		tableHeader = new HistoryTableHeader();
		
		items = new ArrayList<>(historyPOs.size());
		for(InitialHistoryPO historyPO : historyPOs){
			HistoryItem item = new HistoryItem(mainPanel, historyPO);
			items.add(item);
		}
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, tableHeader, 0, 0, 1, 1, 1, 0);
		
		int i=0;
		for(i=0;i<items.size();i++){
			
			HistoryItem item = items.get(i);
			
			SwingConsole.addComponent
				(gridBagLayout, gridBagConstraints, this, item, 0, i+1, 1, 1, 1, 0);
		}
		
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, empty, 0, i+1, 1, 1, 1, 1);
		
	}
}
