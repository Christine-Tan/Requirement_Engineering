package gap.client.vo;

import gap.common.po.UserPO;
import gap.common.util.Gender;
import gap.common.util.UserType;

/**
 * 
 * @author seven
 *
 */
public class UserVO {
	// 用户编号
	private String userId;
	// 用户类型
	private UserType type;
	// 用户名
	private String userName;
	// 密码
	private String password;
	// 所属机构的名字
	private String insName;
	// 用户性别
	private Gender gender;
	// 用户姓名
	private String name;

	public UserVO() {

	}

	public UserVO(String userId, String userName, String password, String name,
			UserType type, Gender gender, String insName) {
		super();
		this.userId = userId;
		this.type = type;
		this.userName = userName;
		this.insName = insName;
		this.password = password;
		this.name = name;
		this.gender = gender;
	}

	public UserPO toUserPO(String ins_id) {
		return new UserPO(userId, userName, password, name, type, gender,
				ins_id);
	}

	public String getInsName() {
		return insName;
	}

	public void setInsName(String insname) {
		this.insName = insname;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
