package gap.client.ui.inventoryui.distributesector;

import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.gapcomponents.ComponentStyle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Icon extends JPanel {
	static int height = 60;
	static int width = 60;
	double usedRatio;

	public Icon(double ratio) {
		usedRatio = ratio;
		setBackground(Color.white);
		setPreferredSize(new Dimension(width + 4, height + 4));
		setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
				ComponentStyle.light_gray));

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.blue);
		double y1 = height * ((100 - usedRatio) / 100);
		int y = (int) y1;
		// double height1 = height*(usedRatio/100);
		// int height = (int)height1;
		g2d.fillRect(2, y + 2, width, height - y);
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		Icon icon = new Icon(50);
		JPanel panel = new JPanel();
		panel.add(icon);
		jf.setSize(300, 300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setContentPane(panel);

	}

}
