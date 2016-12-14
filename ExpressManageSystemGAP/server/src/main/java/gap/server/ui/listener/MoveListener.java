package gap.server.ui.listener;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class MoveListener implements MouseListener, MouseMotionListener {
	JFrame frame;
	int beforeX;
	int beforeY;
	int beforeFrameX;
	int beforeFrameY;
	boolean isPressed = false;

	public MoveListener(JFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isPressed) {
			int deltaX = e.getXOnScreen() - beforeX;
			int deltaY = e.getYOnScreen() - beforeY;

			frame.setLocation(beforeFrameX + deltaX, beforeFrameY + deltaY);
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getY() <= 40) {

			if (frame.getCursor().getType() == Cursor.NE_RESIZE_CURSOR
					|| frame.getCursor().getType() == Cursor.N_RESIZE_CURSOR) {
				return;
			}

			beforeX = e.getXOnScreen();
			beforeY = e.getYOnScreen();
			beforeFrameX = frame.getX();
			beforeFrameY = frame.getY();
			isPressed = true;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		isPressed = false;
	}

}
