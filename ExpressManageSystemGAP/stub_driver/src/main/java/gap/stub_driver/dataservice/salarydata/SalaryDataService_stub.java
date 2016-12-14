package gap.stub_driver.dataservice.salarydata;

import gap.common.dataservice.strategydataservice.SalaryDataService;
import gap.common.po.SalaryPO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

import java.util.ArrayList;
import java.util.List;

public class SalaryDataService_stub implements SalaryDataService {
	private ArrayList<SalaryPO>  list;

	public  SalaryDataService_stub() {
		list = new ArrayList<SalaryPO>();
	}
	
	
	@Override
	public ResultMessage add(SalaryPO po) {
		// TODO 自动生成的方法存根
		for (SalaryPO salary : list)
			if (po.getType().equals(salary.getType()))
				return ResultMessage.EXITED;
		list.add(po);
		return ResultMessage.SUCCEED;
	}
	
	@Override
	public SalaryPO find(UserType type) {
		// TODO 自动生成的方法存根
		for (SalaryPO salary : list)
			if (type.equals(salary.getType()))
				return salary;
		return null;
	}

	@Override
	public ResultMessage modify(SalaryPO po) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < list.size(); i++) {
			if (po.getType().equals(list.get(i).getType()))
				list.remove(i);
				list.add(po);
				return ResultMessage.SUCCEED;
			}
		return ResultMessage.NOTFOUND;
	}
	
	public List<SalaryPO> getAll() {
		// TODO 自动生成的方法存根
		return list;
	}


}
