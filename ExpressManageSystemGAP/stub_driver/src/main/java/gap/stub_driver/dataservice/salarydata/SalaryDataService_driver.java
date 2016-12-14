package gap.stub_driver.dataservice.salarydata;


import java.rmi.RemoteException;

import gap.common.dataservice.strategydataservice.SalaryDataService;
import gap.common.po.SalaryPO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

public class SalaryDataService_driver {
	public void driver(SalaryDataService salaryData) throws RemoteException {
		SalaryPO salary1 = new SalaryPO(UserType.BUSSINESSCLERK, 3000);
		SalaryPO salary2 = new SalaryPO(UserType.INVENTORY, 2000);
		if (salaryData.add(salary1).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (salaryData.add(salary2).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (salaryData.add(salary1).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,car exited");
		}
		SalaryPO get = salaryData.find(UserType.BUSSINESSCLERK);
		if (get != null)
			System.out.println("find:type=" + get.getType() + ",salary="
					+ get.getSalary());
		get = salaryData.find(UserType.ADMINISTRATOR);
		if (get == null)
			System.out.println("find failed,not found");
		salary1.setType(UserType.CENTERCLERK);
		if (salaryData.modify(salary1).equals(ResultMessage.SUCCEED))
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
