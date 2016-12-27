package gap.server.databaseutility;

/**
 * 
 * @author YangYanfei
 * 存贮数据库配置信息的类
 */
public class Config {
	/**
	 * 数据库驱动、地址、用户名、密码
	 */
	public static String driver, url, user, password, database;
//	public static String filePath = "server/config.txt";

	/**
	 * 读取配置信息
	 */
	public static void config() {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://127.0.0.1:3306/expressmanagesystem?useUnicode=true&characterEncoding=UTF-8";
		user = "root";
		password = "";
		database = "expressmanagesystem";
	}
}
