package gap.client.blservice.strategyblservice;

import gap.client.vo.SalaryVO;
import gap.common.util.UserType;

import java.util.List;

public interface SalaryService {
	public List<SalaryVO> getAll();

	public SalaryVO getSalary(UserType type);

	public void modifySalary(SalaryVO vo);

	public void addSalary(SalaryVO vo);
}
