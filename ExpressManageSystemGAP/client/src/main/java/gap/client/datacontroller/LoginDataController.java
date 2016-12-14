package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.userdataservice;
import gap.common.po.UserPO;

import java.rmi.RemoteException;

public class LoginDataController {

	protected LoginDataController() {
	}

	public UserPO findByUsername(String username) {
		try {
			return userdataservice.findByUsername(username);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	// static {
	// try {
	// userDataService = (UserDataService) Naming.lookup(RMIConfig.url
	// + ServiceName.USER_DATA_SERVICE);
	// } catch (MalformedURLException e) {
	// // TODO 自动生成的 catch 块
	// e.printStackTrace();
	// } catch (RemoteException e) {
	// // TODO 自动生成的 catch 块
	// e.printStackTrace();
	// } catch (NotBoundException e) {
	// // TODO 自动生成的 catch 块
	// e.printStackTrace();
	// }
	// }
	//
}
