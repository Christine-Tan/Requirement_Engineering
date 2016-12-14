package gap.client.ui.expressorderquery.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class SmallCircle {

	private double out_ra, in_ra, tem_in_ra, tem_out_ra, x, y;
	private Color inColor, outColor;
	private Thread changeThread;
	private JPanel jp;

	public SmallCircle(double x, double y, double out_ra, double in_ra,
			JPanel jp) {
		inColor = Colors.orange;
		outColor = Color.white;
		this.jp = jp;
		this.x = x;
		this.y = y;
		this.out_ra = out_ra;
		this.in_ra = in_ra;
		tem_in_ra = 0;
		tem_out_ra = 0;
	}

	public void change(int time, int rate) {
		if (changeThread != null && changeThread.isAlive())
			changeThread.interrupt();
		changeThread = new Thread(new ChangeRunnable(time, rate));
		changeThread.start();
	}

	public void draw(Graphics2D g2d) {
		Color reColor = g2d.getColor();

		g2d.setColor(outColor);
		g2d.fill(new Ellipse2D.Double(x - tem_out_ra, y - tem_out_ra,
				tem_out_ra * 2, out_ra * 2));
		g2d.setColor(inColor);
		g2d.fill(new Ellipse2D.Double(x - tem_in_ra, y - tem_in_ra,
				tem_in_ra * 2, tem_in_ra * 2));

		g2d.setColor(reColor);
	}

	class ChangeRunnable implements Runnable {
		int changTime, changeRate;

		public ChangeRunnable(int changeTime, int changeRate) {
			// TODO 自动生成的构造函数存根
			this.changeRate = changeRate;
			this.changTime = changeTime;
		}

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			int deltaTime = changTime / changeRate;
			double deltaRa = in_ra / changeRate;
			tem_in_ra = 0;
			tem_out_ra = out_ra;
			for (int i = 0; i < changeRate; i++) {
				tem_in_ra += deltaRa;
				jp.repaint();
				try {
					Thread.sleep(deltaTime);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					tem_in_ra = in_ra;
					return;
				}
			}
		}

	}
}
