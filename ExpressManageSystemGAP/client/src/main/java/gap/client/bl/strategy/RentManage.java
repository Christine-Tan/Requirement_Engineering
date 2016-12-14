package gap.client.bl.strategy;

import gap.client.blservice.strategyblservice.RentService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.RentDataController;
import gap.client.util.AbstractOperation;
import gap.client.util.Operation;
import gap.client.vo.RentVO;
import gap.common.po.RentPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class RentManage implements RentService {
	private static final String ADD = "add", MODIFY = "modify";
	RentDataController controller;
	List<Operation> operations;

	public RentManage() {
		operations = new ArrayList<Operation>();
		controller = ControllerFactory.getRentDataController();
	}

	@Override
	public List<RentVO> getAll() {
		// TODO Auto-generated method stub
		List<RentVO> rents = new ArrayList<RentVO>();
		for (RentPO po : controller.getAll()) {
			rents.add(new RentVO(po));
		}
		return rents;
	}

	@Override
	public void addRent(RentVO vo) {
		// TODO Auto-generated method stub
		operations.add(new AddOperation(vo.toRentPO()));
	}

	@Override
	public void modifyRent(RentVO vo) {
		// TODO Auto-generated method stub
		operations.add(new ModifyOperation(vo.toRentPO()));
	}

	/**
	 * 将操作缓存起来，按序处理缓存队列
	 * 
	 * @return
	 */
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
		}
	}

	class ModifyOperation extends AbstractOperation {
		public ModifyOperation(Object args) {
			super(controller, MODIFY, args);
		}
	}
}
