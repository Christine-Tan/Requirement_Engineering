package gap.client.ui.deliveryui.expressorderinput;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.common.util.ExpressType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExpressInfoUI extends JPanel {
	JLabel title, type, price_la, time_la;
	GAPTextField price, time;
	JButton price_get;
	JComboBox<String> type_list;

	public ExpressInfoUI() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 100));

		// 初始化组件
		title = new GAPLabel("快件信息：");

		type = new GAPLabel("快递类型：");

		price_la = new GAPLabel("价格（元）：");

		time_la = new GAPLabel("预估时间（小时）：");

		price_get = new GAPButton("计算");
		price_get.setFont(ComponentStyle.defaultFont);

		price = new GAPTextField(5);
		price.setEditable(false);
		price.setFocusable(false);
		// price.closeEdit();
		price.setCenter();

		time = new GAPTextField(5);
		time.setEditable(false);
		time.setFocusable(false);
		// time.closeEdit();
		time.setCenter();

		type_list = new GAPComboBox<String>();
		type_list.addItem("特快");
		type_list.addItem("经济");
		type_list.addItem("标准");
		type_list.setSelectedIndex(2);

		// 布局
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		gcons.insets = new Insets(10, 10, 10, 10);
		gcons.fill = GridBagConstraints.HORIZONTAL;
		setLayout(gb);
		SwingConsole.addComponent(gb, gcons, this, title, 0, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, type, 1, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, type_list, 2, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, price_la, 3, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, price, 4, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, time_la, 5, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, time, 6, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 10, 10, 40);
		SwingConsole.addComponent(gb, gcons, this, price_get, 7, 0, 1, 1, 0, 0);
	}

	// 获得快递类型
	public ExpressType getType() {
		switch ((String) type_list.getSelectedItem()) {
		case "特快":
			return ExpressType.EXPRESS;
		case "经济":
			return ExpressType.ECONOMIC;
		case "标准":
			return ExpressType.STANDARD;
		default:
			break;
		}
		return null;
	}

	public void reSet() {
		price.setText("");
	}

	public void setPrice(double price) {
		this.price.setText(String.format("%.2f", price));
	}

	public void setTime(double time) {
		this.time.setText(String.format("%.1f", time));
	}

}
