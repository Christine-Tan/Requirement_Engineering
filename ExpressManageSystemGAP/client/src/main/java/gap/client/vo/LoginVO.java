package gap.client.vo;

import gap.common.util.UserType;

public class LoginVO {
	private UserType userType;
	private boolean succeed;

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
