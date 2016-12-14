package gap.client.ui.gapcomponents;

import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.expressorderquery.components.Colors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GAPDialog extends JDialog {

	private CloseIcon close;
	private DiaContentPanel contentpane;

	public GAPDialog() {
		super();
		initial();
	}

	public GAPDialog(JFrame jf) {
		super(jf);
		initial();
	}

	public GAPDialog(String title, JFrame jf) {
		super(jf, title);
		initial();
	}

	public void showMessage(String... mess) {
		if (!isVisible())
			setVisible(true);
		contentpane.showMessage(mess);
	}

	private void initial() {
		setUndecorated(true);

		contentpane = new DiaContentPanel();

		setContentPane(contentpane);

	}

	class DiaContentPanel extends JPanel {
		int row;
		String[] message;
		Font drawFont;

		public DiaContentPanel() {
			super();
			drawFont = new Font("黑体", Font.PLAIN, 25);
			setLayout(new BorderLayout());

			setBackground(Color.white);
			setBorder(BorderFactory.createLineBorder(
					new Color(0, 129, 182, 80), 2));

			JPanel jp = new JPanel();
			jp.setLayout(new FlowLayout(FlowLayout.RIGHT));
			close = new CloseIcon();
			close.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO 自动生成的方法存根

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO 自动生成的方法存根

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO 自动生成的方法存根

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO 自动生成的方法存根

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO 自动生成的方法存根
					dispose();
				}
			});
			jp.add(close);
			jp.setOpaque(false);
			add(BorderLayout.NORTH, jp);

		}

		public void showMessage(String... message) {
			row = message.length;
			this.message = message;
			repaint();
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = RenderSetter.OpenRender(g);
			g2d.setFont(drawFont);
			g2d.setColor(Colors.drakblue);
			int width = getWidth(), height = getHeight();
			FontMetrics fontM = g2d.getFontMetrics(drawFont);
			int fontHeight = fontM.getHeight();
			int y = (height - (message.length - 1) * fontHeight) / 2;
			int fontWidth, x;
			for (int i = 0; i < message.length; i++) {
				fontWidth = fontM.stringWidth(message[i]);
				x = (width - fontWidth) / 2;
				g2d.drawString(message[i], x, y + i * fontHeight);
			}
		}
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		GAPDialog gapDia = new GAPDialog("测试", jf);
		jf.setSize(50, 50);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gapDia.setVisible(true);
		gapDia.setBounds(200, 200, 400, 300);
		jf.setVisible(true);
		gapDia.showMessage("这是一个测试", "1515515");

	}
}
