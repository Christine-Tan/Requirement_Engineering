package gap.client.bl.receipt;

import gap.client.blservice.orderblservice.BillOrderService;
import gap.client.datacontroller.BillOrderDateController;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.UserDataController;
import gap.client.util.LocalInfo;
import gap.client.vo.BillOrderVO;
import gap.client.vo.BillVO;
import gap.common.po.BillOrderPO;
import gap.common.po.UserPO;
import gap.common.util.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class BillOrder implements BillOrderService {
	BillOrderDateController billorderdataController;

	public BillOrder() {
		billorderdataController = ControllerFactory
				.getBillOrderDateController();
	}

	@Override
	public List<BillVO> getPreviewBills(String date) {
		// TODO 自动生成的方法存根
		List<UserPO> users = getDelivery();
		List<BillVO> bills = new ArrayList<>();
		for (UserPO user : users) {
			BillVO vo = new BillVO();
			String id = user.getUserId();
			vo.id = id;
			UserDataController userdata = ControllerFactory
					.getUserDataController();
			vo.name = userdata.findById(id).getName();
			vo.date = date;
			vo.money = billorderdataController.getDeliveryMoney(vo.date, id);
			bills.add(vo);
		}
		return bills;
	}

	@Override
	public ResultMessage save(BillOrderVO billorder) {
		// TODO 自动生成的方法存根
		String date = billorder.date.toString().replaceAll("-", "");
		String pre = LocalInfo.ins_id + date;
		String id = billorderdataController.getNextID(pre) + "";
		while (id.length() < 5)
			id = "0" + id;
		System.out.println(pre + id);
		BillOrderPO billOrderPO = new BillOrderPO(billorder.bills, pre + id,
				billorder.date);
		return billorderdataController.add(billOrderPO);
	}

	@Override
	public List<UserPO> getDelivery() {
		// TODO 自动生成的方法存根
		return billorderdataController.getDelivery(LocalInfo.ins_id);
	}

}
