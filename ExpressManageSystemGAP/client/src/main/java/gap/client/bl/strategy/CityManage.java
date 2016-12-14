package gap.client.bl.strategy;

import gap.client.blservice.strategyblservice.CityService;
import gap.client.datacontroller.CityDataController;
import gap.client.datacontroller.ControllerFactory;
import gap.client.util.AbstractOperation;
import gap.client.util.City;
import gap.client.util.Distance;
import gap.client.util.Operation;
import gap.client.vo.CityVO;
import gap.common.po.CityPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class CityManage implements CityService {
	private static final String ADD = "add";
	CityDataController controller;
	List<Operation> operations;

	public CityManage() {
		controller = ControllerFactory.getCityDataController();
		operations = new ArrayList<Operation>();
	}

	@Override
	public List<CityVO> getAll() {
		// TODO Auto-generated method stub
		List<CityVO> cities = new ArrayList<CityVO>();
		for (CityPO po : controller.getAll()) {
			City city = new City(po);
			cities.add(city.toCityVO());
		}
		return cities;
	}

	@Override
	public CityVO getCity(String City) {
		// TODO Auto-generated method stub
		City city = new City(controller.find(City));
		return city.toCityVO();
	}

	@Override
	public void addCity(City city) {
		// TODO Auto-generated method stub
		operations.add(new AddOperation(city.toCityPO()));
	}

	/**
	 * 将操作缓存起来，按序处理缓存队列
	 * 
	 * @return
	 */
	public ResultMessage flush() {
		for (Operation ope : operations) {
			ResultMessage re = ope.excute();
			if (!re.equals(ResultMessage.SUCCEED)) {
				operations.clear();
				return re;
			}
		}
		operations.clear();
		return ResultMessage.SUCCEED;
	}

	@Override
	public double getDistance(City city1, City city2) {
		// TODO Auto-generated method stub
		Distance dis = new Distance();
		return 30 + dis.distanceCal(city1.getLatitude(), city1.getLongitude(),
				city2.getLatitude(), city2.getLongitude());
	}

	class AddOperation extends AbstractOperation {
		public AddOperation(Object args) {
			super(controller, ADD, args);
		}
	}

}
