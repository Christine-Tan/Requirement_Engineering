package gap.client.ui.expressorderquery.components;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ArcAndString {
	QueryArc arc;
	StringInfo info;
	JPanel jp;

	public ArcAndString(String str, int x, int y, int type, JPanel jp) {
		this.jp = jp;
		arc = new QueryArc(x, y, type, jp);
		info = new StringInfo(str, arc.getEndPoint(), type, jp);
	}

	public void change() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				arc.change();
				while (arc.changThread.isAlive())
					;
				info.change(500, 100);
			}
		}).start();

	}

	public void draw(Graphics2D g2d) {
		arc.draw(g2d);
		info.draw(g2d);
	}
}
