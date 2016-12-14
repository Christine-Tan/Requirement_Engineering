package gap.client.ui.paymentUI.RewardPanels;

import gap.client.ui.UITools.RenderSetter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class RewardAddLabel extends JLabel{
	static Image addImage;
	static Image addSelectedImage;
	Image currentImage;
	AddListener addListener;
	
	PaymentRewardPanel rewardPanel;
	
	static {
		addImage = new ImageIcon("images/accountIcon/addSmaller.png").getImage();
		addSelectedImage = new ImageIcon("images/accountIcon/addSelectedSmaller.png")
				.getImage();
	}
	
	public RewardAddLabel(PaymentRewardPanel rewardPanel) {
		this.rewardPanel = rewardPanel;
		currentImage = addImage;
		addListener = new AddListener();
		addMouseListener(addListener);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
		if(currentImage==null){
			return ;
		}
		
		int imageWidth = currentImage.getWidth(null);
		int imageHeight = currentImage.getHeight(null);
		int width = getWidth();
		int height = getHeight();
		
		int x = (width-imageWidth)/2;
		int y = (height-imageHeight)/2;
		Graphics2D graphics2d = RenderSetter.OpenRender(g);
		
		graphics2d.drawImage(currentImage, x, y, null);
	}
	
	class AddListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			rewardPanel.addItem();
			rewardPanel.repaint();
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
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
//	public static void main(String[] args) {
//	
//	
//	JFrame frame = new JFrame("test");
//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	//frame.setLayout(null);
//	frame.setSize(800, 600);
//	
////	PayeeVO vo = new PayeeVO(PaymentType.BUSSINESSCLERK, "00110011",
////				"小花", null, 1000, "主账户", "营业厅业务员工资", "12月工资");
////	ArrayList<AccountVO> accountVOs = new ArrayList<>();
////	accountVOs.add(new AccountVO("账户111111111",8000));
////	accountVOs.add(new AccountVO("账户2",8000));
////	accountVOs.add(new AccountVO("账户3",8000));
////	PayeeItem payeeItem = new PayeeItem(vo, accountVOs);
//	RewardAddLabel label = new RewardAddLabel(null);
//	label.setPreferredSize(new Dimension(100, 100));
//	frame.add(label);
//	
//	frame.setVisible(true);
//
//
//}
}
