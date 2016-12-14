package gap.client.blservice.transmanageblservice;

import gap.client.util.Car;
import gap.client.vo.CarVO;
import gap.common.util.ResultMessage;

import java.util.List;

public interface CarService {
	public List<CarVO> getAll();

	public CarVO getSingle(String id);

	public void modify(Car vo);

	public void delete(String id);

	public void add(Car vo);

	public ResultMessage flush();
}
