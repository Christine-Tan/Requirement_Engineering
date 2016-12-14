package gap.stub_driver.blservice.user;

import gap.client.blservice.userblservice.UserService;
import gap.client.vo.UserVO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

public class UserService_driver {
	public void driver(UserService user) {
		UserVO user1 = new UserVO("100000001", UserType.DELIVERY, "Feifei", 1,"0011001","abc123");
		UserVO user2 = new UserVO("000000001",UserType.ADMINISTRATOR,"Shiny",5,"","admin");
		if (user.add(user1).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (user.add(user2).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (user.add(user1).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,car exited");
		}
		UserVO get = user.getSingle("100000001");
		if (get != null)
			System.out.println("find:name=" + get.getUserName() + ",password="
					+ get.getPassword());
		get = user.getSingle("100000002");
		if (get == null)
			System.out.println("find failed,not found");
		user1.setUserId("100000002");
		if (user.modify(user1).equals(ResultMessage.SUCCEED))
			System.out.println("modify succeed");
		if (user.delete("100000002").equals(ResultMessage.SUCCEED))
			System.out.println("delete succeed");
		if (user.delete("100000002").equals(ResultMessage.NOTFOUND))
			System.out.println("delete failed,not found");
	}
	
	public static void main(String[] args) {
		UserService user= new UserService_stub();
		UserService_driver driver = new UserService_driver();
		driver.driver(user);
	}
}
