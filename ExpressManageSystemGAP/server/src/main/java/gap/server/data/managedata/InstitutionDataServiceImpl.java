package gap.server.data.managedata;

import gap.common.dataservice.managedataservice.InstitutionDataService;
import gap.common.po.InstitutionPO;
import gap.common.util.InstitutionType;
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
public class InstitutionDataServiceImpl extends UnicastRemoteObject implements
		InstitutionDataService {
	// 表名
	private String tableName = "institution";
	// 字段
	private String id_f = "ins_id", insname_f = "name",
			memberNum_f = "memberNum", address_f = "address",
			instype_f = "ins_type";

	private InsertSQL insertSQL;
	private UpdateSQL updateSQL;
	public static InstitutionDataService instance;

	public static InstitutionDataService getInstance() throws RemoteException {
		if (instance == null)
			instance = new InstitutionDataServiceImpl();
		return instance;
	}

	public InstitutionDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		insertSQL = new InsertSQL(tableName);
		updateSQL = new UpdateSQL(tableName);
	}

	@Override
	public ResultMessage add(InstitutionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		String ins_id = po.getInsId(), name = po.getInsName();
		int memberNum = po.getInsMember();
		String city = po.getInsCity();
		InstitutionType instype = po.getInsType();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM institution WHERE ins_id='"
							+ ins_id + "';");
			if (re.next()) {
				System.out.println(re.getString(insname_f));
				return ResultMessage.EXISTED;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		try {
			insertSQL.clear();
			insertSQL.add(id_f, ins_id);
			insertSQL.add(insname_f, name);
			insertSQL.add(memberNum_f, memberNum);
			insertSQL.add(address_f, city);
			insertSQL.add(instype_f, instype);
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
	public InstitutionPO findById(String ins_id) throws RemoteException {
		// TODO Auto-generated method stub
		ResultSet re;
		try {
			re = NetModule.excutor
					.excuteQuery("SELECT * FROM institution WHERE ins_id='"
							+ ins_id + "';");
			re.next();
			String id = re.getString(id_f), name = re.getString(insname_f), city = re
					.getString(address_f);
			;
			int num = re.getInt(memberNum_f);
			InstitutionPO po = new InstitutionPO(id, name, city, num);
			return po;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ResultMessage modify(InstitutionPO po) throws RemoteException {
		// TODO Auto-generated method stub
		String ins_id = po.getInsId(), name = po.getInsName();
		int memberNum = po.getInsMember();
		String city = po.getInsCity();
		InstitutionType instype = po.getInsType();
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM institution WHERE ins_id='"
							+ ins_id + "';");
			if (!re.next()) {
				return ResultMessage.NOTFOUND;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		try {
			updateSQL.clear();
			// updateSQL.add(id_f, ins_id);
			updateSQL.add(insname_f, name);
			updateSQL.add(memberNum_f, memberNum);
			updateSQL.add(address_f, city);
			updateSQL.add(instype_f, instype);
			updateSQL.setKey(id_f, ins_id);
			String sql = updateSQL.createSQL();
			NetModule.excutor.excute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage delete(String ins_id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM institution WHERE ins_id='"
							+ ins_id + "';");
			if (!re.next()) {
				return ResultMessage.NOTFOUND;
			} else
				NetModule.excutor
						.excute("DELETE FROM institution WHERE ins_id='"
								+ ins_id + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
		return ResultMessage.SUCCEED;
	}

	@Override
	public List<InstitutionPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			List<InstitutionPO> institutions = new ArrayList<InstitutionPO>();
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM institution;");
			while (re.next()) {
				String id = re.getString(id_f), name = re.getString(insname_f), city = re
						.getString(address_f);
				int num = re.getInt(memberNum_f);
				InstitutionPO po = new InstitutionPO(id, name, city, num);
				institutions.add(po);
			}
			return institutions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<InstitutionPO> findByCity(String city) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			List<InstitutionPO> institutions = new ArrayList<InstitutionPO>();
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM institution WHERE address='"
							+ city + "';");
			while (re.next()) {
				String id = re.getString(id_f), name = re.getString(insname_f);
				int num = re.getInt(memberNum_f);
				InstitutionPO po = new InstitutionPO(id, name, city, num);
				institutions.add(po);
			}
			return institutions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InstitutionPO findByName(String ins_name) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet re = NetModule.excutor
					.excuteQuery("SELECT * FROM institution WHERE name='"
							+ ins_name + "';");
			if (re.next()) {
				String id = re.getString(id_f), city = re.getString(address_f);
				int num = re.getInt(memberNum_f);
				InstitutionPO po = new InstitutionPO(id, ins_name, city, num);
				return po;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
