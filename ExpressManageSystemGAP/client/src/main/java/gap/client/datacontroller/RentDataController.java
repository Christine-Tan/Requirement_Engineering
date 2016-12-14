package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.rentdataservice;
import gap.common.po.RentPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

public class RentDataController {

	protected RentDataController() {
	}

	public List<RentPO> getAll() {
		try {
			return rentdataservice.getAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage add(RentPO po) {
		try {
			return rentdataservice.add(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage modify(RentPO po) {
		try {
			return rentdataservice.modify(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage setPaid(String institution) {
		try {
			return rentdataservice.setPaid(institution);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}
}
