package gap.client.ui.inventoryui.checkstock;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StockCheckButtonArea extends JPanel {
	public JButton confirm;
	public JButton export;

	public StockCheckButtonArea() {
		setBackground(Color.WHITE);

		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 45));

		confirm = new GAPButton("开始盘点");
		export = new GAPButton("导出Excel");

		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(200, 50));
		panel1.setBackground(null);
		panel1.setOpaque(false);

		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(200, 50));
		panel2.setBackground(null);
		panel2.setOpaque(false);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		// gcons.anchor = GridBagConstraints.CENTER;
		SwingConsole.addComponent(gb, gcons, this, panel1, 0, 0, 1, 1, 1, 0.5);
		SwingConsole.addComponent(gb, gcons, this, panel2, 3, 0, 1, 1, 1, 0.5);
		gcons.insets = new Insets(0, 0, 0, 50);
		SwingConsole.addComponent(gb, gcons, this, confirm, 1, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(0, 50, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, export, 2, 0, 1, 1, 1, 0);
	}

	// public void paintComponent(Graphics g) {
	// super.paintComponent(g);
	// Graphics2D g2d = RenderSetter.OpenRender(g);
	// g2d.setColor(ComponentStyle.light_gray);
	// int width = getWidth();
	// g2d.drawLine(10, 5, width - 20, 5);
	// }
}
