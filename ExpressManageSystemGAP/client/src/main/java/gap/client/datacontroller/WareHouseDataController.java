package gap.client.datacontroller;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.inventorydataservice.WareHouseDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.util.ResultMessage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class WareHouseDataController {
	WareHouseDataService wareHouse;

	protected WareHouseDataController() {
		try {
			wareHouse = (WareHouseDataService) Naming.lookup(RMIConfig.url
					+ ServiceName.WAREHOUSE_DATA_SERVICE);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultMessage add(String ins_id, String sector_id) {
		// TODO Auto-generated method stub
		try {
			wareHouse.add(ins_id, sector_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage delete(String ins_id) {
		// TODO Auto-generated method stub
		try {
			wareHouse.delete(ins_id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
