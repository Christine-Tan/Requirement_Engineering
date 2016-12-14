package gap.client.ui.inventoryui.stockinorderinput;

import gap.client.ui.UITools.SwingConsole;
import gap.client.vo.ExpressOrderVO;
import gap.client.vo.GoodsVO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ListPanel extends JPanel {
	List<ListItem> items; 
	List<ExpressOrderVO> expressorders;
	List<GoodsVO> goods;
	GridBagLayout gb;
	GridBagConstraints gcons;

	public ListPanel(List<ExpressOrderVO> orders) {
		setBackground(Color.white);
		
		items = new ArrayList<ListItem>();
		int size = orders.size();
		for (int i = 0; i < size; i++) {
			ListItem item = new ListItem(orders.get(i));
			items.add(item);
		}

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.fill = GridBagConstraints.BOTH;

//		gcons.insets = new Insets(0, 10, 0, 10);
		for (int i = 0; i < size; i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1,
					1, 1, 0);
		}
		
		
	}
	
	public List<GoodsVO> getGoodsVOs(){
		goods = new ArrayList<GoodsVO>();
		for(int i = 0;i<items.size();i++){
			GoodsVO vo = items.get(i).getGoodVO();
			if(vo!=null){
				goods.add(vo);
			}
		}
		return goods;
	}
	
	public void reLayout(){
		for(int i = 0;i<items.size();i++){
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1,
					1, 1, 0);
		}
	}
}
