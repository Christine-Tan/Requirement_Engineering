package gap.client.ui.inventoryui.distributesector;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Unit extends JPanel {
	Icon icon;
	JLabel value, type;

	public Unit(double ratio, String sectorName) {

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(icon.width + 4, icon.height + 45));

		icon = new Icon(ratio);
		String r = String.valueOf(ratio);
		value = new GAPLabel(r + "%");

		type = new GAPLabel(" ");
		type.setForeground(ComponentStyle.blue);
		if (!sectorName.equals("机动区")) {
			type.setText(sectorName);
		}

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.anchor = GridBagConstraints.CENTER;
		// gcons.fill = GridBagConstraints.HORIZONTAL;
		SwingConsole.addComponent(gb, gcons, this, icon, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, type, 0, 1, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, value, 0, 2, 1, 1, 1, 0);

	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(500, 500);
		JPanel panel = new JPanel();
		panel.add(new Unit(58, "机动区"));
		jf.add(panel);
		jf.setVisible(true);
	}
}
