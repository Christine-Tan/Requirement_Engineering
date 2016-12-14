package gap.client.ui.inventoryui.checkstock;

import gap.client.blcontroller.InventoryController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListItem extends JPanel {
	public Unit[] shelf;
	JLabel rowName;
	char r;
	GridBagLayout gb;
	GridBagConstraints gcons;
	public ListItem(int numOfshf, int idOfRow,String sector_id) {
		setBackground(Color.white);
		int width = Default.PANEL_WIDTH;
		width = Math.max(width, (Icon.width + 20) * (numOfshf + 2));
		setPreferredSize(new Dimension(width, 100));

		r = (char) ('A' + idOfRow);
		rowName = new GAPLabel(r + "排");
		rowName.setPreferredSize(new Dimension(40, 30));
		shelf = new Unit[numOfshf];

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.insets = new Insets(0, 10, 0, 10);
		gcons.anchor = GridBagConstraints.CENTER;
		SwingConsole.addComponent(gb, gcons, this, rowName, 0, 0, 1, 1, 1, 0);
		for (int i = 0; i <numOfshf; i++) {
			char s = (char) ('A' + i);
			String position = r+","+s;
			double ratio = InventoryController.getOneShelfRatio(position, sector_id);
			shelf[i] = new Unit(formatDouble(ratio));
//			shelf[i] = new Unit(9.0*(i+1));
			SwingConsole.addComponent(gb, gcons, this, shelf[i], i+1, 0, 1,
					1, 1, 0);

		}
		

		
		
	}

	public double formatDouble(double ratio) {
		BigDecimal b = new BigDecimal(ratio);
		ratio = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
		return ratio;
	}
	
//	public void flush(int numOfshf, int idOfRow,String sector_id){
//		removeAll();
		
//			for(int j = shelf[i].icon.height;j>shelf[i].icon.y;j--){
//				shelf[i].icon.i = j;
//				shelf[i].icon.repaint();
//				try {
//					Thread.sleep(50);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//	}

}
