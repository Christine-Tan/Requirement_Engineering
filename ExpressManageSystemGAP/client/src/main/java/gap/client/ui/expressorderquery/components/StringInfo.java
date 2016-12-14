package gap.client.ui.expressorderquery.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StringInfo extends JLabel {
	float alpha;
	Color fontColor;
	Color backColor;

	public static int UP = 0, DOWN = 1;
	private int x, y, width, height;
	private static int defautWidth = 150;
	int rowNum, rowHeight, type;
	private FontMetrics fontM;
	private Font defautFont;
	private Point connetPoint;
	private Thread changeThread;
	String info;
	JPanel jp;

	public StringInfo(String info, Point connectPoint, int type, JPanel jp) {
		this.jp = jp;
		this.type = type;
		defautFont = new Font("微软雅黑", Font.BOLD, 15);
		this.info = info;
		this.connetPoint = connectPoint;
		fontM = getFontMetrics(defautFont);
		rowHeight = fontM.getHeight();

		fontColor = Colors.drakblue;
		backColor = new Color(Integer.parseInt("d5f3f4", 16));

		reLocat();
	}

	public void reLocat() {
		rowNum = fontM.stringWidth(info) / defautWidth + 1;
		x = connetPoint.x - defautWidth / 2;
		if (type == 0)
			y = connetPoint.y - rowNum * rowHeight;
		else
			y = connetPoint.y;
		width = defautWidth;
		height = rowHeight * rowNum;
		setBounds(x, y, width, height);

	}

	public void change(int time, int rate) {
		if (changeThread != null && changeThread.isAlive())
			changeThread.interrupt();
		changeThread = new Thread(new ChangeRunnable(time, rate));
		changeThread.start();
	}

	public void draw(Graphics2D g2d) {
		Font reFont = g2d.getFont();
		Color reColor = g2d.getColor();
		Composite recom = g2d.getComposite();

		AlphaComposite acom = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, alpha);
		g2d.setComposite(acom);
		g2d.setColor(fontColor);

		// g2d.setColor(backColor);
		// g2d.fillRect(x, y, defautWidth, height);

		g2d.setFont(defautFont);
		String handlingStr = info;
		int row = 1;
		while (handlingStr.length() != 0) {
			int strWidth = 0;
			String temStr = "";
			while (handlingStr.length() != 0
					&& ((strWidth + fontM.charWidth(handlingStr.charAt(0))) < defautWidth)) {
				temStr += handlingStr.charAt(0);
				handlingStr = handlingStr.substring(1);
				strWidth = fontM.stringWidth(temStr);
			}
			if (type == 0) {
				g2d.drawString(temStr, connetPoint.x - strWidth / 2, y + row
						* rowHeight - 5);
			} else {
				g2d.drawString(temStr, connetPoint.x - strWidth / 2, y + row
						* rowHeight - 5);
			}
			row++;
			temStr = "";
			strWidth = 0;
		}

		g2d.setColor(reColor);
		g2d.setFont(reFont);
		g2d.setComposite(recom);
	}

	class ChangeRunnable implements Runnable {
		int changeTime, changeRate;

		public ChangeRunnable(int changeTime, int changeRate) {
			this.changeRate = changeRate;
			this.changeTime = changeTime;
		}

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			int deltaTime = changeTime / changeRate;
			float changeDelta = 1f / changeRate;
			alpha = 0f;
			for (int i = 0; i < changeRate; i++) {
				alpha += changeDelta;
				jp.repaint();
				try {
					Thread.sleep(deltaTime);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					alpha = 1f;
					return;
				}
			}
		}

	}
}
