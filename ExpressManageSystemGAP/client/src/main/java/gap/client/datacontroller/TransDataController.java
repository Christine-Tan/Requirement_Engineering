package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.cardataservice;
import static gap.client.datacontroller.NetModule.driverdataservice;
import gap.client.util.LocalInfo;
import gap.common.po.CarPO;
import gap.common.po.DriverPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

public class TransDataController {

	protected TransDataController() {
	}

	public ResultMessage addCar(CarPO po) {
		try {
			return cardataservice.add(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage addDriver(DriverPO po) {
		try {
			return driverdataservice.add(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public List<CarPO> getAllCar() {
		try {
			return cardataservice.getAll(LocalInfo.ins_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public List<DriverPO> getAllDriver() {
		try {
			return driverdataservice.getAll(LocalInfo.ins_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public CarPO findCar(String car_id) {
		try {
			return cardataservice.find(car_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public DriverPO findDriver(String driver_id) {
		try {
			return driverdataservice.find(driver_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage modifyCar(CarPO po) {
		try {
			return cardataservice.modify(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage modifyDriver(DriverPO po) {
		try {
			return driverdataservice.modify(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage deleteCar(String car_id) {
		try {
			return cardataservice.delete(car_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage deleteDriver(String driver_id) {
		try {
			return driverdataservice.delete(driver_id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}
}
