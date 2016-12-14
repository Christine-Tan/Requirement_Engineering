package gap.server.data.transFareData;

import gap.common.dataservice.transFareDataService.TransFareDataService;
import gap.common.po.TransFarePO;
import gap.server.data.util.ListMaker;
import gap.server.data.util.SQLBuilder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransFareDataImpl extends UnicastRemoteObject implements
		TransFareDataService {

	private static TransFareDataImpl transFareDataImpl;

	public final String tableName = "transfare";
	public final String ID_col = "ID";
	public final String driverID_col = "driver_id";
	public final String money_col = "money";
	public final String orderID_col = "order_id";

	SQLBuilder builder;

	private TransFareDataImpl() throws RemoteException {
		builder = new SQLBuilder();
	}

	public static TransFareDataService getInstance() {
		if (transFareDataImpl == null) {
			try {
				transFareDataImpl = new TransFareDataImpl();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return transFareDataImpl;

	}

	@Override
	public List<TransFarePO> getTransFare() throws RemoteException {
		// TODO Auto-generated method stub
		builder = new SQLBuilder();
		builder.Select("*").From(tableName);

		ListMaker<TransFarePO> transFareListMaker = new ListMaker<TransFarePO>() {

			@Override
			public TransFarePO getPO(ResultSet resultSet) {
				// TODO Auto-generated method stub

				try {
					String orderID = resultSet.getString(orderID_col);
					String driverID = resultSet.getString(driverID_col);
					double money = resultSet.getDouble(money_col);
					return new TransFarePO(money, orderID, driverID);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}

			}

		};

		List<TransFarePO> list = transFareListMaker.getList(builder);
		return list;
	}

	@Override
	public boolean deleteTransFare(List<TransFarePO> transFareList)
			throws RemoteException {
		// TODO Auto-generated method stub
		builder = new SQLBuilder();

		for (TransFarePO po : transFareList) {
			String driverID = po.getDriverID();
			String orderID = po.getOrderID();

			builder.DeleteFrom(tableName).Where(orderID_col).EQUALS(orderID);
			
			System.out.println(builder);
			
			try {
				builder.excute();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}

		return true;

	}

	@Override
	public boolean addTransFare(TransFarePO po) throws RemoteException {
		// TODO Auto-generated method stub
		String driverID = po.getDriverID();
		String orderID = po.getOrderID();
		double money = po.getFare();
		builder = new SQLBuilder();
		builder.InsertInto(tableName, money_col, orderID_col, driverID_col)
				.Values(money, orderID, driverID);

		try {
			builder.excute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
