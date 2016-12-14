package gap.server.data.order;

import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.dataservice.inventorydataservice.InventoryDataService;
import gap.common.dataservice.orderdataservice.StockoutOrderDataService;
import gap.common.po.StockoutOrderPO;
import gap.common.util.ResultMessage;
import gap.server.data.expressorder.ExpressOrderDataServiceImpl;
import gap.server.data.inventorydata.InventoryDataServiceImpl;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.SQLBuilder;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockoutOrderDataServiceImpl extends UnicastRemoteObject implements
		StockoutOrderDataService {
	// 表名
	private String stockoutTable = "stockoutorder";
	// 字段
	private String order_id_f = "order_id", time_f = "time",
			transport_f = "transport", target_ins_f = "target_ins",
			ins_id_f = "ins_id", passed_f = "passed", loaded_f = "loaded";
	// 表名
	private String stockoutItemTable = "stockoutitem";
	// 字段
	private String id_f = "id", expressorder_id_f = "expressorder_id",
			orderId_f = "order_id";

	private InsertSQL orderInsert, itemInsert;
	private UpdateSQL update;

	public static StockoutOrderDataService instance;

	public StockoutOrderDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		orderInsert = new InsertSQL(stockoutTable);
		itemInsert = new InsertSQL(stockoutItemTable);
		update = new UpdateSQL(stockoutTable);
	}

	public static StockoutOrderDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new StockoutOrderDataServiceImpl();
		return instance;
	}

	@Override
	public ResultMessage add(StockoutOrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		String order_id = po.getID(), target_ins = po.getTarget_ins(), time = po
				.getOutDate(), transport = po.getTransport(), ins_id = po
				.getIns_id();
		List<String> ids = po.getExpressorder_ids();

		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM stockoutorder WHERE order_id='"
							+ order_id + "';");
			if (re.next()) {
				System.out.println(" 出库单号为" + order_id + "的出库单已经存在");
				return ResultMessage.EXISTED;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}

		try {
			orderInsert.clear();
			orderInsert.add(order_id_f, order_id);
			orderInsert.add(transport_f, transport);
			orderInsert.add(target_ins_f, target_ins);
			orderInsert.add(time_f, time);
			orderInsert.add(ins_id_f, ins_id);
			orderInsert.add(passed_f, false);
			orderInsert.add(loaded_f, false);
			NetModule.excutor.excute(orderInsert.createSQL());
			InventoryDataService inventory = InventoryDataServiceImpl
					.getInstance();
			for (String id : ids) {
				itemInsert.clear();
				itemInsert.add(expressorder_id_f, id);
				itemInsert.add(orderId_f, order_id);
				NetModule.excutor.excute(itemInsert.createSQL());
				inventory.setUnexisted(id);
			}
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("入库单增加失败");

		}
		return ResultMessage.FAILED;

	}

	public ResultMessage delete(String order_id) {
		SQLBuilder sql = new SQLBuilder();
		sql.DeleteFrom(stockoutTable).Where(order_id_f).EQUALS(order_id)
				.excute();
		sql.DeleteFrom(stockoutItemTable).Where(orderId_f).EQUALS(order_id)
				.excute();
		System.out.println("删除了");
		return ResultMessage.SUCCEED;

	}

	@Override
	public StockoutOrderPO find(String order_id, String ins_id)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT *FROM stockoutorder WHERE order_id = '"
					+ order_id + "' AND " + ins_id_f + " ='" + ins_id + "';";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			if (re.next()) {
				return getByResultSet(re);
			} else {
				System.out.println("出库单号为" + order_id + "的出库单没找到");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("出库单号为" + order_id + "的出库单获取失败");
			e.printStackTrace();
		}
		return null;
	}

	public StockoutOrderPO getByResultSet(ResultSet re) {
		try {
			String transport = re.getString(transport_f);
			String target_ins = re.getString(target_ins_f);
			String outDate = re.getString(time_f);
			String ins_id = re.getString(ins_id_f);
			String order_id = re.getString(order_id_f);

			List<String> order_ids = getidsByOrderId(order_id);

			StockoutOrderPO po = new StockoutOrderPO(order_ids, outDate,
					target_ins, order_id, transport, ins_id);

			return po;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getidsByOrderId(String order_id) {
		try {
			List<String> order_ids = new ArrayList<String>();
			ResultSet itemre = NetModule.excutor.excuteQuery("SELECT * FROM "
					+ stockoutItemTable + " WHERE order_id = '" + order_id
					+ "';");

			while (itemre.next()) {
				String id = itemre.getString(expressorder_id_f);
				order_ids.add(id);
			}
			return order_ids;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<StockoutOrderPO> getOneDay(String date, String ins_id)
			throws RemoteException {
		// TODO Auto-generated method stub

		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM stockoutorder WHERE ins_id ='"
							+ ins_id + "' AND time ='" + date + "';");
			List<StockoutOrderPO> stockoutOrders = new ArrayList<StockoutOrderPO>();
			while (re.next()) {
				StockoutOrderPO po = getByResultSet(re);
				stockoutOrders.add(po);
			}
			return stockoutOrders;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("中转中心" + ins_id + "," + date + "号的出库单获取失败");
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<StockoutOrderPO> getRequired(String beginDate, String endDate,
			String ins_id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM stockoutorder WHERE ins_id = '"
							+ ins_id + "' AND time >= '" + beginDate
							+ "'AND time<= '" + endDate + "' AND " + passed_f
							+ " = " + true + ";");
			ArrayList<StockoutOrderPO> stockoutOrders = new ArrayList<StockoutOrderPO>();
			while (re.next()) {
				StockoutOrderPO po = getByResultSet(re);
				stockoutOrders.add(po);
			}
			return stockoutOrders;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("中转中心" + ins_id + "," + beginDate + "号到"
					+ endDate + "号的出库单获取失败");
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public ResultMessage setPassed(String order_id, String state_info)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			update.clear();
			update.add(passed_f, true);
			update.setKey(order_id_f, order_id);
			NetModule.excutor.excute(update.createSQL());

			List<String> ids = getidsByOrderId(order_id);
			InventoryDataService inventory = InventoryDataServiceImpl
					.getInstance();
			ExpressOrderDataService expressorderData = ExpressOrderDataServiceImpl.getInstance();
			if (ids != null && ids.size() > 0) {
				for (String id : ids) {
					inventory.delete(id);
					expressorderData.setStockout(id, order_id.substring(0, 7));
				}
			}

			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	public ResultMessage setUnPassed(String order_id, String state_info)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			update.clear();
			update.add(passed_f, "false");
			update.setKey(order_id_f, order_id);
			NetModule.excutor.excute(update.createSQL());

			List<String> ids = getidsByOrderId(order_id);
			if (ids != null) {
				InventoryDataService inventoryData = InventoryDataServiceImpl
						.getInstance();
				inventoryData.delete(ids);
			}
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	@Override
	public ResultMessage setLoaded(String order_id) {
		try {
			update.clear();
			update.add(loaded_f, true);
			update.setKey(order_id_f, order_id);
			NetModule.excutor.excute(update.createSQL());

			List<String> ids = getidsByOrderId(order_id);
			if (ids != null) {
				InventoryDataService inventoryData = InventoryDataServiceImpl
						.getInstance();
				inventoryData.delete(ids);

				return ResultMessage.SUCCEED;
			} else {
				return ResultMessage.NOTFOUND;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.FAILED;

	}

	@Override
	public List<StockoutOrderPO> getUnpassedOrders() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM " + stockoutTable + " WHERE "
					+ passed_f + " = 'false' ;";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<StockoutOrderPO> orders = new ArrayList<StockoutOrderPO>();
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
	public List<StockoutOrderPO> getUnLoadedOrders() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM " + stockoutTable + " WHERE "
					+ passed_f + " = " + true + " AND " + loaded_f + " = '"
					+ false + "';";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<StockoutOrderPO> orders = new ArrayList<StockoutOrderPO>();
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
	public int getNextId(String cons) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT max(RIGHT(order_id,5)) max_id FROM "
					+ stockoutTable + " WHERE LEFT(order_id,15)='" + cons
					+ "';";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			int result;
			if (re.next()) {
				result = re.getInt(1) + 1;
			} else {
				result = 1;
			}
			return result;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}

}
