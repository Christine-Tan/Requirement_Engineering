package gap.stub_driver.blservice.city;


import gap.client.blservice.strategyblservice.CityService;
import gap.client.blservice.strategyblservice.DistanceService;
import gap.client.vo.CityVO;
import gap.client.vo.DistanceVO;
import gap.common.po.CityPO;
import gap.common.util.ResultMessage;
import gap.stub_driver.dataservice.citydata.CityDataService_stub;

import java.util.ArrayList;
import java.util.List;

public class CityService_stub implements CityService{
	CityDataService_stub datastub;

	public 	CityService_stub() {
		datastub = new 	CityDataService_stub();
	}
	
	@Override
	public List<CityVO> getAll() {
		// TODO 自动生成的方法存根
		List<CityVO> list = new ArrayList<CityVO>();
		for (CityPO city: datastub.getAll())
			list.add(getVO(city.getCity()));
		return list;
	}

	@Override
	public ResultMessage add(CityVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}
     
	private CityVO getVO(String city){
		return new CityVO(city);
	}

	@Override
	public ResultMessage modifyCity(CityVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityVO getCity(String CityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDistance(CityVO vo1, CityVO vo2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
