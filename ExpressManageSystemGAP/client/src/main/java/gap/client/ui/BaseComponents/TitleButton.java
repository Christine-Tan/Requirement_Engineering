package gap.client.ui.BaseComponents;

import gap.client.ui.UITools.RenderSetter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TitleButton extends JLabel {

	Type type;
	JFrame frame;

	Image image;
	Image selectedImage;
	Image currentImage;

	public TitleButton(JFrame frame, Type type) {
		this.type = type;
		this.frame = frame;

		switch (type) {
		case close:

			image = new ImageIcon("images/login/exitBlack.png").getImage();
			selectedImage = new ImageIcon("images/login/exitSelected.png")
					.getImage();
			break;

		case iconified:

			image = new ImageIcon("images/login/iconified.png").getImage();
			selectedImage = new ImageIcon("images/login/iconifiedSelected.png")
					.getImage();
			break;
		}
		currentImage = image;

		setSize(15, 15);
		addMouseListener(new ButtonListener());

	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = RenderSetter.OpenRender(g);

		if (currentImage != null) {
			g2d.drawImage(currentImage, 0, 0, 15, 15, null);
		}

	}

	class ButtonListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (type == Type.close) {
//				frame.dispose();
				System.exit(1);
			} else if (type == Type.iconified) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
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

	public enum Type {
		close, iconified
	}

}
