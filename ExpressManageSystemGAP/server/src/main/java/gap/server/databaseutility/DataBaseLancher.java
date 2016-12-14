package gap.server.databaseutility;

import static gap.server.databaseutility.Config.database;
import static gap.server.databaseutility.Config.driver;
import static gap.server.databaseutility.Config.password;
import static gap.server.databaseutility.Config.url;
import static gap.server.databaseutility.Config.user;
import static gap.server.databaseutility.Excutor.cone;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author YangYanfei
 * 启动数据库服务的类
 */
public class DataBaseLancher {
	// 启动数据库，返回数据库引用
	public static Excutor lanch() {
		Config.config();
		try {
			// 加载驱动
			Class.forName(driver);
			// 创建连接
			cone = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Excutor ex = Excutor.getInstance();
		try {
			ex.excute("USE " + database + ";");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ex;
	}
}
