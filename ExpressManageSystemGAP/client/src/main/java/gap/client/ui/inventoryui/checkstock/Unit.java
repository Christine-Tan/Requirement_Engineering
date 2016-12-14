package gap.client.ui.inventoryui.checkstock;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Unit extends JPanel {
	public Icon icon;
	JLabel value;

	public Unit(double ratio) {

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(icon.width + 4, icon.height + 25));

		icon = new Icon(ratio);
		icon.startAnimation();
		String r = String.valueOf(ratio);
		value = new GAPLabel(r + "%");

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		gcons.anchor = GridBagConstraints.CENTER;
		// gcons.fill = GridBagConstraints.HORIZONTAL;
		SwingConsole.addComponent(gb, gcons, this, icon, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, value, 0, 2, 1, 1, 1, 0);
//		for(int i = icon.height;i>icon.y;i--){
//			icon.i = i;
//			icon.repaint();
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		Unit unit = new Unit(50);
		jf.getContentPane().add(unit);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(500, 500);
		jf.setVisible(true);
		
		for(int i = unit.icon.height;i>unit.icon.y;i--){
			unit.icon.i = i;
			unit.icon.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}
}
