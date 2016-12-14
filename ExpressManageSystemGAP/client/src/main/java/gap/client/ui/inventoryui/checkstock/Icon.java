package gap.client.ui.inventoryui.checkstock;

import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.gapcomponents.ComponentStyle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Icon extends JPanel {
	static int height = 60;
	static int width = 60;
	double usedRatio;
	int y;
	int i;

	public Icon(double ratio) {
		usedRatio = ratio;
		setBackground(Color.white);
		setPreferredSize(new Dimension(width + 4, height + 4));
		setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
				ComponentStyle.light_gray));
		initial(ratio);

	}
	
	public void initial(double ratio){
		double y1 = height * ((100 - usedRatio) / 100);
		y = (int) y1;
		i = height;
	}
	
	public void paint(){
		
		for(int j = height;j>y;j--){
			i = j;
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void startAnimation(){
		new PaintThread().start();
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.blue);

//			g2d.fillRect(2, y + 2, width, height - y);

		g2d.fillRect(2, i + 2, width, height - i);
	}
	
	
	class PaintThread extends Thread{
		
	
		public void run() {
			paint();
		}
	
	}

//	public static void main(String[] args) {
//		JFrame jf = new JFrame();
//		Icon icon = new Icon(50);
//		JPanel p = new JPanel();
//		p.add(icon);
//		jf.getContentPane().add(p);
//		jf.setSize(300, 300);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.setVisible(true);
//		for(int i = icon.height;i>icon.y;i--){
//			icon.i = i;
//			icon.repaint();
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////		}
//		icon.paint();
//		
//
//	}
	
	

}
