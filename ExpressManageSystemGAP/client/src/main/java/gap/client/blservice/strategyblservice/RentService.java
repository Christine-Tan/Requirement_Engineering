package gap.client.blservice.strategyblservice;

import gap.client.vo.RentVO;

import java.util.List;

public interface RentService {
	public List<RentVO> getAll();

	public void addRent(RentVO vo);

	public void modifyRent(RentVO vo);
}
