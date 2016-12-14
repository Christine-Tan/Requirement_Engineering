package gap.client.ui.inventoryui.distributesector;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListItem extends JPanel {
	Unit[] shelf;
	JLabel rowName;

	public ListItem(int numOfshf, int idOfRow) {
		setBackground(Color.white);
		int width = Default.PANEL_WIDTH;
		width = Math.max(width, (Icon.width + 20) * (numOfshf + 2));
		setPreferredSize(new Dimension(width, 120));

		char c = (char) ('A' + idOfRow);
		rowName = new GAPLabel(c + "排");
		rowName.setPreferredSize(new Dimension(40, 30));
		shelf = new Unit[numOfshf];

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.insets = new Insets(0, 10, 0, 10);
		gcons.anchor = GridBagConstraints.CENTER;

		SwingConsole.addComponent(gb, gcons, this, rowName, 0, 0, 1, 1, 1, 0);
		for (int i = 1; i <= numOfshf; i++) {
			double temp = i * 9.1;
			shelf[i - 1] = new Unit(formatDouble(temp), "铁运区");
			SwingConsole.addComponent(gb, gcons, this, shelf[i - 1], i, 0, 1,
					1, 1, 0);
		}
	}

	public double formatDouble(double ratio) {
		BigDecimal b = new BigDecimal(ratio);
		ratio = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
		return ratio;
	}

	// public void paintComponent(Graphics g){
	// super.paintComponent(g);
	//
	// Graphics2D g2d = RenderSetter.OpenRender(g);
	// g2d.setColor(ComponentStyle.light_gray);
	// int width=getWidth(),height=getHeight();
	// g2d.drawLine(20, height-5, width-30, height-5);
	// }
}
