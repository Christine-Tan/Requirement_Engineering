package gap.client.blservice.strategyblservice;

import gap.client.vo.PriceVO;

import java.util.List;

public interface PriceService {
	public List<PriceVO> getAll();

	public void modifyPrice(PriceVO vo);

	public PriceVO getPrice(String city);

	public void addPrice(PriceVO vo);

}
