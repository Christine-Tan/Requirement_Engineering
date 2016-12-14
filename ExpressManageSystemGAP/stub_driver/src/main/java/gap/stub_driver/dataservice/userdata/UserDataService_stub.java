package gap.stub_driver.dataservice.userdata;

import gap.common.dataservice.userdataservice.UserDataService;
import gap.common.po.UserPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserDataService_stub implements UserDataService {
	private ArrayList<UserPO> list;

	public UserDataService_stub() {
		list = new ArrayList<UserPO>();
	}

	@Override
	public ResultMessage add(UserPO po) {
		// TODO 自动生成的方法存根
		for (UserPO user : list)
			if (po.getUserId().equals(user.getUserId()))
				return ResultMessage.EXITED;
		list.add(po);
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage modify(UserPO po) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < list.size(); i++) {
			if (po.getUserId().equals(list.get(i).getUserId()))
				list.remove(i);
			list.add(po);
			return ResultMessage.SUCCEED;
		}
		return ResultMessage.NOTFOUND;
	}

	@Override
	public ResultMessage delete(String user_id) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserId().equals(user_id)) {
				list.remove(i);
				return ResultMessage.SUCCEED;
			}
		}
		return ResultMessage.NOTFOUND;
	}

	@Override
	public List<UserPO> getAll() {
		// TODO 自动生成的方法存根
		return list;
	}

	@Override
	public UserPO findById(String user_id) {
		// TODO 自动生成的方法存根
		for (UserPO user : list)
			if (user_id.equals(user.getUserId()))
				return user;
		return null;
	}

	@Override
	public UserPO findByUsername(String username) {
		// TODO 自动生成的方法存根
		for (UserPO user : list)
			if (username.equals(user.getUserName()))
				return user;
		return null;
	}

	@Override
	public List<UserPO> findUnpaidUser(Date date) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage setPaid(String user_id) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

}
