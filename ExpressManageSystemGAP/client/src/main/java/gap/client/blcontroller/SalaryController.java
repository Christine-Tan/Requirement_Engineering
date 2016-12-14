package gap.client.blcontroller;

import gap.client.bl.strategy.SalaryManage;
import gap.client.vo.SalaryVO;
import gap.common.util.ResultMessage;
import gap.common.util.UserType;

import java.util.List;

public class SalaryController {
	private static SalaryManage salaryManage = new SalaryManage();

	public static List<SalaryVO> getAll() {
		return salaryManage.getAll();
	}

	public static SalaryVO getSalary(UserType type) {
		return salaryManage.getSalary(type);
	}

	public static void modifySalary(SalaryVO vo) {
		salaryManage.modifySalary(vo);
	}

	public static void addSalary(SalaryVO vo) {
		salaryManage.addSalary(vo);
	}

	public static ResultMessage flush() {
		return salaryManage.flush();
	}
}
