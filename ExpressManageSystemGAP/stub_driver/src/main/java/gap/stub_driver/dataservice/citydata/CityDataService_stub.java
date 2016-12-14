package gap.stub_driver.dataservice.citydata;

import gap.common.dataservice.strategydataservice.CityDataService;
import gap.common.po.CityPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class CityDataService_stub implements CityDataService{
	private ArrayList<CityPO>  list;

	public CityDataService_stub() {
		list = new ArrayList<CityPO>();
	}
	
	@Override
	public CityPO find(String city) {
		// TODO 自动生成的方法存根
		for (CityPO cityname: list)
			if (cityname.equals(city))
				return new CityPO(city);
		return null;
	}

	@Override
	public ResultMessage add(CityPO po) {
		// TODO 自动生成的方法存根
		for (CityPO cityname : list)
			if (po.getCity().equals(cityname.getCity()))
				return ResultMessage.EXITED;
		list.add(po);
		return ResultMessage.SUCCEED;
	}


	public List<CityPO> getAll() {
		// TODO 自动生成的方法存根
		return list;
	}
    
	
}
