package gap.server.data.order;

import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.dataservice.inventorydataservice.InventoryDataService;
import gap.common.dataservice.orderdataservice.StockinOrderDataService;
import gap.common.po.GoodsPO;
import gap.common.po.StockinOrderPO;
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

public class StockinOrderDataServiceImpl extends UnicastRemoteObject implements
		StockinOrderDataService {
	// 表名
	private String stockinTable = "stockinorder";
	// 字段
	private String order_id_f = "order_id", time_f = "time",
			ins_id_f = "ins_id", passed_f = "passed";
	// 表名
	private String stockinItemTable = "stockinitem";
	// 字段
	private String expressorder_id_f = "expressorder_id",
			orderId_f = "order_id", destination_f = "destination",
			sector_id_f = "sector_id", location_f = "location";

	private InsertSQL orderInsert, itemInsert;
	private UpdateSQL update;

	public static StockinOrderDataService instance;

	public StockinOrderDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		orderInsert = new InsertSQL(stockinTable);
		itemInsert = new InsertSQL(stockinItemTable);
		update = new UpdateSQL(stockinTable);

	}

	public static StockinOrderDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new StockinOrderDataServiceImpl();
		return instance;
	}

	@Override
	public ResultMessage add(StockinOrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		String order_id = po.getID(), time = po.getInDate(), ins_id = po
				.getIns_id();
		List<GoodsPO> goodPOs = po.getGoods();

		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM stockinorder WHERE order_id='"
							+ order_id + "';");
			if (re.next()) {
				System.out.println(" 入库单号为" + order_id + "的入库单已经存在");
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
			orderInsert.add(time_f, time);
			orderInsert.add(ins_id_f, ins_id);
			orderInsert.add(passed_f, false);
			NetModule.excutor.excute(orderInsert.createSQL());
			for (GoodsPO goods : goodPOs) {
				itemInsert.clear();
				itemInsert.add(expressorder_id_f, goods.getExpressorder_id());
				itemInsert.add(orderId_f, order_id);
				itemInsert.add(destination_f, goods.getDestination());
				itemInsert.add(sector_id_f, goods.getSector_id());
				itemInsert.add(location_f, goods.getLocation());
				NetModule.excutor.excute(itemInsert.createSQL());
			}
//			ArrivedOrderDataService arrivedData = ArrivedOrderDataServiceImpl.getInstance();
//			arrivedData.setStockIn(order_id);
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("入库单增加失败");

		}
		return ResultMessage.FAILED;
	}

	public StockinOrderPO getByResultSet(ResultSet re) {

		try {
			String date = re.getString(time_f);
			String order_id = re.getString(order_id_f);
			String ins_id = re.getString(ins_id_f);

			List<GoodsPO> goodPOs = getPOsByOrderId(order_id);

			StockinOrderPO po = new StockinOrderPO(goodPOs, date, order_id,
					ins_id);
			return po;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<GoodsPO> getPOsByOrderId(String order_id) {
		try {

			List<GoodsPO> goodPOs = new ArrayList<GoodsPO>();
			ResultSet itemre = NetModule.excutor
					.excuteQuery("SELECT * FROM stockinitem WHERE order_id = '"
							+ order_id + "' ;");
			// System.out.println(order_id);

			while (itemre.next()) {
				String id = itemre.getString(expressorder_id_f), destination = itemre
						.getString(destination_f), sector_id = itemre
						.getString(sector_id_f), location = itemre
						.getString(location_f);
				GoodsPO goods = new GoodsPO(id, location, null, "", sector_id,
						"", destination);
				goodPOs.add(goods);
			}
			return goodPOs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StockinOrderPO find(String order_id, String ins_id)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM stockinorder WHERE order_id = '"
							+ order_id
							+ "' AND "
							+ ins_id_f
							+ " = '"
							+ ins_id
							+ "';");
			if (re.next()) {
				return getByResultSet(re);
			} else {
				System.out.println("入库单号为" + order_id + "的入库单没找到");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("入库单号为" + order_id + "的入库单获取失败");
			e.printStackTrace();
		}
		return null;
	}

	public ResultMessage delete(String order_id) {
		SQLBuilder sql = new SQLBuilder();
		sql.DeleteFrom(stockinTable).Where(order_id_f).EQUALS(order_id)
				.excute();
		sql.DeleteFrom(stockinItemTable).Where(orderId_f).EQUALS(order_id)
				.excute();
		System.out.println("删除了");
		return ResultMessage.SUCCEED;

	}

	@Override
	public List<StockinOrderPO> getOneDay(String date, String ins_id)
			throws RemoteException {
		// TODO Auto-generated method stub

		// Calendar calendar = Calendar.getInstance();
		// calendar.set(2005, 11, 11);
		//
		// SimpleDateFormat simple = new SimpleDateFormat("yyyy-mm-dd");
		// simple.format(calendar.getTime());

		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM stockinorder WHERE ins_id = '"
							+ ins_id + "' AND time = '" + date + "';");
			List<StockinOrderPO> stockinOrders = new ArrayList<StockinOrderPO>();
			while (re.next()) {
				// System.out.println("找到啦");
				StockinOrderPO po = getByResultSet(re);
				stockinOrders.add(po);
			}
			return stockinOrders;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("中转中心" + ins_id + "," + date + "号的入库单获取失败");
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<StockinOrderPO> getRequired(String beginDate, String endDate,
			String ins_id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM stockinorder WHERE ins_id = '"
							+ ins_id + "' AND time >= '" + beginDate
							+ "'AND time <= '" + endDate + "' AND "+passed_f+" = "+true+";");
			ArrayList<StockinOrderPO> stockinOrders = new ArrayList<StockinOrderPO>();
			// if(!re.next()){
			// System.out.println();
			// }
			while (re.next()) {
				StockinOrderPO po = getByResultSet(re);
				stockinOrders.add(po);
			}
			return stockinOrders;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("中转中心" + ins_id + "," + beginDate + "号到"
					+ endDate + "号的入库单获取失败");
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
			
			List<GoodsPO> POs = getPOsByOrderId(order_id);
			if(POs!=null){
				InventoryDataService inventoryData = InventoryDataServiceImpl.getInstance();
//				inventoryData.setlistExisted(POs);
				ExpressOrderDataService expressorderData = ExpressOrderDataServiceImpl.getInstance();
				for(GoodsPO po: POs){
					inventoryData.setExisted(po.getExpressorder_id());
					expressorderData.setStockin(po.getExpressorder_id());
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

	@Override
	public List<StockinOrderPO> getUnpassedOrders() throws RemoteException {
		// TODO Auto-generated method stub
		List<StockinOrderPO> orders = new ArrayList<StockinOrderPO>();
		try {
			String sql = "SELECT * FROM " + stockinTable + " WHERE " + passed_f
					+ " = 'false' ;";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			
			while (re.next()) {
				orders.add(getByResultSet(re));
			}
			return orders;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public int getNextId(String cons) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT max(RIGHT(order_id,5)) max_id FROM "
					+ stockinTable + " WHERE LEFT(order_id,15)='" + cons + "';";
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
