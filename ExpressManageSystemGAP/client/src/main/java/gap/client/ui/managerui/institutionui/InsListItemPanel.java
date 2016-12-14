package gap.client.ui.managerui.institutionui;

import gap.client.blcontroller.CityController;
import gap.client.blcontroller.InstitutionController;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.vo.CityVO;
import gap.client.vo.InstitutionVO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InsListItemPanel extends JPanel {
	// 列表中所有的项
	List<ItemPanel> items;
	List<InstitutionVO> institutions;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	JButton addButton;
	JFrame frame;

	public InsListItemPanel(JFrame frame, String city, String id) {
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

		if (city != null) {
			institutions = InstitutionController.findByCity(city);
			reLayout();
			// System.out.println("City");
		} else if (id != null) {
			institutions.add(InstitutionController.findById(id));
			reLayout();
			// System.out.println("ID");
		} else {
			institutions = InstitutionController.getAll();
		}

		for (InstitutionVO institution : institutions) {
			addItem(institution);
		}

	}

	private void addItem(InstitutionVO institution) {
		items.add(new ItemPanel(institution));
		reLayout();
		frame.validate();
	}

	private void addNewItem() {
		ItemPanel item = new ItemPanel();
		item.newly = true;
		item.openEdit();
		items.add(item);
		reLayout();
		frame.validate();
	}

	private void removeItem(ItemPanel ip) {
		items.remove(ip);
		remove(ip);
		InstitutionController.deleteInstitution(ip.vo.getInsId());
		reLayout();
		frame.validate();
	}

	private void reLayout() {
		for (int i = 0; i < items.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1, 1, 1, 0);
		}
		SwingConsole.addComponent(gb, gcons, this, addButton, 0, items.size(), 1, 1, 1, 0);
	}

	// 每一个列表项都是一个panel，用GridBagLayout布局
	class ItemPanel extends JPanel {
		InstitutionVO vo;
		GAPTextField ins_id, ins_name, ins_member;
		JComboBox<String> ins_type_list, ins_city_list;
		// 编辑、删除按钮
		JButton edit, delete;
		GridBagLayout gbl;
		// 是否处于编辑状态，是否是新建的表项
		boolean edited, newly;

		public ItemPanel() {
			setBackground(Color.WHITE);
			setFocusable(true);
			gbl = new GridBagLayout();
			ins_id = new GAPTextField(7);
			ins_name = new GAPTextField(15);
			ins_member = new GAPTextField(5);
			ins_type_list = new GAPComboBox<String>();
			ins_city_list = new GAPComboBox<String>();
			this.setLayout(gbl);
			ins_id.setHorizontalAlignment(JTextField.CENTER);
			ins_name.setHorizontalAlignment(JTextField.CENTER);
			ins_member.setHorizontalAlignment(JTextField.CENTER);

			// 初始化下拉框列表项
			ins_type_list.addItem("营业厅");
			ins_type_list.addItem("中转中心");
			ins_type_list.setSelectedIndex(0);

			for (CityVO city : CityController.getAll()) {
				ins_city_list.addItem(city.getCityName());
			}
			ins_city_list.setSelectedIndex(0);

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
							vo = getInstitutionVO();
							InstitutionController.addInstitution(vo);
						} else {
							InstitutionController.modifyInstitution(vo);
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

			// 列表项布局
			gcons.insets = new Insets(10, 0, 0, 20);
			SwingConsole.addComponent(gbl, gcons, this, ins_id, 0, 0, 1, 1, 1, 0);
			gcons.insets = new Insets(10, 28, 0, 50);
			SwingConsole.addComponent(gbl, gcons, this, ins_type_list, 1, 0, 1, 1, 1, 0);
			gcons.insets = new Insets(10, 10, 0, 35);
			SwingConsole.addComponent(gbl, gcons, this, ins_name, 2, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 20, 0, 20);
			SwingConsole.addComponent(gbl, gcons, this, ins_city_list, 3, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 20, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, ins_member, 4, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 15, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, edit, 5, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 10, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, delete, 6, 0, 1, 1, 0, 0);

		}

		public ItemPanel(InstitutionVO vo) {
			this();
			this.vo = vo;
			ins_id.setText(vo.getInsId());
			ins_type_list.setSelectedIndex(vo.getInsId().charAt(3) - '0');
			ins_name.setText(vo.getInsName());
			// 获得机构所在城市对应的id
			int city_id = 1;
			List<CityVO> cities = CityController.getAll();
			for (int i = 0; i < cities.size(); i++) {
				if (vo.getInsCity().equals(cities.get(i).getCityName()))
					city_id = i;
			}
			ins_city_list.setSelectedIndex(city_id);
			ins_member.setText(vo.getInsMember() + "");
			closeEdit();
		}

		InstitutionVO getInstitutionVO() {
			return new InstitutionVO(ins_id.getText(), ins_name.getText(),
					ins_city_list.getItemAt(ins_city_list.getSelectedIndex()), Integer.valueOf(ins_member.getText()));
		}

		void openEdit() {
			ins_id.openEdit();
			ins_name.openEdit();
			ins_member.openEdit();
			ins_type_list.setFocusable(true);
			ins_city_list.setFocusable(true);
			edit.setText("√");
			edited = true;
		}

		void closeEdit() {
			ins_id.closeEdit();
			ins_name.closeEdit();
			ins_member.closeEdit();
			ins_type_list.setFocusable(false);
			ins_city_list.setFocusable(false);
			edit.setText("E");
			vo = getInstitutionVO();
			edited = false;
		}

	}
}
