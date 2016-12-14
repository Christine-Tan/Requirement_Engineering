package gap.stub_driver.blservice.car;

import gap.client.blservice.transmanageblservice.CarService;
import gap.client.util.Car;
import gap.client.vo.CarVO;
import gap.common.util.ResultMessage;

public class CarService_driver {
	public void driver(CarService car) {
		Car car1 = new Car("0011001001", "88888", 10, "0011001");
		Car car2 = new Car("0010001001", "44444", 5, "0010001");
		if (car.add(car1).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (car.add(car2).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (car.add(car1).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,car exited");
		}
		CarVO get = car.getSingle("0011001001");
		if (get != null)
			System.out.println("find:id=" + get.getCar_id() + ",num="
					+ get.getCar_num() + ",serve_time=" + get.getServe_time());
		get = car.getSingle("0011001002");
		if (get == null)
			System.out.println("find failed,not found");
		car1.setCar_num("777777");
		if (car.modify(car1).equals(ResultMessage.SUCCEED))
			System.out.println("modify succeed");
		if (car.delete("0010001001").equals(ResultMessage.SUCCEED))
			System.out.println("delete succeed");
		if (car.delete("0010001002").equals(ResultMessage.NOTFOUND))
			System.out.println("delete failed,not found");
	}

	public static void main(String[] args) {
		CarService car = new CarService_stub();
		CarService_driver driver = new CarService_driver();
		driver.driver(car);

	}
}
