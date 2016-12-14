package gap.server.data.receiptdata;

import gap.common.dataservice.receiptdataservice.BillOrderDataService;
import gap.common.po.BillOrderPO;
import gap.common.po.BillPO;
import gap.common.util.NumberLength;
import gap.common.util.OrderState;
import gap.common.util.ResultMessage;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.SQLBuilder;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BillOrderDataServiceImpl extends UnicastRemoteObject implements
		BillOrderDataService {
	private InsertSQL tableInsert, itemInsert;

	private UpdateSQL update;

	private String tableName = "billorder", itemTable = "billorderitem";
	private String order_id_f = "order_id", total_money_f = "total_money",
			time_f = "time", passed_f = "passed";
	private String item_delivery_id_f = "delivery_id", item_money_f = "money",
			item_order_id_f = "order_id";

	public static BillOrderDataService instance;

	private BillOrderDataServiceImpl() throws RemoteException {
		super();
		tableInsert = new InsertSQL(tableName);
		itemInsert = new InsertSQL(itemTable);
		update = new UpdateSQL(tableName);
	}

	public static BillOrderDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new BillOrderDataServiceImpl();
		return instance;
	}

	@Override
	public ResultMessage add(BillOrderPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String order_id = po.getID(), time = po.getBillDate().toString();
			double total_money = po.getTotal();
			List<BillPO> bills = po.getBills();
			tableInsert.add(order_id_f, order_id);
			tableInsert.add(time_f, time);
			tableInsert.add(total_money_f, total_money);
			NetModule.excutor.excute(tableInsert.createSQL());
			for (BillPO bill : bills) {
				itemInsert.clear();
				itemInsert.add(item_delivery_id_f, bill.getCourierID());
				itemInsert.add(item_money_f, bill.getMoney());
				itemInsert.add(item_order_id_f, order_id);
				NetModule.excutor.excute(itemInsert.createSQL());
			}
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return ResultMessage.FAILED;
	}

	@Override
	public BillOrderPO find(String order_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE " + order_id
					+ " = '" + order_id + "' AND " + passed_f + " = true;";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			re.next();
			return getByResultSet(re);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage setPassed(String order_id, String state_info)
			throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			update.clear();
			update.add(passed_f, true);
			update.setKey(order_id_f, order_id);
			NetModule.excutor.excute(update.createSQL());
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	@Override
	public List<BillOrderPO> getUnpassedOrders() throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE " + passed_f
					+ " = false ;";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<BillOrderPO> orders = new ArrayList<>();
			while (re.next()) {
				orders.add(getByResultSet(re));
			}
			return orders;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getMaxId(String cons) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT max(RIGHT(order_id,5)) max_id FROM "
					+ tableName + " WHERE LEFT(order_id,15)='" + cons + "';";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			int result;
			if (re.next()) {
				result = re.getInt(1) + 1;
			} else {
				result = 0;
			}
			return result;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}

	private BillOrderPO getByResultSet(ResultSet re) {
		try {
			String order_id = re.getString(order_id_f);
			Date date = re.getDate(time_f);
			List<BillPO> bills = getByOrderID(order_id);
			return new BillOrderPO(bills, order_id, date);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	private List<BillPO> getByOrderID(String order_id) {
		try {
			String sql = "SELECT * FROM " + itemTable + " WHERE "
					+ item_order_id_f + " = '" + order_id + "' ;";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<BillPO> bills = new ArrayList<BillPO>();
			while (re.next()) {
				String delivery_id = re.getString(item_delivery_id_f);
				double money = re.getDouble(item_money_f);
				bills.add(new BillPO(money, delivery_id));
			}
			return bills;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BillOrderPO> getPassedOrder(Calendar start, Calendar end)
			throws RemoteException {
		// TODO Auto-generated method stub

		if (start == null || end == null) {
			return null;
		}

		SQLBuilder builder = new SQLBuilder();
		builder.Select("*").From(tableName).Where(passed_f).EQUALS(1)
				.AND(time_f).Between(start).AND(end);
		ResultSet set = builder.excuteQuery();
		List<BillOrderPO> orders = new ArrayList<>();

		try {
			while (set.next()) {
				orders.add(getByResultSet(set));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orders;

	}

	@Override
	public List<BillOrderPO> getPassedOrder(Calendar oneDay,
			String institutionID) throws RemoteException {
		// TODO Auto-generated method stub
		if (oneDay == null && institutionID == null) {
			return null;
		}

		// 机构编号格式不对
		if (institutionID.length() != NumberLength.INSTITUTION_NUM_LEN) {
			return null;
		}

		SQLBuilder builder = new SQLBuilder();
		builder.Select("*").From(tableName).Where(passed_f).EQUALS(1);

		if (oneDay != null) {
			builder.AND(time_f).EQUALS(oneDay);
		}

		// 假如机构编号不是空，且长度符合要求
		if (institutionID != null) {
			// 机构编号是单据编号的开头
			builder.AND(order_id_f).LIKE(institutionID + "%");
		}

		ResultSet set = builder.excuteQuery();
		List<BillOrderPO> orders = new ArrayList<>();
		try {
			while (set.next()) {
				orders.add(getByResultSet(set));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public OrderState isOrderPassed(String orderID) throws RemoteException {
		// TODO Auto-generated method stub
		SQLBuilder builder = new SQLBuilder();
		builder.Select(passed_f).From(tableName).Where(order_id_f)
				.EQUALS(orderID);
		ResultSet set = builder.excuteQuery();
		boolean hasNext = false;
		try {
			hasNext = set.next();
			if (!hasNext) {
				return null;
			} else {
				boolean isPassed = set.getBoolean(passed_f);

				if (isPassed) {
					return OrderState.PASSED;
				} else {
					return OrderState.SUBMITTED;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
