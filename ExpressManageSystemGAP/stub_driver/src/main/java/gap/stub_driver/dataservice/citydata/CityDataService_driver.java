package gap.stub_driver.dataservice.citydata;

import java.rmi.RemoteException;

import gap.common.dataservice.strategydataservice.CityDataService;
import gap.common.po.CityPO;
import gap.common.util.ResultMessage;

public class CityDataService_driver {
	public void driver(CityDataService distanceData) throws RemoteException {
		CityPO po = new CityPO("Nanjing");
		if (distanceData.add(po).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (distanceData.add(po).equals(ResultMessage.SUCCEED)) {
			System.out.println("add succeed");
		}
		if (distanceData.add(po).equals(ResultMessage.EXITED)) {
			System.out.println("add failed,distance exited");
		}
		CityPO get = distanceData.find("Nanjing");
		if (get != null)
			System.out.println("find: from " + get.getCity() + " Longitutde: "
					+ get.getLongitude() + " Latitude=" + get.getLatitude());
		get = distanceData.find("Beijing");
		if (get == null)
			System.out.println("find failed,not found");
	}
	public static void main(String[] args) {
		CityDataService cityData = new CityDataService_stub();
		CityDataService_driver driver = new CityDataService_driver();
		try {
			driver.driver(cityData);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
