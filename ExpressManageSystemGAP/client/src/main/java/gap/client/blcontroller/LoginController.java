package gap.client.blcontroller;

import gap.client.bl.login.Login;
import gap.client.vo.LoginVO;

public class LoginController {
	public static Login login = new Login();

	public static LoginVO login(String username, String password) {
		return login.login(username, password);
	}

}
