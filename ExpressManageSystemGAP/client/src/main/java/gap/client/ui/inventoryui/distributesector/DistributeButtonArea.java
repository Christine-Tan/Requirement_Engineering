package gap.client.ui.inventoryui.distributesector;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DistributeButtonArea extends JPanel {
	private JButton confirm;
	private JComboBox<String> sectors;
	private JLabel set;

	public DistributeButtonArea() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 60));

		confirm = new GAPButton("确认");
		sectors = new GAPComboBox<String>();
		sectors.addItem("汽运区");
		sectors.addItem("铁运区");
		sectors.addItem("航运区");
		sectors.addItem("机动区");
		sectors.setPreferredSize(new Dimension(90, 30));
		set = new GAPLabel("设置为");
		set.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		set.setForeground(ComponentStyle.blue);

		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(200, 60));
		panel1.setBackground(null);
		panel1.setOpaque(false);

		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(200, 60));
		panel2.setBackground(null);
		panel2.setOpaque(false);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.anchor = GridBagConstraints.CENTER;

		SwingConsole.addComponent(gb, gcons, this, panel1, 0, 0, 1, 1, 1, 0.5);
		SwingConsole.addComponent(gb, gcons, this, panel2, 4, 0, 1, 1, 1, 0.5);

		SwingConsole.addComponent(gb, gcons, this, set, 1, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(0, 50, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, confirm, 3, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(5, 0, 0, 50);
		SwingConsole.addComponent(gb, gcons, this, sectors, 2, 0, 1, 1, 1, 0);

	}

	// public void paintComponent(Graphics g){
	// super.paintComponent(g);
	//
	// Graphics g2d = RenderSetter.OpenRender(g);
	// g2d.setColor(ComponentStyle.light_gray);
	// int width = getWidth();
	// g2d.drawLine(10, 5, width-10, 5);
	// }

}
