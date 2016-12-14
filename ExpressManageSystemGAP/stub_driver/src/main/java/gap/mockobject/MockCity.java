package gap.mockobject;

import gap.client.bl.distance.City;
import gap.client.bl.distance.DistanceCal;
import gap.client.vo.CityVO;
import gap.testers.GetDistanceTest;

public class MockCity extends City {

	public MockCity(DistanceCal c) {
		super(c);
		// TODO 自动生成的构造函数存根
	}

	public CityVO getCity(String city_name) {
		if (city_name.equals("南京市"))
			return new CityVO("南京市");
		else
			return new CityVO("北京市");
	}

}
