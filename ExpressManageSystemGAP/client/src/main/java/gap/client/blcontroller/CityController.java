package gap.client.blcontroller;

import gap.client.bl.strategy.CityManage;
import gap.client.util.City;
import gap.client.vo.CityVO;
import gap.common.util.ResultMessage;

import java.util.List;

public class CityController {
	private static CityManage cityManage = new CityManage();

	public static List<CityVO> getAll() {
		// TODO Auto-generated method stub
		return cityManage.getAll();
	}

	public static CityVO getCity(String City) {
		// TODO Auto-generated method stub
		return cityManage.getCity(City);
	}

	public static void addCity(City city) {
		// TODO Auto-generated method stub
		cityManage.addCity(city);
	}

	public static ResultMessage flush() {
		return cityManage.flush();
	}

}
