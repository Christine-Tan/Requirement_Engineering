package gap.stub_driver.blservice.driver;

import gap.client.blservice.transmanageblservice.DriverService;
import gap.client.vo.DriverVO;
import gap.common.util.Gender;
import gap.common.util.ResultMessage;

public class DriverService_driver {
	public void drive(DriverService driver) {
		DriverVO driver1 = new DriverVO("0011001001", "0011001", "yyf",
				"19960301", "500113199603013932", "15520065137", "20200101",
				Gender.MALE);
		DriverVO driver2 = new DriverVO("0010001001", "0010001", "txy",
				"19700101", "500000000000000000", "119", "20200101",
				Gender.FEMALE);
		if (driver.add(driver1).equals(ResultMessage.EXITED)) {
			System.out.println("add succeed");
		}
		System.out.println("add succeed");

		if (driver.add(driver2).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (driver.add(driver1).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,Driver exited");
		}
		DriverVO get = driver.getSingle("0011001001");
		if (get != null)
			System.out.println("find:id=" + get.getId() + ",name="
					+ get.getName() + ",gender=" + get.getGender() + ",phone="
					+ get.getPhone());
		get = driver.getSingle("0011001002");
		if (get == null)
			System.out.println("find failed,not found");
		driver1.setPhone("110");
		if (driver.modify(driver1).equals(ResultMessage.SUCCEED))
			System.out.println("modify succeed");
		if (driver.delete("0010001001").equals(ResultMessage.SUCCEED))
			System.out.println("delete succeed");
		if (driver.delete("0010001002").equals(ResultMessage.NOTFOUND))
			System.out.println("delete failed,not found");
	}

	public static void main(String[] args) {
		DriverService driverse = new DriverService_stub();
		DriverService_driver driver = new DriverService_driver();
		driver.drive(driverse);

	}
}
