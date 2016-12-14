package gap.server.databaseutility;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabase {
	public static void main(String[] args) throws IOException {

		try {
			Excutor excutor = DataBaseLancher.lanch();
			excutor.excute("USE bank;");
			String sql = "SELECT open_date FROM account";
			ResultSet re = excutor.excuteQuery(sql);
			while (re.next()) {
				System.out.println(re.getDate("open_date"));
			}
			excutor.excute("SELECT * FROM employee into outfile 'D://test.csv'"
					+ "fields terminated by ','");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
