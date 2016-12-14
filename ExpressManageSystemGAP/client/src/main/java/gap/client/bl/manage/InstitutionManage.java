package gap.client.bl.manage;

import gap.client.blcontroller.LogController;
import gap.client.blservice.manageblservice.InstitutionService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.InstitutionDataController;
import gap.client.util.AbstractOperation;
import gap.client.util.LocalInfo;
import gap.client.util.Operation;
import gap.client.vo.InstitutionVO;
import gap.common.po.InstitutionPO;
import gap.common.po.LogPO;
import gap.common.util.ResultMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InstitutionManage implements InstitutionService {
	private static final String ADD = "add", DELETE = "delete",
			MODIFY = "modify";
	List<Operation> operations;
	InstitutionDataController controller;
    //生成日志记录
	List<LogPO> logs;
	
	public InstitutionManage() {
		controller = ControllerFactory.getInstitutionDataController();
		operations = new ArrayList<Operation>();
		logs=new ArrayList<LogPO>();
	}
    
	@Override
	public List<InstitutionVO> getAll() {
		// TODO Auto-generated method stub
		List<InstitutionVO> institutions = new ArrayList<InstitutionVO>();
		for (InstitutionPO po : controller.getAll()) {
			institutions.add(new InstitutionVO(po));
		}
		return institutions;
	}

	@Override
	public InstitutionVO findById(String id) {
		// TODO Auto-generated method stub
		return new InstitutionVO(controller.findById(id));
	}

	@Override
	public List<InstitutionVO> findByCity(String city) {
		// TODO Auto-generated method stub
		List<InstitutionVO> institutions = new ArrayList<InstitutionVO>();
		for (InstitutionPO po : controller.findByCity(city)) {
			institutions.add(new InstitutionVO(po));
		}
		return institutions;
	}

	@Override
	public InstitutionVO findByName(String ins_name) {
		// TODO Auto-generated method stub
		return new InstitutionVO(controller.findByName(ins_name));
	}
	
	@Override
	public void modifyInstitution(InstitutionVO vo) {
		// TODO Auto-generated method stub
		operations.add(new ModifyOperation(vo.toInstitutionPO()));
		this.logCache("修改机构 "+vo.getInsName());
	}
 
	@Override
	public void deleteInstitution(String id) {
		// TODO Auto-generated method stub
		operations.add(new DeleteOperation(id));
		this.logCache("删除机构 "+this.findById(id).getInsName());
	}

	@Override
	public void addInstitution(InstitutionVO vo) {
		// TODO Auto-generated method stub
		operations.add(new AddOperation(vo.toInstitutionPO()));
		this.logCache("新增机构 "+vo.getInsName());
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

	class DeleteOperation extends AbstractOperation {
		public DeleteOperation(Object args) {
			super(controller, DELETE, args);
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
