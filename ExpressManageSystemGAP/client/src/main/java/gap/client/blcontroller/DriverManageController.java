package gap.client.blcontroller;

import gap.client.bl.transmanage.DriverManage;
import gap.client.util.Driver;
import gap.client.util.LocalInfo;
import gap.client.vo.DriverVO;
import gap.common.util.ResultMessage;

import java.util.List;

public class DriverManageController {
	public static DriverManage driverManage = new DriverManage();

	public static List<DriverVO> getAll() {
		// TODO 自动生成的方法存根
		return driverManage.getAll();
	}

	public static String nextId() throws Exception {
		List<DriverVO> drivers = driverManage.getAll();
		if (drivers.size() == 0)
			return LocalInfo.ins_id + "001";
		int num = Integer.valueOf(drivers.get(drivers.size() - 1).getId()
				.substring(7));
		num++;
		if (num >= 1000)
			throw new Exception();
		String last = String.valueOf(num);
		int length = last.length();
		while (length++ < 3)
			last = "0" + last;
		return LocalInfo.ins_id + last;
	}

	public static DriverVO getSingle(String id) {
		// TODO 自动生成的方法存根
		return driverManage.getSingle(id);
	}

	public static void modify(DriverVO vo) {
		// TODO 自动生成的方法存根
		driverManage.modify(new Driver(vo));
	}

	public static void delete(String id) {
		// TODO 自动生成的方法存根
		driverManage.delete(id);
	}

	public static void add(DriverVO vo) {
		// TODO 自动生成的方法存根
		System.out.println(vo.getId() + "," + vo.getName());
		driverManage.add(new Driver(vo));
	}

	public static ResultMessage flush() {
		// TODO 自动生成的方法存根
		return driverManage.flush();
	}

}
