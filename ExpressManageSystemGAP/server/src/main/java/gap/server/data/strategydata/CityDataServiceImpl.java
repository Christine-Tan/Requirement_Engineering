package gap.server.data.strategydata;

import gap.common.dataservice.strategydataservice.CityDataService;
import gap.common.po.CityPO;
import gap.common.util.ResultMessage;
import gap.server.data.util.InsertSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDataServiceImpl extends UnicastRemoteObject implements
		CityDataService {
	// 表名
	private String tablename = "city";
	// 字段
	private String id_f = "id", city_f = "name", lati_f = "latitude",
			long_f = "longitude", province_f = "province_id";
	private InsertSQL insertSQL;

	public static CityDataService instance;

	public static CityDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new CityDataServiceImpl();
		return instance;
	}

	public CityDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		insertSQL = new InsertSQL(tablename);
	}

	@Override
	public CityPO find(String name) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT province.name provincename,city.name cityname, city.latitude,city.longitude FROM city,province WHERE city.name='"
							+ name + "'AND province.id=city.province_id" + ";");
			re.next();
			String city = re.getString("cityname"), province = re
					.getString("provincename");
			double latitude = Double.valueOf(re.getString("latitude")), longitude = Double
					.valueOf(re.getString("longitude"));
			CityPO po = new CityPO(city, province, latitude, longitude);
			return po;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage add(CityPO po) throws RemoteException {
		// TODO Auto-generated method stub
		double latitude = po.getLatitude();
		double longitude = po.getLongitude();
		String city = po.getCity(), province = po.getProvince();
		try {
			ResultSet rs = NetModule.excutor
					.excuteQuery("SELECT * FROM city WHERE name='" + city
							+ "';");
			if (rs.next()) {
				return ResultMessage.EXISTED;
			}
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT id FROM province WHERE name='"
							+ province + "';");
			re.next();
			int province_id = re.getInt("id");
			insertSQL.clear();
			insertSQL.add(city_f, city);
			insertSQL.add(province_f, province_id);
			insertSQL.add(lati_f, latitude);
			insertSQL.add(long_f, longitude);
			String sql = insertSQL.createSQL();
			NetModule.excutor.excute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public List<CityPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		List<CityPO> cities = new ArrayList<CityPO>();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT city.name cityname,province.name provincename,city.latitude lati,city.longitude longi FROM city,province WHERE province.id=city.province_id;");
			while (re.next()) {
				String city = re.getString("cityname"), province = re
						.getString("provincename");
				double latitude = Double.valueOf(re.getString("lati")), longitude = Double
						.valueOf(re.getString("longi"));
				CityPO po = new CityPO(city, province, latitude, longitude);
				cities.add(po);
			}
			return cities;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
