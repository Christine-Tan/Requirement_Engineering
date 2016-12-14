package gap.client.bl.order;

import gap.client.blservice.orderblservice.ArrivedOrderService;
import gap.client.datacontroller.ArrivedOrderDataController;
import gap.client.datacontroller.ControllerFactory;
import gap.client.util.LocalInfo;
import gap.client.vo.ArrivedOrderVO;
import gap.common.util.ResultMessage;

public class ArrivedOrder implements ArrivedOrderService {

	ArrivedOrderDataController arrivedOrderData;

	public ArrivedOrder() {
		arrivedOrderData = ControllerFactory.getArrivedOrderDataController();
	}


	@Override
	public ResultMessage save(ArrivedOrderVO order) {
		// TODO 自动生成的方法存根
		String date = order.time.replaceAll("-", "");
		String pre = LocalInfo.ins_id + date;
		String id = "" + arrivedOrderData.nextId(LocalInfo.ins_id + date);
		while (id.length() < 5)
			id = "0" + id;
		order.id = pre + id;
		return arrivedOrderData.add(order.toPO());
	}
	
//	public 


}
