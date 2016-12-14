package gap.stub_driver.blservice.table;

import gap.client.blservice.tableblservice.TableService;
import gap.client.vo.Cost_ProfitListVO;
import gap.client.vo.OperatingConditionListVO;
import gap.common.util.ResultMessage;

import java.util.Date;

public class Table_stub implements TableService {

	@Override
	public Cost_ProfitListVO getCost_ProfitList() {
		// TODO Auto-generated method stub
		return new Cost_ProfitListVO();
	}

	@Override
	public OperatingConditionListVO getOperatingConditionList(Date begin, Date end) {
		// TODO Auto-generated method stub
		return new OperatingConditionListVO();
	}

	@Override
	public ResultMessage Cost_ProfitExcel(Cost_ProfitListVO costProfitListVO) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCEED;
	}

	@Override
	public ResultMessage OperatingConditionExcel(OperatingConditionListVO operatingConditionListVO) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCEED;
	}

}
