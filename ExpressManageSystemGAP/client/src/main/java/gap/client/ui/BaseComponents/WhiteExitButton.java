package gap.client.ui.BaseComponents;

import gap.client.ui.UITools.RenderSetter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class WhiteExitButton extends JLabel {

	Image image = new ImageIcon("images/login/exitWhite.png").getImage();
	Image selectedImage = new ImageIcon("images/login/exitSelected.png")
			.getImage();
	Image currentImage = image;

	public WhiteExitButton() {
		setSize(15, 15);
		// setBounds(960, 12, 15, 15);
		addMouseListener(new ExitListener());
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.drawImage(currentImage, 0, 0, 15, 15, null);

	}

	class ExitListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			currentImage = selectedImage;
			repaint();
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			currentImage = image;
			repaint();
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
