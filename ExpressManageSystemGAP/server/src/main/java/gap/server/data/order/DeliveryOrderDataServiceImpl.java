package gap.server.data.order;

import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.dataservice.orderdataservice.DeliveryOrderDataService;
import gap.common.po.DeliveryOrderPO;
import gap.common.util.ResultMessage;
import gap.server.data.expressorder.ExpressOrderDataServiceImpl;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeliveryOrderDataServiceImpl extends UnicastRemoteObject implements
		DeliveryOrderDataService {

	private String tableName = "deliveryorder",
			itemTable = "deliveryorderitem";
	private String order_id_f = "order_id", comment_f = "comment",
			time_f = "time", passed_f = "passed";
	private String item_id_expressorder_f = "expressorder_id",
			item_order_id_f = "order_id", item_delivery_id_f = "delivery_id";
	private InsertSQL orderInsert, itemInsert;
	private UpdateSQL update;

	public static DeliveryOrderDataService instance;

	private DeliveryOrderDataServiceImpl() throws RemoteException {
		super();
		orderInsert = new InsertSQL(tableName);
		itemInsert = new InsertSQL(itemTable);
		update = new UpdateSQL(tableName);
		// TODO 自动生成的构造函数存根
	}

	public static DeliveryOrderDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new DeliveryOrderDataServiceImpl();
		return instance;
	}

	@Override
	public ResultMessage add(DeliveryOrderPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String order_id = po.getID(), comment = po.getComment(), time = po
					.getTime();
			orderInsert.clear();
			orderInsert.add(order_id_f, order_id);
			orderInsert.add(comment_f, comment);
			orderInsert.add(time_f, time);
			NetModule.excutor.excute(orderInsert.createSQL());
			Map<String, List<String>> orders = po.getDeliveryInfo();
			Set<String> sets = orders.keySet();
			ExpressOrderDataService expressorder = ExpressOrderDataServiceImpl
					.getInstance();
			for (String str : sets) {
				for (String str1 : orders.get(str)) {
					expressorder.setSubmit(str1);
					itemInsert.clear();
					itemInsert.add(item_order_id_f, order_id);
					itemInsert.add(item_id_expressorder_f, str1);
					itemInsert.add(item_delivery_id_f, str);
					NetModule.excutor.excute(itemInsert.createSQL());
				}
			}
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	@Override
	public DeliveryOrderPO find(String order_id) throws RemoteException {
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
			ExpressOrderDataService expressorder = ExpressOrderDataServiceImpl
					.getInstance();
			Map<String, List<String>> orders = getByOrder_id(order_id);
			Set<String> keyset = orders.keySet();
			for (String key : keyset) {
				for (String order : orders.get(key)) {
					expressorder.setDelivery(order, key, state_info);
				}
			}
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	private DeliveryOrderPO getByResultSet(ResultSet re) {
		try {
			String order_id = re.getString(order_id_f), time = re
					.getString(time_f), comment = re.getString(comment_f);
			Map<String, List<String>> orders = getByOrder_id(order_id);
			DeliveryOrderPO po = new DeliveryOrderPO(orders, time, order_id,
					comment);
			return po;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return null;
	}

	private Map<String, List<String>> getByOrder_id(String order_id) {
		try {
			String sql = "SELECT * FROM " + itemTable + " WHERE "
					+ item_order_id_f + " = '" + order_id + "' GROUP BY "
					+ item_delivery_id_f + ";";
			Map<String, List<String>> result = new HashMap<String, List<String>>();
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			while (re.next()) {
				String delivery_id = re.getString(item_delivery_id_f);
				List<String> orders = getByDelivery_id(delivery_id, order_id);
				result.put(delivery_id, orders);
			}
			return result;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	private List<String> getByDelivery_id(String delivery_id, String order_id) {
		try {
			String sql = "SELECT * FROM " + itemTable + " WHERE "
					+ item_order_id_f + " = '" + order_id + "' AND "
					+ item_delivery_id_f + " = '" + delivery_id + "';";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<String> orders = new ArrayList<String>();
			while (re.next())
				orders.add(re.getString(item_id_expressorder_f));
			return orders;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DeliveryOrderPO> getUnpassedOrders() throws RemoteException {
		// TODO 自动生成的方法存根

		try {
			String sql = "SELECT * FROM " + tableName + " WHERE " + passed_f
					+ " = false ;";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<DeliveryOrderPO> orders = new ArrayList<>();
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
}
