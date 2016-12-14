package gap.server.data.strategydata;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.strategydataservice.SalaryDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.po.SalaryPO;
import gap.common.util.UserType;
import gap.server.initial.NetInitial;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

public class SalaryDataServiceImplTest {
	@Before
	public void setup() throws Exception {
		NetInitial.initial();
	}

	@Test
	public void test() throws RemoteException, MalformedURLException,
			NotBoundException {
		SalaryDataService salarydata = (SalaryDataService) Naming
				.lookup(RMIConfig.url + ServiceName.SALARY_DATA_SERVICE);
		SalaryPO po1 = new SalaryPO(UserType.ADMINISTRATOR, 2500);
		SalaryPO po2 = new SalaryPO(UserType.BUSSINESSCLERK, 3000);
		SalaryPO po3 = new SalaryPO(UserType.CENTERCLERK, 3000);
		SalaryPO po4 = new SalaryPO(UserType.DELIVERY, 0.02);
		SalaryPO po5 = new SalaryPO(UserType.INVENTORY, 2000);
		SalaryPO po6 = new SalaryPO(UserType.ACCOUNTER, 4000);
		System.out.println(salarydata.add(po1).getMessage());
		System.out.println(salarydata.add(po2).getMessage());
		System.out.println(salarydata.add(po3).getMessage());
		System.out.println(salarydata.add(po4).getMessage());
		System.out.println(salarydata.add(po5).getMessage());
		System.out.println(salarydata.add(po6).getMessage());

		SalaryPO po7 = new SalaryPO(UserType.ACCOUNTER, 3500);
		System.out.println(salarydata.modify(po7).getMessage());
		System.out.println(salarydata.find(UserType.ACCOUNTER).getSalary());
		System.out.println("GET ALL!");
		for (SalaryPO get : salarydata.getAll()) {
			System.out.println(get.getType().toString() + get.getSalary());
		}
	}

}
