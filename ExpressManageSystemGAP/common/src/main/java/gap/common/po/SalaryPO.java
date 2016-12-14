package gap.common.po;

import gap.common.util.UserType;

import java.io.Serializable;

public class SalaryPO implements Serializable {
	// 薪水类型
	UserType type;
	// 薪水值
	double salary;

	public SalaryPO() {

	}

	public SalaryPO(UserType type, double salary) {
		this.type = type;
		this.salary = salary;
	}

	/**
	 * @return type
	 */
	public UserType getType() {
		return type;
	}

	/**
	 * @param type Ҫ���õ� type
	 */
	public void setType(UserType type) {
		this.type = type;
	}

	/**
	 * @return salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @param salary Ҫ���õ� salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}
}
