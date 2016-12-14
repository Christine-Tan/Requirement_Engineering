package gap.stub_driver.blservice.inventory;

import gap.client.blservice.inventoryblservice.InventoryService;
import gap.client.vo.ExpressOrderVO;
import gap.client.vo.GoodsVO;
import gap.client.vo.StockCountVO;
import gap.client.vo.StockObVO;
import gap.client.vo.StockinOrderVO;
import gap.client.vo.StockoutOrderVO;
import gap.common.util.ResultMessage;
import gap.stub_driver.dataservice.inventory.InventoryDataService_stub;

import java.util.ArrayList;
import java.util.List;

public class InventoryService_stub implements InventoryService{
	InventoryDataService_stub datastub;

	@Override
	public StockObVO observeStock(String begin, String end) {
		// TODO Auto-generated method stub
		return new StockObVO();
	}

	@Override
	public StockCountVO countStock() {
		// TODO Auto-generated method stub
		return new StockCountVO();
	}

	@Override
	public ResultMessage setAlarm(double alarmValue) {
		// TODO Auto-generated method stub
		datastub.setAlarm(alarmValue, null);
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage distributeSector(String beginColumn, String endColumn,
			String toSector) {
		// TODO Auto-generated method stub
		
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage initialadd(GoodsVO expressorder) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage initialdelete(String expressorder_id) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage initialmodify(GoodsVO expressorder) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCEED;
	}

	@Override
	public List<ExpressOrderVO> getArrivingOrders(String institution_id) {
		// TODO Auto-generated method stub
		return new ArrayList<ExpressOrderVO>();
	}

	@Override
	public StockinOrderVO createStockinOrder(List<String> expressorders_id) {
		// TODO Auto-generated method stub
		return new StockinOrderVO(null, "20151026", "上海", "car", "00100011970010100002");
	}

	@Override
	public StockoutOrderVO createStockoutOrder(
			List<ExpressOrderVO> expressorders) {
		// TODO Auto-generated method stub
		return new StockoutOrderVO(null, "20151026", "上海", "car", "00100011970010100002");
	}

	@Override
	public ExpressOrderVO getSingleExpressOrder(String expressorder_id) {
		// TODO Auto-generated method stub
		return new ExpressOrderVO();
	}

	@Override
	public String Alarm() {
		// TODO Auto-generated method stub
		return "Alarmed";
	}

	@Override
	public double getAlarm() {
		// TODO Auto-generated method stub
		return 80;
	}

	@Override
	public String stockIn(ExpressOrderVO expressorder, String ins_center_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stockOut(String destination, String transportation,
			String expressorder_id, String ins_center_id) {
		// TODO Auto-generated method stub
		
	}

	
}
