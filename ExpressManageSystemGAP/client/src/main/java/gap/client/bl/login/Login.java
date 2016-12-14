package gap.client.bl.login;

import gap.client.blservice.loginblservice.LoginService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.LoginDataController;
import gap.client.util.LocalInfo;
import gap.client.util.User;
import gap.client.vo.LoginVO;
import gap.common.po.UserPO;

public class Login implements LoginService {
	LoginDataController loginData = ControllerFactory.getLoginDataController();

	@Override
	public LoginVO login(String username, String password) {
		// TODO 自动生成的方法存根
		LoginVO log = new LoginVO();
		UserPO user = loginData.findByUsername(username);
		System.out.println(user);
		if (user==null||!user.getPassword().equals(password)) {
			log.setSucceed(false);
			return log;
		}
		log.setSucceed(true);
		log.setUserType(user.getType());

		setLocalInfo(user);
		return log;
	}

	/**
	 * 登录后设置本地信息
	 *
	 * @param user
	 */
	private void setLocalInfo(UserPO user) {
		LocalInfo.localuser = new User(user);
		LocalInfo.ins_id = user.getIns_id();
		
	}

}
