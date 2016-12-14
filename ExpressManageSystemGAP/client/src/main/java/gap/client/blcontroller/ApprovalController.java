package gap.client.blcontroller;

import gap.client.bl.approval.Approval;
import gap.common.util.ResultMessage;

import java.util.List;

public class ApprovalController {
    private static Approval approvalManage=new Approval();
	public ApprovalController() {
		// TODO Auto-generated constructor stub
	}
    
	public static List<Object> getUnpassedOrder(){
		return approvalManage.getUnpassedOrder();
	}
	
	public static  ResultMessage approve(List<Object> orders){
		return approvalManage.approve(orders);
	}
	
}
