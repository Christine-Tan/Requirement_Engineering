package gap.client.ui.managerui.rentui;

import gap.client.blcontroller.CityController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.vo.CityVO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RentQueryPanel extends JPanel {
	JLabel inputId, inputCity, edit;
	JButton search1, search2;
	JTextField id;
	JComboBox<String> city_list;

	public RentQueryPanel() {
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 60));
		setBackground(Color.WHITE);
		inputId = new GAPLabel("请输入机构ID：");
		inputCity = new GAPLabel("请选择城市：");
		search1 = new JButton("搜索");
		search2 = new JButton("搜索");
		id = new GAPTextField(7);
		city_list = new GAPComboBox<String>();

		// 初始化城市选择下拉框

		for (CityVO city : CityController.getAll()) {
			city_list.addItem(city.getCityName());
		}

		// 布局
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(20, 0, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, inputId, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(20, 10, 10, 10);
		SwingConsole.addComponent(gb, gcons, this, id, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(20, 10, 10, 100);
		SwingConsole.addComponent(gb, gcons, this, search1, 2, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(20, 150, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, inputCity, 3, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(20, 10, 10, 10);
		SwingConsole.addComponent(gb, gcons, this, city_list, 4, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(20, 10, 10, 25);
		SwingConsole.addComponent(gb, gcons, this, search2, 5, 0, 1, 1, 0, 0);
	}
}
