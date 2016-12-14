package gap.client.datacontroller;

import static gap.client.datacontroller.NetModule.pricedataservice;
import gap.common.po.PricePO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

/**
 * 
 * @author seven
 *
 */
public class PriceDataController {

	protected PriceDataController() {
	}

	public PricePO find(String city) {
		try {
			return pricedataservice.find(city);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage add(PricePO po) {
		try {
			return pricedataservice.add(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage modify(PricePO po) {
		try {
			return pricedataservice.modify(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public List<PricePO> getAll() {
		try {
			return pricedataservice.getAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
