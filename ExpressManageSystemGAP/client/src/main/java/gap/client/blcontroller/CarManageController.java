package gap.client.blcontroller;

import gap.client.bl.transmanage.CarManage;
import gap.client.util.Car;
import gap.client.util.LocalInfo;
import gap.client.vo.CarVO;
import gap.common.util.ResultMessage;

import java.util.List;

public class CarManageController {
	private static CarManage carManage = new CarManage();

	public static List<CarVO> getAll() {
		// TODO 自动生成的方法存根
		return carManage.getAll();
	}

	public static String nextId() throws Exception {
		List<CarVO> cars = carManage.getAll();
		if (cars.size() == 0)
			return LocalInfo.ins_id + "001";
		int num = Integer.valueOf(cars.get(cars.size() - 1).getCar_id()
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

	public static CarVO getSingle(String id) {
		// TODO 自动生成的方法存根
		return carManage.getSingle(id);
	}

	public static void modify(CarVO vo) {
		// TODO 自动生成的方法存根
		carManage.modify(new Car(vo));
	}

	public static void delete(String id) {
		// TODO 自动生成的方法存根
		carManage.delete(id);
	}

	public static void add(CarVO vo) {
		// TODO 自动生成的方法存根
		carManage.add(new Car(vo));
	}

	public static ResultMessage flush() {
		// TODO 自动生成的方法存根
		return carManage.flush();
	}

}
