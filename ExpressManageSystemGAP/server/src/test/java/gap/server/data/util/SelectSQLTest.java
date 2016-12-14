package gap.server.data.util;

import org.junit.Before;
import org.junit.Test;

public class SelectSQLTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		SelectSQL sql = new SelectSQL("tableName");
		sql.addConstraint("cons1", 10);
		sql.addConstraint("cons2", "value2");
		System.out.println(sql.createSQL());
	}

}
