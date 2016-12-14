package gap.client.ui.managerui.priceui;

import gap.client.blcontroller.CityController;
import gap.client.blcontroller.PriceController;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.vo.CityVO;
import gap.client.vo.PriceVO;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PriceListItemPanel extends JPanel {
	// 列表中所有的项
	List<ItemPanel> items;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	JButton addButton;
	JFrame frame;

	public PriceListItemPanel(JFrame frame) {
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
		// 获得所有价格信息
		List<PriceVO> prices = PriceController.getAll();
		for (PriceVO price : prices) {
			addItem(price);
		}
	}

	// 添加一个列表项
	private void addItem(PriceVO price) {
		items.add(new ItemPanel(price));
		reLayout();
		frame.validate();
	}

	// 新增一个空列表项
	private void addNewItem() {
		ItemPanel item = new ItemPanel();
		item.openEdit();
		item.newly = true;
		items.add(item);
		reLayout();
		frame.validate();
	}

	// 重新布局
	private void reLayout() {
		for (int i = 0; i < items.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1, 1, 1, 0);
		}
		SwingConsole.addComponent(gb, gcons, this, addButton, 0, items.size(), 1, 1, 1, 0);
	}

	class ItemPanel extends JPanel {
		// 每一个列表项中的组件
		GAPTextField express_f, standard_f, economic_f, base_f;
		JComboBox<String> city_list;
		JButton edit;
		// 两个冒号
		JLabel colon1, colon2;
		PriceVO vo;
		boolean newly, edited;
		// 布局
		GridBagLayout gbl;

		public ItemPanel() {
			setBackground(Color.WHITE);
			setFocusable(true);
			city_list = new GAPComboBox<String>();
			express_f = new GAPTextField(3);
			standard_f = new GAPTextField(3);
			economic_f = new GAPTextField(3);
			base_f = new GAPTextField(4);
			colon1 = new JLabel("：");
			colon2 = new JLabel("：");

			express_f.setHorizontalAlignment(JTextField.CENTER);
			standard_f.setHorizontalAlignment(JTextField.CENTER);
			economic_f.setHorizontalAlignment(JTextField.CENTER);
			base_f.setHorizontalAlignment(JTextField.CENTER);

			// 初始化下拉框
			for (CityVO city : CityController.getAll()) {
				city_list.addItem(city.getCityName());
			}
			city_list.setSelectedItem(0);

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
							vo = getPriceVO();
							PriceController.addPrice(vo);
						} else {
							PriceController.modifyPrice(vo);
						}
					}
				}
			});

			// 列表项布局
			gbl = new GridBagLayout();
			this.setLayout(gbl);
			gcons.insets = new Insets(10, 100, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, city_list, 0, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 90, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, express_f, 1, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 0, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, colon1, 2, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 0, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, standard_f, 3, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 0, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, colon2, 4, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 0, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, economic_f, 5, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 90, 0, 255);
			SwingConsole.addComponent(gbl, gcons, this, base_f, 6, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(10, 10, 0, 50);
			SwingConsole.addComponent(gbl, gcons, this, edit, 7, 0, 1, 1, 0, 0);

		}

		public ItemPanel(PriceVO price) {
			this();
			this.vo = price;
			// 获得出发城市对应的id
			int city_id = 1;
			List<CityVO> cities = CityController.getAll();
			for (int i = 0; i < cities.size(); i++) {
				if (vo.getCity().equals(cities.get(i).getCityName()))
					city_id = i;
			}
			city_list.setSelectedIndex(city_id);
			express_f.setText(vo.getExpress() + "");
			standard_f.setText(vo.getStandard() + "");
			economic_f.setText(vo.getEconomic() + "");
			base_f.setText(vo.getBase() + "");

			closeEdit();
		}

		PriceVO getPriceVO() {
			return new PriceVO(city_list.getSelectedItem().toString(), Integer.valueOf(express_f.getText()),
					Integer.valueOf(standard_f.getText()), Integer.valueOf(economic_f.getText()),
					Double.valueOf(base_f.getText()));
		}

		void openEdit() {
			express_f.openEdit();
			standard_f.openEdit();
			economic_f.openEdit();
			base_f.openEdit();
			edit.setText("√");
			edited = true;
		}

		void closeEdit() {
			express_f.closeEdit();
			standard_f.closeEdit();
			economic_f.closeEdit();
			base_f.closeEdit();
			edit.setText("E");
			vo = getPriceVO();
			edited = false;
		}
	}
}
