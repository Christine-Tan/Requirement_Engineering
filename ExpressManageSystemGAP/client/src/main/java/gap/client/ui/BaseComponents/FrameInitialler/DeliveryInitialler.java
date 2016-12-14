package gap.client.ui.BaseComponents.FrameInitialler;

import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.NavigateBar;
import gap.client.ui.BaseComponents.NavigateButton;
import gap.client.ui.deliveryui.expressorderinput.ExpressorderInputPanel;
import gap.client.ui.deliveryui.expressorderreceive.ExpressorderReceivePanel;
import gap.client.util.MessageType;

public class DeliveryInitialler extends FrameInitialler{

	@Override
	protected void specificInitial(MainFrame mainFrame) {
		NavigateBar navBar = mainFrame.getNavigateBar();
		ExpressorderInputPanel expressorderInput = new ExpressorderInputPanel(
				mainFrame);
		ExpressorderReceivePanel expressorderReceive = new ExpressorderReceivePanel(
				mainFrame);
		NavigateButton orderInput = new NavigateButton(mainFrame,
				expressorderInput, navBar, "订单信息输入");
		NavigateButton orderReceive = new NavigateButton(mainFrame,
				expressorderReceive, navBar, "收件信息输入");
		navBar.addButton(orderInput);
		navBar.addButton(orderReceive);
		navBar.setSelect(orderInput);
		mainFrame.setVisible(true);
		MainFrame.setMessage("登录成功", MessageType.succeed, 3000);
		
	}

}
