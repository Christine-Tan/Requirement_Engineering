package gap.stub_driver.blservice.city;

import gap.client.blservice.strategyblservice.CityService;
import gap.client.blservice.strategyblservice.DistanceService;
import gap.client.vo.CityVO;
import gap.client.vo.DistanceVO;
import gap.common.util.ResultMessage;

public class CityService_driver {
	public void driver(CityService city) {
		CityVO vo = new CityVO("Nanjing");
		if (city.add(vo).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (city.add(vo).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (city.add(vo).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,distance exited");
		}
	}

	public static void main(String[] args) {
		CityService city = new CityService_stub();
		CityService_driver driver = new CityService_driver();
		driver.driver(city);
	}
}
