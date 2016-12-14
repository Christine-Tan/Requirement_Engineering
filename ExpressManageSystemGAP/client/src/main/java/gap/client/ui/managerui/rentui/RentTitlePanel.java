package gap.client.ui.managerui.rentui;

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

public class RentTitlePanel extends JPanel {
	JLabel institution, money;
	GridBagLayout gb;
	GridBagConstraints gcons;

	public RentTitlePanel() {
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 80));
		setBackground(Color.WHITE);
		institution = new GAPLabel("机构名称");
		money = new GAPLabel("租金");

		// 布局
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.insets = new Insets(40, 80, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, institution, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(40, 150, 10, 430);
		SwingConsole.addComponent(gb, gcons, this, money, 1, 0, 1, 1, 0, 0);

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
