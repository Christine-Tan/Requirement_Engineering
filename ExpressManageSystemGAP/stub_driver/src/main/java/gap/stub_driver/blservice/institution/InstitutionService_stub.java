package gap.stub_driver.blservice.institution;

import gap.client.blservice.manageblservice.InstitutionService;
import gap.client.vo.InstitutionVO;
import gap.common.po.InstitutionPO;
import gap.common.util.ResultMessage;
import gap.stub_driver.dataservice.institution.InstitutionDataService_stub;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class InstitutionService_stub implements InstitutionService{
   InstitutionDataService_stub datastub;

	public InstitutionService_stub() {
		datastub = new InstitutionDataService_stub();
	}
	@Override
	public List<InstitutionVO> getAll() {
		// TODO 自动生成的方法存根
		List<InstitutionVO> list = new ArrayList<InstitutionVO>();
		for (InstitutionPO institution : datastub.getAll())
			list.add(getVO(institution));
		return list;
	}

	@Override
	public InstitutionVO getSingle(String id) {
		// TODO 自动生成的方法存根
		try {
			return getVO(datastub.findById(id));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage modify(InstitutionVO vo) {
		// TODO 自动生成的方法存根
		return datastub.modify(getPO(vo));
	}

	@Override
	public ResultMessage delete(String id) {
		// TODO 自动生成的方法存根
		return datastub.delete(id);
	}

	@Override
	public ResultMessage add(InstitutionVO vo) {
		// TODO 自动生成的方法存根
		return datastub.add(getPO(vo));
		
	}

	private InstitutionVO getVO(InstitutionPO po) {
		return new InstitutionVO();
	}

	private InstitutionPO getPO(InstitutionVO vo) {
		return new InstitutionPO();
	}
}
