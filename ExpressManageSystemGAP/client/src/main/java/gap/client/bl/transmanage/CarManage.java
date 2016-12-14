package gap.client.bl.transmanage;

import gap.client.blservice.transmanageblservice.CarService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.TransDataController;
import gap.client.util.AbstractOperation;
import gap.client.util.Car;
import gap.client.util.Operation;
import gap.client.vo.CarVO;
import gap.common.po.CarPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class CarManage implements CarService {
	private static final String ADD = "addCar", MODIFY = "modifyCar",
			DELETE = "deleteCar";
	List<Operation> operations;
	TransDataController controller;

	public CarManage() {
		controller = ControllerFactory.getTransDataController();
		operations = new ArrayList<Operation>();
	}

	@Override
	public List<CarVO> getAll() {
		// TODO 自动生成的方法存根
		List<CarVO> cars = new ArrayList<CarVO>();
		for (CarPO carpo : controller.getAllCar()) {
			Car car = new Car(carpo);
			cars.add(car.toCarVO());
		}
		return cars;
	}

	@Override
	public CarVO getSingle(String id) {
		// TODO 自动生成的方法存根
		Car car = new Car(controller.findCar(id));
		return car.toCarVO();
	}

	@Override
	public void modify(Car car) {
		// TODO 自动生成的方法存根
		operations.add(new ModifyOperation(car.toCarPO()));
	}

	@Override
	public void delete(String id) {
		// TODO 自动生成的方法存根
		operations.add(new DeleteOperation(id));
	}

	@Override
	public void add(Car car) {
		// TODO 自动生成的方法存根
		operations.add(new AddOperation(car.toCarPO()));
	}

	public ResultMessage flush() {
		for (Operation ope : operations) {
			ResultMessage re = ope.excute();
			if (!re.equals(ResultMessage.SUCCEED)) {
				operations.clear();
				return re;
			}
		}
		operations.clear();
		return ResultMessage.SUCCEED;
	}

	class AddOperation extends AbstractOperation {
		public AddOperation(Object args) {
			super(controller, ADD, args);
			// TODO 自动生成的构造函数存根
		}

	}

	class ModifyOperation extends AbstractOperation {
		public ModifyOperation(Object args) {
			super(controller, MODIFY, args);
			// TODO 自动生成的构造函数存根
		}
	}

	class DeleteOperation extends AbstractOperation {
		public DeleteOperation(Object args) {
			super(controller, DELETE, args);
			// TODO 自动生成的构造函数存根
		}
	}

}
