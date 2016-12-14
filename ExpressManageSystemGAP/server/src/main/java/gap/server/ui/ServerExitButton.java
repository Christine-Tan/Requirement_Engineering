package gap.server.ui;

import gap.server.ui.tools.AreaMaker;
import gap.server.ui.tools.ColorAndFonts;
import gap.server.ui.tools.RenderSetter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ServerExitButton extends JButton{
	Font font = ColorAndFonts.getChinese(18);
	
	Color normalColor = new Color(200, 200, 200);
	Color selectColor = ColorAndFonts.orange;
	Color currentColor = normalColor;
	
	public ServerExitButton() {
		setFont(font);
		setForeground(Color.white);
		setBorder(BorderFactory.createEmptyBorder());
		setOpaque(false);
		addMouseListener(new ExitListener());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Area roundRect = AreaMaker.getRoundRect(getWidth(), getHeight(), 10);
		Graphics2D graphics2d = RenderSetter.OpenRender(g);
		graphics2d.setColor(currentColor);
		graphics2d.fill(roundRect);
		graphics2d.setFont(font);
		graphics2d.setColor(Color.black);
		graphics2d.drawString("关闭服务", 23, 21);

	}
	
	class ExitListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			System.exit(0);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			currentColor = selectColor;
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			currentColor = normalColor;
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
	}
}
