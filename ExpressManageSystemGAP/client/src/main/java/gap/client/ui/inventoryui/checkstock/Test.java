package gap.client.ui.inventoryui.checkstock;

import gap.client.datacontroller.NetModule;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.util.LocalInfo;
import gap.client.util.User;
import gap.common.util.Gender;
import gap.common.util.UserType;

public class Test {
	public static void main(String[] args) {
		LocalInfo.ins_id = "0010001";
		User user = new User("000000005", UserType.INVENTORY, "xiaoming",
				"123456", "0010001", "王小二", Gender.MALE);
		LocalInfo.localuser = user;
		LocalInfo.ins_id = "0011001";
		MainFrame mainFrame = new MainFrame();
		NetModule.initial(mainFrame);
		NetModule.connect();
		CheckStockPanel panel = new CheckStockPanel(mainFrame);
		mainFrame.setMainPanel(panel);
		mainFrame.setVisible(true);
		
	}
	
//	public static void paint(CheckStockPanel panel){
//		for(int i = 0;i<panel.list.items.size();i++){
//			ListItem item = panel.list.items.get(i);
//			for(int j = 0;j<item.shelf.length;j++){
//				Unit unit = item.shelf[j];
//				unit.icon.paint();
//			}
//		}
//	}
}
