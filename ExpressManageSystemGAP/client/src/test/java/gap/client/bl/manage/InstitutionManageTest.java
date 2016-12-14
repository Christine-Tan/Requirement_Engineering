package gap.client.bl.manage;

import gap.client.datacontroller.NetModule;
import gap.client.vo.InstitutionVO;

import org.junit.Before;
import org.junit.Test;

public class InstitutionManageTest {
    InstitutionManage institution;
    
	@Before
	public void setUp() throws Exception {
		NetModule.connect();
		institution=new InstitutionManage();
	}

	@Test
	public void testGetAll() {
		institution.getAll();
	}

	@Test
	public void testFindById() {
	    institution.findById("0010001");
	}

	@Test
	public void testFindByCity() {
        institution.findByCity("南京");
	}

	@Test
	public void testModifyInstitution() {
         InstitutionVO vo=new InstitutionVO("0010001","南京市玄武区营业厅","南京",37);
         institution.modifyInstitution(vo);
	}

	@Test
	public void testDeleteInstitution() {
          institution.deleteInstitution("0010001");
	}

	@Test
	public void testAddInstitution() {
		  InstitutionVO vo=new InstitutionVO("0010004","南京市玄武区营业厅","南京",37);
           institution.addInstitution(vo);
	}

}
