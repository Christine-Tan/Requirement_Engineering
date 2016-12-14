package gap.client.bl.order;

import gap.client.blservice.orderblservice.StockoutOrderService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.InventoryDataController;
import gap.client.datacontroller.StockoutOrderDataController;
import gap.client.vo.StockoutOrderVO;
import gap.common.po.StockoutOrderPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class StockoutOrder implements StockoutOrderService {
	StockoutOrderDataController stockoutData;
	InventoryDataController inventoryData;

	public StockoutOrder() {
		stockoutData = ControllerFactory.getStockoutOrderDataController();
	}

	@Override
	public ResultMessage save(StockoutOrderVO vo) {
		// TODO Auto-generated method stub
		if (vo.getExpressorder_ids() == null
				|| vo.getExpressorder_ids().size() == 0) {
			return ResultMessage.FAILED;
		} else {
			return stockoutData.add(vo.toPO());
		}
	}

	// @Override
	// public StockoutOrderVO find(String id, String ins_id) {
	// // TODO Auto-generated method stub
	// return new StockoutOrderVO(stockoutData.find(id, ins_id));
	// }

	// @Override
	// public String getLocation(String id) {
	// // TODO Auto-generated method stub
	//
	// return null;
	// }

	@Override
	public List<StockoutOrderVO> getRequired(String beginDate, String endDate,
			String ins_id) {
		// TODO Auto-generated method stub
		List<StockoutOrderPO> list = stockoutData.getRequired(beginDate,
				endDate, ins_id);
		return StockoutOrderVO.toVOList(list);
	}

	@Override
	public int getTotalNum(List<StockoutOrderVO> list) {
		// TODO Auto-generated method stub
		int num = 0;
		for (StockoutOrderVO vo : list) {
			num += vo.getNum();
		}
		return num;
	}

	@Override
	public String getNextId(String cons) {
		// TODO Auto-generated method stub
		int n = stockoutData.getNextId(cons);
		String num = n + "";
		while (num.length() < 5) {
			num = "0" + num;
		}
		return cons + num;
	}

	@Override
	public List<StockoutOrderVO> getUnloadStockOrders() {
		// TODO 自动生成的方法存根
		List<StockoutOrderVO> orders = new ArrayList<StockoutOrderVO>();
		List<StockoutOrderPO> poorders = stockoutData.getUnLoadedOrders();
		if (poorders == null)
			return null;
		orders = StockoutOrderVO.toVOList(poorders);
		return orders;
	}

	// public List<StockoutOrderVO> getUnLoadedOrders(String ins_id){
	// List<StockoutOrderVO> orders = new ArrayList<StockoutOrderVO>();
	// orders = stockoutData.get
	// }

}
