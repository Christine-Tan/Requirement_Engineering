package gap.server.data.strategydata;

import gap.common.dataservice.strategydataservice.RentDataService;
import gap.common.po.RentPO;
import gap.common.util.ResultMessage;
import gap.server.data.util.InsertSQL;
import gap.server.data.util.UpdateSQL;
import gap.server.initial.NetModule;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author seven
 *
 */
public class RentDataServiceImpl extends UnicastRemoteObject implements
		RentDataService {
	// 表名
	private String tablename = "rent", instable = "institution";
	// 字段
	private String money_f = "money", lastPaid_f = "lastPaid",
			insti_f = "institution_id";

	private InsertSQL insertSQL;
	private UpdateSQL updateSQL;

	public static RentDataService instance;

	public static RentDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new RentDataServiceImpl();
		return instance;
	}

	public RentDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		insertSQL = new InsertSQL(tablename);
		updateSQL = new UpdateSQL(tablename);
	}

	@Override
	public List<RentPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		List<RentPO> rents = new ArrayList<RentPO>();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT rent.money money_f,institution.name institution_f,rent.lastPaid lastPaid_f  FROM rent,institution WHERE institution.ins_id=rent.institution_id;");
			while (re.next()) {
				String institution = re.getString("institution_f");
				Date lastPaid = re.getDate("lastPaid_f");
				double money = Double.valueOf(re.getString("money_f"));
				RentPO po = new RentPO(institution, money, lastPaid);
				rents.add(po);
			}
			return rents;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage add(RentPO po) throws RemoteException {
		// TODO Auto-generated method stub
		String insname = po.getInstitution();
		double rent = po.getMoney();
		Date date = po.getLastPaidDate();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT rent.money money,institution.ins_id id FROM rent,institution WHERE institution.name='"
							+ insname
							+ "' AND institution.ins_id=rent.institution_id");
			if (re.next())
				return ResultMessage.EXISTED;
			ResultSet rs = NetModule.excutor
					.excuteQuery("SELECT ins_id FROM institution WHERE name='"
							+ insname + "';");
			rs.next();
			String id = rs.getString("ins_id");
			insertSQL.clear();
			insertSQL.add(insti_f, id);
			insertSQL.add(money_f, rent);
			insertSQL.add(lastPaid_f, date.toString());
			String sql = insertSQL.createSQL();
			NetModule.excutor.excute(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage modify(RentPO po) throws RemoteException {
		// TODO Auto-generated method stub
		String insname = po.getInstitution();
		double rent = po.getMoney();
		Date date = po.getLastPaidDate();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT rent.money money,institution.ins_id ins_id,rent.id id FROM rent,institution WHERE institution.name='"
							+ insname
							+ "' AND institution.ins_id=rent.institution_id");
			if (!re.next())
				return ResultMessage.NOTFOUND;
			updateSQL.clear();
			updateSQL.setKey("id", re.getInt("id"));
			updateSQL.add(insti_f, re.getString("ins_id"));
			updateSQL.add(money_f, rent);
			updateSQL.add(lastPaid_f, date.toString());
			String sql = updateSQL.createSQL();
			NetModule.excutor.excute(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return ResultMessage.FAILED;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage setPaid(String institution) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT ins_id FROM institution WHERE name='"
							+ institution + "';");
			if (!re.next())
				return ResultMessage.NOTFOUND;
			String ins_id = re.getString("ins_id");
			updateSQL.clear();
			updateSQL.add(lastPaid_f, new Date(System.currentTimeMillis()).toString());
			updateSQL.setKey(insti_f, ins_id);
			NetModule.excutor.excute(updateSQL.createSQL());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
