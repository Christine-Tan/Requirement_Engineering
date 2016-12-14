package gap.client.ui.BaseComponents.FrameInitialler;

import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.NavigateBar;
import gap.client.ui.BaseComponents.NavigateButton;
import gap.client.ui.inventoryui.alarmvalueset.AlarmValueSetPanel;
import gap.client.ui.inventoryui.checkstock.CheckStockPanel;
import gap.client.ui.inventoryui.initialstock.InitialStockPanel;
import gap.client.ui.inventoryui.observestock.ObserveStockPanel;
import gap.client.ui.inventoryui.stockinorderinput.StockinOrderInputPanel;
import gap.client.ui.inventoryui.stockoutorderinput.StockoutOrderInputPanel;

public class InventoryInitailler extends FrameInitialler{
	/**
	 * 初始化仓库管理人员界面
	 * 
	 * @param mainFrame
	 */
	protected void specificInitial(MainFrame mainFrame) {
		NavigateBar navBar = mainFrame.getNavigateBar();
		NavigateButton checkStock = new NavigateButton(mainFrame,
				new CheckStockPanel(mainFrame), navBar, "库存盘点");
		NavigateButton observeStock = new NavigateButton(mainFrame, new ObserveStockPanel(mainFrame), navBar, "库存查看");
		NavigateButton stockin = new NavigateButton(mainFrame,
				new StockinOrderInputPanel(mainFrame), navBar, "快递入库");
		NavigateButton stockout = new NavigateButton(mainFrame,
				new StockoutOrderInputPanel(mainFrame), navBar, "快递出库");
		NavigateButton setAlarmValue = new NavigateButton(mainFrame,
				new AlarmValueSetPanel(mainFrame), navBar, "设置警戒值");
		NavigateButton initialStock = new NavigateButton(mainFrame,
				new InitialStockPanel(mainFrame), navBar, "库存信息初始化");
		navBar.addButton(checkStock);
		navBar.addButton(observeStock);
		navBar.addButton(stockin);
		navBar.addButton(stockout);
		navBar.addButton(setAlarmValue);
		navBar.addButton(initialStock);
		navBar.setSelect(checkStock);
		mainFrame.setVisible(true);
	}

}
