package gap.client.ui.managerui.rentui;

import gap.client.blcontroller.RentController;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.vo.RentVO;

import java.awt.Color;
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

public class RentListItemPanel extends JPanel {
	// 列表中所有的项
	List<ItemPanel> items;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	JButton addButton;
	JFrame frame;

	public RentListItemPanel(JFrame frame) {
		this.frame = frame;
		setBackground(Color.WHITE);
		addButton = new GAPButton("+");
		// 对新增按钮添加监听
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addNewItem();
			}

		});
		// 对列表项里每一个panel进行布局
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		items = new ArrayList<>();

		// 获得所有租金信息
		for (RentVO rent : RentController.getAll()) {
			addItem(rent);
		}

	}

	private void addItem(RentVO rent) {
		items.add(new ItemPanel(rent));
		reLayout();
		frame.validate();
	}

	private void addNewItem() {
		ItemPanel item = new ItemPanel();
		item.openEdit();
		item.newly = true;
		items.add(item);
		reLayout();
		frame.validate();
	}

	private void reLayout() {
		for (int i = 0; i < items.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1, 1, 1, 0);
		}
		SwingConsole.addComponent(gb, gcons, this, addButton, 0, items.size(), 1, 1, 1, 0);
	}

	class ItemPanel extends JPanel {
		// 每一个列表项中的组件
		GAPTextField institution, money;
		JButton edit;
		// 是否处于被编辑状态，是否是新建表项
		boolean newly, edited;
		RentVO vo;
		// 布局
		GridBagLayout gbl;

		public ItemPanel() {
			setBackground(Color.WHITE);
			setFocusable(true);
			institution = new GAPTextField(15);
			money = new GAPTextField(7);
			institution.setHorizontalAlignment(JTextField.CENTER);
			money.setHorizontalAlignment(JTextField.CENTER);
			// 对编辑按钮添加监听
			edit = new GAPButton("E");
			edit.setFont(ComponentStyle.defaultFont);
			edit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (!edited) {
						openEdit();
					} else {
						closeEdit();
						if (newly) {
							newly = false;
							vo = getRentVO();
							RentController.addRent(vo);
						} else {
							RentController.modifyRent(vo);
						}
					}
				}

			});

			// 列表项布局
			gbl = new GridBagLayout();
			this.setLayout(gbl);
			gcons.insets = new Insets(10, 10, 10, 0);
			SwingConsole.addComponent(gbl, gcons, this, institution, 0, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 50, 10, 300);
			SwingConsole.addComponent(gbl, gcons, this, money, 1, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 50, 10, 50);
			SwingConsole.addComponent(gbl, gcons, this, edit, 2, 0, 1, 1, 0, 0);
		}

		public ItemPanel(RentVO vo) {
			this();
			this.vo = vo;
			institution.setText(vo.getInstitution());
			money.setText(vo.getMoney() + "");
			closeEdit();
		}

		RentVO getRentVO() {
			return new RentVO(institution.getText(), Double.valueOf(money.getText()));
		}

		void openEdit() {
			institution.openEdit();
			money.openEdit();
			edit.setText("√");
			edited = true;
		}

		void closeEdit() {
			institution.closeEdit();
			money.closeEdit();
			edit.setText("E");
			vo = getRentVO();
			edited = false;
		}
	}
}
