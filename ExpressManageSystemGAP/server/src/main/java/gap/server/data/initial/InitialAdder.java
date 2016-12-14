package gap.server.data.initial;

import static gap.server.data.initial.InitialTable.accountName_col;
import static gap.server.data.initial.InitialTable.accountTableName;
import static gap.server.data.initial.InitialTable.balance_col;
import static gap.server.data.initial.InitialTable.busClerk_col;
import static gap.server.data.initial.InitialTable.busHall_col;
import static gap.server.data.initial.InitialTable.centerClerk_col;
import static gap.server.data.initial.InitialTable.center_col;
import static gap.server.data.initial.InitialTable.city_col;
import static gap.server.data.initial.InitialTable.courier_col;
import static gap.server.data.initial.InitialTable.goodsNum_col;
import static gap.server.data.initial.InitialTable.historyTableName;
import static gap.server.data.initial.InitialTable.initialHistory_col;
import static gap.server.data.initial.InitialTable.occupiedRate_col;
import static gap.server.data.initial.InitialTable.peopleTableName;
import static gap.server.data.initial.InitialTable.stockMan_col;
import static gap.server.data.initial.InitialTable.stockName_col;
import static gap.server.data.initial.InitialTable.stockTableName;
import gap.common.po.AccountPO;
import gap.common.po.InitialHistoryPO;
import gap.common.po.InitialPeoplePO;
import gap.common.po.InitialStockPO;
import gap.server.data.util.SQLBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InitialAdder {
	
	public void addInitial(InitialHistoryPO po){
		SQLBuilder builder = new SQLBuilder();
		
		builder.Select("MAX(ID)").From(historyTableName);
		ResultSet set = builder.excuteQuery();
		int maxID = 100;
		
		try {
			set.next();
			maxID = set.getInt("MAX(ID)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		maxID++;
		
		builder.InsertInto(historyTableName).Values(
					maxID,
					po.getDate(),
					po.getTotalBusinessHall(),
					po.getTotalCenter(),
					po.getTotalCourier(),
					po.getTotalBusinessClerk(),
					po.getTotalCenterClerk(),
					po.getTotalStockman(),
					po.getTotalWarehouse(),
					po.getTotalStock(),
					po.getTotalAccount(),
					po.getTotalBalance()
				);
		builder.excute();
		
		builder.clear();
		
		
		for(AccountPO accountPO:po.accountPOs){
			builder = new SQLBuilder();
			
			builder.InsertInto(accountTableName, accountName_col,balance_col,initialHistory_col).
				Values(accountPO.getName(),accountPO.getBalance(),maxID);
			builder.excute();
		}
		
		for(InitialPeoplePO peoplePO:po.initialPeoplePOs){
			builder = new SQLBuilder();
			builder.InsertInto(
						peopleTableName, 
						city_col,
						busHall_col,
						center_col,
						courier_col,
						busClerk_col,
						centerClerk_col,
						stockMan_col,
						initialHistory_col
					).Values(
						peoplePO.getCityName(),
						peoplePO.getBusinessHallNum(),
						peoplePO.getCenterNum(),
						peoplePO.getCourierNum(),
						peoplePO.getBusinessClerkNum(),
						peoplePO.getCenterClerkNum(),
						peoplePO.getStockmanNum(),
						maxID
					);
			builder.excute();
		}
		
		for(InitialStockPO stockPO:po.initialStockPOs){
			builder = new SQLBuilder();
			builder.InsertInto(stockTableName, 
						stockName_col,
						goodsNum_col,
						occupiedRate_col,
						initialHistory_col
					).Values(
						stockPO.getStockName(),
						stockPO.getGoodsNum(),
						stockPO.getOccupiedRate(),
						maxID
					);
			builder.excute();
		}
		
	}

}
