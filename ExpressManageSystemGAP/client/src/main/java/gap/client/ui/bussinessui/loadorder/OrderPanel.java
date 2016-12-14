package gap.client.ui.bussinessui.loadorder;

import gap.client.blcontroller.ExpressorderController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.util.LocalInfo;
import gap.client.vo.ExpressOrderVO;
import gap.common.util.CurrentOrderType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OrderPanel extends JPanel {
	JPanel searchPanel, titlePanel;
	JLabel order_search, serach_icon;
	GAPTextField order_id_input, title_order_id, title_target_city;
	JCheckBox checkbox;
	List<OrderItemPanel> items;
	GridBagLayout gb;
	GridBagConstraints gcons;
	JFrame frame;

	public OrderPanel(JFrame frame) {
		this.frame = frame;
		setBackground(Color.WHITE);

		items = new ArrayList<>();

		order_search = new GAPLabel("订单选择");
		serach_icon = new GAPLabel("搜索");
		order_id_input = new GAPTextField(11);

		title_order_id = new GAPTextField("订单编号", 11);
		title_order_id.setHorizontalAlignment(JTextField.CENTER);
		title_order_id.closeEdit();
		title_target_city = new GAPTextField("目的地", 11);
		title_target_city.setHorizontalAlignment(JTextField.CENTER);
		title_target_city.closeEdit();

		checkbox = new JCheckBox();
		checkbox.setBackground(Color.WHITE);

		searchPanel = new JPanel();
		searchPanel.setPreferredSize(new Dimension(Default.PANEL_WIDTH - 100,
				50));
		searchPanel.setOpaque(false);
		searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		searchPanel.add(order_search);
		searchPanel.add(order_id_input);
		searchPanel.add(serach_icon);

		titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setBackground(Color.white);
		GridBagLayout gb1 = new GridBagLayout();
		gcons = new GridBagConstraints();
		titlePanel.setLayout(gb1);
		gcons.insets = new Insets(0, 20, 5, 100);
		SwingConsole.addComponent(gb1, gcons, titlePanel, checkbox, 0, 0, 1, 1,
				0, 0);
		gcons.insets = new Insets(0, 50, 5, 100);
		SwingConsole.addComponent(gb1, gcons, titlePanel, title_order_id, 1, 0,
				1, 1, 0, 0);
		gcons.insets = new Insets(0, 50, 5, 100);
		SwingConsole.addComponent(gb1, gcons, titlePanel, title_target_city, 2,
				0, 1, 1, 0, 0);

		gb = new GridBagLayout();
		setLayout(gb);
		SwingConsole.addComponent(gb, gcons, this, searchPanel, 0, 0, 1, 1, 1,
				0);
		SwingConsole
				.addComponent(gb, gcons, this, titlePanel, 0, 1, 1, 1, 1, 0);

		checkbox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				int state = e.getStateChange();
				if (state == ItemEvent.SELECTED) {
					setSelected(true);
				} else {
					setSelected(false);
				}
			}
		});

	}

	void setSelected(boolean bool) {
		for (OrderItemPanel item : items) {
			item.setSelected(bool);
		}
	}

	public void refresh() {
		items.clear();
		removeAll();
		SwingConsole.addComponent(gb, gcons, this, searchPanel, 0, 0, 1, 1, 1,
				0);
		SwingConsole
				.addComponent(gb, gcons, this, titlePanel, 0, 1, 1, 1, 1, 0);
		List<ExpressOrderVO> orders;
		if (LocalInfo.ins_id.charAt(3) != '1') {
			orders = ExpressorderController
					.getCurrentOrders(CurrentOrderType.LOAD);
		} else {
			orders = ExpressorderController
					.getCurrentOrders(CurrentOrderType.ALL);
		}
		for (ExpressOrderVO vo : orders) {
			addItem(vo);
		}
		reLayout();
	}

	public List<String> getOrders() {
		List<String> list = new ArrayList<String>();
		for (OrderItemPanel item : items) {
			if (item.getOrder() != null)
				list.add(item.getOrder().order_id);
		}
		return list;
	}

	private void addItem(ExpressOrderVO vo) {
		items.add(new OrderItemPanel(vo));
	}

	private void reLayout() {
		for (int i = 0; i < items.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i + 2,
					1, 1, 1, 0);
		}
		frame.validate();
	}

	class OrderItemPanel extends JPanel {
		ExpressOrderVO order;
		JCheckBox checkbox;
		GAPTextField order_id, target_city;

		public OrderItemPanel(ExpressOrderVO vo) {
			super();
			setOpaque(false);
			order = vo;
			order_id = new GAPTextField(vo.order_id, 11);
			order_id.setHorizontalAlignment(JTextField.CENTER);
			order_id.closeEdit();
			target_city = new GAPTextField(vo.receiver_info.getAddress()
					.getCity_name(), 11);
			target_city.setHorizontalAlignment(JTextField.CENTER);
			target_city.closeEdit();
			checkbox = new JCheckBox();
			checkbox.setBackground(Color.WHITE);

			GridBagLayout gb = new GridBagLayout();
			GridBagConstraints gcons = new GridBagConstraints();
			setLayout(gb);
			gcons.insets = new Insets(0, 20, 5, 100);
			SwingConsole.addComponent(gb, gcons, this, checkbox, 0, 0, 1, 1, 0,
					0);
			gcons.insets = new Insets(0, 50, 5, 100);
			SwingConsole.addComponent(gb, gcons, this, order_id, 1, 0, 1, 1, 0,
					0);
			SwingConsole.addComponent(gb, gcons, this, target_city, 2, 0, 1, 1,
					0, 0);
		}

		void setSelected(boolean bool) {
			this.checkbox.setSelected(bool);
		}

		public ExpressOrderVO getOrder() {
			if (checkbox.isSelected())
				return order;
			return null;
		}
	}

}
