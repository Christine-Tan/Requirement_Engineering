package gap.stub_driver.dataservice.driverdata;

import gap.common.dataservice.transdataservice.DriverDataService;
import gap.common.po.DriverPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class DriverDataService_stub implements DriverDataService {
	private List<DriverPO> drivers;

	public DriverDataService_stub() {
		drivers = new ArrayList<DriverPO>();
	}

	@Override
	public ResultMessage add(DriverPO po) {
		// TODO 自动生成的方法存根
		for (DriverPO driver : drivers)
			if (driver.getId().equals(po.getId()))
				return ResultMessage.EXITED;
		drivers.add(po);
		return ResultMessage.SUCCEED;
	}

	@Override
	public DriverPO find(String driver_id) {
		// TODO 自动生成的方法存根
		for (DriverPO driver : drivers)
			if (driver.getId().equals(driver_id))
				return driver;
		return null;
	}

	@Override
	public ResultMessage modify(DriverPO po) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < drivers.size(); i++) {
			if (drivers.get(i).getId().equals(po.getId())) {
				drivers.remove(i);
				drivers.add(po);
				return ResultMessage.SUCCEED;
			}
		}
		return ResultMessage.NOTFOUND;
	}

	@Override
	public ResultMessage delete(String driver_id) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < drivers.size(); i++) {
			if (drivers.get(i).getId().equals(driver_id)) {
				drivers.remove(i);
				return ResultMessage.SUCCEED;
			}
		}
		return ResultMessage.NOTFOUND;
	}

	@Override
	public List<DriverPO> getAll() {
		// TODO 自动生成的方法存根
		return drivers;
	}

}
