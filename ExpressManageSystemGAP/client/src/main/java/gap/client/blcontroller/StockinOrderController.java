package gap.client.blcontroller;

import gap.client.bl.order.StockinOrder;
import gap.client.vo.StockinOrderVO;
import gap.common.po.ArrivedOrderPO;
import gap.common.util.ResultMessage;

import java.util.List;

public class StockinOrderController {
	private static StockinOrder stockinOrder = new StockinOrder();
	

	public static ResultMessage save(StockinOrderVO vo) {
		return stockinOrder.save(vo);
	}

	public static List<StockinOrderVO> getRequired(String beginDate,
			String endDate, String ins_id) {
		return stockinOrder.getRequired(beginDate, endDate, ins_id);
	}

	public static int getTotalNum(List<StockinOrderVO> list) {
		return stockinOrder.getTotalNum(list);
	}
	
	public static String getNextId(String cons){
		return stockinOrder.getNextId(cons);
	}
	
	public static List<ArrivedOrderPO> getArrivedOrderPO(String ins_id){
		return stockinOrder.getArrivedOrderPO(ins_id);
	}
	
	public static ResultMessage setOrderStockin(String order_id){
		return stockinOrder.setOrderStockin(order_id);
	}
}
