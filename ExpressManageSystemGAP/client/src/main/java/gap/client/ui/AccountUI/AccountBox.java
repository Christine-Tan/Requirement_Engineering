package gap.client.ui.AccountUI;

import gap.client.ui.AccountUI.ComponentBehave.NameChangeBehave;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.ConvertString;
import gap.client.ui.UITools.RenderSetter;
import gap.client.vo.AccountVO;

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
	private AccountVO accountVO;
	private String imageURL = "images/accountIcon/account.png";

	private EditableLable nameLabel;
	NameChangeBehave nameChangeBehave;
	
	private JLabel balanceLabel = new JLabel();

	private DeleteButton deleteButton;
	protected static int width = 175;
	protected static int height = 220;
	Color background = Color.white;
	
	MyListener listener = new MyListener();
	
	AccountDisplayPanel accountDisplayPanel;
	AccountManagePanel managePanel;
	public AccountBox(AccountDisplayPanel accountDisplayPanel,
				AccountVO accountVO,AccountManagePanel managePanel){
		this.accountDisplayPanel = accountDisplayPanel;
		this.managePanel = managePanel;
		this.accountVO = accountVO;
		
		
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		if(accountIcon==null)
		{
			accountIcon = new ImageIcon(imageURL).getImage();
		}	
		setOpaque(false);
		
		//文字居中
		Font font = ColorAndFonts.getChinese(16);
		balanceLabel.setHorizontalAlignment(JLabel.CENTER);
		balanceLabel.setFont(font);
		balanceLabel.setOpaque(false);
		
		//创建可编辑label和它对应的行为
		nameLabel = new EditableLable();
		nameChangeBehave = new NameChangeBehave(managePanel, nameLabel, accountVO);
		nameLabel.setBehave(nameChangeBehave);
		
		nameLabel.setFont(font);
		
		nameLabel.setBounds(0, height-90, width, 25);
		balanceLabel.setBounds(0, height-60, width, 25);
		
		nameLabel.setText(accountVO.getName());
		String moneyString = ConvertString.getString(accountVO.getBalance());
		balanceLabel.setText("余额： "+moneyString);
		
		deleteButton = new DeleteButton(this);
		deleteButton.setVisible(false);
		
		add(nameLabel);
		add(balanceLabel);
		add(deleteButton);
		
		addMouseListener(listener);
	}
	

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D graphics2d = RenderSetter.OpenRender(g);
		
		graphics2d.setColor(background);
		graphics2d.fillRect(0, 0, this.getWidth(),this.getHeight()-90);
		
		graphics2d.drawImage
		(accountIcon, 0, -this.getWidth()/10, this.getWidth(), this.getWidth(), null);
	}
	
	public void mouseExit(){
		listener.mouseExited(null);
	}
	
	public void removeThisBox(){
		accountDisplayPanel.removeOneAccount(accountVO);
	}
	
	class MyListener implements MouseListener{

		Color gray = new Color(100, 100, 100, 50);
		Color red = new Color(251, 141, 158,100);
		Color white = Color.white;
		Rectangle rectangle = new Rectangle(0, 0, width-10, height-100);
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(background == red){
				background = gray;
				deleteButton.setVisible(false);
			}else if(background == gray){
				background = red;
				deleteButton.setVisible(true);
			}
			repaint();
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
			if(e==null || !rectangle.contains(e.getPoint())){
				background = white;
				deleteButton.setVisible(false);
				repaint();
			}
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
