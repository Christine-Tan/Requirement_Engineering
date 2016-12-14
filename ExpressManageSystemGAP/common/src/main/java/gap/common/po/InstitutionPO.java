package gap.common.po;

import gap.common.util.InstitutionType;

import java.io.Serializable;

public class InstitutionPO implements Serializable {

	// 机构编号
	private String insId;
	// 机构名称
	private String insName;
	// 机构所在城市
	private String insCity;
	// 机构人员数目
	private int insMember;
	// 机构类型
	private InstitutionType insType;

	public InstitutionPO(String id, String name, String city, int member) {
		insId = id;
		insName = name;
		insCity = city;
		insMember = member;
		this.setInsType(insId);
	}

	public InstitutionPO() {

	}

	/**
	 * @return insId
	 */
	public String getInsId() {
		return insId;
	}

	/**
	 * @param insId
	 *            Ҫ���õ� insId
	 */
	public void setInsId(String insId) {
		this.insId = insId;
	}

	/**
	 * @return insName
	 */
	public String getInsName() {
		return insName;
	}

	/**
	 * @param insName
	 *            Ҫ���õ� insName
	 */
	public void setInsName(String insName) {
		this.insName = insName;
	}

	public String getInsCity() {
		return insCity;
	}

	public void setInsCity(String insCity) {
		this.insCity = insCity;
	}

	/**
	 * @return insMember
	 */
	public int getInsMember() {
		return insMember;
	}

	/**
	 * @param insMember
	 *            Ҫ���õ� insMember
	 */
	public void setInsMember(int insMember) {
		this.insMember = insMember;
	}

	public void setInsType(String insId) {
		insType = InstitutionType.getInsType(insId);
	}

	public InstitutionType getInsType() {
		return insType;
	}

}
