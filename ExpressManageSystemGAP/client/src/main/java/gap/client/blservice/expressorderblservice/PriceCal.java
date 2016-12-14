package gap.client.blservice.expressorderblservice;

import gap.client.bl.strategy.CityManage;
import gap.client.vo.ExpressOrderVO;

public abstract class PriceCal {

	protected CityManage city;

	// 返回价格
	public abstract double getPrice(ExpressOrderVO vo);

}
