package gap.client.ui.tableUI.Cost_profitUI;

import gap.client.bl.table.TableBlController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanelWithGird;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.RichLable;
import gap.client.vo.Cost_ProfitListVO;

import java.awt.GridBagConstraints;

public class CostProfitPanel extends MainPanelWithGird{
	TableBlController controller;
	Cost_ProfitListVO vo;
	ArcGraphPanel arcGraphPanel;
	RichLable titleLable;
	ButtonArea buttonArea;
	GAPButton excelButton;
	
	boolean isFirstShow = true;
	
	public CostProfitPanel(MainFrame frame){
		
		super(frame);
		
		controller = TableBlController.getInstance();
		vo = controller.getCost_ProfitList();
		arcGraphPanel = new ArcGraphPanel(vo);
		
		titleLable = new RichLable("成本收益表", 35);
		titleLable.setTextBottom();
		
		buttonArea = new ButtonArea();
		buttonArea.removeAll();
		excelButton = new GAPButton("导出excel");
		buttonArea.add(excelButton);
		
		SwingConsole.addComponent(gb, gcons, this, titleLable, 0, 0, 1, 1, 1, 0.2);
		
		gcons.fill = GridBagConstraints.BOTH;
		SwingConsole.addComponent(gb, gcons, this, arcGraphPanel, 0, 1, 1, 1, 1, 1);
		SwingConsole.addComponent(gb, gcons, this, buttonArea, 0, 2, 1, 1, 1, 0);
		
		arcGraphPanel.startAnimation();
		
	}

	@Override
	public void refresh() {
		
		
		vo = controller.getCost_ProfitList();
		arcGraphPanel.setVO(vo);
		arcGraphPanel.startAnimation();
	}

}
