package gap.server.data;

import gap.common.dataservice.Contactor;
import gap.common.po.UserPO;
import gap.server.ui.ServerMainFrame;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * 用于测试客户端和服务器连接是否正常的类
 * @author YangYanfei
 *
 */
public class ContactorImpl extends UnicastRemoteObject implements Contactor {
	private ArrayList<UserPO> userPOs;
	
	public ContactorImpl() throws RemoteException {
		super();
		userPOs = new ArrayList<>();
	}

	@Override
	public boolean getInfo(String IP,UserPO userPO) throws RemoteException {
		// TODO 自动生成的方法存根

		if(!userPOs.contains(userPO)){
			userPOs.add(userPO);
			ServerMainFrame.addUser(userPO, IP);
		}
		return true;
	}

}
