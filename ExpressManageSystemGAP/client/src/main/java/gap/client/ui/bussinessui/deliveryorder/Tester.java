package gap.client.ui.bussinessui.deliveryorder;

import gap.client.datacontroller.NetModule;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.util.LocalInfo;
import gap.client.util.User;
import gap.common.util.Gender;
import gap.common.util.UserType;

public class Tester {
	public static void main(String[] args) {
		User user = new User("000000005", UserType.BUSSINESSCLERK, "xiaoming",
				"123456", "0010001", "小红", Gender.FEMALE);
		LocalInfo.localuser = user;
		LocalInfo.ins_id = "0010001";
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
		NetModule.initial(mainFrame);
		NetModule.connect();
		DeliveryOrderPanel panel = new DeliveryOrderPanel(mainFrame);
//		UserVO vo=new UserVO();
//		UserVO vo1=new UserVO();
//		UserVO vo2=new UserVO();
//		UserVO vo3=new UserVO();
//		UserVO vo4=new UserVO();
//		UserVO vo5=new UserVO();
//		
//		vo.setName("yyf");
//		vo1.setName("txy");
//		vo2.setName("txy1");
//		vo3.setName("txy2");
//		vo4.setName("txy3");
//		vo5.setName("txy4");
//		panel.mainContentPanel.addUser(vo);
//		panel.mainContentPanel.addUser(vo1);
//		panel.mainContentPanel.addUser(vo2);
//		panel.mainContentPanel.addUser(vo3);
//		panel.mainContentPanel.addUser(vo4);
//		panel.mainContentPanel.addUser(vo5);
		mainFrame.setMainPanel(panel);

	}
}
