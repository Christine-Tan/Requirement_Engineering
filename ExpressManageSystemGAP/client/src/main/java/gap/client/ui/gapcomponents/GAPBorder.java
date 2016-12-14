package gap.client.ui.gapcomponents;

import gap.client.ui.UITools.RenderSetter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.border.AbstractBorder;

public class GAPBorder extends AbstractBorder {
	int high = 20, low = 0, border_width = 2;

	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		Graphics2D g2d = RenderSetter.OpenRender(g);
		int step = (high - low) / (border_width - 1);
		for (int i = 0; i < border_width; i++) {
			Color co = new Color(0, 0, 0, low + i * step);
			g2d.setColor(co);
			g2d.drawRoundRect(i, i, width - 2 * i, height - 2 * i, 4, 4);
		}
	}

}
