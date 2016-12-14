package gap.client.ui.managerui.approvalui;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.common.po.BillOrderPO;
import gap.common.po.BillPO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BillOrderDetailPanel extends JPanel {
	JLabel courierid_jl, money_jl;
	List<ItemPanel> items;
	BillOrderPO po;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	public BillOrderDetailPanel(BillOrderPO billOrder) {
		// TODO Auto-generated constructor stub
		this.po = billOrder;
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(ComponentStyle.gray));
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		courierid_jl = new GAPLabel("快递员编号");
		money_jl = new GAPLabel("运费");
		items = new ArrayList<>();
		for (BillPO bill : po.getBills()) {
			items.add(new ItemPanel(bill));
		}
		reLayout();
	}

	private void reLayout() {

		gcons.insets = new Insets(5, 30, 5, 25);
		SwingConsole.addComponent(gb, gcons, this, courierid_jl, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(5, 10, 5, 30);
		SwingConsole.addComponent(gb, gcons, this, money_jl, 1, 0, 1, 1, 0, 0);
		for (int i = 1; i <= items.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i - 1), 0, i, 2, 1, 1, 0);
		}
	}

	class ItemPanel extends JPanel {
		GAPTextField courierid, money;
		GridBagLayout gbl;

		ItemPanel(BillPO po) {
			setBackground(Color.WHITE);

			courierid = new GAPTextField();
			money = new GAPTextField();
			courierid.closeEdit();
			money.closeEdit();
			courierid.setHorizontalAlignment(JTextField.CENTER);
			money.setHorizontalAlignment(JTextField.CENTER);
			courierid.setText(po.getCourierID());
			money.setText(po.getMoney() + "");

			// 布局
			gbl = new GridBagLayout();
			setLayout(gbl);
			gcons.insets = new Insets(5, 20, 5, 30);
			SwingConsole.addComponent(gbl, gcons, this, courierid, 0, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, money, 1, 0, 1, 1, 0, 0);
		}
	}
}
