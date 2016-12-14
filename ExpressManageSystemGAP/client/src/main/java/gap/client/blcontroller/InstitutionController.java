package gap.client.blcontroller;

import gap.client.bl.manage.InstitutionManage;
import gap.client.vo.InstitutionVO;
import gap.common.util.ResultMessage;

import java.util.List;

public class InstitutionController {
	private static InstitutionManage institutionManage = new InstitutionManage();

	public static List<InstitutionVO> getAll() {
		return institutionManage.getAll();
	}

	public static InstitutionVO findById(String id) {
		return institutionManage.findById(id);
	}

	public static List<InstitutionVO> findByCity(String city) {
		return institutionManage.findByCity(city);
	}

	public static InstitutionVO findByName(String ins_name){
		return institutionManage.findByName(ins_name);
	}
	public static void modifyInstitution(InstitutionVO vo) {
		institutionManage.modifyInstitution(vo);
	}

	public static void deleteInstitution(String id) {
		institutionManage.deleteInstitution(id);
	}

	public static void addInstitution(InstitutionVO vo) {
		institutionManage.addInstitution(vo);
	}

	public static ResultMessage flush() {
		return institutionManage.flush();
	}
}
