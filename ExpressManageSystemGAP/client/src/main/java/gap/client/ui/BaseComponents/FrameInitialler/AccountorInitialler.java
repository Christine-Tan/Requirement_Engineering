package gap.client.ui.BaseComponents.FrameInitialler;

import gap.client.ui.AccountUI.AccountManagePanel;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.NavigateBar;
import gap.client.ui.BaseComponents.NavigateButton;
import gap.client.ui.BillOrderQueryUI.AccountorBillQueryMainPanel;
import gap.client.ui.initialUI.InitialMainPanel;
import gap.client.ui.paymentUI.PaymentMainPanel;
import gap.client.ui.tableUI.Cost_profitUI.CostProfitPanel;
import gap.client.ui.tableUI.OperationUI.OperationMainPanel;

public class AccountorInitialler extends FrameInitialler{
	protected void specificInitial(MainFrame mainFrame){
		NavigateBar navBar = mainFrame.getNavigateBar();
		
		NavigateButton accountManage = new NavigateButton(mainFrame,
				new AccountManagePanel(mainFrame),navBar, "账户管理");
		NavigateButton payment = new NavigateButton(mainFrame,
				new PaymentMainPanel(mainFrame), navBar, "制定付款单");
		NavigateButton costProfit = new NavigateButton(mainFrame,
				new CostProfitPanel(mainFrame), navBar, "成本收益");
		NavigateButton billOrderQuery = new NavigateButton(mainFrame,
				new AccountorBillQueryMainPanel(mainFrame), navBar, "查询收款单");
		NavigateButton operationCondition = new NavigateButton(mainFrame,
				new OperationMainPanel(mainFrame), navBar, "经营状况");
		NavigateButton initial = new NavigateButton(mainFrame,
				new InitialMainPanel(mainFrame), navBar, "期初建账");
		

		navBar.addButton(costProfit);
		navBar.addButton(operationCondition);
		navBar.addButton(accountManage);
		navBar.addButton(payment);
		navBar.addButton(billOrderQuery);
		navBar.addButton(initial);
		
		navBar.setSelect(costProfit);
		mainFrame.setVisible(true);
		
	}
}
