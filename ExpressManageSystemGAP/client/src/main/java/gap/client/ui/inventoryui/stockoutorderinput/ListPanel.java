package gap.client.ui.inventoryui.stockoutorderinput;

import gap.client.ui.UITools.SwingConsole;
import gap.client.vo.GoodsVO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ListPanel extends JPanel {
	List<ListItem> items;
	List<String> ids;
	String sector_id;

	public ListPanel(List<GoodsVO> list) {
		setBackground(Color.white);

		
		items = new ArrayList<ListItem>();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			ListItem item = new ListItem(list.get(i));
			items.add(item);
		}

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);
		
		gcons.fill = GridBagConstraints.BOTH;

//		gcons.insets = new Insets(0, 10, 0, 10);
		for (int i = 0; i < size; i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1,
					1, 1, 0);
		}
	}
	
	public List<String> getOrderIds(){
		ids = new ArrayList<String>();
		for(ListItem item:items){
			String id = item.getGoodsId();
			if(id!=null){
				ids.add(id);
			}
		}
		return ids;
	}
}
