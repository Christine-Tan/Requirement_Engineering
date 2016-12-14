package gap.client.ui.BaseComponents.FrameInitialler;

import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.NavigateBar;
import gap.client.ui.BaseComponents.NavigateButton;
import gap.client.ui.administratorui.UserPanel;
import gap.common.util.UserType;

public class AdminInitialler extends AccountorInitialler{
	/**
	 * 初始化管理员界面
	 *
	 * @param mainFrame
	 */
	public AdminInitialler() {
		// TODO Auto-generated constructor stub
	}

	protected void specificInitial(MainFrame mainFrame) {
		NavigateBar navBar = mainFrame.getNavigateBar();
		NavigateButton admin = new NavigateButton(mainFrame, new UserPanel(
				mainFrame, UserType.ADMINISTRATOR), navBar, "管理员");
		NavigateButton accounter = new NavigateButton(mainFrame, new UserPanel(
				mainFrame, UserType.ACCOUNTER), navBar, "财务人员");
		NavigateButton busclerk = new NavigateButton(mainFrame, new UserPanel(
				mainFrame, UserType.BUSSINESSCLERK), navBar, "营业厅业务员");
		NavigateButton cenclerk = new NavigateButton(mainFrame, new UserPanel(
				mainFrame, UserType.CENTERCLERK), navBar, "中转中心业务员");
		NavigateButton delivery = new NavigateButton(mainFrame, new UserPanel(
				mainFrame, UserType.DELIVERY), navBar, "快递员");
		NavigateButton inventory = new NavigateButton(mainFrame, new UserPanel(
				mainFrame, UserType.INVENTORY), navBar, "仓库管理人员");
		NavigateButton manager = new NavigateButton(mainFrame, new UserPanel(
				mainFrame, UserType.MANAGER), navBar, "总经理");
		navBar.addButton(admin);
		navBar.addButton(accounter);
		navBar.addButton(busclerk);
		navBar.addButton(cenclerk);
		navBar.addButton(delivery);
		navBar.addButton(inventory);
		navBar.addButton(manager);
		navBar.setSelect(admin);
		mainFrame.setVisible(true);
	
	}
}
