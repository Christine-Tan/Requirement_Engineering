package gap.client.blservice.transmanageblservice;

import gap.client.util.Driver;
import gap.client.vo.DriverVO;
import gap.common.util.ResultMessage;

import java.util.List;

public interface DriverService {
	public List<DriverVO> getAll();

	public DriverVO getSingle(String id);

	public void modify(Driver vo);

	public void delete(String id);

	public void add(Driver vo);

	public ResultMessage flush();
}
