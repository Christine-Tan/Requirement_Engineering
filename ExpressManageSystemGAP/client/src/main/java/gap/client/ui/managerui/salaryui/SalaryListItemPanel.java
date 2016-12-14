package gap.client.ui.managerui.salaryui;

import gap.client.blcontroller.SalaryController;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.vo.SalaryVO;
import gap.common.util.UserType;

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

public class SalaryListItemPanel extends JPanel {
	// 列表中所有的项
	List<ItemPanel> items;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	JButton addButton;
	JFrame frame;

	public SalaryListItemPanel(JFrame frame) {
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

		// 获取所有薪水策略信息
		for (SalaryVO salary : SalaryController.getAll()) {
			addItem(salary);
		}

	}

	private void addItem(SalaryVO salary) {
		items.add(new ItemPanel(salary));
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
		// 列表中的每一项
		GAPTextField usertype, money;
		JButton edit;
		// 是否处于被编辑状态，是否是新建表项
		boolean edited, newly;

		SalaryVO vo;
		// 布局
		GridBagLayout gbl;

		public ItemPanel() {
			setBackground(Color.WHITE);
			setFocusable(true);
			usertype = new GAPTextField(10);
			money = new GAPTextField(7);
			usertype.setHorizontalAlignment(JTextField.CENTER);
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
							vo = getSalaryVO();
							SalaryController.addSalary(vo);
						} else {
							SalaryController.modifySalary(vo);
						}
					}
				}

			});

			// 列表项布局
			gbl = new GridBagLayout();
			this.setLayout(gbl);
			gcons.insets = new Insets(10, 10, 10, 0);
			SwingConsole.addComponent(gbl, gcons, this, usertype, 0, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 50, 10, 300);
			SwingConsole.addComponent(gbl, gcons, this, money, 1, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 50, 10, 50);
			SwingConsole.addComponent(gbl, gcons, this, edit, 2, 0, 1, 1, 0, 0);
		}

		public ItemPanel(SalaryVO vo) {
			this();
			this.vo = vo;
			String userType;
			switch (vo.getType()){
			case ACCOUNTER:
				usertype.setText("财务人员");
				break;
			case DELIVERY:
				usertype.setText("快递员");
				break;
			case ADMINISTRATOR:
				usertype.setText("管理员");
				break;
			case BUSSINESSCLERK:
				usertype.setText("营业厅业务员");
				break;
			case CENTERCLERK:
				usertype.setText("中转中心业务员");
				break;
			case INVENTORY:
				usertype.setText("仓库管理人员");
				break;
			case MANAGER:
				usertype.setText("总经理");
				break;
			}
			money.setText(vo.getSalary()+"");
			closeEdit();
		}

		SalaryVO getSalaryVO() {
			UserType userType=null;
			switch (usertype.getText()){
			case "财务人员":
				userType=UserType.ACCOUNTER;
				break;
			case"快递员":
				userType=UserType.DELIVERY;
				break;
			case"管理员":
				userType=UserType.ADMINISTRATOR;
				break;
			case"营业厅业务员":
				userType=UserType.BUSSINESSCLERK;
				break;
			case"中转中心业务员":
				userType=UserType.CENTERCLERK;
				break;
			case"仓库管理人员":
				userType=UserType.INVENTORY;
			case"总经理":
				userType=UserType.MANAGER;
			}
			return new SalaryVO (userType,Double.valueOf(money.getText()));
		}

		void openEdit() {
			usertype.openEdit();
			money.openEdit();
			edit.setText("√");
			edited = true;
		}

		void closeEdit() {
			usertype.closeEdit();
			money.closeEdit();
			edit.setText("E");
			vo = getSalaryVO();
			edited = false;
		}

	}
}
