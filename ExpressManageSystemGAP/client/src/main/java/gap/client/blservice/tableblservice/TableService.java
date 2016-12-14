package gap.client.blservice.tableblservice;

import gap.client.vo.Cost_ProfitListVO;
import gap.client.vo.OperatingConditionListVO;
import gap.common.util.ResultMessage;

import java.util.Date;

public interface TableService {

	public Cost_ProfitListVO getCost_ProfitList();

	public OperatingConditionListVO getOperatingConditionList(Date begin,
			Date end);

	public ResultMessage Cost_ProfitExcel(Cost_ProfitListVO costProfitListVO,String path);

	public ResultMessage OperatingConditionExcel(
			OperatingConditionListVO operatingConditionListVO,String path);

}
