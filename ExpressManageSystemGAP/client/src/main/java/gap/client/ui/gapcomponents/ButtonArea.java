package gap.client.ui.gapcomponents;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author YangYanfei
 * 主面板的按钮区域
 */
public class ButtonArea extends JPanel {
	public JButton submit;
	private int buttonIndex = 1;
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints gcons = new GridBagConstraints();
	
	public ButtonArea() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 60));
		submit = new GAPButton("提交订单");
		setLayout(gb);
		
		//用来占位的label
		JLabel label = new JLabel();
		gcons.gridx = 0;
		gcons.gridy = 0;
		gcons.gridheight = 1;
		gcons.gridwidth = 1;
		gcons.weightx = 1;
		gcons.weighty = 0;
		gb.setConstraints(label, gcons);
		super.add(label);
		
		add(submit);

	}
	
	public Component add(Component component){
		gcons.insets = new Insets(10, 10, 10, 50);
		gcons.anchor = GridBagConstraints.EAST;	
		gcons.gridx = buttonIndex;
		gcons.gridy = 0;
		gcons.gridheight = 1;
		gcons.gridwidth = 1;
		gcons.weightx = 0;
		gcons.weighty = 0;
		gb.setConstraints(component, gcons);
		super.add(component);
		
		buttonIndex++;
		return component;
	}
	

	public void removeAll(){
		buttonIndex = 1;
		super.removeAll();
		JLabel label = new JLabel();
		gcons.gridx = 0;
		gcons.gridy = 0;
		gcons.gridheight = 1;
		gcons.gridwidth = 1;
		gcons.weightx = 1;
		gcons.weighty = 0;
		gb.setConstraints(label, gcons);
		super.add(label);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, 5, width - 20, 5);
	}
}
