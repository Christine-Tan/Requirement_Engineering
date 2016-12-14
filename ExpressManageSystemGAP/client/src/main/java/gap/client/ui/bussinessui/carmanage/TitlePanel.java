package gap.client.ui.bussinessui.carmanage;

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
	JLabel id, car_id, car_num, server_time;

	public TitlePanel() {
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));
		setBackground(Color.white);
		id = new GAPLabel("编号");
		car_id = new GAPLabel("车辆代号");
		car_num = new GAPLabel("车牌号");
		server_time = new GAPLabel("服役时间（年）");

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(10, 165, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, id, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 73, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, car_id, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 117, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, car_num, 2, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 100, 10, 215);
		SwingConsole.addComponent(gb, gcons, this, server_time, 3, 0, 1, 1, 0,
				0);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
