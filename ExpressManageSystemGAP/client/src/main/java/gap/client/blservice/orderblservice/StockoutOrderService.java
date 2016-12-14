package gap.client.blservice.orderblservice;

import gap.client.vo.StockoutOrderVO;
import gap.common.util.ResultMessage;

import java.util.List;

public interface StockoutOrderService {
	public ResultMessage save(StockoutOrderVO order);

//	public StockoutOrderVO find(String id, String ins_id);

//	public String getLocation(String expressorder_id);

	public List<StockoutOrderVO> getRequired(String beginDate, String endDate,
			String ins_id);

	public int getTotalNum(List<StockoutOrderVO> list);

	public String getNextId(String cons);

	public List<StockoutOrderVO> getUnloadStockOrders();


}
