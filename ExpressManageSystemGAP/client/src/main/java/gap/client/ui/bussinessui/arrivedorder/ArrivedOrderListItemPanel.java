package gap.client.ui.bussinessui.arrivedorder;

import gap.client.blcontroller.DriverManageController;
import gap.client.blcontroller.ExpressorderController;
import gap.client.blcontroller.InstitutionController;
import gap.client.blcontroller.LoadOrderController;
import gap.client.blcontroller.UserController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.util.LocalInfo;
import gap.client.vo.ArrivedOrderVO;
import gap.client.vo.DriverVO;
import gap.client.vo.ExpressOrderVO;
import gap.client.vo.InstitutionVO;
import gap.client.vo.LoadOrderVO;
import gap.client.vo.UserVO;
import gap.common.util.ArrivedState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ArrivedOrderListItemPanel extends JPanel {
	List<ItemPanel> items;
	GridBagLayout gb;
	GridBagConstraints gcons;
	JFrame jf;

	public ArrivedOrderListItemPanel(JFrame jf) {
		super();
		// setPreferredSize(new
		// Dimension(gap.client.ui.UITools.Default.PANEL_WIDTH, ));
		setOpaque(false);
		this.jf = jf;
		items = new ArrayList<>();
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);

		refresh();
	}

	/**
	 * 刷新
	 */
	public void refresh() {
		removeAll();
		items.clear();
		List<LoadOrderVO> orders = LoadOrderController.getArrivingLoadOrder();
		for (LoadOrderVO order : orders) {
			items.add(new ItemPanel(order));
		}
		reLayOut();
	}

	/**
	 * 重新布局
	 */
	void reLayOut() {
		for (int i = 0; i < items.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1,
					1, 1, 0);
		}
	}

	public List<ArrivedOrderVO> getArrivedOrders() {
		List<ArrivedOrderVO> orders = new ArrayList<ArrivedOrderVO>();
		for (ItemPanel item : items) {
			if (item.isSelected())
				orders.add(item.getArrivedOrderVO());
		}
		return orders;
	}

	class ItemPanel extends JPanel {
		JPanel shortMessPanel;
		JButton detail;
		GAPTextField car_id, departure_ins_name, guard_name, driver_name,
				comment;
		DetailPanel detailPanel;
		JCheckBox checkBox;
		boolean showed;
		LoadOrderVO loadOrder;

		public ItemPanel(LoadOrderVO loadOrder) {
			super();
			this.loadOrder = loadOrder;
			setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));
			setOpaque(false);

			shortMessPanel = new JPanel();
			shortMessPanel.setOpaque(false);

			List<ExpressOrderVO> list = ExpressorderController
					.getByOrderIdList(loadOrder.orders);
			InstitutionVO depature_ins = InstitutionController
					.findById(loadOrder.departureins_id);
			UserVO guard = UserController.findById(loadOrder.guard_id);
			DriverVO driver = DriverManageController
					.getSingle(loadOrder.driver_id);
			checkBox = new JCheckBox();
			checkBox.setBackground(Color.white);
			car_id = new GAPTextField(loadOrder.car_id, 8);
			departure_ins_name = new GAPTextField(depature_ins.getInsName(), 12);
			guard_name = new GAPTextField(guard.getName(), 4);
			driver_name = new GAPTextField(driver.getName(), 4);
			comment = new GAPTextField(loadOrder.comment, 20);

			setStyle();
			detailPanel = new DetailPanel(list);
			detail = new GAPButton(">");
			detail.setFont(ComponentStyle.defaultFont);
			detail.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					if (showed) {
						hideDetail();
					} else {
						showDetail();
					}
				}
			});

			GridBagLayout gb = new GridBagLayout();
			GridBagConstraints gcons = new GridBagConstraints();
			setLayout(gb);
			shortMessPanel.setLayout(gb);

			gcons.insets = new Insets(5, 10, 0, 10);
			SwingConsole.addComponent(gb, gcons, shortMessPanel, checkBox, 0,
					0, 1, 1, 0, 0);
			gcons.insets = new Insets(0, 0, 0, 0);
			SwingConsole.addComponent(gb, gcons, shortMessPanel, detail, 1, 0,
					1, 1, 0, 0);
			gcons.insets = new Insets(5, 10, 0, 10);
			SwingConsole.addComponent(gb, gcons, shortMessPanel, car_id, 2, 0,
					1, 1, 0, 0);
			gcons.insets = new Insets(5, 10, 0, 10);
			SwingConsole.addComponent(gb, gcons, shortMessPanel,
					departure_ins_name, 3, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 10, 0, 10);
			SwingConsole.addComponent(gb, gcons, shortMessPanel, guard_name, 4,
					0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 10, 0, 10);
			SwingConsole.addComponent(gb, gcons, shortMessPanel, driver_name,
					5, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 0, 0, 0);
			SwingConsole.addComponent(gb, gcons, shortMessPanel, comment, 6, 0,
					1, 1, 0, 0);

			SwingConsole.addComponent(gb, gcons, this, shortMessPanel, 0, 0, 1,
					1, 1, 0);
			SwingConsole.addComponent(gb, gcons, this, detailPanel, 0, 1, 1, 1,
					1, 0);

			hideDetail();
		}

		void setStyle() {
			car_id.closeEdit();
			car_id.setCenter();

			departure_ins_name.setCenter();
			departure_ins_name.closeEdit();

			guard_name.setCenter();
			guard_name.closeEdit();

			driver_name.setCenter();
			driver_name.closeEdit();

			comment.setCenter();
			comment.closeEdit();
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = RenderSetter.OpenRender(g);
			g2d.setColor(ComponentStyle.light_gray);
			int width = getWidth(), height = getHeight();
			g2d.drawLine(10, height - 5, width - 20, height - 5);
		}

		void showDetail() {
			showed = true;

			setPreferredSize(new Dimension(Default.PANEL_WIDTH,
					55 + detailPanel.getPreferredSize().height));
			detail.setText("v");
			detailPanel.setVisible(true);
		}

		void hideDetail() {
			showed = false;
			setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));
			detail.setText(">");
			detailPanel.setVisible(false);
		}

		public boolean isSelected() {
			return checkBox.isSelected();
		}

		public ArrivedOrderVO getArrivedOrderVO() {
			ArrivedOrderVO vo = new ArrivedOrderVO();
			vo.des_ins_id = LocalInfo.ins_id;
			vo.from_ins_id = loadOrder.departureins_id;
			vo.orders = detailPanel.getOrderAndState();
			vo.time = (new Date(System.currentTimeMillis())).toString();
			vo.load_id = loadOrder.order_id;
			return vo;
		}
	}

	/**
	 * 详细信息的面板
	 * @author YangYanfei
	 *
	 */
	class DetailPanel extends JPanel {
		JLabel id_la, order_id_la, target_la, arrived_state_la;
		List<JComboBox<String>> arrivedstates;
		List<ExpressOrderVO> expressOrder;
		GridBagLayout gb;
		GridBagConstraints gcons;

		public DetailPanel(List<ExpressOrderVO> expressOrder) {
			super();
			setOpaque(false);
			// setPreferredSize(new Dimension(Default.PANEL_WIDTH - 100,
			// 40 + expressOrder.size() * 35));
			setBorder(BorderFactory.createLineBorder(ComponentStyle.gray));
			gb = new GridBagLayout();
			gcons = new GridBagConstraints();
			setLayout(gb);
			this.expressOrder = expressOrder;
			arrivedstates = new ArrayList<JComboBox<String>>();
			for (int i = 0; i < expressOrder.size(); i++) {
				GAPComboBox<String> combobox = new GAPComboBox<>();
				combobox.addItem(ArrivedState.toChinese(ArrivedState.COMPLETE));
				combobox.addItem(ArrivedState.toChinese(ArrivedState.DAMAGE));
				combobox.addItem(ArrivedState.toChinese(ArrivedState.LOST));
				arrivedstates.add(combobox);
			}

			id_la = new GAPLabel("编号");
			order_id_la = new GAPLabel("订单编号");
			target_la = new GAPLabel("目的地");
			arrived_state_la = new GAPLabel("到达状态");
			gcons.insets = new Insets(5, 40, 5, 40);
			SwingConsole.addComponent(gb, gcons, this, id_la, 0, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gb, gcons, this, order_id_la, 1, 0, 1, 1,
					0, 0);
			SwingConsole.addComponent(gb, gcons, this, target_la, 2, 0, 1, 1,
					0, 0);
			SwingConsole.addComponent(gb, gcons, this, arrived_state_la, 3, 0,
					1, 1, 0, 0);
			for (int i = 0; i < expressOrder.size(); i++) {
//				System.out.println(expressOrder.get(i));
				addItem(expressOrder.get(i), i + 1);
			}

		}

		void addItem(ExpressOrderVO express, int i) {
			JLabel id = new GAPLabel("" + i), order_id = new GAPLabel(
					express.order_id), target = new GAPLabel(
					express.sender_info.getAddress().getCity_name());

			SwingConsole.addComponent(gb, gcons, this, id, 0, i, 1, 1, 0, 0);
			SwingConsole.addComponent(gb, gcons, this, order_id, 1, i, 1, 1, 0,
					0);
			SwingConsole
					.addComponent(gb, gcons, this, target, 2, i, 1, 1, 0, 0);
			SwingConsole.addComponent(gb, gcons, this,
					arrivedstates.get(i - 1), 3, i, 1, 1, 0, 0);
		}

		public Map<String, String> getOrderAndState() {
			Map<String, String> orderAndState = new HashMap<>();
			for (int i = 0; i < expressOrder.size(); i++) {
				orderAndState.put(expressOrder.get(i).order_id,
						(String) arrivedstates.get(i).getSelectedItem());
			}
			return orderAndState;
		}
	}
}
