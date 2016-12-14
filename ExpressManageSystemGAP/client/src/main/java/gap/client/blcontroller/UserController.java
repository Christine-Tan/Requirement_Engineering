package gap.client.blcontroller;

/**
 * @author seven
 */
import gap.client.bl.user.UserManage;
import gap.client.vo.UserVO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

import java.util.List;

public class UserController {
	private static UserManage userManage = new UserManage();

	public static List<UserVO> getAll(UserType userType) {
		return userManage.getAll(userType);
	}

	public static List<UserVO> getAllDelivery(String ins_id) {
		return userManage.getAllDelivery(ins_id);
	}

	public static void delete(String id) {
		userManage.delete(id);
	}

	public static void modify(UserVO vo) {
		userManage.modify(vo);
	}

	public static void add(UserVO vo) {
		userManage.add(vo);
	}

	public static ResultMessage flush() {
		return userManage.flush();
	}

	public static UserVO findById(String id) {
		return userManage.findById(id);
	}

	public static UserVO findByUsername(String username) {
		return userManage.findByUsername(username);
	}

}
