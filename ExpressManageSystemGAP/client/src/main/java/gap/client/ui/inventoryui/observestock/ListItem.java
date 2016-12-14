package gap.client.ui.inventoryui.observestock;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListItem extends JPanel {
	JLabel id,order_id,inDate,amount;
//	InventoryOrderVO
	public ListItem(int num,InventoryOrder vo){
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH-10, 50));

		id = new GAPLabel();
		// id.setBackground(Color.red);
		id.setPreferredSize(new Dimension(80, 30));
		id.setHorizontalAlignment(JLabel.RIGHT);
		id.setText(num + "");

		inDate = new GAPLabel();
		inDate.setText(vo.getDate());
		
		order_id = new GAPLabel();
		order_id.setText(vo.getId());
		
		amount = new GAPLabel();
		amount.setPreferredSize(new Dimension(80, 30));
		amount.setHorizontalAlignment(JLabel.RIGHT);
		amount.setText(vo.getNum()+"");

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.insets = new Insets(0, 10, 0, 10);
		gcons.anchor = GridBagConstraints.CENTER;
		gcons.insets = new Insets(0, 10, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, id, 0, 0, 1, 1, 0.3, 0);
		gcons.insets = new Insets(0, 10, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, order_id, 1, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(0, 40, 0, -20);
		SwingConsole.addComponent(gb, gcons, this, inDate, 2, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(0, 10, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, amount, 3, 0, 1, 1, 1, 0);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(20, height - 5, width - 30, height - 5);
	}

}
