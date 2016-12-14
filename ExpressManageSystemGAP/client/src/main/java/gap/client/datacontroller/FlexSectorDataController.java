package gap.client.datacontroller;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.inventorydataservice.FlexSectorDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.util.ResultMessage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class FlexSectorDataController {
	FlexSectorDataService flexSectorData;

	protected FlexSectorDataController() {
		try {
			flexSectorData = (FlexSectorDataService) Naming
					.lookup(RMIConfig.url + ServiceName.FLEXSECTOR_DATA_SERVICE);
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

	public ResultMessage add(String beginShelf, String endShelf, String type,
			String ins_id) {
		// TODO Auto-generated method stub
		return flexSectorData.add(beginShelf, endShelf, type, ins_id);
	}

	public ResultMessage delete(String beginShelf, String endShelf,
			String ins_id) {
		// TODO Auto-generated method stub
		return flexSectorData.delete(beginShelf, endShelf, ins_id);
	}

	public ResultMessage modify(String beginShelf, String endShelf,
			String type, String ins_id) {
		// TODO Auto-generated method stub
		return flexSectorData.modify(beginShelf, endShelf, type, ins_id);
	}

	public String getBelongSecId(String beginShelf, String endShelf,
			String ins_id) {
		// TODO Auto-generated method stub
		return flexSectorData.getBelongSecId(beginShelf, endShelf, ins_id);
	}

	public String isUsed(String beginShelf, String endShelf, String ins_id) {
		// TODO Auto-generated method stub
		return flexSectorData.isUsed(beginShelf, endShelf, ins_id);
	}
}
