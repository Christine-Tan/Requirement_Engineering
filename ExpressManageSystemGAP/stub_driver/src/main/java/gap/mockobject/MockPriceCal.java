package gap.mockobject;

import gap.client.blservice.expressorderblservice.PriceCal;
import gap.client.vo.ExpressOrderVO;

public class MockPriceCal extends PriceCal {

	@Override
	public double getPrice(ExpressOrderVO vo) {
		// TODO 自动生成的方法存根
		switch (vo.getExpressType()) {
		case EXPRESS:
			return 20.0;
		case ECONOMIC:
			return 10.0;
		case STANDARD:
			return 15.0;
		default:
			return 0;
		}
	}
}
