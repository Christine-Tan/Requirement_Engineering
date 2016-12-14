package gap.stub_driver.blservice.price;

import gap.client.blservice.strategyblservice.PriceService;
import gap.client.vo.PriceVO;
import gap.common.util.ResultMessage;

public class PriceService_driver {
	
	public void driver(PriceService price) {
		PriceVO vo = new PriceVO("000","18:23:25",1);
		PriceVO get = price.getPrice("000");
		if (get != null)
			System.out.println("find:id=" + get.getCityId() + ",rate="
					+ get.getRate() + ",base=" + get.getBase());
		get = price.getPrice("001");
		if (get == null)
			System.out.println("find failed,not found");
		vo.setCityId("001");
		if (price.modifyPrice(vo).equals(ResultMessage.SUCCEED))
			System.out.println("modify succeed");
	}
	
	public static void main(String[] args) {
		PriceService price = new PriceService_stub();
		PriceService_driver driver = new PriceService_driver();
		driver.driver(price);
	}
}
