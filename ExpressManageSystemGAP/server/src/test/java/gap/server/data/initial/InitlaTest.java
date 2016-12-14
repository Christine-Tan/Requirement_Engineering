package gap.server.data.initial;

import static org.junit.Assert.fail;
import gap.common.dataservice.initialdata.InitialDataService;
import gap.common.po.AccountPO;
import gap.common.po.InitialHistoryPO;
import gap.common.po.InitialPeoplePO;
import gap.common.po.InitialStockPO;
import gap.server.initial.NetInitial;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InitlaTest {

	InitialDataService initial;
	@Before
	public void setUp() throws Exception {
		NetInitial.initial();
		initial = InitialDataServiceImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddInitial() {
		ArrayList<AccountPO> accountPOs = new ArrayList<>();
		accountPOs.add(new AccountPO("账户1", 1000));
		accountPOs.add(new AccountPO("账户2", 1000));
		
		ArrayList<InitialPeoplePO> peoplePOs = new ArrayList<>();
		int[] numbers = {2,2,3,4,5,6};
		peoplePOs.add(new InitialPeoplePO("北京", numbers));
		peoplePOs.add(new InitialPeoplePO("南京", numbers));
		
		ArrayList<InitialStockPO> stockPOs = new ArrayList<>();
		stockPOs.add(new InitialStockPO("南京仓库", 200, 0.53));
		stockPOs.add(new InitialStockPO("北京仓库", 500, 0.83));
		stockPOs.add(new InitialStockPO("上海仓库", 130, 0.66));
		
		InitialHistoryPO po = new InitialHistoryPO(accountPOs, peoplePOs, stockPOs);
		
		try {
			initial.addInitial(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetHistory() {
		try {
			List<InitialHistoryPO> historyPOs = initial.getHistory();
			System.out.println(historyPOs.size());
			for(InitialHistoryPO po:historyPOs){
				System.out.println("-------------------");
				System.out.println(po.accountPOs);
				System.out.println(po.initialPeoplePOs);
				System.out.println(po.initialStockPOs);
				System.out.println("-------------------");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
