package gap.server.data.strategydata;

import gap.common.dataservice.strategydataservice.PriceDataService;
import gap.common.po.PricePO;
import gap.common.util.ResultMessage;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author seven
 */
public class PriceDataServiceImpl extends UnicastRemoteObject implements
		PriceDataService {
	// 表名
	private String tablename = "price", city = "city";
	// 字段
	private String id_f = "id", cityid_f = "city_id", express_f = "express",
			standard_f = "standard", economic_f = "economic", base_f = "base";

	private InsertSQL priceInsert;
	private UpdateSQL priceUpdate;

	public static PriceDataService instance;

	public static PriceDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new PriceDataServiceImpl();
		return instance;
	}

	public PriceDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		priceInsert = new InsertSQL(tablename);
		priceUpdate = new UpdateSQL(tablename);
	}

	@Override
	public PricePO find(String city) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM city WHERE name='" + city
							+ "';");
			re.next();
			ResultSet rs = NetModule.excutor
					.excuteQuery("SELECT * FROM price WHERE city_id="
							+ Integer.valueOf(re.getString(id_f)) + ";");
			rs.next();
			int express = Integer.valueOf(rs.getString(express_f)), standard = Integer
					.valueOf(rs.getString(standard_f)), economic = Integer
					.valueOf(rs.getString(economic_f));
			double base = Double.valueOf(rs.getString(base_f));
			PricePO po = new PricePO(city, express, standard, economic, base);
			return po;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage add(PricePO po) throws RemoteException {
		// TODO Auto-generated method stub
		String city = po.getCity();
		try {
			ResultSet rs = NetModule.excutor
					.excuteQuery("SELECT * FROM city WHERE name='" + city
							+ "';");
			rs.next();
			int city_id = Integer.valueOf(rs.getString(id_f));
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM price WHERE city_id=" + city_id
							+ ";");
			if (re.next())
				return ResultMessage.FAILED;
			// 不存在该价格信息，可以新增
			int express = po.getExpress(), standard = po.getStandard(), economic = po
					.getEconomic();
			double base = po.getBase();
			try {
				priceInsert.clear();
				priceInsert.add(cityid_f, city_id);
				priceInsert.add(express_f, express);
				priceInsert.add(standard_f, standard);
				priceInsert.add(economic_f, economic);
				priceInsert.add(base_f, base);
				String sql = priceInsert.createSQL();
				NetModule.excutor.excute(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResultMessage.FAILED;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage modify(PricePO po) throws RemoteException {
		// TODO Auto-generated method stub
		String city = po.getCity();
		System.out.println("s");
		try {

			ResultSet rs = NetModule.excutor
					.excuteQuery("SELECT * FROM city WHERE name='" + city
							+ "';");
			rs.next();
			int city_id = rs.getInt(id_f);
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM price WHERE city_id=" + city_id
							+ ";");

			// 未找到该价格信息
			if (!re.next()) {
				System.out.println("aaa");
				return ResultMessage.NOTFOUND;
			} else {
				System.out.println("bbb");
				// 已存在该价格，可以修改
				int express = po.getExpress(), standard = po.getStandard(), economic = po
						.getEconomic();
				double base = po.getBase();
				priceUpdate.clear();
				priceUpdate.add(express_f, express);
				priceUpdate.add(standard_f, standard);
				priceUpdate.add(economic_f, economic);
				priceUpdate.add(base_f, base);
				priceUpdate.setKey(cityid_f, city_id);
				String sql = priceUpdate.createSQL();
				NetModule.excutor.excute(sql);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public List<PricePO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		List<PricePO> prices = new ArrayList<PricePO>();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM price;");
			while (re.next()) {
				ResultSet rs = NetModule.excutor
						.excuteQuery("SELECT * FROM city WHERE id="
								+ Integer.valueOf(re.getString(cityid_f)) + ";");
				rs.next();
				String city = rs.getString("name");
				int express = Integer.valueOf(re.getString(express_f)), standard = Integer
						.valueOf(re.getString(standard_f)), economic = Integer
						.valueOf(re.getString(economic_f));
				double base = Double.valueOf(re.getString(base_f));
				PricePO po = new PricePO(city, express, standard, economic,
						base);
				prices.add(po);
			}
			return prices;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
