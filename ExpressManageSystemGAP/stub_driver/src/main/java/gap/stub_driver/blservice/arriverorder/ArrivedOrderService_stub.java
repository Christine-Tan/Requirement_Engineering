package gap.stub_driver.blservice.arriverorder;

import gap.client.blservice.orderblservice.ArrivedOrderService;
import gap.client.vo.ArrivedOrderVO;
import gap.client.vo.ExpressOrderVO;
import gap.common.po.ArrivedOrderPO;
import gap.common.util.ResultMessage;
import gap.stub_driver.dataservice.arrivedorderdata.ArrivedOrderDataService_stub;

import java.util.List;

public class ArrivedOrderService_stub implements ArrivedOrderService {

	ArrivedOrderDataService_stub datastub;

	public ArrivedOrderService_stub() {
		datastub = new ArrivedOrderDataService_stub();
	}

	@Override
	public ArrivedOrderVO create(List<ExpressOrderVO> orders,
			ArrivedOrderVO orderinfo) {
		// TODO 自动生成的方法存根
		return orderinfo;
	}

	@Override
	public ResultMessage save(ArrivedOrderVO order) {
		// TODO 自动生成的方法存根
		return datastub.add(toPO(order));
	}

	@Override
	public ArrivedOrderVO find(String id) {
		// TODO 自动生成的方法存根
		return getVO(datastub.find(id));
	}

	private ArrivedOrderVO getVO(ArrivedOrderPO po) {
		if (po == null)
			return null;
		return new ArrivedOrderVO(po.getOrders(), po.getTime(), po.getID());
	}

	private ArrivedOrderPO toPO(ArrivedOrderVO vo) {
		if (vo == null)
			return null;
		return new ArrivedOrderPO(vo.getOrders(), vo.getTime(), vo.getID());
	}

}
