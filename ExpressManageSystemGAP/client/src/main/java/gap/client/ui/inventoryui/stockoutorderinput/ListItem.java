package gap.client.ui.inventoryui.stockoutorderinput;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.vo.GoodsVO;
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

public class ListItem extends JPanel {
	JCheckBox box;
	GAPTextField id, outDate, destination, location;
	String goods_id;
	GoodsVO vo;
	public ListItem(GoodsVO vo) {
		this.vo = vo;
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));

		box = new JCheckBox();
		box.setBackground(Color.white);

		id = new GAPTextField(16);
		id.setText(vo.getExpressorder_id());
		id.setCenter();
		id.closeEdit();

		outDate = new GAPTextField(8);
		outDate.setText((new Date(System.currentTimeMillis())).toString());
		outDate.setCenter();
		outDate.closeEdit();

		destination = new GAPTextField(12);
		destination.setText(vo.getDestination());
		destination.setCenter();
		destination.closeEdit();
		
		location = new GAPTextField(10);
		location.setCenter();
		location.setText(getLocation(vo));
		location.closeEdit();

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(0, 10, 0, 10);
		gcons.anchor = GridBagConstraints.CENTER;
		SwingConsole.addComponent(gb, gcons, this, box, 0, 0, 1, 1, 1, 0);
		gcons.anchor = GridBagConstraints.WEST;
		SwingConsole.addComponent(gb, gcons, this, id, 1, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, outDate, 2, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, destination, 3, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, location, 5, 0, 1, 1, 1, 0);
		
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int state = e.getStateChange();
				if(state == ItemEvent.SELECTED){
					setGoodsId();
				}else{
					goods_id=null;
				}
			}
		});

	}
	
	public String getLocation(GoodsVO vo){
		String sec = vo.getSector_id();
		String location = vo.getLocation();
		location = SectorType.getName(sec.charAt(7))+" "+location;
		return location;
		
	}

	void setSelected(boolean bool){
		this.box.setSelected(bool);
	}
	
	public void setGoodsId(){
		goods_id = vo.getExpressorder_id();
	}
	
	public String getGoodsId(){
		return goods_id;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(20, height - 5, width - 30, height - 5);
	}

}
