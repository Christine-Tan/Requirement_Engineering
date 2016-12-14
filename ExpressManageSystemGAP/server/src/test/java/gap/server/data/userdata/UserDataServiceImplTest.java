package gap.server.data.userdata;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.userdataservice.UserDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.po.UserPO;
import gap.common.util.UserType;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

public class UserDataServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void testGetNum(){
		UserDataService userdata = null;
		try {
			userdata = (UserDataService) Naming
					.lookup(RMIConfig.url + ServiceName.USER_DATA_SERVICE);
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			int num = userdata.getPeopleNum("0010001", UserType.DELIVERY);
			System.out.println(num);
			num = userdata.getPeopleNum("0010001", UserType.BUSSINESSCLERK);
			System.out.println(num);
			num = userdata.getPeopleNum("0010001", UserType.ACCOUNTER);
			System.out.println(num);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws RemoteException, MalformedURLException,
			NotBoundException {
		UserDataService userdata = (UserDataService) Naming
				.lookup(RMIConfig.url + ServiceName.USER_DATA_SERVICE);
		// UserPO po1 = new UserPO("000000001", UserType.ADMINISTRATOR, "yyf",
		// 0,
		// "0010001", "123456", "杨雁飞", Gender.MALE);
		// UserPO po2 = new UserPO("000000002", UserType.ADMINISTRATOR, "txy",
		// 0,
		// "0010001", "123456", "谭昕玥", Gender.FEMALE);
		// UserPO po3 = new UserPO("000000003", UserType.ADMINISTRATOR, "plw",
		// 0,
		// "0010001", "123456", "潘凌伟", Gender.MALE);
		// UserPO po4 = new UserPO("000000004", UserType.ADMINISTRATOR,
		// "shenbin",
		// 0, "0010001", "123456", "申斌", Gender.MALE);
		// System.out.println(userdata.add(po1).getMessage());
		// System.out.println(userdata.add(po2).getMessage());
		// System.out.println(userdata.add(po3).getMessage());
		// System.out.println(userdata.add(po4).getMessage());
		for (UserPO get : userdata.getAll(UserType.ADMINISTRATOR))
			System.out.println(get.getName() + "," + get.getType());
		// userdata.modify(po1);
		userdata.setPaid("0000000002");
		Date da = new Date(2015 - 1900, 4, 1);
		System.out.println("unpaiduser," + da.toString());

		for (UserPO get : userdata.findUnpaidUser(da))
			System.out.println(get.getName() + "," + get.getType());
		UserPO user = userdata.findByUsername("yyf");
		System.out.println("password:" + user.getPassword());
	}
}
