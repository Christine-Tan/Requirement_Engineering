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
import javax.swing.JTextField;

public class TotalNumPanel extends JPanel {
	JLabel total, Num;

	public TotalNumPanel(String totalName, String num) {
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));

		total = new GAPLabel(totalName + "合计");
		Num = new GAPLabel(num);
		Num.setPreferredSize(new Dimension(150,35));
		Num.setHorizontalAlignment(JTextField.RIGHT);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(10, 75, 10, 35);
		gcons.anchor = GridBagConstraints.WEST;
		SwingConsole.addComponent(gb, gcons, this, total, 0, 0, 1, 1, 1, 0);
		gcons.anchor = GridBagConstraints.EAST;
		gcons.insets = new Insets(10, 67, 10, 78);
		SwingConsole.addComponent(gb, gcons, this, Num, 1, 0, 1, 1, 1, 0);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(20, height - 5, width - 30, height - 5);
	}
}
