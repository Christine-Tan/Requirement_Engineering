package gap.client.blcontroller;

import gap.client.bl.order.StockoutOrder;
import gap.client.vo.ExpressOrderVO;
import gap.client.vo.StockoutOrderVO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class StockoutOrderController {
	private static StockoutOrder stockoutOrder = new StockoutOrder();

	public static ResultMessage save(StockoutOrderVO vo) {
		return stockoutOrder.save(vo);
	}

	public static List<StockoutOrderVO> getRequired(String beginDate,
			String endDate, String ins_id) {
		return stockoutOrder.getRequired(beginDate, endDate, ins_id);
	}

	public static int getTotalNum(List<StockoutOrderVO> list) {
		return stockoutOrder.getTotalNum(list);
	}

	public static String getNextId(String cons) {
		return stockoutOrder.getNextId(cons);
	}

	public static List<ExpressOrderVO> getUnloadStockOutOrder() {
		List<StockoutOrderVO> vos = stockoutOrder.getUnloadStockOrders();
		List<ExpressOrderVO> expressorders = new ArrayList<ExpressOrderVO>();
		for (StockoutOrderVO vo : vos) {
			expressorders.addAll(ExpressorderController.getByOrderIdList(vo
					.getExpressorder_ids()));
		}
		return expressorders;
	}
}
