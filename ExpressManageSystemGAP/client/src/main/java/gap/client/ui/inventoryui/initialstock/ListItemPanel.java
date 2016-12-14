package gap.client.ui.inventoryui.initialstock;

import gap.client.blcontroller.InventoryController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.util.LocalInfo;
import gap.client.vo.GoodsVO;
import gap.common.util.SectorType;

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
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListItemPanel extends JPanel {
	// 列表中所有的项
	List<ItemPanel> items;
	GridBagLayout gb;
	GridBagConstraints gcons;
	// 添加button
	JButton addButton;
	JFrame frame;
	String sector_id;
	InitialStockPanel initialPanel;

	public ListItemPanel(JFrame frame,String sector_id,InitialStockPanel panel) {
		this.frame = frame;
		this.sector_id = sector_id;
		this.initialPanel = panel;
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
		gcons.fill = GridBagConstraints.BOTH;
		setLayout(gb);

		items = new ArrayList<ItemPanel>();

		List<GoodsVO> VOs = new ArrayList<GoodsVO>();
		VOs = InventoryController.getOneSector(sector_id, LocalInfo.getIns_ID());
		if(VOs.size()!=0){
			for (int i = 0;i<VOs.size();i++) {
				addItem(VOs.get(i));
			}
		}else{
			addNewItem();
		}
		
	}

	/**
	 * 根据一个已有VO添加项
	 * 
	 * @param driver
	 */
	private void addItem(GoodsVO goods) {
		items.add(new ItemPanel(goods));
		reLayout();
//		frame.validate();
	}

	/**
	 * 添加一个新项目
	 */
	private void addNewItem() {
		ItemPanel item = new ItemPanel();
		item.original = true;
		item.openEdit();
		items.add(item);
		reLayout();
//		frame.validate();
	}

	/**
	 * 移除一项
	 * 
	 * @param item
	 */
	private void removeItem(ItemPanel item) {
		items.remove(item);
		initialPanel.reLayout();
		reLayout();
	}

	/**
	 * 重新布局
	 */
	private void reLayout() {
		removeAll();
		for (int i = 0; i < items.size(); i++) {
			items.get(i).id.setText((i+1)+"");;
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1,
					1, 1, 0);
		}
		SwingConsole.addComponent(gb, gcons, this, addButton, 0, items.size(),
				1, 1, 1, 0);
		repaint();
		frame.validate();
	}

	// 每一项
	class ItemPanel extends JPanel {
		GoodsVO goods;
		// 显示详细信息、删除、改变编辑状态的按钮
		JButton delete_la, edit_la;
		JLabel id;
		GAPTextField order_id, destination, inDate, location;
		GridBagLayout gb;
		GridBagConstraints gcons;
		boolean original;
		boolean edited, showed;

		public ItemPanel() {
//			original = true;
			setBackground(Color.white);
			setFocusable(true);
			setPreferredSize(new Dimension(Default.PANEL_WIDTH-10,50));
			gb = new GridBagLayout();
			gcons = new GridBagConstraints();

			edit_la = new GAPButton("E");
			edit_la.setFont(ComponentStyle.defaultFont);
			edit_la.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					if (!edited) {
						openEdit();
					} else {
						closeEdit();
						if (original) {
							original = false;
							InventoryController.InitialAdd(getGoodsVO());
							// DriverManageController.add(driver);
							System.out.println("add");
						} else {
							InventoryController.InitialModify(getGoodsVO());
							// DriverManageController.modify(driver);
							System.out.println("modify");
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
					GoodsVO vo = getGoodsVO();
					if(vo!=null){
						InventoryController.InitialDelete(vo.getExpressorder_id());
						System.out.println("delete");
						reLayout();
					}
					removeItem(ItemPanel.this);
				}
			});
			
			id = new GAPLabel();
			id.setPreferredSize(new Dimension(40, 25));
			id.setHorizontalAlignment(JLabel.RIGHT);
			
			order_id = new GAPTextField(8);
			order_id.setCenter();
			destination = new GAPTextField(12);
			destination.setCenter();
			inDate = new GAPTextField(7);
			inDate.setCenter();
			location = new GAPTextField(8);
			location.setCenter();
			setLayout(gb);
			
			gcons.insets = new Insets(0, 10, 0, 10);
			SwingConsole.addComponent(gb, gcons, this, id, 0, 0, 1, 1, 1, 0);
			SwingConsole.addComponent(gb, gcons, this, order_id, 1, 0, 1, 1,1, 0);
			SwingConsole
					.addComponent(gb, gcons, this, inDate, 2, 0, 1, 1, 1, 0);
			SwingConsole.addComponent(gb, gcons, this, location, 3, 0, 1, 1, 1,
					0);
			// gcons.insets = new Insets(10, 80, 10, 0);
			SwingConsole.addComponent(gb, gcons, this, destination, 4, 0, 1, 1,
					1, 0);
			// gcons.insets = new Insets(10, 40, 10, 10);
			SwingConsole.addComponent(gb, gcons, this, edit_la, 5, 0, 1, 1, 1,
					0);
			// gcons.insets = new Insets(10, 10, 10, 10);
			SwingConsole.addComponent(gb, gcons, this, delete_la, 6, 0, 1, 1,
					1, 0);

		}

		public ItemPanel(GoodsVO goods) {
			this();
			this.goods = goods;
			order_id.setText(goods.getExpressorder_id());
			destination.setText(goods.getDestination());
			String sec = SectorType.getName(goods.getSector_id().charAt(7));
			String loc = sec+" "+goods.getLocation();
			location.setText(loc);
			inDate.setText(goods.getDate());
			
			closeEdit();
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = RenderSetter.OpenRender(g);
			g2d.setColor(ComponentStyle.light_gray);
			int width = getWidth(), height = getHeight();
			g2d.drawLine(10, height - 5, width - 20, height - 5);
		}

		public GoodsVO getGoodsVO() {
			try{
				String id = order_id.getText(),loc = location.getText(),date = inDate.getText(),
						des = destination.getText();
				String[] details = loc.split(" ");
				SectorType sectorType = SectorType.getSectorTypeByChinese(details[0]);
				String sec_id = SectorType.getSectorId(LocalInfo.ins_id, sectorType);
				String bel_id = sector_id;
				return new GoodsVO(id, details[1], sectorType, date, sec_id, bel_id, des);

			}catch(Exception e){
				System.out.println("有未填写的文本框");
			}
			return null;
			
			
			
		}

		// 关闭编辑
		void closeEdit() {
			order_id.closeEdit();
			destination.closeEdit();
			inDate.closeEdit();
			location.closeEdit();

			edit_la.setText("E");
			goods = getGoodsVO();
			edited = false;
		}

		// 启用编辑
		void openEdit() {
			order_id.openEdit();
			destination.openEdit();
			inDate.openEdit();
			location.openEdit();

			edit_la.setText("√");
			edited = true;
		}
	}
}
