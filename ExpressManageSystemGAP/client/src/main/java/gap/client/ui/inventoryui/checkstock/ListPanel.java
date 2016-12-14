package gap.client.ui.inventoryui.checkstock;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ListPanel extends JPanel {
	public List<ListItem> items;
	String sector_id;
	GridBagLayout gb;
	GridBagConstraints gcons;

	public ListPanel(final int rowNum,String sector_id) {
		this.sector_id = sector_id;
		setBackground(Color.white);
		setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,
				ComponentStyle.light_gray));

		items = new ArrayList<ListItem>();
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
//		gcons.insets = new Insets(0, 10, 0, 10);
		gcons.anchor = GridBagConstraints.CENTER;
		gcons.fill = GridBagConstraints.BOTH;
//		gcons.insets = new Insets(10, 0, 0, 0);
		for (int i = 0; i < rowNum; i++) {
			items.add(new ListItem(rowNum, i,sector_id));
//			System.out.println(sector_id);
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1, 1, 1,
					0);
			
			
		}
	}

}
