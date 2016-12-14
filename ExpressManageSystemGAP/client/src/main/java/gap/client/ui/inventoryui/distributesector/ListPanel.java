package gap.client.ui.inventoryui.distributesector;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ListPanel extends JPanel {
	ListItem[] list;

	public ListPanel(int rowNum) {
		setBackground(Color.white);
		setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,
				ComponentStyle.light_gray));

		list = new ListItem[rowNum];

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(0, 10, 0, 10);
		gcons.anchor = GridBagConstraints.CENTER;

		gcons.insets = new Insets(10, 0, 0, 0);
		for (int i = 0; i < rowNum; i++) {
			list[i] = new ListItem(10, i);
			SwingConsole.addComponent(gb, gcons, this, list[i], 0, i, 1, 1, 1,
					0);
		}
	}
}
