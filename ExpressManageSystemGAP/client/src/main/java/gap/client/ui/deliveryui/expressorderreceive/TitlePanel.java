package gap.client.ui.deliveryui.expressorderreceive;

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

public class TitlePanel extends JPanel {
	JLabel id, order_id, receiver, receive_day, comment;

	public TitlePanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 80));

		id = new GAPLabel("编号");
		order_id = new GAPLabel("订单号");
		receiver = new GAPLabel("收件人");
		receive_day = new GAPLabel("收件日期");
		comment = new GAPLabel("备注");

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.anchor = GridBagConstraints.EAST;
		gcons.insets = new Insets(20, 22, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, id, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(20, 55, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, order_id, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(20, 85, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, receiver, 2, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(20, 78, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, receive_day, 3, 0, 1, 1, 0,
				0);
		gcons.insets = new Insets(20, 200, 0, 200);
		SwingConsole.addComponent(gb, gcons, this, comment, 4, 0, 1, 1, 0, 0);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}

}
