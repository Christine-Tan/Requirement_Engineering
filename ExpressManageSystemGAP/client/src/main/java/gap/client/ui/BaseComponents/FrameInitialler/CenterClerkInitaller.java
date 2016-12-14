package gap.client.ui.BaseComponents.FrameInitialler;

import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.NavigateBar;
import gap.client.ui.BaseComponents.NavigateButton;
import gap.client.ui.bussinessui.arrivedorder.ArrivedOrderPanel;
import gap.client.ui.bussinessui.carmanage.CarManagePanel;
import gap.client.ui.bussinessui.drivermanage.DriverManagePanel;
import gap.client.ui.bussinessui.loadorder.LoadOrderPanel;

public class CenterClerkInitaller extends FrameInitialler{

	/**
	 * 初始化中转中心业务员界面
	 *
	 * @param mainFrame
	 */
	protected void specificInitial(MainFrame mainFrame) {
		NavigateBar navBar = mainFrame.getNavigateBar();
		NavigateButton arrivedOrder = new NavigateButton(mainFrame,
				new ArrivedOrderPanel(mainFrame), navBar, "制定到达单");
		NavigateButton loadOrder = new NavigateButton(mainFrame,
				new LoadOrderPanel(mainFrame), navBar, "制定中转单");
		NavigateButton carManage = new NavigateButton(mainFrame,
				new CarManagePanel(mainFrame), navBar, "车辆管理");
		NavigateButton driverManage = new NavigateButton(mainFrame,
				new DriverManagePanel(mainFrame), navBar, "司机管理");

		navBar.addButton(arrivedOrder);
		navBar.addButton(loadOrder);
		navBar.addButton(driverManage);
		navBar.addButton(carManage);
		navBar.setSelect(arrivedOrder);
		mainFrame.setVisible(true);

	}
}
