package gap.client.ui.bussinessui.arrivedorder;

import gap.client.datacontroller.NetModule;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.util.LocalInfo;
import gap.client.util.User;
import gap.common.util.Gender;
import gap.common.util.UserType;

public class Tester {
	public static void main(String[] args) {

		User user = new User("000000005", UserType.BUSSINESSCLERK, "xiaoming",
				"123456", "0010001", "小明", Gender.MALE);
		LocalInfo.localuser = user;
		LocalInfo.ins_id = "0011001";
		MainFrame mainFrame = new MainFrame();
		NetModule.initial(mainFrame);
		NetModule.connect();
		ArrivedOrderPanel panel = new ArrivedOrderPanel(mainFrame);
		mainFrame.setMainPanel(panel);
	}
}
