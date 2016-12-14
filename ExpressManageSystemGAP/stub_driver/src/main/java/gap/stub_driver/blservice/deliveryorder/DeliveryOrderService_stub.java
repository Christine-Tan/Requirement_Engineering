package gap.stub_driver.blservice.deliveryorder;

import gap.client.blservice.orderblservice.DeliveryOrderService;
import gap.client.vo.DeliveryOrderVO;
import gap.client.vo.ExpressOrderVO;
import gap.common.po.DeliveryOrderPO;
import gap.common.util.ResultMessage;
import gap.stub_driver.dataservice.deliveryorderdata.DeliveryOrderDataService_stub;

import java.util.List;

public class DeliveryOrderService_stub implements DeliveryOrderService {

	private DeliveryOrderDataService_stub datastub;

	public DeliveryOrderService_stub() {
		datastub = new DeliveryOrderDataService_stub();
	}

	@Override
	public DeliveryOrderVO create(List<ExpressOrderVO> orders,
			DeliveryOrderVO orderinfo) {
		// TODO 自动生成的方法存根
		return orderinfo;
	}

	@Override
	public ResultMessage save(DeliveryOrderVO order) {
		// TODO 自动生成的方法存根
		return datastub.add(getPO(order));
	}

	@Override
	public DeliveryOrderVO find(String id) {
		// TODO 自动生成的方法存根
		return getVO(datastub.find(id));
	}

	private DeliveryOrderVO getVO(DeliveryOrderPO po) {
		if (po == null)
			return null;
		return new DeliveryOrderVO(po.getDeliveryInfo(), po.getTime(),
				po.getID());
	}

	private DeliveryOrderPO getPO(DeliveryOrderVO vo) {
		if(vo==null)
			return null;
		return new DeliveryOrderPO(vo.getDeliveryInfo(), vo.getTime(),
				vo.getID());
	}

}
