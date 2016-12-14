package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.citydataservice;
import gap.common.po.CityPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

public class CityDataController {

	protected CityDataController() {
	}

	public CityPO find(String name) {
		try {
			return citydataservice.find(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage add(CityPO po) {
		try {
			return citydataservice.add(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public List<CityPO> getAll() {
		try {
			return citydataservice.getAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
