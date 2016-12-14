package gap.stub_driver.blservice.loadorder;

import gap.client.blservice.orderblservice.LoadOrderService;
import gap.client.vo.LoadOrderVO;
import gap.common.util.ResultMessage;

public class LoadOrderService_driver {
	public void driver(LoadOrderService arrivedOrder) {
		LoadOrderVO orderInfo = new LoadOrderVO("19700101",
				"00100011970010100001", "0010001", "0011001", "", "", null);
		LoadOrderVO comOrder = arrivedOrder.create(null, orderInfo);
		if (arrivedOrder.save(comOrder).equals(ResultMessage.SUCCEED))
			System.out.println("save succeed!");
		if (arrivedOrder.save(comOrder).equals(ResultMessage.EXITED))
			System.out.println("save failed,order exited");
		LoadOrderVO get = arrivedOrder.find("00100011970010100001");
		if (get != null)
			System.out.println("find:id=" + get.getNumber() + ",date="
					+ get.getDate());
		get = arrivedOrder.find("001100119700101000001");
		if (get == null)
			System.out.println("find failed,not found");
	}

	public static void main(String[] args) {
		LoadOrderService_driver driver = new LoadOrderService_driver();
		LoadOrderService arrivedOrder = new LoadOrderService_stub();
		driver.driver(arrivedOrder);
	}
}
