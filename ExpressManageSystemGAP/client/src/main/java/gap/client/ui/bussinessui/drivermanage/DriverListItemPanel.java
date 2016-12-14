package gap.client.ui.bussinessui.drivermanage;

import gap.client.blcontroller.DriverManageController;
import gap.client.exception.InvalidInputException;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.util.LocalInfo;
import gap.client.util.MessageType;
import gap.client.vo.DriverVO;
import gap.common.util.Gender;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DriverListItemPanel extends JPanel {
	// 列表中所有的项
	List<ItemPanel> items;
	GridBagLayout gb;
	GridBagConstraints gcons;
	// 添加button
	JButton addButton;
	JFrame frame;

	public DriverListItemPanel(JFrame frame) {
		this.frame = frame;
		setBackground(Color.white);

		addButton = new GAPButton("+");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				addNewItem();
			}
		});

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);

		items = new ArrayList<>();

		refresh("");
	}

	public void refresh(String id) {
		items.clear();
		removeAll();
		List<DriverVO> drivers = DriverManageController.getAll();
		for (DriverVO driver : drivers) {
			if (id == null || id.length() == 0 || driver.getId().contains(id))
				addItem(driver);
		}
		reLayout();
	}

	/**
	 * 根据一个已有VO添加项
	 * @param driver
	 */
	private void addItem(DriverVO driver) {
		items.add(new ItemPanel(driver));

	}

	/**
	 * 添加一个新项目
	 */
	private void addNewItem() {
		ItemPanel item = new ItemPanel();
		item.original = true;
		item.openEdit();
		item.showDetail();
		try {
			String id = DriverManageController.nextId();
			item.id.setText(id);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		items.add(item);
		reLayout();
		frame.validate();
	}

	/**
	 * 移除一项
	 * @param item
	 */
	private void removeItem(ItemPanel item) {
		items.remove(item);
		remove(item);
		if (!item.original)
			DriverManageController.delete(item.driver.getId());
		reLayout();
		frame.validate();
	}

	/**
	 * 重新布局
	 */
	private void reLayout() {
		for (int i = 0; i < items.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1,
					1, 1, 0);
		}
		SwingConsole.addComponent(gb, gcons, this, addButton, 0, items.size(),
				1, 1, 1, 0);
		frame.validate();
	}

	// 每一项
	class ItemPanel extends JPanel {
		DriverVO driver;
		JLabel birth_la, phone_la, license_la;
		// 显示详细信息、删除、改变编辑状态的按钮
		JButton detail_la, delete_la, edit_la;
		GAPTextField id, name, gender, id_card, birth, phone, driverLi_due;
		GridBagLayout gb;
		GridBagConstraints gcons;
		JPanel detailPanel;
		boolean original;
		boolean edited, showed;

		public ItemPanel() {
			setBackground(Color.white);
			setFocusable(true);
			gb = new GridBagLayout();
			gcons = new GridBagConstraints();

			detail_la = new GAPButton(">");
			detail_la.setFont(ComponentStyle.defaultFont);
			detail_la.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					if (showed) {
						closeDetail();
					} else {
						showDetail();
					}
				}
			});
			edit_la = new GAPButton("E");
			edit_la.setFont(ComponentStyle.defaultFont);
			edit_la.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					if (!edited) {
						openEdit();
					} else {
						try {
							closeEdit();
							if (original) {
								original = false;
								DriverManageController.add(driver);
								System.out.println("add");
							} else {
								DriverManageController.modify(driver);
								System.out.println("modify");
							}
						} catch (InvalidInputException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
							MainFrame.setMessage("请输入正确的信息", MessageType.alram,
									2000);
						}

					}
				}
			});
			delete_la = new GAPButton("x");
			delete_la.setFont(ComponentStyle.defaultFont);
			delete_la.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					removeItem(ItemPanel.this);
				}
			});
			id = new GAPTextField(8);
			name = new GAPTextField(4);

			gender = new GAPTextField(2);

			id_card = new GAPTextField(15);

			birth_la = new GAPLabel("生日：");
			birth = new GAPTextField(7);

			phone_la = new GAPLabel("手机：");
			phone = new GAPTextField(8);

			license_la = new GAPLabel("行驶证期限：");
			driverLi_due = new GAPTextField(7);

			detailPanel = new JPanel();
			detailPanel.setBackground(Color.white);
			detailPanel.setBorder(BorderFactory
					.createLineBorder(ComponentStyle.gray));
			GridBagLayout gb1 = new GridBagLayout();
			detailPanel.setLayout(gb1);
			gcons.insets = new Insets(10, 20, 10, 20);
			// gcons.fill = GridBagConstraints.BOTH;
			// detailPanel布局
			SwingConsole.addComponent(gb1, gcons, detailPanel, birth_la, 0, 0,
					1, 1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, birth, 1, 0, 1,
					1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, phone_la, 2, 0,
					1, 1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, phone, 3, 0, 1,
					1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, license_la, 0,
					1, 1, 1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, driverLi_due, 1,
					1, 1, 1, 0, 0);

			setLayout(gb);
			gcons.insets = new Insets(10, 20, 10, 0);
			SwingConsole.addComponent(gb, gcons, this, detail_la, 0, 0, 1, 1,
					0, 0);
			gcons.insets = new Insets(10, 50, 10, 0);
			SwingConsole.addComponent(gb, gcons, this, id, 1, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gb, gcons, this, name, 2, 0, 1, 1, 0, 0);
			SwingConsole
					.addComponent(gb, gcons, this, gender, 3, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 80, 10, 0);
			SwingConsole.addComponent(gb, gcons, this, id_card, 4, 0, 1, 1, 0,
					0);
			gcons.insets = new Insets(10, 40, 10, 10);
			SwingConsole.addComponent(gb, gcons, this, edit_la, 5, 0, 1, 1, 0,
					0);
			gcons.insets = new Insets(10, 10, 10, 10);
			SwingConsole.addComponent(gb, gcons, this, delete_la, 6, 0, 1, 1,
					0, 0);
			gcons.insets = new Insets(0, 10, 10, 10);
			SwingConsole.addComponent(gb, gcons, this, detailPanel, 0, 1, 7, 1,
					0, 0);
			detailPanel.setVisible(false);

		}

		public ItemPanel(DriverVO driver) {
			this();
			this.driver = driver;
			birth.setText(driver.getBirth());
			phone.setText(driver.getPhone());
			id_card.setText(driver.getIdentity_number());
			id.setText(driver.getId());
			name.setText(driver.getName());
			driverLi_due.setText(driver.getDriving_license_due());
			switch (driver.getGender()) {
			case MALE:
				gender.setText("男");
				break;
			case FEMALE:
				gender.setText("女");
				break;
			}
			try {
				closeEdit();
			} catch (InvalidInputException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = RenderSetter.OpenRender(g);
			g2d.setColor(ComponentStyle.light_gray);
			int width = getWidth(), height = getHeight();
			g2d.drawLine(10, height - 5, width - 20, height - 5);
		}

		DriverVO getDriverVO() {
			Gender gen = null;
			switch (gender.getText()) {
			case "男":
				gen = Gender.MALE;
				break;
			case "女":
				gen = Gender.FEMALE;
				break;
			}
			return new DriverVO(id.getText(), LocalInfo.ins_id, name.getText(),
					birth.getText(), id_card.getText(), phone.getText(),
					driverLi_due.getText(), gen);
		}

		// 打开详细信息面板
		void showDetail() {
			detailPanel.setVisible(true);
			detail_la.setText("v");
			showed = true;
		}

		// 关闭详细信息面板
		void closeDetail() {
			detailPanel.setVisible(false);
			detail_la.setText(">");
			showed = false;
		}

		// 关闭编辑
		void closeEdit() throws InvalidInputException {

			if (id.getText().length() == 0) {
				id.alarm();
				throw new InvalidInputException();
			}
			if (name.getText().length() == 0) {
				name.alarm();
				throw new InvalidInputException();
			}
			if (gender.getText().length() == 0
					|| (!gender.getText().equals("男") && !gender.getText()
							.equals("女"))) {
				gender.alarm();
				throw new InvalidInputException();
			}
			if (id_card.getText().length() == 0) {
				id_card.alarm();
				throw new InvalidInputException();
			}
			if (!birth.getText().matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
				id_card.alarm();
				throw new InvalidInputException();
			}
			if (phone.getText().length() == 0) {
				phone.alarm();
				throw new InvalidInputException();
			}
			if (driverLi_due.getText().length() == 0) {
				driverLi_due.alarm();
				throw new InvalidInputException();
			}
			id.closeEdit();
			name.closeEdit();
			gender.closeEdit();
			id_card.closeEdit();
			birth.closeEdit();
			phone.closeEdit();
			driverLi_due.closeEdit();
			edit_la.setText("E");
			driver = getDriverVO();
			edited = false;
		}

		// 启用编辑
		void openEdit() {
			id.closeEdit();
			name.openEdit();
			gender.openEdit();
			id_card.openEdit();
			birth.openEdit();
			phone.openEdit();
			driverLi_due.openEdit();
			edit_la.setText("√");
			edited = true;
		}
	}
}
