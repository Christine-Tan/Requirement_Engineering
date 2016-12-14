package gap.server.data.accountdata;

import gap.common.dataservice.accountdataservice.AccountDataService;
import gap.common.po.AccountPO;
import gap.common.po.Cost_profitPO;
import gap.common.po.TradePO;
import gap.server.data.util.ListMaker;
import gap.server.data.util.SQLBuilder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDataServiceImpl extends UnicastRemoteObject implements
		AccountDataService {

	protected AccountDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final String tableName = "account";
	public final String Name_Col = "Name";
	public final String Balance_Col = "Balance";
	public final String Income_Col = "Income";
	public final String Pay_Col = "Pay";
	private SQLBuilder builder = new SQLBuilder();
	private static AccountDataServiceImpl accountDataService;

	public static AccountDataService getInstance() {

		if (accountDataService == null) {
			try {
				accountDataService = new AccountDataServiceImpl();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return accountDataService;
	}

	@Override
	public boolean add(AccountPO accountPO) throws RemoteException {
		// TODO Auto-generated method stub
		String name = accountPO.getName();
		double balance = accountPO.getBalance();
		builder.InsertInto(tableName).Values(name, balance, 0, 0);
		boolean result = builder.excute();
		return result;
	}

	@Override
	public boolean delete(AccountPO accountPO) throws RemoteException {
		// TODO Auto-generated method stub
		String name = accountPO.getName();
		builder.DeleteFrom(tableName).Where(this.Name_Col).EQUALS(name);
		boolean result = builder.excute();
		return result;
	}

	@Override
	public boolean modify(AccountPO accountPO) throws RemoteException {
		// TODO Auto-generated method stub
		String oldName = accountPO.getName();
		String newName = accountPO.getNewName();
		builder.Update(tableName).Set(this.Name_Col).Assign(newName)
				.Where(this.Name_Col).EQUALS(oldName);

		System.out.println(builder.getSQL());

		boolean result = builder.excute();
		return result;
	}

	@Override
	public boolean trade(TradePO tradePO) throws RemoteException {
		// TODO Auto-generated method stub
		double tradeMoney = tradePO.getTradeNum();
		String name = tradePO.getAccountName();
		builder.Select("*").From(tableName).Where(Name_Col).EQUALS(name);

		ResultSet resultSet = builder.excuteQuery();
		if (resultSet == null) {
			return false;
		}

		try {
			if (!resultSet.next()) {
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		double balance = 0;
		double income = 0;
		double pay = 0;

		try {
			balance = resultSet.getDouble(this.Balance_Col);
			income = resultSet.getDouble(this.Income_Col);
			pay = resultSet.getDouble(this.Pay_Col);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		balance += tradeMoney;

		if (tradeMoney >= 0) {
			income += tradeMoney;
		} else {
			pay -= tradeMoney;
		}
		builder.Update(tableName).Set(Balance_Col).Assign(balance)
				.Comma(Income_Col).Assign(income).Comma(Pay_Col).Assign(pay)
				.Where(Name_Col).EQUALS(name);

		return builder.excute();

	}

	@Override
	public ArrayList<Cost_profitPO> getCost_Profit() throws RemoteException {
		// TODO Auto-generated method stub
		ListMaker<Cost_profitPO> listMaker = new ListMaker<Cost_profitPO>() {
			@Override
			public Cost_profitPO getPO(ResultSet resultSet) {

				String name;
				double income = 0;
				double payment = 0;
				try {
					name = resultSet.getString(Name_Col);
					income = resultSet.getDouble(Income_Col);
					payment = resultSet.getDouble(Pay_Col);
					return new Cost_profitPO(name, income, payment);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		};

		return listMaker.getList(tableName);
	}

	@Override
	public ArrayList<AccountPO> getAccountList() throws RemoteException {
		// TODO Auto-generated method stub
		ListMaker<AccountPO> listMaker = new ListMaker<AccountPO>() {

			@Override
			public AccountPO getPO(ResultSet resultSet) {
				// TODO Auto-generated method stub
				String name;
				double balance;
				try {
					name = resultSet.getString(Name_Col);
					balance = resultSet.getDouble(Balance_Col);
					return new AccountPO(name, balance);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}

		};

		return listMaker.getList(tableName);
	}

}
