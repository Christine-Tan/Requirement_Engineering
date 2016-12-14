package gap.stub_driver.dataservice.pricedata;


import gap.common.dataservice.strategydataservice.PriceDataService;
import gap.common.po.PricePO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class PriceDataService_stub implements PriceDataService{
	private ArrayList<PricePO>  list;

	public  PriceDataService_stub() {
		list = new ArrayList<PricePO>();
	}
	@Override
	public PricePO find(String city_id) {
		// TODO 自动生成的方法存根
		for (PricePO price: list)
			if (city_id.equals(price.getCityId()))
				return price;
		return null;
	}

	@Override
	public ResultMessage add(PricePO po) {
		// TODO 自动生成的方法存根
		for (PricePO price : list)
			if (po.getCityId().equals(price.getCityId()))
				return ResultMessage.EXITED;
		list.add(po);
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage modify(PricePO po) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < list.size(); i++) {
			if (po.getCityId().equals(list.get(i).getCityId()))
				list.remove(i);
				list.add(po);
				return ResultMessage.SUCCEED;
			}
		return ResultMessage.NOTFOUND;
	}
	
	public List<PricePO> getAll() {
		// TODO 自动生成的方法存根
		return list;
	}

}
