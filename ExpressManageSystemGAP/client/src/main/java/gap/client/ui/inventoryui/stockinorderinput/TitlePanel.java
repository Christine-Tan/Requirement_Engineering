package gap.client.ui.inventoryui.stockinorderinput;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class TitlePanel extends JPanel {
	JCheckBox box;
	GAPTextField id, inDate, destination, sector, location;

	public TitlePanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 40));

		box = new JCheckBox();
		box.setBackground(Color.white);
		
		

		id = new GAPTextField("快递编号",8);
		id.setCenter();
		id.closeEdit();
		
		inDate = new GAPTextField("入库日期",8);
		inDate.setCenter();
		inDate.closeEdit();
		
		destination = new GAPTextField("目的地",12);
		destination.setCenter();
		destination.closeEdit();
		
		sector = new GAPTextField("分区",4);
		sector.setCenter();
		sector.closeEdit();
		
		location = new GAPTextField("存放位置",10);
		location.setCenter();
		location.closeEdit();

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);
		

		gcons.anchor = GridBagConstraints.CENTER;
		gcons.insets = new Insets(0, 5, 0, 5);
		SwingConsole.addComponent(gb, gcons, this, box, 0, 0, 1, 1, 1, 0);
		gcons.anchor = GridBagConstraints.WEST;
		gcons.insets = new Insets(0, 5, 0, 5);
		SwingConsole.addComponent(gb, gcons, this, id, 1, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(0, 5, 0, 5);
		SwingConsole.addComponent(gb, gcons, this, inDate, 2, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(0, 5, 0, 5);
		SwingConsole.addComponent(gb, gcons, this, destination, 3, 0, 1, 1, 1,
				0);
		gcons.insets = new Insets(0, 5, 0, 5);
		SwingConsole.addComponent(gb, gcons, this, sector, 4, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(0, 5, 0, 5);
		SwingConsole.addComponent(gb, gcons, this, location, 5, 0, 1, 1, 1, 0);

	}

//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//
//		Graphics2D g2d = RenderSetter.OpenRender(g);
//		g2d.setColor(ComponentStyle.light_gray);
//		int width = getWidth(), height = getHeight();
//		g2d.drawLine(20, height - 5, width - 30, height - 5);
//	}

}
