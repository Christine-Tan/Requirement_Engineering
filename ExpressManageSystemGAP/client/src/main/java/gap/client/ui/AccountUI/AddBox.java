package gap.client.ui.AccountUI;

import gap.client.ui.UITools.RenderSetter;
import gap.client.vo.AccountVO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * 添加账户的按钮
 * @author 申彬
 *
 */
public class AddBox extends JPanel{

    static Image addImage;
    static Image addSelectedImage;
    static Image addClickedImage;
    
    private Image currentImage;
    private String addURL = "images/accountIcon/add.png";
    private String addSelectedURL = "images/accountIcon/addSelected.png";
    private String addClickedURL = "images/accountIcon/addClicked.png";
    private DefaultText_Field nameField;
    private DefaultText_Field balanceField;
    private int width = AccountBox.width;
    private int height = AccountBox.height;
    
    private ConfirmButton confirmButton;
    private CancelButton cancelButton;
    
    private double rate = 1;
    private AddListener listener = new AddListener();
    
    private AddBoxPanel addPanel;
    
    private boolean isClicked = false;
    
    private AccountManagePanel managePanel;
    private AccountDisplayPanel displayPanel;
    
    int imageSize = 115;
    
	public AddBox(AccountManagePanel managePanel,AccountDisplayPanel displayPanel){
		this.managePanel = managePanel;
		this.displayPanel = displayPanel;
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		setOpaque(false);
		if(addImage==null){
			addImage = new ImageIcon(addURL).getImage();
		}
		
		if(addSelectedImage == null){
			addSelectedImage = new ImageIcon(addSelectedURL).getImage();
		}
		
		if(addClickedImage == null){
			addClickedImage = new ImageIcon(addClickedURL).getImage();
		}
		
		currentImage = addImage;
		
		addMouseListener(listener);
		nameField = new DefaultText_Field("账户名称");
		balanceField = new DefaultText_Field("账户余额");
		
		confirmButton = new ConfirmButton(this);
		cancelButton = new CancelButton(this);
		
		confirmButton.setLocation(0, 140);
		cancelButton.setLocation(100, 140);
		
		
		nameField.setControl("\\.+", 1, 20);
		
		//setControl好反人类啊！这里是浮点数的取反
		balanceField.setControl("[^(\\d+(\\.\\d+)?)]", 1, 20);
		nameField.setBounds(0, height-190, width, 25);
		balanceField.setBounds(0, height-140, width, 25);
		
		nameField.setText("账户名称");
		balanceField.setText("账户余额");
		
		addPanel = new AddBoxPanel(confirmButton, cancelButton);		
		addPanel.setBackground(Color.white);
		addPanel.setBounds(0, 0, width, height);
		addPanel.setLayout(null);
		addPanel.add(nameField);
		addPanel.add(balanceField);
		addPanel.add(confirmButton);
		addPanel.add(cancelButton);
		

	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
		int x = (this.getWidth()-imageSize)/2;
		int y = (this.getHeight()-imageSize)/2 -30;
		
		Graphics2D graphics2d = RenderSetter.OpenRender(g);		
		graphics2d.drawImage(currentImage, x, y, imageSize, imageSize, null);
		super.paintComponent(graphics2d);
		
	}
	
	public void confirm(){
		String name = nameField.getText();
		double balance = 0;
		try {
			balance = Double.parseDouble(balanceField.getText());
		} catch (Exception e) {
			balanceField.setText("");
			balanceField.toAlarm();
			return;
		}
		
		AccountVO vo = new AccountVO(name, balance);
		managePanel.addAccount(vo, this);
		
	}
	
	public void cancel(){
		//设置失焦状态
		nameField.setFocused(false);
		balanceField.setFocused(false);
		//去掉可能的红色背景
		nameField.toNormal();
		balanceField.toNormal();
		
		//按钮样式变回正常
		addPanel.reset();
		remove(addPanel);
		repaint();
	}
	
	public void nameRepeat(){
		nameField.setFocused(false);
		balanceField.setFocused(false);
	
		nameField.toNormal();
		balanceField.toNormal();
	}

//	public static void main(String[] args) {
//		JFrame frame = new JFrame("test");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLayout(null);
//		frame.setSize(300, 300);
//		AddBox box = new AddBox();
//		box.setSize(220, 175);
//		
//		frame.add(box);
//		frame.setVisible(true);
//		
//		
//	}	
	
	class AddListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			new Thread(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					add(addPanel);
				}
			}.start();
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			currentImage = addSelectedImage;
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			currentImage = addImage;
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			currentImage = addClickedImage;
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			currentImage = addSelectedImage;
			repaint();
		}
		
		
	}
	

}
