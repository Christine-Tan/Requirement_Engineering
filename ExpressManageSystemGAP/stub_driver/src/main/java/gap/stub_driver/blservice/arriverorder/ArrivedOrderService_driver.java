package gap.stub_driver.blservice.arriverorder;

import gap.client.blservice.orderblservice.ArrivedOrderService;
import gap.client.vo.ArrivedOrderVO;
import gap.common.util.ResultMessage;

public class ArrivedOrderService_driver {
	public void driver(ArrivedOrderService arrivedOrder) {
		ArrivedOrderVO orderInfo = new ArrivedOrderVO(null, "19700101",
				"00110011970010100001");
		ArrivedOrderVO comOrder = arrivedOrder.create(null, orderInfo);
		if (arrivedOrder.save(comOrder).equals(ResultMessage.SUCCEED))
			System.out.println("save succeed!");
		if (arrivedOrder.save(comOrder).equals(ResultMessage.EXITED))
			System.out.println("save failed,order exited");
		ArrivedOrderVO get = arrivedOrder.find("00110011970010100001");
		if (get != null)
			System.out.println("find:id=" + get.getID() + ",date="
					+ get.getTime());
		get = arrivedOrder.find("001100119700101000001");
		if (get == null)
			System.out.println("find failed,not found");
	}

	public static void main(String[] args) {
		ArrivedOrderService_driver driver = new ArrivedOrderService_driver();
		ArrivedOrderService arrivedOrder = new ArrivedOrderService_stub();
		driver.driver(arrivedOrder);
	}

}
