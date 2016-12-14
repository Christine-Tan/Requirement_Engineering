package gap.server.data.util;

import gap.common.util.Gender;

import org.junit.Before;
import org.junit.Test;

public class InsertSQLTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		InsertSQL insertSQL = new InsertSQL("tableName");
		insertSQL.clear();
		insertSQL.add("name", "yyf");
		insertSQL.add("gender", "male");
		insertSQL.add("age", 20);
		insertSQL.add("favorite", "...");
		insertSQL.add("gender", Gender.FEMALE);
		System.out.println(insertSQL.createSQL());
	}

}
