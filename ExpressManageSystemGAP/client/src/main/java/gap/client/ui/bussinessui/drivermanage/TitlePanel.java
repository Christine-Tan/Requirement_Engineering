package gap.client.ui.bussinessui.drivermanage;

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
	JLabel car_id, name, gender, id_card;

	public TitlePanel() {
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));
		setBackground(Color.white);
		car_id = new GAPLabel("司机编号");
		name = new GAPLabel("姓名");
		gender = new GAPLabel("性别");
		id_card = new GAPLabel("身份证号");

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(10, 150, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, car_id, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 85, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, name, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 65, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, gender, 2, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 130, 10, 240);
		SwingConsole.addComponent(gb, gcons, this, id_card, 3, 0, 1, 1, 0, 0);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
