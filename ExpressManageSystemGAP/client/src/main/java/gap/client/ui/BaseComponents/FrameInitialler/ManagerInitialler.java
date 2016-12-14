package gap.client.ui.BaseComponents.FrameInitialler;

import gap.client.ui.AccountUI.AccountManagePanel;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.NavigateBar;
import gap.client.ui.BaseComponents.NavigateButton;
import gap.client.ui.managerui.approvalui.ApprovalPanel;
import gap.client.ui.managerui.institutionui.InstitutionPanel;
import gap.client.ui.managerui.logui.LogPanel;
import gap.client.ui.managerui.priceui.PricePanel;
import gap.client.ui.managerui.rentui.RentPanel;
import gap.client.ui.managerui.salaryui.SalaryPanel;
import gap.client.ui.tableUI.Cost_profitUI.CostProfitPanel;
import gap.client.ui.tableUI.OperationUI.OperationMainPanel;

public class ManagerInitialler extends FrameInitialler {
	/**
	 * 初始化总经理界面
	 * 
	 * @param mainFrame
	 */

	protected void specificInitial(MainFrame mainFrame) {
		NavigateBar navBar = mainFrame.getNavigateBar();
		NavigateButton approval = new NavigateButton(mainFrame, new ApprovalPanel(mainFrame), navBar, "审批单据");
		NavigateButton institution = new NavigateButton(mainFrame, new InstitutionPanel(mainFrame), navBar, "机构管理");
		NavigateButton rent = new NavigateButton(mainFrame, new RentPanel(mainFrame), navBar, "制定租金");
		NavigateButton price = new NavigateButton(mainFrame, new PricePanel(mainFrame), navBar, "制定价格常量");
		NavigateButton salary = new NavigateButton(mainFrame, new SalaryPanel(mainFrame), navBar, "制定薪水策略");
		NavigateButton account = new NavigateButton(mainFrame, new AccountManagePanel(mainFrame), navBar, "查看银行账户");
		NavigateButton table = new NavigateButton(mainFrame, new CostProfitPanel(mainFrame), navBar, "查看成本收益表");
		NavigateButton opeCondition = new NavigateButton(mainFrame, new OperationMainPanel(mainFrame), navBar, "查看经营状况表");
		NavigateButton log = new NavigateButton(mainFrame, new LogPanel(mainFrame), navBar, "查看日志记录");
		navBar.setHeight(50);
		navBar.addButton(approval);
		navBar.addButton(institution);
		navBar.addButton(rent);
		navBar.addButton(price);
		navBar.addButton(salary);
		navBar.addButton(account);
		navBar.addButton(table);
		navBar.addButton(opeCondition);
		navBar.addButton(log);
		navBar.setSelect(approval);
		mainFrame.setVisible(true);
	}
}
