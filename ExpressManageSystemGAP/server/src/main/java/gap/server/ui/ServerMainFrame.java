package gap.server.ui;


import gap.common.po.UserPO;
import gap.server.ui.listener.MoveListener;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ServerMainFrame extends JFrame{
	ServerRightPanel rightPanel;
	ServiceLeftPanel leftPanel;
	MoveListener moveListener;
	public ServerMainFrame(){
		
		try {
			UIManager
			.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setResizable(false);
		setUndecorated(true);
		setBackground(Color.white);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);

		moveListener = new MoveListener(this);
		addMouseListener(moveListener);
		addMouseMotionListener(moveListener);
		
		getContentPane().setLayout(null);
		
		rightPanel = new ServerRightPanel();
		rightPanel.setBounds(400, 0, 200, 400);
		getContentPane().add(rightPanel);
		
		leftPanel = new ServiceLeftPanel();
		leftPanel.setBounds(0, 0, 400, 400);
		getContentPane().add(leftPanel);
		
		
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void addUser(UserPO userPO,String IP){
		ServiceLeftPanel.addUser(userPO, IP);
	}
	
	//测试代码
//	public static void main(String[] args) {
//		ServerMainFrame frame = new ServerMainFrame();
//		frame.go();
//		
//		
//	}
//
//	private void go() {
//		UserPO userPO = new UserPO
//				("11111", "xiaoming", "11111", "小明", UserType.ACCOUNTER, Gender.FEMALE, "111111");
//		String IP = "192.168.0.0.1";
//		
//		ServiceLeftPanel.addUser(userPO, IP);
//		ServiceLeftPanel.addUser(userPO, IP);
//		ServiceLeftPanel.addUser(userPO, IP);
//		ServiceLeftPanel.addUser(userPO, IP);
//		ServiceLeftPanel.addUser(userPO, IP);
//		ServiceLeftPanel.addUser(userPO, IP);
//	}
}
