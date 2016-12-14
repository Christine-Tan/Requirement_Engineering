package gap.stub_driver.dataservice.institution;

import gap.common.dataservice.managedataservice.InstitutionDataService;
import gap.common.po.InstitutionPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class InstitutionDataService_stub implements InstitutionDataService{
	private ArrayList<InstitutionPO>  list;

	public  InstitutionDataService_stub() {
		list = new ArrayList<InstitutionPO>();
	}
	
	@Override
	public ResultMessage add(InstitutionPO po) {
		// TODO 自动生成的方法存根
		for (InstitutionPO institution : list)
			if (po.getInsId().equals(institution.getInsId()))
				return ResultMessage.EXITED;
		list.add(po);
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage modify(InstitutionPO po) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < list.size(); i++) {
			if (po.getInsId().equals(list.get(i).getInsId()))
				list.remove(i);
				list.add(po);
				return ResultMessage.SUCCEED;
			}
		return ResultMessage.NOTFOUND;
	}
	

	@Override
	public ResultMessage delete(String ins_id) {
		// TODO 自动生成的方法存根
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getInsId().equals(ins_id)) {
				list.remove(i);
				return ResultMessage.SUCCEED;
			}
		}
		return ResultMessage.NOTFOUND;
	}

	@Override
	public List<InstitutionPO> getAll() {
		// TODO 自动生成的方法存根
		return list;
	}

	@Override
	public List<InstitutionPO> findByCity(String city) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InstitutionPO findById(String ins_id) throws RemoteException {
		// TODO Auto-generated method stub
		for (InstitutionPO institution : list)
			if (ins_id.equals(institution.getInsId()))
				return institution;
		return null;
	}
}
