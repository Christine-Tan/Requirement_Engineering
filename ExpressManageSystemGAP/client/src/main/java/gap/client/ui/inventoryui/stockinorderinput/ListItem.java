package gap.client.ui.inventoryui.stockinorderinput;

import gap.client.blcontroller.InventoryController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.util.LocalInfo;
import gap.client.vo.ExpressOrderVO;
import gap.client.vo.GoodsVO;
import gap.common.util.Address;
import gap.common.util.ExpressType;
import gap.common.util.SectorType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ListItem extends JPanel {
	JCheckBox box;
	GAPTextField id, inDate, destination, sector, location;
	GoodsVO goods;
	public ListItem(ExpressOrderVO vo) {
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH-10, 50));

		box = new JCheckBox();
		box.setBackground(Color.white);

		id = new GAPTextField(8);
		id.setText(vo.order_id);
		id.setCenter();
		id.closeEdit();

		inDate = new GAPTextField(8);
		inDate.setText((new Date(System.currentTimeMillis())).toString());
		inDate.setCenter();
		inDate.closeEdit();

		destination = new GAPTextField(12);
		Address addre = vo.receiver_info.getAddress();
		String l = addre.getProvince_name()+addre.getCity_name()+addre.getDistrict_name();
		destination.setText(l);
		destination.setCenter();
		destination.closeEdit();

		sector = new GAPTextField(4);
		sector.setCenter();
		sector.setText(ExpressType.getSectorByExpressType(vo.expressType));
		sector.closeEdit();

		location = new GAPTextField(10);
		location.setHorizontalAlignment(JTextField.CENTER);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.anchor = GridBagConstraints.CENTER;
		gcons.insets = new Insets(0, 5, 0, 5);
		SwingConsole.addComponent(gb, gcons, this, box, 0, 0, 1, 1, 1, 0);
		gcons.anchor = GridBagConstraints.WEST;
		gcons.insets = new Insets(0, 5, 0, 5);
		SwingConsole.addComponent(gb, gcons, this, id, 1, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, inDate, 2, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, destination, 3, 0, 1, 1, 1,
				0);
		SwingConsole.addComponent(gb, gcons, this, sector, 4, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, location, 5, 0, 1, 1, 1, 0);
		
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int itemState = e.getStateChange();
				if(itemState== ItemEvent.SELECTED){
					setLocation();
					setGoodsVO();
					addGoodsVO();
				}else{
					deleteGoodsVO();
					clearLocation();
					goods = null;
				}
			}
		});

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
	
	public void setGoodsVO(){
		SectorType sec = SectorType.getSectorTypeByChinese(sector.getText());
		String expressorder_id = id.getText(),
				date = inDate.getText(),
				des = destination.getText(),
				loc = location.getText(),
				belong_sec = SectorType.getSectorId(LocalInfo.getIns_ID(), sec);
		String[] locations = loc.split(" ");
		String Chinese = locations[0];
		loc = locations[1];
		String sector_id = LocalInfo.ins_id+SectorType.getSectorTypeByChinese(Chinese).getChar();	
		
		this.goods = new GoodsVO(expressorder_id, loc, sec, date, sector_id, belong_sec, des);	
	}
	public GoodsVO getGoodVO(){
		return goods;
	}
	
	public void addGoodsVO(){
		InventoryController.Stockin(goods);
	}
	
	public void deleteGoodsVO(){
		InventoryController.Stockout(goods);
	}
	
	public void setSelected(boolean bool){
		this.box.setSelected(bool);
	}
	
	public void setLocation(){
		this.location.setText(getNextLocation());
		this.location.closeEdit();
	}
	
	public void clearLocation(){
		this.location.openEdit();
		this.location.setText("");
	}
	
	public String getNextLocation(){
		String ins_id = LocalInfo.getIns_ID();
		SectorType sec = SectorType.getSectorTypeByChinese(sector.getText());
		String sector_id = SectorType.getSectorId(ins_id, sec);
		String location = InventoryController.getNextLocation(sector_id, ins_id);
		
		if(location.charAt(0)!='f'){
			return sector.getText()+" "+location;
		}else{
			return "机动区"+location.replaceFirst("f", " ");
		}
		
	}
	

}
