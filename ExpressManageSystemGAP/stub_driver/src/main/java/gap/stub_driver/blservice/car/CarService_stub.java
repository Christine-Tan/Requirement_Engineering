package gap.stub_driver.blservice.car;

import gap.client.blservice.transmanageblservice.CarService;
import gap.client.util.Car;
import gap.client.vo.CarVO;
import gap.common.po.CarPO;
import gap.common.util.ResultMessage;
import gap.stub_driver.dataservice.cardata.CarDataService_stub;

import java.util.ArrayList;
import java.util.List;

public class CarService_stub implements CarService {

	CarDataService_stub datastub;

	public CarService_stub() {
		datastub = new CarDataService_stub();
	}

	@Override
	public List<CarVO> getAll() {
		// TODO 自动生成的方法存根
		List<CarVO> re = new ArrayList<CarVO>();
		for (CarPO carpo : datastub.getAll()) {
			Car car = new Car(carpo);
			re.add(car.toCarVO());
		}
		return re;
	}

	@Override
	public CarVO getSingle(String id) {
		// TODO 自动生成的方法存根
		Car car = new Car(datastub.find(id));
		return car.toCarVO();
	}

	@Override
	public ResultMessage modify(Car vo) {
		return datastub.modify(vo.toCarPO());

	}

	@Override
	public ResultMessage delete(String id) {
		return datastub.delete(id);

	}

	@Override
	public ResultMessage add(Car vo) {
		return datastub.add(vo.toCarPO());

	}

}
