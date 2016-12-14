package gap.client.ui.bussinessui.carmanage;

import gap.client.blcontroller.CarManageController;
import gap.client.exception.EmptyInputException;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.util.LocalInfo;
import gap.client.util.MessageType;
import gap.client.vo.CarVO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CarListItemPanel extends JPanel {
	List<ItemPanel> items;
	GridBagLayout gb;
	GridBagConstraints gcons;
	JButton addButton;
	JFrame frame;

	public CarListItemPanel(JFrame frame) {
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
		List<CarVO> cars = CarManageController.getAll();
		for (CarVO car : cars) {
			if (id == null || id.length() == 0 || car.getCar_id().contains(id))
				addItem(car);
		}
		reLayout();
	}

	private void addItem(CarVO car) {
		items.add(new ItemPanel(car));
	}

	private void addNewItem() {
		ItemPanel item = new ItemPanel();
		item.original = true;
		item.openEdit();
		try {
			String id = CarManageController.nextId();
			item.car_id.setText(id);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		items.add(item);
		reLayout();
		frame.validate();
	}

	private void removeItem(ItemPanel item) {
		items.remove(item);
		remove(item);
		reLayout();
		frame.validate();
	}

	private void reLayout() {
		for (int i = 0; i < items.size(); i++) {
			ItemPanel item = items.get(i);
			item.setId(i + 1);
			if (i == items.size() - 1)
				item.last = true;
			else
				item.last = false;
			SwingConsole.addComponent(gb, gcons, this, item, 0, i, 1, 1, 1, 0);
		}
		SwingConsole.addComponent(gb, gcons, this, addButton, 0, items.size(),
				1, 1, 1, 0);
		frame.validate();
	}

	// 每一项
	class ItemPanel extends JPanel {
		CarVO car;
		JButton delete, edit;
		GAPTextField id, car_id, car_num, serve_time;
		GridBagLayout gb;
		GridBagConstraints gcons;
		boolean original;
		boolean edited;
		boolean last;

		public ItemPanel() {
			setBackground(Color.white);
			setPreferredSize(new Dimension(Default.PANEL_WIDTH - 80, 45));
			setFocusable(true);
			gb = new GridBagLayout();
			gcons = new GridBagConstraints();
			id = new GAPTextField(3);
			car_id = new GAPTextField(8);

			car_num = new GAPTextField(10);

			serve_time = new GAPTextField(8);

			try {
				id.setText(CarManageController.nextId());
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}

			edit = new GAPButton("E");
			edit.setFont(ComponentStyle.defaultFont);
			edit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					if (edited) {
						try {
							closeEdit();
							if (original) {
								System.out.println("add");
								CarManageController.add(getCarVO());
								original = false;
							} else {
								System.out.println("modify");
								CarManageController.modify(getCarVO());
							}
						} catch (EmptyInputException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
							MainFrame.setMessage("请填写正确信息", MessageType.alram,
									2000);
						}

					} else {
						openEdit();
					}
				}
			});

			delete = new GAPButton("x");
			delete.setFont(ComponentStyle.defaultFont);
			delete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					removeItem(ItemPanel.this);
					CarManageController.delete(car_id.getText());
				}
			});

			setLayout(gb);
			gcons.insets = new Insets(10, 40, 10, 0);
			SwingConsole.addComponent(gb, gcons, this, id, 0, 0, 1, 1, 0, 0);
			SwingConsole
					.addComponent(gb, gcons, this, car_id, 1, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gb, gcons, this, car_num, 2, 0, 1, 1, 0,
					0);
			SwingConsole.addComponent(gb, gcons, this, serve_time, 3, 0, 1, 1,
					0, 0);
			SwingConsole.addComponent(gb, gcons, this, edit, 4, 0, 1, 1, 0, 0);
			SwingConsole
					.addComponent(gb, gcons, this, delete, 5, 0, 1, 1, 0, 0);
			openEdit();
			setCenter();
		}

		public ItemPanel(CarVO car) {
			this();
			this.car = car;
			car_id.setText(car.getCar_id());
			car_num.setText(car.getCar_num());
			serve_time.setText("" + car.getServe_time());
			try {
				closeEdit();
			} catch (EmptyInputException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		public void setId(int id) {
			this.id.setText(id + "");
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (last) {
				Graphics2D g2d = RenderSetter.OpenRender(g);
				g2d.setColor(ComponentStyle.light_gray);
				int width = getWidth(), height = getHeight();
				g2d.drawLine(10, height - 5, width - 20, height - 5);
			}
		}

		CarVO getCarVO() {
			return new CarVO(car_id.getText(), car_num.getText(),
					LocalInfo.ins_id, new Integer(serve_time.getText()));
		}

		void setCenter() {
			id.setHorizontalAlignment(JTextField.CENTER);
			car_id.setHorizontalAlignment(JTextField.CENTER);
			car_num.setHorizontalAlignment(JTextField.CENTER);
			serve_time.setHorizontalAlignment(JTextField.CENTER);
		}

		// 关闭编辑
		void closeEdit() throws EmptyInputException {

			if ((car_id.getText().length() == 0)
					|| (car_num.getText().length() == 0)
					|| (serve_time.getText().length() == 0))
				throw new EmptyInputException();
			edited = false;
			id.closeEdit();
			car_id.closeEdit();
			car_num.closeEdit();
			serve_time.closeEdit();
			edit.setText("E");
			frame.validate();
		}

		// 启用编辑
		void openEdit() {
			edited = true;
			edit.setText("√");
			id.closeEdit();
			car_id.openEdit();
			car_num.openEdit();
			serve_time.openEdit();
			frame.validate();
		}
	}
}
