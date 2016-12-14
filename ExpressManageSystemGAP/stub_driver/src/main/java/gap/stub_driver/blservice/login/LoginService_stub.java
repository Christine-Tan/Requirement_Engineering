package gap.stub_driver.blservice.login;

import java.rmi.RemoteException;

import gap.client.blservice.loginblservice.LoginService;
import gap.client.vo.LoginVO;
import gap.common.dataservice.userdataservice.UserDataService;
import gap.common.po.UserPO;
import gap.common.util.Gender;
import gap.common.util.UserType;
import gap.stub_driver.dataservice.userdata.UserDataService_stub;

public class LoginService_stub implements LoginService {
	UserDataService user;

	public LoginService_stub() throws RemoteException {
		user = new UserDataService_stub();
		UserPO po = new UserPO("001", UserType.BUSSINESSCLERK, "yyf", 0,
				"0010001", "000000", "杨雁飞", Gender.MALE);
		user.add(po);
	}

	@Override
	public LoginVO login(String username, String password) {
		// TODO 自动生成的方法存根
		LoginVO log = new LoginVO();
		log.setSucceed(false);
		try {
			for (UserPO po : user.getAll()) {
				if (po.getUserName().equals(username)
						&& po.getPassword().equals(password)) {
					log.setSucceed(true);
					log.setUserType(po.getType());
				}
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return log;
	}
}
