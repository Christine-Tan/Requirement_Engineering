package gap.client.ui.expressorderquery.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

/**
 * 一段弧线
 * 分上下两种类型
 * change方法可以让弧线缓慢画出
 * @author YangYanfei
 *
 */
public class QueryArc {
	public static final int UP = 0, DOWN = 1;
	int type;
	double changeTime, changeRate;
	double size;
	double fx, fy, fstart, fextent, sx, sy, sstart, sextent;

	Thread changThread;

	Arc2D.Double firstArc, secArc;
	JPanel jp;

	public QueryArc(int x, int y, int type, JPanel jp) {
		// TODO 自动生成的构造函数存根
		size = 20;
		changeTime = 500;
		changeRate = 500;
		this.jp = jp;
		this.type = type;
		if (type == 0) {
			fx = x - 2 * size;
			fy = y - size;
			fstart = 0;
			fextent = 90;
			sx = x - 2 * size;
			sy = y - 3 * size;
			sstart = 270;
			sextent = -90;
		} else {
			fx = x - 2 * size;
			fy = y - size;
			fstart = 0;
			fextent = -90;
			sx = x - 2 * size;
			sy = y + size;
			sstart = 90;
			sextent = 90;
		}

		firstArc = new Arc2D.Double(fx, fy, 2 * size, 2 * size, fstart, 0,
				Arc2D.Double.OPEN);
		secArc = new Arc2D.Double(sx, sy, 2 * size, 2 * size, sstart, 0,
				Arc2D.Double.OPEN);
	}

	public Point getEndPoint() {
		if (type == 0)
			return new Point((int) (fx), (int) (fy - size));
		else
			return new Point((int) (fx), (int) (fy + 3 * size));
	}

	public void change() {
		if (changThread != null && changThread.isAlive())
			changThread.interrupt();
		changThread = new Thread(new changeRunnable());
		changThread.start();
	}

	public void draw(Graphics2D g2d) {
		Color defautColor = g2d.getColor();
		Stroke defautStro = g2d.getStroke();

		Stroke newStrock = new BasicStroke(3);
		g2d.setStroke(newStrock);

		g2d.setColor(Colors.red);

		g2d.draw(firstArc);
		g2d.draw(secArc);

		g2d.setColor(defautColor);
		g2d.setStroke(defautStro);

	}

	class changeRunnable implements Runnable {

		int tempTime;
		double fdeltaEx, sdeltaEx;

		public changeRunnable() {
			tempTime = (int) (changeTime / changeRate);
			fdeltaEx = 2 * fextent / changeRate;
			sdeltaEx = 2 * sextent / changeRate;
		}

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			firstArc.extent = 0;
			secArc.extent = 0;
			for (int i = 0; i < changeRate / 2; i++) {
				firstArc.extent += fdeltaEx;
				jp.repaint();
				try {
					Thread.sleep(tempTime);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					firstArc.extent = fextent;
					secArc.extent = sextent;

					jp.repaint();
					return;
				}
			}

			for (int i = 0; i < changeRate / 2; i++) {
				secArc.extent += sdeltaEx;
				jp.repaint();
				try {
					Thread.sleep(tempTime);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					firstArc.extent = fextent;
					secArc.extent = sextent;
					return;
				}
			}
		}

	}

}
