package gap.server.data.util;

import org.junit.Before;
import org.junit.Test;

public class UpdateSQLTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		UpdateSQL update = new UpdateSQL("tableName");
		update.add("received", true);
		update.setKey("id", "001");
		try {
			System.out.println(update.createSQL());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
