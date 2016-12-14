package gap.client.bl.user;

import gap.client.blservice.userblservice.UserService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.InstitutionDataController;
import gap.client.datacontroller.UserDataController;
import gap.client.util.AbstractOperation;
import gap.client.util.Operation;
import gap.client.util.User;
import gap.client.vo.UserVO;
import gap.common.po.UserPO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserManage implements UserService {
	private static final String ADD = "add", DELETE = "delete",
			MODIFY = "modify";
	List<Operation> operations;
	UserDataController controller;
	InstitutionDataController insController;

	public UserManage() {
		controller = ControllerFactory.getUserDataController();
		insController = ControllerFactory.getInstitutionDataController();
		operations = new ArrayList<Operation>();
	}

	@Override
	public List<UserVO> getAll(UserType userType) {
		// TODO Auto-generated method stub
		List<UserVO> users = new ArrayList<UserVO>();
		for (UserPO po : controller.getAll(userType)) {
			String insname = insController.findById(po.getIns_id())
					.getInsName();
			User user = new User(po);
			users.add(user.toUserVO(insname));
		}
		return users;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		operations.add(new DeleteOperation(id));
	}

	@Override
	public void modify(UserVO vo) {
		// TODO Auto-generated method stub
		String insId = insController.findByName(vo.getInsName()).getInsId();
		operations.add(new ModifyOperation(vo.toUserPO(insId)));
	}

	@Override
	public void add(UserVO vo) {
		// TODO Auto-generated method stub
		String insId = insController.findByName(vo.getInsName()).getInsId();
		operations.add(new AddOperation(vo.toUserPO(insId)));
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

	class DeleteOperation extends AbstractOperation {
		public DeleteOperation(Object args) {
			super(controller, DELETE, args);
		}
	}

	class ModifyOperation extends AbstractOperation {
		public ModifyOperation(Object args) {
			super(controller, MODIFY, args);
		}
	}

	@Override
	public UserVO findById(String id) {
		// TODO Auto-generated method stub
		User user = new User(controller.findById(id));
		String insname = insController.findById(user.getInsId()).getInsName();
		return user.toUserVO(insname);
	}

	@Override
	public UserVO findByUsername(String username) {
		// TODO Auto-generated method stub
		User user = new User(controller.findByUsername(username));
		String insname = insController.findById(user.getInsId()).getInsName();
		return user.toUserVO(insname);
	}

	@Override
	public List<UserVO> getAllDelivery(String ins_id) {
		// TODO 自动生成的方法存根
		List<UserVO> users = new ArrayList<>();
		for (UserPO po : controller.getAllDelivery(ins_id)) {
			String insname = insController.findById(po.getIns_id())
					.getInsName();
			User user = new User(po);
			users.add(user.toUserVO(insname));
		}
		return users;
	}
}
