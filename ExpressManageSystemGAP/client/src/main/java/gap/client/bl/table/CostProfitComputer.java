package gap.client.bl.table;

import gap.client.vo.Cost_ProfitListVO;
import gap.common.po.Cost_profitPO;

import java.util.ArrayList;

public class CostProfitComputer {
	public Cost_ProfitListVO compute(ArrayList<Cost_profitPO> list){
		
		double sumIncome = 0;
		double sumPayment = 0;
		double rate = 0;
		
		for(Cost_profitPO po: list){
			sumIncome+= po.getIncome();
			sumPayment+= po.getPayment();
		}
	
		double netIncome = sumIncome-sumPayment;
		
		if(netIncome>0){
			rate = netIncome/sumPayment;
		}else{
			rate = 0;
		}
		
		Cost_ProfitListVO vo = new Cost_ProfitListVO(sumIncome, sumPayment, rate, netIncome);
		
	//	System.out.println(vo.getIncome()+"  "+vo.getPayment());
		return vo;
	}
}
