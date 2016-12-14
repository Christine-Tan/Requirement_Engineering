package gap.server.ui;

import gap.common.po.UserPO;
import gap.server.ui.tools.ColorAndFonts;
import gap.server.ui.tools.PanelWithGrid;
import gap.server.ui.tools.SwingConsole;
import gap.server.ui.userList.UserPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ServiceLeftPanel extends PanelWithGrid{
	JLabel titleLabel;
	JScrollPane scrollPane;
	
	public static Color grayColor = Color.white;
	
	private static UserPanel userPanel;
	
	static{
		userPanel = new UserPanel();
	}
	
	public ServiceLeftPanel(){
		titleLabel = new JLabel("  当前用户：");
		titleLabel.setFont(ColorAndFonts.getChinese(17));
		titleLabel.setPreferredSize(new Dimension(150, 20));
		
		scrollPane = new JScrollPane(userPanel);
		setBackground(new Color(230, 230, 230));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.getViewport().setBackground(Color.white);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(10, 10, 10, 5);
		
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, titleLabel, 0, 0, 1, 1, 1, 0);
		
		gridBagConstraints.insets = new Insets(0, 2, 2, 2);
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, scrollPane, 0, 1, 1, 1, 1, 1);
		
		
	}
	
	public static void addUser(UserPO userPO,String IP){
		userPanel.addUser(userPO, IP);
	}
}
