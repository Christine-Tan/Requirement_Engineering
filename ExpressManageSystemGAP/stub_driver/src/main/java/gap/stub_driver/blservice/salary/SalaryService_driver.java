package gap.stub_driver.blservice.salary;

import java.rmi.RemoteException;

import gap.client.blservice.strategyblservice.SalaryService;
import gap.client.vo.SalaryVO;
import gap.common.dataservice.strategydataservice.SalaryDataService;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;
import gap.stub_driver.dataservice.salarydata.SalaryDataService_driver;
import gap.stub_driver.dataservice.salarydata.SalaryDataService_stub;

public class SalaryService_driver {
	public void driver(SalaryService salary) {
		SalaryVO salary1 = new SalaryVO(UserType.BUSSINESSCLERK, 3000);
		SalaryVO salary2 = new SalaryVO(UserType.INVENTORY, 2000);
		if (salary.addSalary(salary1).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (salary.addSalary(salary2).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (salary.addSalary(salary1).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,car exited");
		}
		SalaryVO get = salary.getSalary(UserType.DELIVERY);
		if (get != null)
			System.out.println("find:type=" + get.getType() + ",salary="
					+ get.getSalary());
		get = salary.getSalary(UserType.MANAGER);
		if (get == null)
			System.out.println("find failed,not found");
		salary1.setType(UserType.CENTERCLERK);
		if (salary.modifySalary(salary1).equals(ResultMessage.SUCCEED))
			System.out.println("modify succeed");
	}

	public static void main(String[] args) {
		SalaryDataService salaryData = new SalaryDataService_stub();
		SalaryDataService_driver driver = new SalaryDataService_driver();
		try {
			driver.driver(salaryData);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
