package gap.stub_driver.dataservice.userdata;

import java.rmi.RemoteException;

import gap.common.dataservice.userdataservice.UserDataService;
import gap.common.po.UserPO;
import gap.common.util.Gender;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

public class UserDataService_driver {
	public void driver(UserDataService userData) throws RemoteException {
		UserPO user1 = new UserPO("100000001", UserType.DELIVERY, "Feifei", 1,
				"0011001", "abc123", "杨雁飞", Gender.MALE);
		UserPO user2 = new UserPO("000000001", UserType.ADMINISTRATOR, "Shiny",
				5, "", "admin", "的哇", Gender.FEMALE);
		if (userData.add(user1).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (userData.add(user2).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (userData.add(user1).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,car exited");
		}
		UserPO get = userData.findById("100000001");
		if (get != null)
			System.out.println("find:name=" + get.getUserName() + ",password="
					+ get.getPassword());
		get = userData.findById("100000002");
		if (get == null)
			System.out.println("find failed,not found");
		user1.setUserId("100000002");
		if (userData.modify(user1).equals(ResultMessage.SUCCEED))
			System.out.println("modify succeed");
		if (userData.delete("100000002").equals(ResultMessage.SUCCEED))
			System.out.println("delete succeed");
		if (userData.delete("100000002").equals(ResultMessage.NOTFOUND))
			System.out.println("delete failed,not found");
	}

	public static void main(String[] args) throws RemoteException {
		UserDataService userData = new UserDataService_stub();
		UserDataService_driver driver = new UserDataService_driver();
		driver.driver(userData);
	}
}
