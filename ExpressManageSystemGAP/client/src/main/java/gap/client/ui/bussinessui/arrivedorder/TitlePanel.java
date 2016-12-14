package gap.client.ui.bussinessui.arrivedorder;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class TitlePanel extends JPanel {
	JCheckBox checkBox;
	GAPTextField car_id, departure_ins_name, guard_name, driver_name, comment;

	public TitlePanel() {
		setOpaque(false);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 60));
		checkBox = new JCheckBox();
		checkBox.setBackground(Color.white);
		car_id = new GAPTextField("待送达车辆编号", 8);
		departure_ins_name = new GAPTextField("出发机构", 12);
		guard_name = new GAPTextField("监装员", 4);
		driver_name = new GAPTextField("押运员", 4);
		comment = new GAPTextField("备注", 20);

		setStyle();

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		this.setLayout(gb);
		gcons.insets = new Insets(10, 10, 0, 20);
		SwingConsole.addComponent(gb, gcons, this, checkBox, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 10, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, car_id, 2, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 10, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, departure_ins_name, 3, 0, 1,
				1, 0, 0);
		gcons.insets = new Insets(10, 10, 0, 10);
		SwingConsole
				.addComponent(gb, gcons, this, guard_name, 4, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 10, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, driver_name, 5, 0, 1, 1, 0,
				0);
		gcons.insets = new Insets(10, 0, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, comment, 6, 0, 1, 1, 0, 0);

	}

	void setStyle() {
		car_id.closeEdit();
		car_id.setCenter();

		departure_ins_name.setCenter();
		departure_ins_name.closeEdit();

		guard_name.setCenter();
		guard_name.closeEdit();

		driver_name.setCenter();
		driver_name.closeEdit();

		comment.setCenter();
		comment.closeEdit();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
