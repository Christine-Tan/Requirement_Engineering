package gap.server.ui.userList;

import gap.common.po.UserPO;
import gap.server.ui.ServiceLeftPanel;
import gap.server.ui.tools.ColorAndFonts;
import gap.server.ui.tools.PanelWithGrid;
import gap.server.ui.tools.SwingConsole;

import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;

public class UserPanel extends PanelWithGrid{
	private ArrayList<UserLabel> userPOList = new ArrayList<>();
	JLabel empty;
	public UserPanel(){
		gridBagConstraints.insets = new Insets(5, 10, 10, 5);
		
		empty = new JLabel("还没有用户登录哦~");
		empty.setFont(ColorAndFonts.getChinese(25));
		empty.setForeground(Color.DARK_GRAY);
		setBackground(ServiceLeftPanel.grayColor);
		
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, empty, 0, 0, 1, 1, 1, 1);
	}
	
	public void addUser(UserPO aUserPO,String IP){
		UserLabel userLabel = new UserLabel(aUserPO, IP);
		userPOList.add(userLabel);
		
		empty.setText("");
		remove(empty);
		gridBagLayout.removeLayoutComponent(empty);
		
		if(userPOList.size()==1){
			gridBagConstraints.insets = new Insets(10, 10, 10, 5);
		}else{
			gridBagConstraints.insets = new Insets(5, 10, 10, 5);
		}
		
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, userLabel,0, userPOList.size()-1, 1, 1, 1, 0);
		
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, empty, 0, userPOList.size(), 1, 1, 1, 1);
		
		Container container = getParent();
		if(container!=null){
			container.validate();
			container.repaint();
		}
	} 

}
