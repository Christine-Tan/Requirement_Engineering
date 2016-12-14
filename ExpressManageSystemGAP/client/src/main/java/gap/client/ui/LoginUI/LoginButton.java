package gap.client.ui.LoginUI;

import gap.client.ui.UITools.AreaMaker;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.RenderSetter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;

import javax.swing.JLabel;

public class LoginButton extends JLabel {

	Color currentColor = ColorAndFonts.blue;
	Font font = ColorAndFonts.Chinese;
	Area area;
	String text;
	Mylistener mylistener;
	LoginPanel loginPanel;

	public LoginButton(String type,LoginPanel loginPanel) {
		switch (type) {
		case "signIn":
			text = "登录";
			break;
		case "goBack":
			text = "返回";
			break;
		}

	
		setSize(200, 35);
		setForeground(Color.white);
		setOpaque(false);
		setHorizontalAlignment(JLabel.CENTER);
		setFont(font);
		setText(text);

		this.loginPanel = loginPanel;
		
		requestFocusInWindow();
		area = AreaMaker.getRoundRect(getWidth(), getHeight(), 8);
		mylistener = new Mylistener();
		addMouseListener(mylistener);
		addKeyListener(mylistener);
	}

	public void enterPressed(KeyEvent e) {
		mylistener.keyPressed(e);
	}

	public void enterReleased(KeyEvent e) {
		mylistener.keyReleased(e);
	}

	public void paintComponent(Graphics g) {
		// �?特效
		Graphics2D g2d = RenderSetter.OpenRender(g);

		g2d.setColor(currentColor);
		g2d.fill(area);
		super.paintComponent(g2d);

	}

	class Mylistener implements MouseListener, KeyListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			requestFocusInWindow();
			loginPanel.login();
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			currentColor = ColorAndFonts.darkBlue;
			repaint();
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			currentColor = ColorAndFonts.blue;
			repaint();
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			currentColor = ColorAndFonts.lightBlue;
			repaint();
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			currentColor = ColorAndFonts.blue;
			repaint();
		}

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				mouseEntered(null);
				mouseClicked(null);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				mouseExited(null);
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
