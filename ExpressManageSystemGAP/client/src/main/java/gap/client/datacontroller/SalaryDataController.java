package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.salarydataservice;
import gap.common.po.SalaryPO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

import java.rmi.RemoteException;
import java.util.List;

public class SalaryDataController {

	protected SalaryDataController() {
	}

	public SalaryPO find(UserType type) {
		try {
			return salarydataservice.find(type);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage add(SalaryPO po) {
		try {
			return salarydataservice.add(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage modify(SalaryPO po) {
		try {
			return salarydataservice.modify(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public List<SalaryPO> getAll() {
		try {
			return salarydataservice.getAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
