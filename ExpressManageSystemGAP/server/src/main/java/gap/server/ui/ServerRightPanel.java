package gap.server.ui;

import gap.server.ui.tools.ColorAndFonts;
import gap.server.ui.tools.IPGetter;
import gap.server.ui.tools.LoginAnimation;
import gap.server.ui.tools.PanelWithGrid;
import gap.server.ui.tools.SwingConsole;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class ServerRightPanel extends PanelWithGrid{
	JLayeredPane layer;
	LoginAnimation animation;
	ServerExitButton exitButton;
	JLabel welcomeLabel;
	JLabel welcomeShadow;
	
	public ServerRightPanel(){
		setBackground(Color.white);
		layer = new JLayeredPane();
		animation = new LoginAnimation();
		exitButton = new ServerExitButton();
		
		
		String ip = IPGetter.getIP();
		welcomeLabel = new JLabel
				("<html>大家好(●—●)<br><br/>我是可爱的服务器<br/><br/>我家在:"+ip+"</html>");
		welcomeLabel.setFont(ColorAndFonts.getChinese(19));
		welcomeLabel.setForeground(Color.white);
		welcomeLabel.setBounds(10, 110, 200, 130);
		
		int shadow = 2;
		welcomeShadow = new JLabel(welcomeLabel.getText());
		welcomeShadow.setFont(ColorAndFonts.getChinese(19));
		welcomeShadow.setForeground(new Color(80, 80, 80));
		welcomeShadow.setBounds(10+shadow, 110+shadow, 200, 130);
		
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, layer, 0, 0, 1, 1, 1, 1);
		
		exitButton.setBounds(40, 300, 120, 30);
		layer.setLayout(null);
		layer.add(animation, Integer.valueOf(0));
		layer.add(welcomeLabel,Integer.valueOf(2));
		layer.add(welcomeShadow,Integer.valueOf(1));
		layer.add(exitButton, Integer.valueOf(1));
		
		animation.startAnimation();
		
	}


}
