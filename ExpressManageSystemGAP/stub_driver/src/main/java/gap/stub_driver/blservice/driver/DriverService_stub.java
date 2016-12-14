package gap.stub_driver.blservice.driver;

import gap.client.blservice.transmanageblservice.DriverService;
import gap.client.vo.DriverVO;
import gap.common.po.DriverPO;
import gap.common.util.ResultMessage;
import gap.stub_driver.dataservice.driverdata.DriverDataService_stub;

import java.util.ArrayList;
import java.util.List;

public class DriverService_stub implements DriverService {
	private DriverDataService_stub datastub;

	public DriverService_stub() {
		datastub = new DriverDataService_stub();
	}

	@Override
	public List<DriverVO> getAll() {
		// TODO 自动生成的方法存根
		List<DriverVO> re = new ArrayList<DriverVO>();
		for (DriverPO Driver : datastub.getAll())
			re.add(getVO(Driver));
		return re;
	}

	@Override
	public DriverVO getSingle(String id) {
		// TODO 自动生成的方法存根
		return getVO(datastub.find(id));
	}

	@Override
	public ResultMessage modify(DriverVO vo) {
		return datastub.modify(getPO(vo));

	}

	@Override
	public ResultMessage delete(String id) {
		return datastub.delete(id);

	}

	@Override
	public ResultMessage add(DriverVO vo) {
		return datastub.add(getPO(vo));

	}

	private DriverVO getVO(DriverPO po) {
		if(po==null)
			return null;
		return new DriverVO(po.getId(), po.getIns_id(), po.getName(),
				po.getBirth(), po.getIdentity_number(), po.getPhone(),
				po.getDriving_license_due(), po.getGender());
	}

	private DriverPO getPO(DriverVO vo) {
		if(vo==null)
			return null;
		return new DriverPO(vo.getId(), vo.getIns_id(), vo.getName(),
				vo.getBirth(), vo.getIdentity_number(), vo.getPhone(),
				vo.getDriving_license_due(), vo.getGender());
	}
}
