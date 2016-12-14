package gap.common.dataservice.inventorydataservice;

import gap.common.util.ResultMessage;

import java.rmi.Remote;

public interface FlexSectorDataService extends Remote {
	public ResultMessage add(String beginShelf, String endShelf, String type,
			String ins_id);

	public ResultMessage delete(String beginShelf, String endShelf,
			String ins_id);

	public ResultMessage modify(String beginShelf, String endShelf,
			String type, String ins_id);

	public String getBelongSecId(String beginShelf, String endShelf,
			String ins_id);

	public String isUsed(String beginShelf, String endShelf, String ins_id);
}
