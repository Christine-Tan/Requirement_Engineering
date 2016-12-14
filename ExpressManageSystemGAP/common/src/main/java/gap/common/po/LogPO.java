package gap.common.po;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogPO implements Serializable {

	private UserPO userPO;
	// private Date date;
	String date;
	private String operate;

	public LogPO(UserPO userPO, String date, String operate) {
		this.setUserPO(userPO);
		this.setDate(date);
		this.setOperate(operate);
	}

	
	/**
	 * 不带日期的构造方法，日期默认为当前日期
	 * @param userPO
	 * @param operate
	 */
	public LogPO(UserPO userPO, String operate) {
		this.setUserPO(userPO);
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		this.setDate(time);
		this.setOperate(operate);
	}
	
	public UserPO getUserPO() {
		return userPO;
	}

	public void setUserPO(UserPO userPO) {
		this.userPO = userPO;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
