package gap.client.ui.managerui.approvalui;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.common.po.ExpressOrderPO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExpressOrderDetailPanel extends JPanel {
	JLabel type_jl, price_jl;
	GAPTextField type_tf, price_tf;
	String type;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	public ExpressOrderDetailPanel(ExpressOrderPO expressOrder) {
		// TODO Auto-generated constructor stub
		setBackground(Color.WHITE);
		type_jl = new GAPLabel("快递类型：");
		price_jl = new GAPLabel("快递价格：");
		switch (expressOrder.getExpressType().toString()) {
		case "STANDARD":
			type = "标准";
			break;
		case "ECONOMIC":
			type = "经济";
			break;
		case "EXPRESS":
			type = "特快";
			break;
		}
		type_tf = new GAPTextField();
		price_tf = new GAPTextField();
		type_tf.setText(type);
		price_tf.setText(String.valueOf(expressOrder.getPrice()));
		type_tf.closeEdit();
		price_tf.closeEdit();

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.insets = new Insets(5, 5, 5, 5);
		SwingConsole.addComponent(gb, gcons, this, type_jl, 0, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, type_tf, 1, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, price_jl, 2, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, price_tf, 3, 0, 1, 1, 0, 0);

	}

}
