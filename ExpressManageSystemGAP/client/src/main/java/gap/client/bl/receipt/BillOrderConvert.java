package gap.client.bl.receipt;

import gap.client.vo.BillOrderVO;
import gap.client.vo.BillVO;
import gap.common.po.BillOrderPO;
import gap.common.po.BillPO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BillOrderConvert {
	public static BillOrderVO orderPO_to_VO(BillOrderPO orderPO){
		Date date = orderPO.getBillDate();
		List<BillPO> POs = orderPO.getBills();
		ArrayList<BillVO> billVOs = new ArrayList<>(POs.size());
		
		BillOrderVO orderVO = new BillOrderVO();
		orderVO.date = date;
		orderVO.bills = POs;
		return orderVO;
	}

}
