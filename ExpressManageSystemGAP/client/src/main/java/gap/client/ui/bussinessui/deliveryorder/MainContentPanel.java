package gap.client.ui.bussinessui.deliveryorder;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPJScrollPane;
import gap.client.vo.UserVO;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 已选快递员、订单选择panel
 * @author YangYanfei
 *
 */
public class MainContentPanel extends JPanel {
	OrderSelectPanel orderSelectPanel, emptySelectPanel;
	UserBar userBar;
	DeliverySelectPanel deliveryPanel;
	JFrame jf;
	GridBagLayout gb;
	GridBagConstraints gcons;

	public MainContentPanel(JFrame jf) {
		// TODO 自动生成的构造函数存根
		this.jf = jf;
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH - 200, 500));
		userBar = new UserBar();
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);

		emptySelectPanel = new OrderSelectPanel();
		emptySelectPanel.selectItems.clear();
		emptySelectPanel.unselectItem.clear();

		orderSelectPanel = emptySelectPanel;

		gcons.ipady = 100;
		// gcons.ipadx = 850;
		gcons.anchor = GridBagConstraints.NORTH;
		gcons.fill = GridBagConstraints.BOTH;

		GAPJScrollPane js = new GAPJScrollPane(userBar);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		SwingConsole.addComponent(gb, gcons, this, js, 0, 0, 1, 1, 1, 0);
		gcons.fill = GridBagConstraints.BOTH;
		gcons.ipady = 700;
		SwingConsole.addComponent(gb, gcons, this,
				emptySelectPanel.getJsPanel(), 0, 1, 1, 1, 1, 1);

		// gcons.ipadx =0;

	}

	public void refresh() {
		userBar.removeAll();
		OrderSelectPanel.initialed = false;
		OrderSelectPanel.initial();
		emptySelectPanel = new OrderSelectPanel();
		emptySelectPanel.selectItems.clear();
		emptySelectPanel.unselectItem.clear();
		setOrderSelectPanel(emptySelectPanel);
	}

	void setDeleverySelectPanel(DeliverySelectPanel deliveryPanel) {
		this.deliveryPanel = deliveryPanel;
	}

	public void setOrderSelectPanel(OrderSelectPanel orderSelectPanel) {
		if (this.orderSelectPanel != null)
			remove(this.orderSelectPanel.getJsPanel());
		this.orderSelectPanel = orderSelectPanel;
		gcons.fill = GridBagConstraints.BOTH;
		SwingConsole.addComponent(gb, gcons, this,
				orderSelectPanel.getJsPanel(), 0, 1, 1, 1, 1, 1);
	}

	/**
	 * 添加一个已选用户
	 * @param user
	 */
	void addUser(UserVO user) {
		userBar.addUser(user);
		if (userBar.users.size() == 1) {
			userBar.users.get(0).select();
			setOrderSelectPanel(userBar.users.get(0).orderSelect);
		}
	}

	/**
	 * 删除一个已选用户
	 * @param user
	 */
	void deleteUser(UserBox user) {
		userBar.deleteUser(user);
		deliveryPanel.addUser(user.getUser());
		remove(user.orderSelect.getJsPanel());
		user.orderSelect.clear();
		if (user.selected && userBar.users.isEmpty()) {
			setOrderSelectPanel(emptySelectPanel);
		} else if (!userBar.users.isEmpty()) {
			userBar.users.get(0).select();
			setOrderSelectPanel(userBar.users.get(0).orderSelect);
		}
		repaint();
		jf.validate();
	}

	public Map<String, List<String>> getDeliveryInfo() {
		return userBar.getOrders();
	}

	/**
	 * 已选用户栏面板
	 * @author YangYanfei
	 *
	 */
	class UserBar extends JPanel {
		List<UserBox> users;

		public UserBar() {
			setBackground(Color.white);
			setPreferredSize(new Dimension(Default.PANEL_WIDTH - 200, 150));
			users = new ArrayList<>();
			setLayout(new FlowLayout(FlowLayout.RIGHT));
		}

		/**
		 * 面板添加用户
		 * @param user
		 */
		void addUser(UserVO user) {
			UserBox u = new UserBox(user);
			users.add(u);
			add(u);
			u.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO 自动生成的方法存根

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO 自动生成的方法存根

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO 自动生成的方法存根
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO 自动生成的方法存根
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO 自动生成的方法存根
					UserBox box = (UserBox) e.getSource();
					for (UserBox user : users) {
						if (user == box) {
							user.select();

						} else
							user.deSelect();
					}
				}
			});

		}

		/**
		 * 面板删除用户
		 * @param box
		 */
		void deleteUser(UserBox box) {
			users.remove(box);
			remove(box);
		}

		public Map<String, List<String>> getOrders() {
			Map<String, List<String>> orders = new HashMap<>();
			for (UserBox item : users) {
				List<String> li = item.orderSelect.getOrders();
				if (li.size() != 0) {
					orders.put(item.user.getUserId(), li);
				}
			}
			return orders;
		}

		// public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		// Graphics2D g2d = RenderSetter.OpenRender(g);
		// g2d.setColor(ComponentStyle.light_gray);
		// int width = getWidth(), height = getHeight();
		// g2d.drawLine(10, height - 3, width - 20, height - 3);
		// }
	}

	/**
	 * 一个用户框选项
	 * 绑定了一个订单选择面板
	 * @author YangYanfei
	 *
	 */
	class UserBox extends JLabel {
		private int width = 100, height = 100;
		Color backColor;
		OrderSelectPanel orderSelect;
		UserVO user;
		boolean selected;
		GAPButton delete;

		UserBox(UserVO user) {
			this.user = user;
			setLayout(null);
			setPreferredSize(new Dimension(width, height));
			setOpaque(true);
			backColor = getBackground();

			delete = new GAPButton("x");
			delete.setOpaque(false);
			delete.setFont(ComponentStyle.defaultFont);
			Dimension size = delete.getPreferredSize();
			add(delete);
			delete.setBounds(width - size.width - 3, -5, size.width,
					size.height);

			delete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					deleteUser(UserBox.this);
				}
			});

			// select();
			orderSelect = new OrderSelectPanel();
		}

		/**
		 * 选择
		 */
		void select() {
			selected = true;
			setBackground(ColorAndFonts.blue);
			delete.setDefautFontColor(Color.white);
			delete.setEnterFontColor(Color.white);
			delete.setPressFontColor(Color.white);
			delete.setForeground(Color.white);
			orderSelect.transreFresh();
			setOrderSelectPanel(orderSelect);
			jf.repaint();
			jf.validate();
			repaint();
		}

		/**
		 * 取消选择
		 */
		void deSelect() {
			selected = false;
			setBackground(backColor);
			delete.setDefautFontColor(ColorAndFonts.blue);
			delete.setEnterFontColor(ColorAndFonts.blue.darker());
			delete.setPressFontColor(ColorAndFonts.otherDarkBulue);
			delete.setForeground(ColorAndFonts.blue);
			repaint();
		}

		UserVO getUser() {
			return user;
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = RenderSetter.OpenRender(g);
			if (selected)
				g2d.setColor(Color.white);
			else
				g2d.setColor(ComponentStyle.blue);
			g2d.setFont(ComponentStyle.plainFont);
			FontMetrics metrics = g2d.getFontMetrics();
			int word_width = metrics.stringWidth(user.getName());
			int word_desent = metrics.getDescent();
			g2d.drawString(user.getName(), (width - word_width) / 2, height
					- word_desent);
		}
	}

}
