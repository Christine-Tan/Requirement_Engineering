package gap.client.ui.AccountUI;

import gap.client.ui.UITools.RenderSetter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class DeleteButton extends JLabel{

	static Image image = new ImageIcon("images/login/exitBlack.png").getImage();
	static Image selectedImage = new ImageIcon("images/login/exitSelected.png")
			.getImage();
	Image currentImage = image;
	AccountBox box;
	boolean isEnter = false;
	
	public DeleteButton(AccountBox box){
		this.box = box;
		setBounds(box.width-15,0,15,15);
		addMouseListener(new Listener());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D graphics2d = RenderSetter.OpenRender(g);
		graphics2d.drawImage(currentImage, 0, 0, this.getWidth(),this.getHeight(), null);
	}
	
	class Listener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			box.removeThisBox();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			isEnter = true;
			currentImage = selectedImage;
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			isEnter = false;
			currentImage = image;
			box.mouseExit();
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
	
}
