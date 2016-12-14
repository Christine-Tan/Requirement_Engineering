package gap.client.ui.initialUI.initialAccount;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.RenderSetter;
import gap.common.po.AccountPO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AccountBox extends JPanel{
	private static Image accountIcon;
	private AccountPO accountPO;
	private String imageURL = "images/accountIcon/account.png";

	private JLabel nameLabel;
	private JLabel balanceLabel;

	protected static int width = 155;
	protected static int height = 180;
	Color background = Color.white;
	
	MyListener listener = new MyListener();
	
	public AccountBox(AccountPO accountPO){

		this.accountPO = accountPO;
		
		
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		if(accountIcon==null)
		{
			accountIcon = new ImageIcon(imageURL).getImage();
		}	
		setOpaque(false);
		
		
		Font font = ColorAndFonts.getChinese(16);
		balanceLabel = new JLabel();
		balanceLabel.setHorizontalAlignment(JLabel.CENTER);
		balanceLabel.setFont(font);
		balanceLabel.setOpaque(false);
		
		nameLabel = new JLabel();
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setFont(font);
		nameLabel.setOpaque(false);
		
		nameLabel.setBounds(0, height-70, width, 25);
		balanceLabel.setBounds(0, height-40, width, 25);
		
		nameLabel.setText(accountPO.getName());
		balanceLabel.setText("余额： "+accountPO.getBalance());
		
		add(nameLabel);
		add(balanceLabel);	
		addMouseListener(listener);
	}
	

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D graphics2d = RenderSetter.OpenRender(g);
		
		graphics2d.setColor(background);
		graphics2d.fillRect(0, 10, this.getWidth(),this.getHeight()-20);
		
		graphics2d.drawImage
		(accountIcon, 0, -this.getWidth()/10, this.getWidth(), this.getWidth(), null);
	}
	
	public void mouseExit(){
		listener.mouseExited(null);
	}
	

	
	class MyListener implements MouseListener{

		Color gray = new Color(100, 100, 100, 50);
		Color red = new Color(251, 141, 158,100);
		Color white = Color.white;
		Rectangle rectangle = new Rectangle(0, 0, width-10, height-100);
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
	
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			background = gray;
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
				background = white;
				repaint();
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
