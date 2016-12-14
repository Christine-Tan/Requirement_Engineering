package gap.stub_driver.blservice.table;

import gap.client.blservice.tableblservice.TableService;
import gap.client.vo.Cost_ProfitListVO;
import gap.client.vo.OperatingConditionListVO;

import java.util.Date;

public class Table_driver {

	public void drive(TableService stub){
		Date begin = new Date(2011, 1, 1);
		Date end = new Date(2011,10,2);
		Cost_ProfitListVO cost_ProfitListVO = stub.getCost_ProfitList();
		System.out.println("get cost and profit list");
		OperatingConditionListVO operatingConditionListVO = 
				stub.getOperatingConditionList(begin, end);
		System.out.println("get operate list from "+begin.toString()+" to "+end.toString());
		System.out.println(stub.Cost_ProfitExcel(cost_ProfitListVO));
		System.out.println(stub.OperatingConditionExcel(operatingConditionListVO));
		
		
		
	}
	
	public static void main(String[] args) {
		
		new Table_driver().drive(new Table_stub());

	}
	
}
