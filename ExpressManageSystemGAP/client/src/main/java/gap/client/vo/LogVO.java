package gap.client.vo;

import gap.client.util.User;
import gap.common.po.LogPO;

public class LogVO {

	private User user;
	// private Date date;
	private String date;
	private String operate;
	
	public LogVO(LogPO log) {
		// TODO Auto-generated constructor stub
		user=new User(log.getUserPO());
		date=log.getDate();
		operate=log.getOperate();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}
 
}
