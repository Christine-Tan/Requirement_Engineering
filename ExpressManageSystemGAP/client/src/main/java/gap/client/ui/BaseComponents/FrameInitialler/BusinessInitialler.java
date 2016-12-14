package gap.client.ui.BaseComponents.FrameInitialler;

import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.NavigateBar;
import gap.client.ui.BaseComponents.NavigateButton;
import gap.client.ui.bussinessui.arrivedorder.ArrivedOrderPanel;
import gap.client.ui.bussinessui.billorderui.BillOrderPanel;
import gap.client.ui.bussinessui.carmanage.CarManagePanel;
import gap.client.ui.bussinessui.deliveryorder.DeliveryOrderPanel;
import gap.client.ui.bussinessui.drivermanage.DriverManagePanel;
import gap.client.ui.bussinessui.loadorder.LoadOrderPanel;

public class BusinessInitialler extends FrameInitialler{

	@Override
	protected void specificInitial(MainFrame mainFrame) {
			NavigateBar navBar = mainFrame.getNavigateBar();
			NavigateButton arrivedOrder = new NavigateButton(mainFrame,
					new ArrivedOrderPanel(mainFrame), navBar, "制定到达单");
			NavigateButton billOrder = new NavigateButton(mainFrame,
					new BillOrderPanel(mainFrame), navBar, "制定收款单");
			NavigateButton carManage = new NavigateButton(mainFrame,
					new CarManagePanel(mainFrame), navBar, "司机信息管理");
			NavigateButton driverManage = new NavigateButton(mainFrame,
					new DriverManagePanel(mainFrame), navBar, "车辆信息管理");
			NavigateButton loadOrder = new NavigateButton(mainFrame,
					new LoadOrderPanel(mainFrame), navBar, "制定装车单");
			NavigateButton deliveryOrder = new NavigateButton(mainFrame,
					new DeliveryOrderPanel(mainFrame), navBar, "制定派件单");
			navBar.addButton(loadOrder);
			navBar.addButton(arrivedOrder);
			navBar.addButton(deliveryOrder);
			navBar.addButton(billOrder);
			navBar.addButton(carManage);
			navBar.addButton(driverManage);
			navBar.setSelect(carManage);
			mainFrame.setVisible(true);
	}
		
	

}
