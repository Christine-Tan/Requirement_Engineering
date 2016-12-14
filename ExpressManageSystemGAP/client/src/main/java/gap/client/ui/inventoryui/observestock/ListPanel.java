package gap.client.ui.inventoryui.observestock;

import gap.client.ui.UITools.SwingConsole;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ListPanel extends JPanel {
	List<ListItem> items;
	List<InventoryOrder> list;
	
	public ListPanel(List<InventoryOrder> list){
		setBackground(Color.white);
		items = new ArrayList<ListItem>();
		
		for(int i = 1;i<=list.size();i++){
			ListItem item = new ListItem(i,list.get(i-1));
			items.add(item);
		}

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.fill = GridBagConstraints.BOTH;
//		gcons.insets = new Insets(0, 10, 0, 10);
		for (int i = 0; i < items.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1,
					1, 1, 0);
		}
	}
}
