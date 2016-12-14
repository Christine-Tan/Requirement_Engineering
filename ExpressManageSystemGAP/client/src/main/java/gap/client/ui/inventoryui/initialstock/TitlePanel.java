package gap.client.ui.inventoryui.initialstock;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel {
	GAPTextField id, order_id, destination, inDate, location;

	public TitlePanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 55));

		id = new GAPTextField("编号", 3);
		id.setCenter();
		id.closeEdit();

		order_id = new GAPTextField("快递单号", 7);
		order_id.setCenter();
		order_id.closeEdit();

		inDate = new GAPTextField("入库日期", 7);
		inDate.setCenter();
		inDate.closeEdit();

		destination = new GAPTextField("目的地", 11);
		destination.setCenter();
		destination.closeEdit();

		location = new GAPTextField("存放位置", 7);
		location.setCenter();
		location.closeEdit();

		JLabel b1 = new JLabel(" ");

		JLabel b2 = new JLabel(" ");

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		// gcons.insets = new Insets(10, 10, 0, 10);
		// SwingConsole.addComponent(gb, gcons, this, id, 0, 0, 1, 1, 1, 0);
		// gcons.insets = new Insets(10, -30, 0, 0);
		// SwingConsole.addComponent(gb, gcons, this, order_id, 1, 0, 1, 1, 1,
		// 0);
		// gcons.insets = new Insets(10, 2, 0, 18);
		// SwingConsole
		// .addComponent(gb, gcons, this, inDate, 2, 0, 1, 1, 1, 0);
		// gcons.insets = new Insets(10, -20, 0, 40);
		// SwingConsole.addComponent(gb, gcons, this, location, 3, 0, 1, 1, 1,
		// 0);
		// gcons.insets = new Insets(10, -33, 0, 103);
		// SwingConsole.addComponent(gb, gcons, this, destination, 4, 0, 1, 1,
		// 1, 0);

		gcons.insets = new Insets(0, 20, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, id, 0, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(0, 10, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, order_id, 1, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, inDate, 2, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(0, 10, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, location, 3, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(0, 13, 0, 7);
		SwingConsole.addComponent(gb, gcons, this, destination, 4, 0, 1, 1, 1,
				0);
		gcons.insets = new Insets(0, 10, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, b1, 5, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(0, 10, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, b2, 6, 0, 1, 1, 1, 0);

	}

	// public void paintComponent(Graphics g) {
	// super.paintComponent(g);
	// Graphics2D g2d = RenderSetter.OpenRender(g);
	// g2d.setColor(ComponentStyle.light_gray);
	// int width = getWidth(), height = getHeight();
	// g2d.drawLine(10, height - 5, width - 20, height - 5);
	// }

}
