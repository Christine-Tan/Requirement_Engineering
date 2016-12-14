package gap.stub_driver.blservice.stockin;

import gap.client.blservice.orderblservice.StockinOrderService;
import gap.client.vo.ExpressOrderVO;
import gap.client.vo.StockinOrderVO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;


public class StockinOrderService_driver {
	private void driver(StockinOrderService stockinData) {
		StockinOrderVO a = stockinData.create(new ArrayList<ExpressOrderVO>(), new StockinOrderVO(null, "20151026", "上海", "car", "00100011970010100002"));
		if(stockinData.save(a).equals(ResultMessage.SUCCEED)){
			System.out.println("save succeed");
		}
		
		StockinOrderVO b = stockinData.find("00100011970010100002");
	}
	public static void main(String[] args){
		StockinOrderService stockin = new StockinOrderService_stub();
		StockinOrderService_driver driver = new StockinOrderService_driver();
		driver.driver(stockin);
	}
}
