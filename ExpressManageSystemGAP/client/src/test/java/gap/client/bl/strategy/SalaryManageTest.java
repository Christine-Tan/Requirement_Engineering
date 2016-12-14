package gap.client.bl.strategy;

import gap.client.datacontroller.NetModule;
import gap.client.vo.SalaryVO;
import gap.common.util.UserType;

import org.junit.Before;
import org.junit.Test;

public class SalaryManageTest {
	SalaryManage salary;

	@Before
	public void setUp() throws Exception {
		NetModule.connect();
		salary = new SalaryManage();
	}

	@Test
	public void testGetAll() {
		salary.getAll();
	}

	@Test
	public void testGetSalary() {
		salary.getSalary(UserType.ACCOUNTER);
	}

	@Test
	public void testModifySalary() {
		SalaryVO vo = new SalaryVO();
		salary.modifySalary(vo);
	}

	@Test
	public void testAddSalary() {
		SalaryVO vo = new SalaryVO();
		salary.addSalary(vo);
	}

}
