package gap.client.ui.administratorui;

import gap.client.blcontroller.InstitutionController;
import gap.client.blcontroller.UserController;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.vo.InstitutionVO;
import gap.client.vo.UserVO;
import gap.common.util.Gender;
import gap.common.util.UserType;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminListItemPanel extends JPanel {
	// 列表中所有的项
	List<ItemPanel> items;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	JButton addButton;
	JFrame frame;

	public AdminListItemPanel(JFrame frame, UserType userType, UserVO searchUser) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		setBackground(Color.WHITE);
		addButton = new GAPButton("+");
		// 对新增列表项的按钮添加监听
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addNewItem();
			}

		});

		// 布局
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		items = new ArrayList<>();
		if (searchUser == null) {
			for (UserVO user : UserController.getAll(userType)) {
				addItem(user);
			}
		} else {
			addItem(searchUser);
			reLayout();
		}
	}

	private void addItem(UserVO user) {
		items.add(new ItemPanel(user));
		reLayout();
		frame.validate();
	}

	private void addNewItem() {
		ItemPanel item = new ItemPanel();
		item.newly = true;
		item.openEdit();
		item.showDetail();
		items.add(item);
		reLayout();
		frame.validate();
	}

	private void removeItem(ItemPanel item) {
		items.remove(item);
		remove(item);
		UserController.delete(item.vo.getUserId());
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
		UserVO vo;
		UserType[] usertypes;
		List<InstitutionVO> institutions;
		GAPTextField user_id, user_name, password, name;
		JComboBox<String> usertype_list, gender_list, institution_list;
		JPanel detailPanel;
		// 编辑、删除按钮
		JButton edit, delete, detail;
		// 是否处于编辑状态，是否是新建的表项,是否展示详细信息
		boolean edited, newly, detailed;
		GridBagLayout gbl;

		public ItemPanel() {
			setBackground(Color.WHITE);
			setFocusable(true);
			gbl = new GridBagLayout();
			user_id = new GAPTextField(9);
			user_name = new GAPTextField(5);
			password = new GAPTextField(15);
			name = new GAPTextField(5);
			usertype_list = new GAPComboBox<String>();
			gender_list = new GAPComboBox<String>();
			institution_list = new GAPComboBox<String>();

			user_id.setHorizontalAlignment(JTextField.CENTER);
			user_name.setHorizontalAlignment(JTextField.CENTER);
			password.setHorizontalAlignment(JTextField.CENTER);

			// 初始化下拉框列表项
			usertypes = UserType.values();
			for (int i = 0; i < usertypes.length; i++) {
				if (!usertypes[i].toString().equals("ADMINISTRATOR")) {
					usertype_list.addItem(usertypes[i].toString());
				}
			}
			usertype_list.setSelectedIndex(0);

			gender_list.addItem("男");
			gender_list.addItem("女");
			gender_list.setSelectedIndex(0);

			institutions = InstitutionController.getAll();
			for (InstitutionVO institution : institutions) {
				institution_list.addItem(institution.getInsName());
			}
			institution_list.setSelectedIndex(0);
			// 对显示详细信息按钮添加监听
			detail = new GAPButton(">");
			detail.setFont(ComponentStyle.defaultFont);
			detail.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					if (detailed) {
						closeDetail();
					} else {
						showDetail();
					}
				}
			});

			// 对编辑按钮添加监听
			edit = new GAPButton("E");
			edit.setFont(ComponentStyle.defaultFont);
			edit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (!edited) {
						openEdit();
					} else {
						closeEdit();
						if (newly) {
							newly = false;
							vo = getUserVO();
							UserController.add(vo);
						} else {
							UserController.modify(vo);
						}
					}
				}
			});

			// 对删除按钮进行监听
			delete = new GAPButton("x");
			delete.setFont(ComponentStyle.defaultFont);
			delete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					removeItem(ItemPanel.this);
				}

			});

			detailPanel = new JPanel();
			detailPanel.setBackground(Color.white);
			detailPanel.setBorder(BorderFactory.createLineBorder(ComponentStyle.gray));
			GridBagLayout gb1 = new GridBagLayout();
			detailPanel.setLayout(gb1);
			JLabel nameLabel = new GAPLabel("姓名");
			JLabel usertypeLabel = new GAPLabel("用户类型");
			JLabel genderLabel = new GAPLabel("性别");
			JLabel institutionLabel = new GAPLabel("所属机构");

			// detailPanel布局
			gcons.insets = new Insets(10, 20, 10, 20);
			SwingConsole.addComponent(gb1, gcons, detailPanel, nameLabel, 0, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, name, 1, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, usertypeLabel, 2, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, usertype_list, 3, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, genderLabel, 0, 1, 1, 1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, gender_list, 1, 1, 1, 1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, institutionLabel, 2, 1, 1, 1, 0, 0);
			SwingConsole.addComponent(gb1, gcons, detailPanel, institution_list, 3, 1, 1, 1, 0, 0);

			// 布局
			this.setLayout(gbl);
			gcons.insets = new Insets(10, 0, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, detail, 0, 0, 1, 1, 1, 0);
			gcons.insets = new Insets(10, 20, 0, 50);
			SwingConsole.addComponent(gbl, gcons, this, user_id, 1, 0, 1, 1, 1, 0);
			gcons.insets = new Insets(10, 20, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, user_name, 2, 0, 1, 1, 1, 0);
			gcons.insets = new Insets(10, 50, 0, 30);
			SwingConsole.addComponent(gbl, gcons, this, password, 3, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 20, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, edit, 4, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 10, 0, 10);
			SwingConsole.addComponent(gbl, gcons, this, delete, 5, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 10, 10, 10);
			SwingConsole.addComponent(gbl, gcons, this, detailPanel, 0, 1, 6, 1, 0, 0);
			detailPanel.setVisible(false);
		}

		public ItemPanel(UserVO vo) {
			this();
			this.vo = vo;
			user_id.setText(vo.getUserId());
			user_name.setText(vo.getUserName());
			password.setText(vo.getPassword());
			name.setText(vo.getName());

			int usertype = 0;
			for (int i = 0; i < usertypes.length; i++) {
				if (usertypes[i].equals(vo.getType())) {
					usertype = i;
				}
			}
			usertype_list.setSelectedIndex(usertype);

			switch (vo.getGender()) {
			case MALE:
				gender_list.setSelectedIndex(0);
				break;
			case FEMALE:
				gender_list.setSelectedIndex(1);
				break;
			}

			int ins_id = 0;
			for (int i = 0; i < institutions.size(); i++) {
				if (institutions.get(i).equals(vo.getInsName())) {
					ins_id = i;
				}
			}
			institution_list.setSelectedIndex(ins_id);
			closeEdit();
		}

		UserVO getUserVO() {
			Gender gender = null;
			switch (gender_list.getSelectedItem().toString()) {
			case "男":
				gender = Gender.MALE;
				break;
			case "女":
				gender = Gender.FEMALE;
			}
			return new UserVO(user_id.getText(), user_name.getText(), password.getText(), name.getText(),
					UserType.getUserType(usertype_list.getSelectedItem().toString()), gender,
					institution_list.getSelectedItem().toString());
		}

		void showDetail() {
			detailPanel.setVisible(true);
			detail.setText("v");
			detailed = true;
		}

		void closeDetail() {
			detailPanel.setVisible(false);
			detail.setText(">");
			detailed = false;
		}

		void openEdit() {
			user_id.openEdit();
			user_name.openEdit();
			password.openEdit();
			name.openEdit();
			usertype_list.setEnabled(true);
			usertype_list.setFocusable(true);
			gender_list.setEnabled(true);
			gender_list.setFocusable(true);
			institution_list.setEnabled(true);
			institution_list.setFocusable(true);
			edit.setText("√");
			edited = true;
		}

		void closeEdit() {
			user_id.closeEdit();
			user_name.closeEdit();
			password.closeEdit();
			name.closeEdit();
			usertype_list.setEnabled(false);
			usertype_list.setFocusable(false);
			gender_list.setEnabled(false);
			gender_list.setFocusable(false);
			institution_list.setEnabled(false);
			institution_list.setFocusable(false);
			edit.setText("E");
			vo = getUserVO();
			edited = false;
		}
	}

}
