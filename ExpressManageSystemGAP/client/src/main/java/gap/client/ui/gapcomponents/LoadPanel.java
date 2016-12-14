package gap.client.ui.gapcomponents;

import gap.client.ui.UITools.RenderSetter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class LoadPanel extends JComponent {
	JFrame jframe;
	Thread loadThread;
	LoadIcon icon;
	ExecutorService excutor;

	public LoadPanel(JFrame jframe) {
		this.jframe = jframe;
		setLayout(new BorderLayout());
		icon = new LoadIcon();
		add(icon);
//		excutor = Executors.newCachedThreadPool();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(new Color(1f, 1f, 1f, 0.5f));
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}

	public void load(Runnable runnable) {
		//假如0.1秒没有load好，就开启load画面
		Timer loadTimer = new Timer();
		loadTimer.schedule(new LoadTask(), 100);
		
		runnable.run();
		loadTimer.cancel();
		setVisible(false);
		jframe.repaint();
		jframe.validate();
		
	}
	
	private class LoadTask extends TimerTask{
		@Override
		public void run() {
			setVisible(true);
			jframe.setGlassPane(LoadPanel.this);
		}
		
	}

}
