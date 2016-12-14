package gap.common.dataservice.transdataservice;

import gap.common.po.CarPO;
import gap.common.util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CarDataService extends Remote {

	public List<CarPO> getAll(String ins_id) throws RemoteException;

	public ResultMessage add(CarPO po) throws RemoteException;

	public CarPO find(String car_id) throws RemoteException;

	public ResultMessage modify(CarPO po) throws RemoteException;

	public ResultMessage delete(String car_id) throws RemoteException;

}
