package gap.client.ui.BaseComponents;

import gap.client.util.LocalInfo;
import gap.client.util.User;
import gap.common.util.Gender;
import gap.common.util.UserType;

public class Tester {
	public static void main(String[] args) {
		LocalInfo.ins_id = "0010001";
		User user = new User("000000005", UserType.DELIVERY, "xiaoming",
				"123456", "0010001", "小明", Gender.MALE);
		LocalInfo.localuser = user;
		new MainFrame();
	}
}
