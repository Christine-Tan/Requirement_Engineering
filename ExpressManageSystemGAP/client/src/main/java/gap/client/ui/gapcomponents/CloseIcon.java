package gap.client.ui.gapcomponents;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public
class CloseIcon extends JLabel {
	String normalPath = "images\\expressorderquery\\close.png",
			focusPath = "images\\expressorderquery\\close1.png";
	ImageIcon normal, focus;

	public CloseIcon() {
		normal = new ImageIcon(normalPath);
		focus = new ImageIcon(focusPath);
		setIcon(normal);
		setSize(25, 25);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				setIcon(focus);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				setIcon(normal);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				setIcon(normal);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				setIcon(focus);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
			}
		});
	}
}