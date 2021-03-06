package gap.client.ui.initialUI.initialStock;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.client.ui.initialUI.InitialMainPanel;
import gap.client.ui.initialUI.InitialTable.CityTableContant;
import gap.client.ui.initialUI.InitialTable.CityTableHeader;
import gap.common.po.InitialStockPO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JViewport;

public class InitialStockPanel extends PanelWithGrid{

	InitialMainPanel tablePanel;
	ArrayList<InitialStockPO> stockPOs;
	
	GAPLabel information;
	StockDisplayPanel displayPanel;
	JViewport viewport;
	
	public  InitialStockPanel
		(InitialMainPanel tablePanel,ArrayList<InitialStockPO> stockPOs,JViewport viewport){

		this.tablePanel = tablePanel;
		this.stockPOs = stockPOs;
		this.viewport =viewport;
		
		information = new GAPLabel("库存信息");
		information.setFont(CityTableContant.bigFont);
		information.setPreferredSize(new Dimension(CityTableHeader.classIdle, 100));
		information.setHorizontalAlignment(JLabel.CENTER);
		
	
		
		displayPanel = new StockDisplayPanel(tablePanel, stockPOs, viewport);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		gridBagConstraints.insets = new Insets(5, 0, 5, 0);
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, information, 0, 0, 1, 1, 0, 1);
		
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, displayPanel, 1, 0, 1, 1, 1, 1);
			
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.gray);
		g.drawLine(2, getHeight()-2, getWidth()-2, getHeight()-2);
	}
}
