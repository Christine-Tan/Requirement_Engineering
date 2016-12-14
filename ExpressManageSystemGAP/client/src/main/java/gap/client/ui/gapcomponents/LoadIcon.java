package gap.client.ui.gapcomponents;

import gap.client.ui.UITools.RenderSetter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class LoadIcon extends JComponent {
	private int ball_r = 5, center_x, center_y, distance = 20;
	private static final int nums = 5;
	int num = 50;
	private static final double unit = Math.PI / nums;
	private Thread changeThead;

	Color darkColor = new Color(0, 150, 255);
	Ball[] balls = new Ball[5];

	public LoadIcon() {
		setPreferredSize(new Dimension(50, 50));
		for (int i = 0; i < balls.length; i++) {
			balls[i] = new Ball();
			balls[i].setLocation(i * num);
		}

		addMouseListener(new MouseAdapter() {
		});
		addMouseMotionListener(new MouseMotionAdapter() {
		});
		addKeyListener(new KeyAdapter() {
		});
		setFocusTraversalKeysEnabled(false);
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent evt) {
				requestFocusInWindow();
			}
		});

		changeThead = new Thread(new MoveRunnable());
		changeThead.setDaemon(true);
		changeThead.start();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = RenderSetter.OpenRender(g);
		center_x = getWidth() / 2;
		center_y = getHeight() / 2;
		// 使背景透明化
		g2d.setColor(new Color(1f, 1f, 1f, 0f));
		g2d.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < balls.length; i++) {
			balls[i].draw(g2d);
		}
	}

	class Ball {
		int current;

		void draw(Graphics2D g) {
			g.setColor(darkColor);
			int x = (int) (center_x + distance
					* Math.sin(-unit * current / num));
			int y = (int) (center_y + distance
					* Math.cos(-unit * current / num));
			Area area = new Area(new Ellipse2D.Double(x, y, ball_r, ball_r));
			g.fill(area);
		}
		
		void move() {
			if (num * nums < current && 2 * num * nums > current)
				current += 2;
			else
				current += 1;
			if (current > 2 * num * nums) {
				current -= 2 * num * nums;
			}
		}

		void setLocation(int location) {
			current = location;
		}
	}

	class MoveRunnable implements Runnable {

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while (true) {
				for (int i = 0; i < num; i++) {
					for (int j = 0; j < balls.length; j++) {
						balls[j].move();
					}
					repaint();
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}

	}

	// public static void main(String[] args) {
	// JFrame jf = new JFrame();
	// jf.setLayout(new FlowLayout());
	// jf.add(new LoadIcon());
	// SwingConsole.run(jf, 400, 300);
	// }

}
