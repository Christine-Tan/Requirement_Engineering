package gap.stub_driver.blservice.stockout;

import gap.client.blservice.orderblservice.StockoutOrderService;
import gap.client.vo.ExpressOrderVO;
import gap.client.vo.StockoutOrderVO;
import gap.common.util.ResultMessage;

import java.util.List;

public class StockoutOrderService_stub implements StockoutOrderService{

	@Override
	public StockoutOrderVO create(List<ExpressOrderVO> orders,
			StockoutOrderVO orderinfo) {
		// TODO 自动生成的方法存根
		return new StockoutOrderVO(null, "20151026", "上海", "car", "00100011970010100002");
	}

	@Override
	public ResultMessage save(StockoutOrderVO order) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCEED;
	}

	@Override
	public StockoutOrderVO find(String id) {
		// TODO 自动生成的方法存根
		return new StockoutOrderVO(null, "20151026", "上海", "car", "00100011970010100002");
	}

	@Override
	public int getOrdersNum() {
		// TODO Auto-generated method stub
		return 0;
	}

}
