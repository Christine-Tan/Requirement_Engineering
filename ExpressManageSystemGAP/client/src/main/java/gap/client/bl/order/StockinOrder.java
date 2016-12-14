package gap.client.bl.order;

import gap.client.blservice.orderblservice.StockinOrderService;
import gap.client.datacontroller.ArrivedOrderDataController;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.StockinOrderDataController;
import gap.client.vo.StockinOrderVO;
import gap.common.po.ArrivedOrderPO;
import gap.common.po.StockinOrderPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class StockinOrder implements StockinOrderService {
	StockinOrderDataController stockinData;
	ArrivedOrderDataController arrivedData;

	public StockinOrder() {
		stockinData = ControllerFactory.getStockinOrderDataController();
		arrivedData = ControllerFactory.getArrivedOrderDataController();
	}

	@Override
	public ResultMessage save(StockinOrderVO vo) {
		// TODO Auto-generated method stub
		if(vo.getGoods()==null||vo.getGoods().size()==0){
			return ResultMessage.FAILED;
		}else{
			return stockinData.add(vo.toPO());
		}
	}

//	@Override
//	public StockinOrderVO find(String id, String ins_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public String getLocation(GoodsVO vo) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<ArrivedOrderPO> getArrivedOrderPO(String ins_id) {
		// TODO Auto-generated method stub
		List<ArrivedOrderPO> orders = new ArrayList<ArrivedOrderPO>();
		orders = arrivedData.getStockinginArrivedOrder(ins_id);
		return orders;
	}

	@Override
	public List<StockinOrderVO> getRequired(String beginDate, String endDate,
			String ins_id) {
		// TODO Auto-generated method stub
		List<StockinOrderPO> list = stockinData.getRequired(beginDate, endDate,
				ins_id);
		return StockinOrderVO.toVOList(list);
	}

	@Override
	public int getTotalNum(List<StockinOrderVO> list) {
		// TODO Auto-generated method stub
		int num = 0;
		for (StockinOrderVO vo : list) {
			num += vo.getNum();
		}
		return num;
	}

	@Override
	public String getNextId(String cons) {
		// TODO Auto-generated method stub
		int n = stockinData.getNextId(cons);
		String num = n+"";
		while(num.length()<5){
			num = "0" + num;
		}
		return cons+num;
	}
	
	@Override
	public ResultMessage setOrderStockin(String order_id){
		return arrivedData.setOrderStockin(order_id);
	}

}
