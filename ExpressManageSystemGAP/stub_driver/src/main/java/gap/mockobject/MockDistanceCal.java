package gap.mockobject;

import gap.client.bl.distance.DistanceCal;
import gap.client.vo.CityVO;

public class MockDistanceCal extends DistanceCal {

	@Override
	public double getDistance(CityVO startCity, CityVO endCity) {
		// TODO Auto-generated method stub
		// 真正实现时是获得起终城市的经纬度，直接计算距离，不需要根据城市名来判断距离
		// 此处为了测试方便进行了简化
		if (startCity.getCity().equals("南京市")
				&& endCity.getCity().equals("北京市")) {
			return 341.7;
		} else if (startCity.getCity().equals("北京市")
				&& endCity.getCity().equals("南京市")) {
			return 480.9;
		}
		return 0;
	}

}
