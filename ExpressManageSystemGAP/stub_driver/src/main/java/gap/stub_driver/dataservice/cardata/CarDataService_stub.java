package gap.stub_driver.dataservice.cardata;

import gap.common.dataservice.transdataservice.CarDataService;
import gap.common.po.CarPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class CarDataService_stub implements CarDataService {

	private List<CarPO> cars;

	public CarDataService_stub() {
		cars = new ArrayList<CarPO>();
	}

	@Override
	public ResultMessage add(CarPO po) {
		// TODO 自动生成的方法存根
		for (CarPO car : cars)
			if (car.getCar_id().equals(po.getCar_id()))
				return ResultMessage.EXITED;
		cars.add(po);
		return ResultMessage.SUCCEED;
	}

	@Override
	public CarPO find(String car_id) {
		// TODO 自动生成的方法存根
		for (CarPO car : cars)
			if (car.getCar_id().equals(car_id))
				return car;
		return null;
	}

	@Override
	public ResultMessage modify(CarPO po) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getCar_id().equals(po.getCar_id())) {
				cars.remove(i);
				cars.add(po);
				return ResultMessage.SUCCEED;
			}
		}
		return ResultMessage.NOTFOUND;
	}

	@Override
	public ResultMessage delete(String car_id) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getCar_id().equals(car_id)) {
				cars.remove(i);
				return ResultMessage.SUCCEED;
			}
		}
		return ResultMessage.NOTFOUND;
	}

	@Override
	public List<CarPO> getAll() {
		// TODO 自动生成的方法存根
		return cars;
	}

}
