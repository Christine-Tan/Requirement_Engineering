package gap.client.blservice.userblservice;

import gap.client.vo.UserVO;
import gap.common.util.UserType;

import java.util.List;

public interface UserService {
	public List<UserVO> getAll(UserType userType);

	public List<UserVO> getAllDelivery(String ins_id);

	public UserVO findById(String id);

	public UserVO findByUsername(String username);

	public void delete(String id);

	public void modify(UserVO vo);

	public void add(UserVO vo);
}
