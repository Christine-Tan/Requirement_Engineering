package gap.client.vo;

import gap.common.po.InstitutionPO;

public class InstitutionVO {

	// 机构编号
	private String insId;
	// 机构名称
	private String insName;
	// 机构所在城市
	private String insCity;
	// 机构人员数目
	private int insMember;

	public InstitutionVO(String id, String name, String city, int member) {
		insId = id;
		insName = name;
		insCity = city;
		insMember = member;
	}

	public InstitutionVO() {

	}

	public InstitutionVO(InstitutionPO po) {
		this.insCity = po.getInsCity();
		this.insId = po.getInsId();
		this.insMember = po.getInsMember();
		this.insName = po.getInsName();
	}

	public InstitutionPO toInstitutionPO() {
		return new InstitutionPO(insId, insName, insCity, insMember);
	}

	/**
	 * @return insId
	 */
	public String getInsId() {
		return insId;
	}

	/**
	 * @param insId
	 *            要设置的 insId
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
	 *            要设置的 insName
	 */
	public void setInsName(String insName) {
		this.insName = insName;
	}

	/**
	 * @return insCity
	 */
	public String getInsCity() {
		return insCity;
	}

	/**
	 * @param insCity
	 *            要设置的 insCity
	 */
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
	 *            要设置的 insMember
	 */
	public void setInsMember(int insMember) {
		this.insMember = insMember;
	}

	public String toString() {
		return insName;
	}
}
