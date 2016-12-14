package gap.client.ui.inventoryui.alarmvalueset;

import gap.client.blcontroller.InventoryController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.util.LocalInfo;
import gap.client.util.MessageType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class AlarmValueUI extends JPanel {
	public GAPTextField alarmValue;
	public Icon icon;

	public AlarmValueUI() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 500));
		
		double value = InventoryController.getAlarmValue(LocalInfo.getIns_ID());
		
		
		alarmValue = new GAPTextField(8,new Font("微软雅黑", Font.PLAIN, 60));
		alarmValue.setBorder(ComponentStyle.text_border);
		
		alarmValue.setHorizontalAlignment(JTextField.CENTER);
		alarmValue.setForeground(ComponentStyle.gray);
		alarmValue.setText(value + "%");

		icon = new Icon(value);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.insets = new Insets(10, 10, 10, 10);
		SwingConsole.addComponent(gb, gcons, this, icon, 0, 0, 1, 1, 0, 0);
		SwingConsole
				.addComponent(gb, gcons, this, alarmValue, 0, 1, 1, 1, 1, 0);
		icon.startAnimation();
	}

	public double getAlarmValue() {
		String newValue = alarmValue.getText();
		newValue = newValue.replace("%", "");
		double value = 0;
		try{
			value = Double.parseDouble(newValue);
		}catch(Exception e){
			MainFrame.setMessage("非数字输入", MessageType.alram, 2000);
		}
		return value;
	}
}
	
//	class ValueUI extends JPanel{
//		Icon icon;
//		public ValueUI(){
//			icon = new Icon()
//		}
//			
//	}
	
//	class Icon extends JPanel {
//		int height = 300;
//		int width = 200;
//		double usedRatio;
//		int y;
//		int i;
//
//		public Icon(double ratio) {
//			usedRatio = ratio;
//			setBackground(Color.white);
//			setPreferredSize(new Dimension(width + 4, height + 4));
//			setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
//					ComponentStyle.light_gray));
//			double y1 = height * ((100 - usedRatio) / 100);
//			y = (int) y1;
//			i = height;
//			System.out.println(y);
//
//		}
//		
//		public void paint(){
//			
//			for(int j = height;j>y;j--){
//				i = j;
//				repaint();
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		public void startAnimation(){
//			new PaintThread().start();
//		}
//		
//
//		public void paintComponent(Graphics g) {
//			super.paintComponent(g);
//			Graphics2D g2d = RenderSetter.OpenRender(g);
//			g2d.setColor(ComponentStyle.blue);
//
////				g2d.fillRect(2, y + 2, width, height - y);
//
//			g2d.fillRect(2, i + 2, width, height - i);
//		}
//		
//		
//		class PaintThread extends Thread{
//			
//		
//			public void run() {
//				paint();
//			}
//		
//		}
//	}

