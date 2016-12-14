package gap.client.blservice.approvalblservice;

import gap.common.util.ResultMessage;

import java.util.List;

public interface ApprovalService {
	public List<Object> getUnpassedOrder();

	public ResultMessage approve(List<Object> orders);

}
