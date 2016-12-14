package gap.client.ui.initialUI;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.client.ui.initialUI.InitialTable.CityTableContant;
import gap.client.ui.initialUI.InitialTable.CityTableHeader;
import gap.client.ui.initialUI.initialAccount.InitialAccountPanel;
import gap.client.ui.initialUI.initialStock.InitialStockPanel;
import gap.common.po.InitialHistoryPO;
import gap.common.po.InitialPeoplePO;

import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JViewport;

public class InitialTablePanel extends PanelWithGrid{
	
	List<InitialPeoplePO> peoplePOs;
	
	CityTableHeader tableHeader;
	CityTableContant tableContant;
	InitialAccountPanel accountPanel;
	InitialStockPanel stockPanel;
	
	public InitialTablePanel(InitialMainPanel mainPanel,InitialHistoryPO historyPO){

		this.peoplePOs = historyPO.initialPeoplePOs;
		
		String[] cityNames = new String[peoplePOs.size()];
		for(int i=0;i<peoplePOs.size();i++){
			cityNames[i] = peoplePOs.get(i).getCityName();
		}
		
		tableHeader = new CityTableHeader(cityNames);
		tableContant = new CityTableContant(peoplePOs);
		
		JViewport viewport = mainPanel.getJsPanel().getViewport();
		
		accountPanel = new InitialAccountPanel(mainPanel, historyPO.accountPOs,viewport);
		stockPanel = new InitialStockPanel(mainPanel, historyPO.initialStockPOs, viewport);
		
		
		gridBagConstraints.fill = GridBagConstraints.NONE;
		
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, tableHeader, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, tableContant, 0, 1, 1, 1, 1, 1);
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, accountPanel, 0, 2, 1, 1, 1, 1);
		
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, stockPanel, 0, 3, 1, 1, 1, 1);
		
	}
}
