package gap.client.ui.inventoryui.distributesector;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChoosePanel extends JPanel {
	JLabel flex;

	public ChoosePanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 60));
		// setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
		// ComponentStyle.light_gray));

		flex = new GAPLabel("机动区");
		flex.setPreferredSize(new Dimension(100, 50));
		flex.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		flex.setForeground(ComponentStyle.blue);
		// flex.setSize(80, 50);

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

		// gcons.insets = new Insets(0,0,0,0);
		gcons.fill = GridBagConstraints.HORIZONTAL;
		// gcons.anchor = GridBagConstraints.CENTER;
		SwingConsole.addComponent(gb, gcons, this, panel1, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, panel2, 2, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(10, 0, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, flex, 1, 0, 1, 1, 0, 0);

	}

}
