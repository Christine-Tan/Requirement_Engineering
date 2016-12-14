package gap.server.data.expressorder;

import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.dataservice.userdataservice.UserDataService;
import gap.common.po.AllAddressPO;
import gap.common.po.ExpressOrderModifyPO;
import gap.common.po.ExpressOrderPO;
import gap.common.po.UserPO;
import gap.common.util.Address;
import gap.common.util.CargoInfo;
import gap.common.util.CurrentOrderType;
import gap.common.util.ExpressType;
import gap.common.util.PeopleInfo;
import gap.common.util.ReceiveInfo;
import gap.common.util.ResultMessage;
import gap.server.data.userdata.UserDataServiceImpl;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.SelectSQL;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressOrderDataServiceImpl extends UnicastRemoteObject implements
		ExpressOrderDataService {
	// 插入语句生成器
	private InsertSQL senderInsert, receiverInsert, orderInsert, cargoInsert,
			addressInsert, stateInsert, receivedInsert, deliveryTimeInsert;

	private UpdateSQL update;
	// 表名
	private String senderTable = "sender_info",
			receivedTable = "received_info", receiverTable = "receiver_info",
			cargoTable = "cargo_info", addressTable = "address",
			stateTable = "expressorderstate",
			deliveryTimeTable = "deliverytime", tableName = "expressorder";
	// expressorder表字段名
	private String order_id_f = "order_id", current_ins_id_f = "currentIns_id",
			target_ins_id_f = "targetIns_id", received_f = "received",
			passed_f = "passed", isTransed_f = "isTransed",
			sender_info_f = "sender_info", receiver_info_f = "receiver_info",
			order_type_f = "order_type", cargo_info_f = "cargo_info",
			price_f = "price", delivery_id_f = "delivery_id",
			create_time_f = "create_time";
	// received表字段名
	private String rece_time_f = "time", rece_order_id_f = "order_id",
			rece_delivery_id_f = "delivery_id",
			rece_receiver_name_f = "receiver_name", rece_comment_f = "comment";
	// sender_info 和 receiver_info 表字段名
	private String info_name_f = "name", info_phone_f = "phone",
			info_depart_f = "department", info_address_f = "address";
	// cargo_info 表字段名
	private String cargo_name_f = "cargoName", cargo_num_f = "numbers",
			cargo_weight_f = "weight", cargo_volume = "volume";
	// address表字段名
	private String add_id_f = "id", add_province_f = "province",
			add_district_f = "district", add_city_f = "city";
	// state 表字段名
	private String state_id_f = "order_id", state_time_f = "state_time",
			state_state_f = "state";
	// deliverytime 表字段名
	private String departure_city_id_f = "departure_city_id",
			target_city_id_f = "target_city_id", cost_time_f = "cost_time",
			order_num_f = "order_num", time_order_type_f = "order_type";

	private static ExpressOrderDataService instance;

	public static ExpressOrderDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new ExpressOrderDataServiceImpl();
		return instance;
	}

	private ExpressOrderDataServiceImpl() throws RemoteException {
		senderInsert = new InsertSQL(senderTable);
		receiverInsert = new InsertSQL(receiverTable);
		cargoInsert = new InsertSQL(cargoTable);
		orderInsert = new InsertSQL(tableName);
		addressInsert = new InsertSQL(addressTable);
		stateInsert = new InsertSQL(stateTable);
		receivedInsert = new InsertSQL(receivedTable);
		deliveryTimeInsert = new InsertSQL(deliveryTimeTable);
		update = new UpdateSQL(tableName);
	}

	/**
	 * 添加订单
	 */
	@Override
	public ResultMessage add(ExpressOrderPO po, String courier_id)
			throws RemoteException {
		// TODO 自动生成的方法存根

		try {
			PeopleInfo sender = po.getSenderInfo(), receiver = po
					.getReceiverInfo();
			CargoInfo cargo = po.getCargoInfo();
			String order_id = po.getID(), current_ins_id = po
					.getCurrentins_id();
			ExpressType type = po.getExpressType();
			double price = po.getPrice();

			// 判断对应订单号是否已经存在
			String sele = "SELECT * FROM expressorder WHERE order_id='"
					+ order_id + "';";
			ResultSet re = NetModule.excutor.excuteQuery(sele);
			if (re.next()) {
				return ResultMessage.EXISTED;
			}

			int sender_id, receiver_id, cargo_id;

			if (((sender_id = saveSenderinfo(sender)) == -1)
					|| ((receiver_id = saveReceiverinfo(receiver)) == -1)
					|| ((cargo_id = saveCargoInfo(cargo)) == -1))
				return ResultMessage.FAILED;

			orderInsert.clear();
			orderInsert.add(sender_info_f, sender_id);
			orderInsert.add(receiver_info_f, receiver_id);
			orderInsert.add(cargo_info_f, cargo_id);
			orderInsert.add(price_f, price);
			orderInsert.add(order_id_f, order_id);
			orderInsert.add(current_ins_id_f, current_ins_id);
			orderInsert.add(order_type_f, type);
			orderInsert.add(delivery_id_f, courier_id);
			orderInsert.add(create_time_f,
					(new Date(System.currentTimeMillis()).toString()));
			String sql = orderInsert.createSQL();
			NetModule.excutor.excute(sql);
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	@Override
	public boolean isExisted(String order_id) throws RemoteException {
		// TODO 自动生成的方法存根
		String sele = "SELECT * FROM expressorder WHERE order_id='" + order_id
				+ "';";
		try {
			ResultSet re = NetModule.excutor.excuteQuery(sele);
			if (re.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 根据订单号查找单一订单
	 */
	@Override
	public ExpressOrderPO find(String order_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT * FROM expressorder WHERE order_id = '"
					+ order_id + "' AND " + passed_f + " = 'true';";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			re.next();
			return getByResultSet(re);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改订单
	 */
	@Override
	public ResultMessage modify(ExpressOrderModifyPO po) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String order_id = po.getOrder_id();
			String current_ins_id = po.getCurrentIns_id(), target_ins_id = po
					.getTargetIns_id();
			boolean received = po.isReceived(), passed = po.isPassed(), isTransed = po
					.isTransed();

			String sele = "SELECT * FROM " + tableName + " WHERE " + order_id_f
					+ " = '" + order_id + "';";
			ResultSet re = NetModule.excutor.excuteQuery(sele);
			if (!re.next()) {
				return ResultMessage.NOTFOUND;
			}
			update.clear();
			update.add(current_ins_id_f, current_ins_id);
			update.add(target_ins_id_f, target_ins_id);
			update.add(received_f, received);
			update.add(passed_f, passed);
			update.add(isTransed_f, isTransed);
			update.setKey(order_id_f, order_id);
			String sql = update.createSQL();
			NetModule.excutor.excute(sql);
			return ResultMessage.SUCCEED;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	/**
	 * 获得即将到达的订单
	 */
	@Override
	public List<ExpressOrderPO> findArrivingOrders(String ins_id)
			throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			List<ExpressOrderPO> orders = new ArrayList<ExpressOrderPO>();
			String sql = "SELECT * FROM expressorder WHERE targetIns_id='"
					+ ins_id + "' AND " + passed_f + " != 'false';";
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

	/**
	 * 获得目前在机构的订单
	 */
	@Override
	public List<ExpressOrderPO> findCurrentOrders(String ins_id,
			CurrentOrderType type) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			List<ExpressOrderPO> orders = new ArrayList<ExpressOrderPO>();
			String sql = "SELECT * FROM expressorder WHERE currentIns_id='"
					+ ins_id + "' AND " + passed_f + " = 'true' ";
			if (type == CurrentOrderType.ALL)
				sql += " ;";
			else if (type == CurrentOrderType.LOAD)
				sql += " AND " + isTransed_f + " = false;";
			else
				sql += " AND " + isTransed_f + " = true;";
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
	public List<ExpressOrderPO> findLoadOrders(String loadorder_id)
			throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<ExpressOrderPO> findDeliveryOrders(String deliveryorder_id)
			throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<ExpressOrderPO> findArrivedOrders(String arrivedorder_id)
			throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage addState(String order_id, String state)
			throws RemoteException {
		try {
			stateInsert.clear();
			stateInsert.add(state_id_f, order_id);
			stateInsert.add(state_state_f, state);
			String sql = stateInsert.createSQL();
			NetModule.excutor.excute(sql);
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;

	}

	@Override
	public List<ExpressOrderPO> getUnpassedOrder() throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE " + passed_f
					+ " = 'false';";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<ExpressOrderPO> orders = new ArrayList<ExpressOrderPO>();
			while (re.next())
				orders.add(getByResultSet(re));
			return orders;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage setPassed(String order_id, String state)
			throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			addState(order_id, state);
			update.clear();
			update.add(passed_f, true);
			update.setKey(order_id_f, order_id);
			NetModule.excutor.excute(update.createSQL());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> getState(String order_id) throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT * FROM " + stateTable + " WHERE order_id='"
					+ order_id + "' ORDER BY " + state_time_f + ";";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			List<String> states = new ArrayList<String>();
			while (re.next()) {
				states.add(re.getString(state_state_f));
			}
			return states;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 制定到达单时设置到达信息
	 */
	@Override
	public ResultMessage setArrived(String order_id, String ins_id,
			String stateMessage) throws RemoteException {
		// TODO 自动生成的方法存根
		addState(order_id, stateMessage);
		ExpressOrderModifyPO modify = new ExpressOrderModifyPO(order_id,
				ins_id, null, false, true, true);
		return modify(modify);
	}

	@Override
	public ResultMessage setLoad(String order_id, String ins_id,
			String stateMessage) throws RemoteException {
		if (addState(order_id, stateMessage) == ResultMessage.FAILED)
			return ResultMessage.FAILED;
		ExpressOrderModifyPO modify = new ExpressOrderModifyPO(order_id, null,
				ins_id, false, true, true);
		return modify(modify);

	}

	@Override
	public ResultMessage setDelivery(String order_id, String delivery_id,
			String stateMessage) throws RemoteException {
		// TODO 自动生成的方法存根
		UserDataService user = UserDataServiceImpl.getInstance();
		UserPO userPO = user.findById(delivery_id);
		String message = userPO.getName() + "正在派件";
		return addState(order_id, message);
	}

	@Override
	public ResultMessage setRecieved(ReceiveInfo info) throws RemoteException {
		// TODO 自动生成的方法存根
		String order_id = info.getOrder_id();
		String stateMessage = "快递已签收,收件人:" + info.getReceiver_name();
		if (addState(order_id, stateMessage) == ResultMessage.FAILED)
			return ResultMessage.FAILED;
		ExpressOrderModifyPO modify = new ExpressOrderModifyPO(order_id, null,
				null, true, true, true);
		modify(modify);

		receivedInsert.clear();
		receivedInsert.add(rece_delivery_id_f, info.getDelivery_id());
		receivedInsert.add(rece_order_id_f, order_id);
		receivedInsert.add(rece_receiver_name_f, info.getReceiver_name());
		receivedInsert.add(rece_time_f, info.getReceive_time());
		receivedInsert.add(rece_comment_f, info.getComment());

		String sql = "SELECT * FROM " + tableName + " WHERE " + order_id_f
				+ "='" + order_id + "';";

		try {
			NetModule.excutor.excute(receivedInsert.createSQL());
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			re.next();
			long createTime, tempTime;
			double costTime;
			createTime = re.getDate(create_time_f).getTime();
			tempTime = Date.valueOf(info.getReceive_time()).getTime();
			costTime = ((double) (tempTime - createTime)) / (3600L * 1000);
			String depar = getPeopleInfo(re.getInt(sender_info_f), senderTable)
					.getAddress().getCity_name();
			String targ = getPeopleInfo(re.getInt(receiver_info_f),
					receiverTable).getAddress().getCity_name();
			String type = re.getString(order_type_f);
			System.out.println(type);

			String sql1 = "SELECT * FROM " + deliveryTimeTable + " WHERE "
					+ departure_city_id_f + " = '" + depar + "' AND "
					+ target_city_id_f + " = '" + targ + "' AND "
					+ time_order_type_f + " = '" + type + "';";
			ResultSet re1 = NetModule.excutor.excuteQuery(sql1);
			if (re1.next()) {
				String id = re1.getString("id");

				int total_order_num = re1.getInt(order_num_f);

				double ave_costTime = re1.getDouble(cost_time_f);

				double ave_time = (ave_costTime * total_order_num + costTime)
						/ (total_order_num + 1);

				total_order_num++;

				String sql2 = "UPDATE " + deliveryTimeTable + " SET "
						+ cost_time_f + " = " + ave_time + "," + order_num_f
						+ " = " + total_order_num + " WHERE id = " + id + ";";

				NetModule.excutor.excute(sql2);
			} else {
				deliveryTimeInsert.clear();
				deliveryTimeInsert.add(departure_city_id_f, depar);
				deliveryTimeInsert.add(target_city_id_f, targ);
				deliveryTimeInsert.add(cost_time_f, costTime);
				deliveryTimeInsert.add(time_order_type_f, type);
				deliveryTimeInsert.add(order_num_f, 1);
				NetModule.excutor.excute(deliveryTimeInsert.createSQL());
			}
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	@Override
	public ResultMessage setStockin(String order_id) throws RemoteException {
		// TODO 自动生成的方法存根
		ExpressOrderModifyPO modify = new ExpressOrderModifyPO(order_id, null,
				null, false, true, true);
		return modify(modify);
	}

	@Override
	public ResultMessage setStockout(String order_id, String ins_id)
			throws RemoteException {
		// TODO 自动生成的方法存根
		ExpressOrderModifyPO modify = new ExpressOrderModifyPO(order_id,
				ins_id, null, false, true, true);
		return modify(modify);
	}

	@Override
	public double getDeliveryMoney(String date, String delivery_id)
			throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT " + price_f + " FROM " + tableName + " WHERE "
					+ delivery_id_f + " = '" + delivery_id + "' AND "
					+ create_time_f + " = '" + date + "';";
			double sum = 0;
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			while (re.next()) {
				sum += re.getDouble(price_f);
			}
			return sum;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int nextId() throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			String sql = "SELECT max(order_id) FROM expressorder;";
			ResultSet re;
			re = NetModule.excutor.excuteQuery(sql);
			if (re.next()) {
				return re.getInt(1) + 1;
			} else
				return 0;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;

	}

	/**
	 * 获得所有地址
	 */
	@Override
	public AllAddressPO getAllAddress() throws RemoteException {
		// TODO 自动生成的方法存根
		try {
			List<String> provinces = new ArrayList<String>();
			Map<String, List<String>> province2city = new HashMap<>();
			Map<String, List<String>> city2district = new HashMap<>();
			String sql = "SELECT * FROM province;";
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			while (re.next()) {
				provinces.add(re.getString("name"));
			}
			for (String str : provinces) {
				List<String> cities = new ArrayList<String>();
				String sql1 = "SELECT city.name FROM city,province WHERE province.name='"
						+ str + "' AND city.province_id=province.id;";
				ResultSet re1 = NetModule.excutor.excuteQuery(sql1);
				while (re1.next()) {
					cities.add(re1.getString("name"));
				}
				province2city.put(str, cities);
				for (String str1 : cities) {
					List<String> districts = new ArrayList<String>();
					String sql2 = "SELECT district.name FROM city,district WHERE city.name='"
							+ str1 + "' AND city.id=district.city_id;";
					ResultSet re2 = NetModule.excutor.excuteQuery(sql2);
					while (re2.next()) {
						districts.add(re2.getString("name"));
					}
					city2district.put(str1, districts);
				}
			}
			AllAddressPO po = new AllAddressPO();
			po.setCity2district(city2district);
			po.setProvinces(provinces);
			po.setProvince2city(province2city);
			return po;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage setSubmit(String order_id) throws RemoteException {
		// TODO 自动生成的方法存根
		String sql = "UPDATE " + tableName + " SET " + passed_f
				+ " = 'submitnotapprove' WHERE " + order_id_f + " = '"
				+ order_id + "';";
		try {
			NetModule.excutor.excute(sql);
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

	@Override
	public double getDeliveryTime(String departure_city, String target_city,
			ExpressType type) throws RemoteException {
		// TODO 自动生成的方法存根
		String sql = "SELECT * FROM " + deliveryTimeTable + " WHERE "
				+ departure_city_id_f + " = '" + departure_city + "' AND "
				+ target_city_id_f + " = '" + target_city + "' AND "
				+ time_order_type_f + " = '" + type + "';";
		try {
			ResultSet re = NetModule.excutor.excuteQuery(sql);
			if (re.next()) {
				return re.getDouble(cost_time_f);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 通过结果集获得订单信息
	 * @param re
	 * @return
	 */
	private ExpressOrderPO getByResultSet(ResultSet re) {
		try {
			String order_id = re.getString(order_id_f), currentins_id = re
					.getString(current_ins_id_f), targetins_id = re
					.getString(target_ins_id_f), time = re
					.getString(create_time_f);
			ExpressType type = ExpressType.valueOf(re.getString(order_type_f));
			double price = re.getDouble(price_f);
			int sender_id = re.getInt(sender_info_f), receiver_id = re
					.getInt(receiver_info_f), cargo_id = re
					.getInt(cargo_info_f);
			PeopleInfo sender = getPeopleInfo(sender_id, senderTable), receiver = getPeopleInfo(
					receiver_id, receiverTable);
			CargoInfo cargo = getCargo(cargo_id);
			ExpressOrderPO order = new ExpressOrderPO(sender, receiver, type,
					cargo, order_id, price, currentins_id, targetins_id, time);
			return order;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得寄件人或收件人信息
	 * @param id
	 * @param tableName
	 * @return
	 */
	private PeopleInfo getPeopleInfo(int id, String tableName) {
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE id= " + id
					+ ";";
			ResultSet re;
			re = NetModule.excutor.excuteQuery(sql);
			re.next();
			String name = re.getString(info_name_f), phone = re
					.getString(info_phone_f), department = re
					.getString(info_depart_f);
			Address address = getAddress(re.getInt(info_address_f));
			PeopleInfo people = new PeopleInfo(name, address, department, phone);
			return people;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得地址信息
	 * @param id
	 * @return
	 */
	private Address getAddress(int id) {
		try {
			String sql = "SELECT * FROM " + addressTable + " WHERE id= " + id
					+ ";";
			ResultSet re;
			re = NetModule.excutor.excuteQuery(sql);
			re.next();
			String province = re.getString(add_province_f), city = re
					.getString(add_city_f), distric = re
					.getString(add_district_f);
			Address add = new Address(province, city, distric);
			return add;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得货物信息
	 * @param id
	 * @return
	 */
	private CargoInfo getCargo(int id) {
		try {
			String sql = "SELECT * FROM " + cargoTable + " WHERE id= " + id
					+ ";";
			ResultSet re;
			re = NetModule.excutor.excuteQuery(sql);
			re.next();
			String name = re.getString(cargo_name_f);
			int numbers = re.getInt(cargo_num_f);
			double weight = re.getDouble(cargo_weight_f), volumn = re
					.getDouble(cargo_volume);
			CargoInfo cargo = new CargoInfo(numbers, weight, volumn, name);
			return cargo;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 保存寄件人信息
	 * @param info
	 * @return
	 */
	private int saveSenderinfo(PeopleInfo info) {
		try {
			int address_id = saveAddress(info.getAddress());
			senderInsert.clear();
			senderInsert.add(info_name_f, info.getName());
			senderInsert.add(info_address_f, address_id);
			senderInsert.add(info_depart_f, info.getDepart());
			senderInsert.add(info_phone_f, info.getCellphone());
			String sql = senderInsert.createSQL();
			NetModule.excutor.excute(sql);
			String seSql = "SELECT max(id) from " + senderTable + ";";
			ResultSet re = NetModule.excutor.excuteQuery(seSql);
			re.next();
			return re.getInt(1);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 保存收件人信息
	 * @param info
	 * @return
	 */
	private int saveReceiverinfo(PeopleInfo info) {
		try {
			int address_id = saveAddress(info.getAddress());
			receiverInsert.clear();
			receiverInsert.add(info_name_f, info.getName());
			receiverInsert.add(info_address_f, address_id);
			receiverInsert.add(info_depart_f, info.getDepart());
			receiverInsert.add(info_phone_f, info.getCellphone());
			String sql = receiverInsert.createSQL();
			NetModule.excutor.excute(sql);
			String seSql = "SELECT max(id) from " + receiverTable + ";";
			ResultSet re = NetModule.excutor.excuteQuery(seSql);
			re.next();
			return re.getInt(1);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 保存货物信息
	 * @param info
	 * @return
	 */
	private int saveCargoInfo(CargoInfo info) {
		try {
			cargoInsert.clear();
			cargoInsert.add(cargo_name_f, info.getName());
			cargoInsert.add(cargo_num_f, info.getCargoNum());
			cargoInsert.add(cargo_weight_f, info.getWeight());
			cargoInsert.add(cargo_volume, info.getVolume());
			String sql = cargoInsert.createSQL();
			NetModule.excutor.excute(sql);
			String seSql = "SELECT max(id) from " + cargoTable + ";";
			ResultSet re = NetModule.excutor.excuteQuery(seSql);
			re.next();
			return re.getInt(1);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 保存地址信息
	 * @param address
	 * @return
	 */
	public int saveAddress(Address address) {
		try {
			// 若地址表中有对应地址项，则直接返回对应记录id
			SelectSQL sql = new SelectSQL(addressTable);
			sql.addConstraint(add_city_f, address.getCity_name());
			sql.addConstraint(add_province_f, address.getProvince_name());
			sql.addConstraint(add_district_f, address.getDistrict_name());
			ResultSet re = NetModule.excutor.excuteQuery(sql.createSQL());
			if (re.next())
				return re.getInt(add_id_f);
			// 若不存在，这先添加对应id
			addressInsert.clear();
			addressInsert.add(add_city_f, address.getCity_name());
			addressInsert.add(add_district_f, address.getDistrict_name());
			addressInsert.add(add_province_f, address.getProvince_name());
			String inSql = addressInsert.createSQL();
			NetModule.excutor.excute(inSql);

			re = NetModule.excutor.excuteQuery(sql.createSQL());
			re.next();
			return re.getInt(add_id_f);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return -1;
	}

}
