package gap.client.blservice.loginblservice;

import gap.client.vo.LoginVO;

public interface LoginService {
	public LoginVO login(String username, String password);
}
