package gap.client.ui.managerui.institutionui;

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

public class InsTitlePanel extends JPanel {
	JLabel ins_id, ins_type, ins_name, ins_city, ins_member;

	public InsTitlePanel() {
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));
		setBackground(Color.WHITE);
		ins_id = new GAPLabel("机构编号");
		ins_type = new GAPLabel("机构类型");
		ins_name = new GAPLabel("机构名称");
		ins_city = new GAPLabel("所在城市");
		ins_member = new GAPLabel("人员数目");

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(10, 0, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, ins_id, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 80, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, ins_type, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 150, 10, 20);
		SwingConsole.addComponent(gb, gcons, this, ins_name, 2, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 110, 10, 8);
		SwingConsole.addComponent(gb, gcons, this, ins_city, 3, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 45, 10, 25);
		SwingConsole.addComponent(gb, gcons, this, ins_member, 4, 0, 1, 1, 0, 0);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
