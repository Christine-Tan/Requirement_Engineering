package gap.client.bl.user;

import gap.client.datacontroller.NetModule;
import gap.client.vo.UserVO;
import gap.common.util.UserType;

import org.junit.Before;
import org.junit.Test;

public class UserManageTest {
   UserManage user;
	@Before
	public void setUp() throws Exception {
		NetModule.connect();
		user=new UserManage();
	}

	@Test
	public void testGetAll() {
	user.getAll(UserType.ADMINISTRATOR);
	}

	@Test
	public void testDelete() {
		user.delete("001000001");
	}

	@Test
	public void testModify() {
		UserVO vo=new UserVO ();
		user.modify(vo);
	}

	@Test
	public void testAdd() {
        UserVO vo=new UserVO();
        user.add(vo);
	}

	@Test
	public void testFindById() {
       user.findById("001000001");
	}

	@Test
	public void testFindByUsername() {
        user.findByUsername("小明");
	}

	@Test
	public void testGetAllDelivery() {
		user.getAllDelivery("0010001");
	}

}
