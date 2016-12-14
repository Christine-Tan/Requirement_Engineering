package gap.client.ui.BaseListener;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ResizeListener implements MouseListener, MouseMotionListener {
	JFrame frame;
	int ox, oy, frameWidth, frameHeight, frameX, frameY;
	boolean titleselected, se_resizeselected, e_resizeselected,
			s_resizeselected;

	Rectangle northEast = new Rectangle();
	Rectangle east = new Rectangle();
	Rectangle southEast = new Rectangle();
	Rectangle south = new Rectangle();
	Rectangle southWest = new Rectangle();
	Rectangle west = new Rectangle();
	Rectangle northWest = new Rectangle();
	Rectangle north = new Rectangle();
	// Map<Rectangle, Cursor> cursorMap = new HashMap<>(8);
	ArrayList<Rectangle> rectangles = new ArrayList<>(8);
	ArrayList<Cursor> cursors = new ArrayList<>(8);

	public ResizeListener(JFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		refrashLoaction();
		//rectangles.add(northEast);
		//rectangles.add(east);
		rectangles.add(southEast);
		rectangles.add(south);
		rectangles.add(southWest);
		rectangles.add(west);
		rectangles.add(northWest);
		rectangles.add(north);

		//cursors.add(new Cursor(Cursor.NE_RESIZE_CURSOR));
		//cursors.add(new Cursor(Cursor.E_RESIZE_CURSOR));
		cursors.add(new Cursor(Cursor.SE_RESIZE_CURSOR));
		cursors.add(new Cursor(Cursor.S_RESIZE_CURSOR));
		cursors.add(new Cursor(Cursor.SW_RESIZE_CURSOR));
		cursors.add(new Cursor(Cursor.W_RESIZE_CURSOR));
		cursors.add(new Cursor(Cursor.NW_RESIZE_CURSOR));
		cursors.add(new Cursor(Cursor.N_RESIZE_CURSOR));

		// cursorMap.put(northEast,new Cursor(Cursor.NE_RESIZE_CURSOR) );
		// cursorMap.put(east, new Cursor(Cursor.E_RESIZE_CURSOR) );
		// cursorMap.put(southEast,new Cursor(Cursor.NW_RESIZE_CURSOR) );
		// cursorMap.put(south, new Cursor(Cursor.S_RESIZE_CURSOR) );
		// cursorMap.put(southWest,new Cursor(Cursor.SW_RESIZE_CURSOR) );
		// cursorMap.put(west, new Cursor(Cursor.W_RESIZE_CURSOR) );
		// cursorMap.put(northWest,new Cursor(Cursor.NW_RESIZE_CURSOR) );
		// cursorMap.put(north, new Cursor(Cursor.N_RESIZE_CURSOR) );

	}

	private void refrashLoaction() {
		int width = frame.getWidth();
		int height = frame.getHeight();

		northEast.setBounds(width - 10, 0, 10, 10);
		east.setBounds(width - 10, 10, 10, height - 20);
		southEast.setBounds(width - 10, height - 10, 10, 10);
		south.setBounds(10, height - 10, width - 20, 10);
		southWest.setBounds(0, height - 10, 10, 10);
		west.setBounds(0, 10, 10, height - 20);
		northWest.setBounds(0, 0, 10, 10);
		north.setBounds(10, 0, width - 100, 10);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自动生成的方法存�?
		refrashLoaction();

		int cursorType = frame.getCursor().getType();

		int nowX = e.getXOnScreen();
		int nowY = e.getYOnScreen();

		switch (cursorType) {

		case Cursor.NE_RESIZE_CURSOR: {
			int newWidth = frameWidth + (nowX - ox);
			int newHeight = frameHeight + (oy - nowY);
			int newX = frameX + (nowX - ox);
			int newY = frameY + (nowY - oy);
			frame.setBounds(frameX, newY, newWidth, newHeight);
			break;
		}
		case Cursor.NW_RESIZE_CURSOR: {
			int newWidth = frameWidth + (ox - nowX);
			int newHeight = frameHeight + (oy - nowY);
			int newX = frameX + (nowX - ox);
			int newY = frameY + (nowY - oy);
			frame.setBounds(newX, newY, newWidth, newHeight);
			break;
		}
		case Cursor.SE_RESIZE_CURSOR: {
			int newWidth = frameWidth + (nowX - ox);
			int newHeight = frameHeight + (nowY - oy);

			frame.setBounds(frameX, frameY, newWidth, newHeight);
			break;
		}
		case Cursor.SW_RESIZE_CURSOR: {
			int newWidth = frameWidth + (ox - nowX);
			int newHeight = frameHeight + (-oy + nowY);
			int newX = frameX + (nowX - ox);
			int newY = frameY + (nowY - oy);
			frame.setBounds(newX, frameY, newWidth, newHeight);
			break;
		}
		case Cursor.N_RESIZE_CURSOR: {

			int newHeight = frameHeight + (oy - nowY);

			int newY = frameY + (nowY - oy);
			frame.setBounds(frameX, newY, frameWidth, newHeight);
			break;
		}
		case Cursor.S_RESIZE_CURSOR: {

			int newHeight = frameHeight + (-oy + nowY);
			frame.setBounds(frameX, frameY, frameWidth, newHeight);
			break;
		}
		case Cursor.W_RESIZE_CURSOR: {
			int newWidth = frameWidth + (ox - nowX);

			int newX = frameX + (nowX - ox);

			frame.setBounds(newX, frameY, newWidth, frameHeight);
			break;
		}
		case Cursor.E_RESIZE_CURSOR: {
			int newWidth = frameWidth + (nowX - ox);

			frame.setBounds(frameX, frameY, newWidth, frameHeight);
			break;
		}

		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自动生成的方法存�?
		refrashLoaction();

		for (int i = 0; i < rectangles.size(); i++) {
			if (rectangles.get(i).contains(e.getPoint())) {
				frame.setCursor(cursors.get(i));
				return;
			}
		}

		frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存�?

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存�?

		int width = frame.getWidth(), height = frame.getHeight();
		frameWidth = width;
		frameHeight = height;
		frameX = frame.getX();
		frameY = frame.getY();
		ox = e.getXOnScreen();
		oy = e.getYOnScreen();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存�?
		titleselected = false;
		se_resizeselected = false;
		e_resizeselected = false;
		s_resizeselected = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存�?

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存�?

	}
}
