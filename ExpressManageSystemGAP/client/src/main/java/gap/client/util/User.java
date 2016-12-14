package gap.client.util;

import gap.client.vo.UserVO;
import gap.common.po.UserPO;
import gap.common.util.Gender;
import gap.common.util.UserType;

public class User {
	// 用户编号
	private String userId;
	// 用户类型
	private UserType type;
	// 用户名
	private String userName;
	// 密码
	private String password;
	// 所属机构的id
	private String insId;
	// 用户性别
	private Gender gender;
	// 用户姓名
	private String name;

	public User() {

	}

	public User(UserPO po) {
		this.userId = po.getUserId();
		this.userName = po.getUserName();
		this.password = po.getPassword();
		this.type = po.getType();
		this.insId = po.getIns_id();
		this.name = po.getName();
		this.gender = po.getGender();
	}

	public User(String userId, UserType type, String userName, String password,
			String insId, String name, Gender gender) {
		super();
		this.userId = userId;
		this.type = type;
		this.userName = userName;
		this.insId = insId;
		this.password = password;
		this.name = name;
		this.gender = gender;
	}

	public UserPO toUserPO(){
		UserPO po=new UserPO(userId, userName, password, name, type, gender,
				insId);
		return po;
	}
	
	public UserVO toUserVO(String insname) {
		return new UserVO(userId, userName, password, name, type, gender,
				insname);
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

	public String getInsId() {
		return insId;
	}

	public void setInsId(String insid) {
		this.insId = insid;
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
