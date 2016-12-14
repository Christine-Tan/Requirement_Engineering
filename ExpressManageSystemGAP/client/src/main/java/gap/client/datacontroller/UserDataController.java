package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.userdataservice;
import gap.common.po.UserPO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

public class UserDataController {

	protected UserDataController() {
	}

	public List<UserPO> getAll(UserType userType) {
		try {
			return userdataservice.getAll(userType);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage add(UserPO po) {
		try {
			return userdataservice.add(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public UserPO findById(String user_id) {
		try {
			return userdataservice.findById(user_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public UserPO findByUsername(String username) {
		try {
			return userdataservice.findByUsername(username);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage modify(UserPO po) {
		try {
			return userdataservice.modify(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage delete(String user_id) {
		try {
			return userdataservice.delete(user_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public List<UserPO> findUnpaidUser(Date date) {
		try {
			return userdataservice.findUnpaidUser(date);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage setPaid(String user_id) {
		try {
			return userdataservice.setPaid(user_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public List<UserPO> getAllDelivery(String ins_id) {
		try {
			return userdataservice.getDilivery(ins_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
}
