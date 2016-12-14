package gap.server.data.order;

import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.dataservice.orderdataservice.LoadOrderDataService;
import gap.common.dataservice.transFareDataService.TransFareDataService;
import gap.common.po.LoadOrderPO;
import gap.common.po.TransFarePO;
import gap.common.util.ResultMessage;
import gap.server.data.expressorder.ExpressOrderDataServiceImpl;
import gap.server.data.transFareData.TransFareDataImpl;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadOrderDataServiceImpl extends UnicastRemoteObject implements
		LoadOrderDataService {
	private String tableName = "loadorder", itemTable = "loadorderitem";
	private String order_id_f = "order_id", driver_id_f = "driver_id",
			guard_id_f = "guard_id", car_num_f = "car_num", time_f = "time",
			passed_f = "passed", target_ins_f = "target_ins_id",
			departure_ins_f = "departure_ins_id", comment_f = "comment",
			isSetArrived_f = "isSetArrived", price_f = "price";
	private String item_expressorder_id_f = "expressorder_id",
			item_order_id_f = "order_id";
	private InsertSQL orderInsert, itemInsert;
	private UpdateSQL update;

	public static LoadOrderDataService instance;

	private LoadOrderDataServiceImpl() throws RemoteException {
		orderInsert = new InsertSQL(tableName);
		itemInsert = new InsertSQL(itemTable);
		update = new UpdateSQL(tableName);
	}

	public static LoadOrderDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new LoadOrderDataServiceImpl();
		return instance;
	}

	@Override
	public ResultMessage add(LoadOrderPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String order_id = po.getID(), driver_id = po.getDriver_id(), guard_id = po
					.getGuard_id(), car_num = po.getCar_number();
			String time = po.getDate();
			orderInsert.clear();
			orderInsert.add(order_id_f, order_id);
			orderInsert.add(driver_id_f, driver_id);
			orderInsert.add(guard_id_f, guard_id);
			orderInsert.add(car_num_f, car_num);
			orderInsert.add(time_f, time);
			orderInsert.add(target_ins_f, po.getTargetins_id());
			orderInsert.add(departure_ins_f, po.getDepartureins_id());
			orderInsert.add(comment_f, po.getComment());
			orderInsert.add(price_f, po.getPrice());
			NetModule.excutor.excute(orderInsert.createSQL());
			List<String> orders = po.getOrders();
			ExpressOrderDataService expressOrder = ExpressOrderDataServiceImpl
					.getInstance();
			for (String str : orders) {
				expressOrder.setSubmit(str);
				itemInsert.clear();
				itemInsert.add(item_expressorder_id_f, str);
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
	public LoadOrderPO find(String order_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE " + order_id_f
					+ " = '" + order_id + "' AND " + passed_f + " = true ;";
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
			String sql = "SELECT * FROM " + tableName + " WHERE " + order_id_f
					+ " = '" + order_id + "' AND " + passed_f + " = true ;";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			re.next();
			String target_ins_id = re.getString(target_ins_f);

			String driver_id = re.getString(driver_id_f);
			double price = re.getDouble(price_f);
			TransFarePO transFare = new TransFarePO(price, order_id, driver_id);
			TransFareDataService transFareData = TransFareDataImpl
					.getInstance();
			transFareData.addTransFare(transFare);

			sql = "SELECT * FROM " + itemTable + " WHERE " + item_order_id_f
					+ " = '" + order_id + "';";
			ExpressOrderDataService orderData = ExpressOrderDataServiceImpl
					.getInstance();
			re = NetModule.excutor.excuteQuery(sql);
			while (re.next()) {
				orderData.setLoad(re.getString(item_expressorder_id_f),
						target_ins_id, state_info);
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
	public List<LoadOrderPO> getUnpassedOrders() throws RemoteException {
		// TODO 自动生成的方法存根

		try {
			String sql = "SELECT * FROM " + tableName + " WHERE " + passed_f
					+ " = false ;";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<LoadOrderPO> orders = new ArrayList<LoadOrderPO>();
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

	@Override
	public List<LoadOrderPO> getArrivingLoadOrder(String ins_id)
			throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE "
					+ target_ins_f + " = '" + ins_id
					+ "' AND isSetArrived = false AND passed=true;";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<LoadOrderPO> loadOrder = new ArrayList<LoadOrderPO>();
			while (re.next()) {
				loadOrder.add(getByResultSet(re));
			}
			return loadOrder;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ResultMessage setArrived(String order_id) throws RemoteException {
		// TODO 自动生成的方法存根
		update.clear();
		update.add(isSetArrived_f, true);
		update.setKey(order_id_f, order_id);
		String sql;
		try {
			sql = update.createSQL();
			NetModule.excutor.excute(sql);
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

	private LoadOrderPO getByResultSet(ResultSet re) {
		try {
			String order_id = re.getString(order_id_f), driver_id = re
					.getString(driver_id_f), guard_id = re
					.getString(guard_id_f), car_num = re.getString(car_num_f), time = re
					.getString(time_f), target_ins = re.getString(target_ins_f), departure_ins = re
					.getString(departure_ins_f), comment = re
					.getString(comment_f);
			double price = re.getDouble(price_f);
			List<String> orders = getByOrder_id(order_id);
			LoadOrderPO loadOrder = new LoadOrderPO(order_id, time, car_num,
					departure_ins, target_ins, driver_id, guard_id, orders,
					comment);
			loadOrder.setPrice(price);
			return loadOrder;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	private List<String> getByOrder_id(String order_id) {
		try {
			String sql = "SELECT * FROM " + itemTable + " WHERE "
					+ item_order_id_f + " = '" + order_id + "';";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<String> result = new ArrayList<String>();
			while (re.next()) {
				result.add(re.getString(item_expressorder_id_f));
			}
			return result;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

}
