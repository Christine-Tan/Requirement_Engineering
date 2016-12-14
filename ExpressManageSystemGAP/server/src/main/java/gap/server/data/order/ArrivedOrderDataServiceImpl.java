package gap.server.data.order;

import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.dataservice.orderdataservice.ArrivedOrderDataService;
import gap.common.dataservice.orderdataservice.LoadOrderDataService;
import gap.common.po.ArrivedOrderPO;
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

public class ArrivedOrderDataServiceImpl extends UnicastRemoteObject implements
		ArrivedOrderDataService {
	private String tableName = "arrivedorder", itemTable = "arrivedorderitem";
	private String order_id_f = "order_id", des_insid_f = "des_ins_id",
			from_ins_id_f = "from_ins_id", comment_f = "comment",
			time_f = "time", passed_f = "passed",
			load_order_id_f = "load_order_id", isStock_in_f = "is_stockin";
	private String item_id_expressorder_f = "expressorder_id",
			item_order_id_f = "order_id", item_arrivedstate_f = "arrivedstate";
	private InsertSQL orderInsert, itemInsert;
	private UpdateSQL update;
	private static ArrivedOrderDataService instance;

	private ArrivedOrderDataServiceImpl() throws RemoteException {
		super();
		orderInsert = new InsertSQL(tableName);
		itemInsert = new InsertSQL(itemTable);
		update = new UpdateSQL(tableName);
	}

	public static ArrivedOrderDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new ArrivedOrderDataServiceImpl();
		return instance;
	}

	public ResultMessage add(ArrivedOrderPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String order_id = po.getID(), des_ins = po.getDes_ins_id(), from_ind = po
					.getFrom_ins_id(), comment = po.getComment(), time = po
					.getTime();
			orderInsert.clear();
			orderInsert.add(order_id_f, order_id);
			orderInsert.add(des_insid_f, des_ins);
			orderInsert.add(from_ins_id_f, from_ind);
			orderInsert.add(comment_f, comment);
			orderInsert.add(time_f, time);
			orderInsert.add(load_order_id_f, po.getLoad_id());
			NetModule.excutor.excute(orderInsert.createSQL());
			Map<String, String> orders = po.getOrders();
			Set<String> sets = orders.keySet();
			for (String str : sets) {
				itemInsert.clear();
				itemInsert.add(item_order_id_f, order_id);
				itemInsert.add(item_id_expressorder_f, str);
				itemInsert.add(item_arrivedstate_f, orders.get(str));
				NetModule.excutor.excute(itemInsert.createSQL());
			}

			LoadOrderDataService loadOrder = LoadOrderDataServiceImpl
					.getInstance();
			loadOrder.setArrived(po.getLoad_id());

			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ArrivedOrderPO find(String order_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE " + order_id_f
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

	// 通过结果集返回到达单PO
	private ArrivedOrderPO getByResultSet(ResultSet re) {
		try {
			String order_id = re.getString(order_id_f), des_ins_id = re
					.getString(des_insid_f), from_ins_id = re
					.getString(from_ins_id_f), comment = re
					.getString(comment_f), time = re.getString(time_f), load_id = re
					.getString(load_order_id_f);
			Map<String, String> orders = getByOrder_id(order_id);
			if (orders == null)
				return null;
			ArrivedOrderPO po = new ArrivedOrderPO(orders, time, order_id,
					from_ins_id, des_ins_id, comment, load_id);
			return po;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	private Map<String, String> getByOrder_id(String order_id) {
		try {
			String sql = "SELECT * from " + itemTable + " WHERE "
					+ item_order_id_f + " = '" + order_id + "';";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			Map<String, String> result = new HashMap<String, String>();
			while (re.next()) {
				String order = re.getString(item_id_expressorder_f);
				String state = re.getString(item_arrivedstate_f);
				result.put(order, state);
			}
			return result;
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

			String sql = "SELECT * FROM " + itemTable + " WHERE "
					+ item_order_id_f + " = '" + order_id + "';";
			ExpressOrderDataService orderData = ExpressOrderDataServiceImpl
					.getInstance();
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			while (re.next()) {
				orderData.setArrived(re.getString(item_id_expressorder_f),
						order_id.substring(0, 7), state_info);
			}
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
	public List<ArrivedOrderPO> getUnpassedOrders() throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE " + passed_f
					+ " = false";
			List<ArrivedOrderPO> orders = new ArrayList<ArrivedOrderPO>();
			ResultSet re = NetModule.excutor.excuteQuery(sql);
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
	public List<ArrivedOrderPO> getStockinginArrivedOrder(String ins_id)
			throws RemoteException {
		// TODO 自动生成的方法存根
		String sql = "SELECT * FROM " + tableName + " WHERE " + des_insid_f
				+ " = '" + ins_id + "' AND " + isStock_in_f + " = "+false+" AND "+passed_f+" = "+true+";";
		try {
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<ArrivedOrderPO> arrived = new ArrayList<ArrivedOrderPO>();
			while (re.next()) {
				arrived.add(getByResultSet(re));
			}
			return arrived;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage setStockIn(String order_id) throws RemoteException {
		// TODO 自动生成的方法存根
		update.clear();
		update.add(isStock_in_f, true);
		update.setKey(order_id_f, order_id);
		String sql;
		try {
			sql = update.createSQL();
			NetModule.excutor.excute(sql);
			return ResultMessage.SUCCEED;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
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
