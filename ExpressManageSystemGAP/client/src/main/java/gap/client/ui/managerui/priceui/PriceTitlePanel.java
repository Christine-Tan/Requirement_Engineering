package gap.client.ui.managerui.priceui;

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

import javax.swing.JPanel;

public class PriceTitlePanel extends JPanel {
	GAPLabel city, express, standard, economic, colon1, colon2, base;
	GridBagLayout gb;
	GridBagConstraints gcons;

	public PriceTitlePanel() {
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 80));
		setBackground(Color.WHITE);

		city = new GAPLabel("出发城市");
		express = new GAPLabel("特快");
		standard = new GAPLabel("标准");
		economic = new GAPLabel("经济");
		base = new GAPLabel("基准价格");
		colon1 = new GAPLabel(":");
		colon2 = new GAPLabel(":");

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(30, 40, 10, 10);
		SwingConsole.addComponent(gb, gcons, this, city, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(30, 100, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, express, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(30, 5, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, colon1, 2, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(30, 5, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, standard, 3, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(30, 5, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, colon2, 4, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(30, 5, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, economic, 5, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(30, 100, 10, 300);
		SwingConsole.addComponent(gb, gcons, this, base, 6, 0, 1, 1, 0, 0);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
