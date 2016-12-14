package gap.stub_driver.blservice.price;

import gap.client.blservice.strategyblservice.PriceService;
import gap.client.vo.PriceVO;
import gap.common.po.PricePO;
import gap.common.util.ResultMessage;
import gap.stub_driver.dataservice.pricedata.PriceDataService_stub;

import java.util.ArrayList;
import java.util.List;

public class PriceService_stub implements PriceService{
	PriceDataService_stub datastub;

	public PriceService_stub() {
		datastub = new PriceDataService_stub();
	}
	
	@Override
	public ResultMessage modifyPrice(PriceVO vo) {
		// TODO 自动生成的方法存根
		return datastub.modify(getPO(vo));
	}

	@Override
	public PriceVO getPrice(String city_id) {
		// TODO 自动生成的方法存根
		return getVO(datastub.find(city_id));
	}

	@Override
	public List<PriceVO> getAll() {
		// TODO 自动生成的方法存根
		List<PriceVO> list = new ArrayList<PriceVO>();
		for (PricePO price : datastub.getAll())
			list.add(getVO(price));
		return list;
	}

	private PriceVO getVO(PricePO po) {
		return new PriceVO();
	}

	private PricePO getPO(PriceVO vo) {
		return new PricePO();
	}

	@Override
	public ResultMessage add(PriceVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}
}
