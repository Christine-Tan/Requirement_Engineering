package gap.stub_driver.blservice.loadorder;

import gap.client.blservice.orderblservice.LoadOrderService;
import gap.client.vo.LoadOrderVO;
import gap.common.po.LoadOrderPO;
import gap.common.util.ResultMessage;
import gap.stub_driver.dataservice.loadorder.LoadOrderDataService_stub;

import java.util.List;

public class LoadOrderService_stub implements LoadOrderService {

	private LoadOrderDataService_stub datastub;

	public LoadOrderService_stub() {
		datastub = new LoadOrderDataService_stub();
	}

	@Override
	public LoadOrderVO create(List<String> orders, LoadOrderVO orderinfo) {
		// TODO 自动生成的方法存根
		orderinfo.setOrders_id(orders);
		return orderinfo;
	}

	@Override
	public ResultMessage save(LoadOrderVO order) {
		// TODO 自动生成的方法存根
		return datastub.add(getPO(order));
	}

	@Override
	public LoadOrderVO find(String id) {
		// TODO 自动生成的方法存根
		return getVO(datastub.find(id));
	}

	private LoadOrderPO getPO(LoadOrderVO vo) {
		if (vo == null)
			return null;
		return new LoadOrderPO(vo.getDate(), vo.getNumber(),
				vo.getDepartureins_id(), vo.getTargetins_id(),
				vo.getLoaded_id(), vo.getTargetins_id(), vo.getOrders_id());
	}

	private LoadOrderVO getVO(LoadOrderPO po) {
		if (po == null)
			return null;
		return new LoadOrderVO(po.getDate(), po.getNumber(),
				po.getDepartureins_id(), po.getTargetins_id(),
				po.getLoaded_id(), po.getTargetins_id(), po.getOrders_id());
	}

}
