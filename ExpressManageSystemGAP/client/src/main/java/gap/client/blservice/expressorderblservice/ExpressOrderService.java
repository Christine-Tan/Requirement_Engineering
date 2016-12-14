package gap.client.blservice.expressorderblservice;

import gap.client.exception.InvalidInputException;
import gap.client.vo.ExpressOrderVO;
import gap.common.po.AllAddressPO;
import gap.common.util.CurrentOrderType;
import gap.common.util.ExpressType;
import gap.common.util.ReceiveInfo;
import gap.common.util.ResultMessage;

import java.util.List;

public interface ExpressOrderService {
	/**
	 * 获得订单货运状态
	 * @param order_id
	 * @return
	 */
	public List<String> getState(String order_id) throws InvalidInputException;

	public ExpressOrderVO getOrder(String order_id)
			throws InvalidInputException;

	public List<ExpressOrderVO> getArrivingOrders(String ins_id)
			throws InvalidInputException;

	public List<ExpressOrderVO> getCurrentOrders(String ins_id,
			CurrentOrderType type) throws InvalidInputException;

	public ExpressOrderVO createOrder(ExpressOrderVO order_info);

	public ResultMessage save(ExpressOrderVO order);

	public ResultMessage receiveOrder(ReceiveInfo receiveInfo);

	public AllAddressPO getAllAddress();

	public boolean isExisted(String order_id);

	public double getDeliveryTime(String departure_city, String target_city,
			ExpressType type);

}
