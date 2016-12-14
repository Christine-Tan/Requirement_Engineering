package gap.stub_driver.blservice.salary;

import gap.client.blservice.strategyblservice.SalaryService;
import gap.client.vo.SalaryVO;
import gap.common.po.SalaryPO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;
import gap.stub_driver.dataservice.salarydata.SalaryDataService_stub;

import java.util.ArrayList;
import java.util.List;

public class SalaryService_stub implements SalaryService{
	 SalaryDataService_stub datastub;

	public SalaryService_stub() {
		datastub = new SalaryDataService_stub();
	}
	@Override
	public SalaryVO getSalary(UserType type) {
		// TODO 自动生成的方法存根
		return getVO(datastub.find(type));
	}

	@Override
	public List<SalaryVO> getAll() {
		// TODO 自动生成的方法存根
		List<SalaryVO> list = new ArrayList<SalaryVO>();
		for (SalaryPO user : datastub.getAll())
			list.add(getVO(user));
		return list;
	}

	@Override
	public ResultMessage modifySalary(SalaryVO vo) {
		// TODO 自动生成的方法存根
		return datastub.modify(getPO(vo));
	}

	private SalaryVO getVO(SalaryPO po) {
		return new SalaryVO();
	}

	private SalaryPO getPO(SalaryVO vo) {
		return new SalaryPO();
	}

	@Override
	public ResultMessage addSalary(SalaryVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}
}
