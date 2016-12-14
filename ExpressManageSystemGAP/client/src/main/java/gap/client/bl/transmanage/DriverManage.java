package gap.client.bl.transmanage;

import gap.client.blservice.transmanageblservice.DriverService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.TransDataController;
import gap.client.util.AbstractOperation;
import gap.client.util.Driver;
import gap.client.util.Operation;
import gap.client.vo.DriverVO;
import gap.common.po.DriverPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class DriverManage implements DriverService {
	private static final String ADD = "addDriver", MODIFY = "modifyDriver",
			DELETE = "deleteDriver";
	List<Operation> operations;
	TransDataController controller;

	public DriverManage() {
		controller = ControllerFactory.getTransDataController();
		operations = new ArrayList<Operation>();
	}

	@Override
	public List<DriverVO> getAll() {
		// TODO 自动生成的方法存根
		List<DriverVO> drivers = new ArrayList<DriverVO>();
		for (DriverPO driverpo : controller.getAllDriver()) {
			Driver driver = new Driver(driverpo);
			drivers.add(driver.toDriverVO());
		}
		return drivers;
	}

	@Override
	public DriverVO getSingle(String id) {
		// TODO 自动生成的方法存根
		DriverPO driverPO = controller.findDriver(id);
		Driver driver = new Driver(driverPO);
		return driver.toDriverVO();
	}

	@Override
	public void modify(Driver vo) {
		// TODO 自动生成的方法存根
		operations.add(new ModifyOperation(vo.toDriverPO()));
	}

	@Override
	public void delete(String id) {
		// TODO 自动生成的方法存根
		operations.add(new DeleteOperation(id));
	}

	@Override
	public void add(Driver vo) {
		// TODO 自动生成的方法存根
		operations.add(new AddOperation(vo.toDriverPO()));
	}

	@Override
	public ResultMessage flush() {
		// TODO 自动生成的方法存根
		for (Operation ope : operations) {
			ResultMessage re = ope.excute();
			if (!re.equals(ResultMessage.SUCCEED)) {
				System.out.println("failed");
				operations.clear();
				return re;
			}
		}
		System.out.println("sucess");
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
