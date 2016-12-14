package gap.client.blservice.orderblservice;

import gap.client.vo.StockinOrderVO;
import gap.common.po.ArrivedOrderPO;
import gap.common.util.ResultMessage;

import java.util.List;

public interface StockinOrderService {
	public ResultMessage save(StockinOrderVO order);

//	public StockinOrderVO find(String id, String ins_id);

//	public List<ArrivedOrderVO> getArrivedOrderVO(String ins_id);

//	public String getLocation(GoodsVO vo);

	public List<StockinOrderVO> getRequired(String beginDate, String endDate,
			String ins_id);

	public int getTotalNum(List<StockinOrderVO> list);

	public String getNextId(String cons);

	public List<ArrivedOrderPO> getArrivedOrderPO(String ins_id);

	public ResultMessage setOrderStockin(String order_id);

}
