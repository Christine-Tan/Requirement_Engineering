package gap.client.bl.strategy;

import gap.client.blcontroller.LogController;
import gap.client.blservice.strategyblservice.SalaryService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.SalaryDataController;
import gap.client.util.AbstractOperation;
import gap.client.util.LocalInfo;
import gap.client.util.Operation;
import gap.client.vo.SalaryVO;
import gap.common.po.LogPO;
import gap.common.po.SalaryPO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalaryManage implements SalaryService {
	private static final String ADD = "add", MODIFY = "modify";
	SalaryDataController controller;
	List<Operation> operations;
    List<LogPO> logs;
	public SalaryManage() {
		operations = new ArrayList<Operation>();
		controller = ControllerFactory.getSalaryDataController();
		logs=new ArrayList<LogPO>();
	}

	@Override
	public List<SalaryVO> getAll() {
		// TODO Auto-generated method stub
		List<SalaryVO> salaries = new ArrayList<SalaryVO>();
		for (SalaryPO po : controller.getAll()) {
			salaries.add(new SalaryVO(po));
		}
		return salaries;
	}

	@Override
	public SalaryVO getSalary(UserType type) {
		// TODO Auto-generated method stub
		return new SalaryVO(controller.find(type));
	}

	@Override
	public void modifySalary(SalaryVO vo) {
		// TODO Auto-generated method stub
		operations.add(new ModifyOperation(vo.toSalaryPO()));
		this.logCache("修改"+vo.getType()+"的薪水价格");
	}

	@Override
	public void addSalary(SalaryVO vo) {
		// TODO Auto-generated method stub
		operations.add(new AddOperation(vo.toSalaryPO()));
	    this.logCache("新增"+vo.getType()+"的薪水价格");
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
