package gap.client.blcontroller;

import gap.client.bl.receipt.BillOrder;
import gap.client.vo.BillOrderVO;
import gap.client.vo.BillVO;
import gap.common.util.ResultMessage;

import java.util.List;

public class BillOrderController {
	public static BillOrder billOrder = new BillOrder();

	public static List<BillVO> getBills(String date) {
		return billOrder.getPreviewBills(date);
	}

	public static ResultMessage saveBill(BillOrderVO VO) {
		return billOrder.save(VO);
	}

}
