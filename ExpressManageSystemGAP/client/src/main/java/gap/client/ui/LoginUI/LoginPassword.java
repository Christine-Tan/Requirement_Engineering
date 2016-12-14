package gap.client.ui.LoginUI;

import gap.client.ui.UITools.ColorAndFonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class LoginPassword extends JPasswordField implements TextInterface {

	Image image;
	Font font = ColorAndFonts.English;
	boolean hadFoucsed = false;
	
	LoginPanel loginPanel;
	
	LoginButton loginButton;
	MyKeyListener keyListener;
	
	

	public LoginPassword(LoginButton loginButton,LoginPanel loginPanel) {
		this.loginButton = loginButton;
		this.loginPanel = loginPanel;
		image = new ImageIcon("images/login/lock.png").getImage();
		setEchoChar('\0');
		setBounds(35, 235, 360, 30);
		setFont(font);
		setForeground(Color.gray);
		setText("Password");
		new LoginTextListener(this);
		
		keyListener = new MyKeyListener();
		addKeyListener(keyListener);
		addFocusListener(keyListener);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 330, 5, null);
	}

	@Override
	public boolean hadFocused() {
		// TODO Auto-generated method stub
		return hadFoucsed;
	}

	@Override
	public void setFocused(boolean focused) {
		// TODO Auto-generated method stub
		hadFoucsed = focused;
		if (focused) {
			setEchoChar('●');
		} else {
			setEchoChar('\0');
			setForeground(Color.gray);
			setText("Password");
		}
	}

	class MyKeyListener implements KeyListener,FocusListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				loginButton.enterPressed(e);
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				loginButton.enterReleased(e);
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			loginPanel.closeEye();
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			loginPanel.openEye();
		}

	}

}
