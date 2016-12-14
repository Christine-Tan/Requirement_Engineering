package gap.server.data.util;

import gap.common.po.AccountPO;
import gap.server.initial.NetInitial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListMakerTest {
	ListMaker<AccountPO> accountMaker;

	private ListMakerTest() {
	};

	@Before
	public void setUp() throws Exception {
		NetInitial.initial();
		accountMaker = new ListMaker<AccountPO>() {

			@Override
			public AccountPO getPO(ResultSet resultSet) {
				// TODO Auto-generated method stub
				String name = null;
				double balance = 0;
				try {
					name = resultSet.getString("Name");
					balance = resultSet.getDouble("Balance");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return new AccountPO(name, balance);
			}
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetListString() {
		ArrayList<AccountPO> accountPOs = accountMaker.getList("Account");
		System.out.println();

	}

}
