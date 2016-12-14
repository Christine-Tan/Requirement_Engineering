package gap.client.blservice.strategyblservice;

import gap.client.util.City;
import gap.client.vo.CityVO;

import java.util.List;

public interface CityService {
	public List<CityVO> getAll();

	public CityVO getCity(String City);

	public void addCity(City city);

	public double getDistance(City city1, City city2);
}
