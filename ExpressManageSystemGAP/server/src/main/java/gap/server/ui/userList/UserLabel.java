package gap.server.ui.userList;

import gap.common.po.UserPO;
import gap.common.util.UserType;
import gap.server.ui.tools.AreaMaker;
import gap.server.ui.tools.ColorAndFonts;
import gap.server.ui.tools.RenderSetter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UserLabel extends JLabel{
	
	UserPO userPO;
	String IP;
	private static Image manImage = null;
	private static Image womanImage = null;
	private static HashMap<UserType, String> jobMap;


		
	private Image currentImage = null;
	private String job;
	private Area roundRect;
	private static int width = 350;
	private static int height = 60;
	
	private Color selectedColor = new Color(250, 200, 213);
	private Color normalColor = new Color(235, 235, 235);
	private Color currentColor = normalColor;
	
	static{
		manImage = new ImageIcon("images/manPhoto.png").getImage();
		womanImage = new ImageIcon("images/womanPhoto.png").getImage();
		jobMap = new HashMap<>();
		jobMap.put(UserType.ACCOUNTER, "财务人员");
		jobMap.put(UserType.ADMINISTRATOR, "管理员");
		jobMap.put(UserType.BUSSINESSCLERK, "营业厅业务员");
		jobMap.put(UserType.CENTERCLERK, "中心业务员");
		jobMap.put(UserType.DELIVERY, "快递员");
		jobMap.put(UserType.INVENTORY, "仓库管理员");
		jobMap.put(UserType.MANAGER, "总经理");
	}
	
	public UserLabel(UserPO userPO,String IP) {
		this.userPO = userPO;
		this.IP = IP;
		setPreferredSize(new Dimension(width, height));
		
		switch(userPO.getGender()){
			case MALE:
				currentImage = manImage;
				break;
			case FEMALE:
				currentImage = womanImage;
				break;
		}
		
		job = jobMap.get(userPO.getType());
		roundRect = AreaMaker.getRoundRect(width, height, 10);
		addMouseListener(new UserListener());
	}
	
	public UserPO getUserPO(){
		return userPO;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		int[] lineHeight = {22,50};
		
		Graphics2D graphics2d = RenderSetter.OpenRender(g);
		
		graphics2d.setColor(currentColor);
		graphics2d.fill(roundRect);
		
		graphics2d.drawImage(currentImage, 6, 3, null);
		
		graphics2d.setColor(new Color(117, 208, 80));
		graphics2d.setStroke(new BasicStroke(2.5f));
		graphics2d.drawLine(65, 0, 65, getHeight());
		
		graphics2d.setColor(Color.black);
		graphics2d.setStroke(new BasicStroke(1.0f));
		
		graphics2d.setFont(ColorAndFonts.getChinese(16));
		
		graphics2d.drawString("IP："+IP, 75, lineHeight[0]+1);
		
		graphics2d.drawString("账号："+userPO.getUserName(), 210, lineHeight[0]);
		graphics2d.drawString("姓名："+userPO.getName(), 75, lineHeight[1]);
		graphics2d.drawString("职务："+job, 210, lineHeight[1]);
		
	}
	
	class UserListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			currentColor = selectedColor;
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			currentColor = normalColor;
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(500, 500);
//		frame.getContentPane().setLayout(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		UserPO userPO = new UserPO
//				("11111", "xiaoming", "11111", "小明", UserType.ACCOUNTER, Gender.FEMALE, "111111");
//		
//		UserLabel userLabel = new UserLabel(userPO, "192.168.0.0.1");
//		userLabel.setBounds(20, 20,width, height);
//		frame.getContentPane().setBackground(Color.gray);
//		frame.getContentPane().add(userLabel);
//		frame.setVisible(true);
//	}
//	
}
