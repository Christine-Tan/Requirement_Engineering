package gap.client.bl.strategy;

import gap.client.blcontroller.LogController;
import gap.client.blservice.strategyblservice.PriceService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.PriceDataController;
import gap.client.util.AbstractOperation;
import gap.client.util.LocalInfo;
import gap.client.util.Operation;
import gap.client.vo.PriceVO;
import gap.common.po.LogPO;
import gap.common.po.PricePO;
import gap.common.util.ResultMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PriceManage implements PriceService {
	private static final String ADD = "add", MODIFY = "modify";
	PriceDataController controller;
	List<Operation> operations;
    //用于缓存生成的日志
	List<LogPO> logs;
	public PriceManage() {
		controller = ControllerFactory.getPriceDataController();
		operations = new ArrayList<Operation>();
		logs=new ArrayList<LogPO>();
	}

	@Override
	public List<PriceVO> getAll() {
		// TODO Auto-generated method stub
		List<PriceVO> prices = new ArrayList<PriceVO>();
		for (PricePO po : controller.getAll()) {
			prices.add(new PriceVO(po));
		}
		return prices;
	}

	@Override
	public PriceVO getPrice(String city) {
		// TODO Auto-generated method stub
		return new PriceVO(controller.find(city));
	}

	@Override
	public void modifyPrice(PriceVO vo) {
		// TODO Auto-generated method stub
		operations.add(new ModifyOperation(vo.toPricePO()));
		this.logCache("修改"+vo.getCity()+"的运费价格策略");
	}

	@Override
	public void addPrice(PriceVO vo) {
		// TODO Auto-generated method stub
		operations.add(new AddOperation(vo.toPricePO()));
		this.logCache("新增"+vo.getCity()+"的运费价格策略");
	}

	/**
	 * 将操作缓存起来，按序处理缓存队列
	 * 
	 * @return
	 */
	public ResultMessage flush() {
		for (Operation ope : operations) {
			ResultMessage re = ope.excute();
			for(LogPO po:logs){
				LogController.addLog(po);
			}
			if (!re.equals(ResultMessage.SUCCEED)) {
				operations.clear();
				return re;
			}
		}
		operations.clear();
		return ResultMessage.SUCCEED;
	}

	class AddOperation extends AbstractOperation {
		public AddOperation(Object args) {
			super(controller, ADD, args);
		}
	}

	class ModifyOperation extends AbstractOperation {
		public ModifyOperation(Object args) {
			super(controller, MODIFY, args);
		}
	}
	
    //将生成的日志缓存起来，在确认修改之后才传输到数据层
	private void logCache(String ope){
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-ss HH-mm-ss");
		String time=format.format(date);
		LogPO po=new LogPO(LocalInfo.localuser.toUserPO(),time,ope);
		logs.add(po);
	}
}
