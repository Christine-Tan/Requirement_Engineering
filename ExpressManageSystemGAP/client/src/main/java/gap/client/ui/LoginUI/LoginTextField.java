package gap.client.ui.LoginUI;

import gap.client.ui.UITools.ColorAndFonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class LoginTextField extends JTextField implements TextInterface {

	Image image;
	Font font = ColorAndFonts.English;
	boolean hadFocused = false;

	public LoginTextField(String type) {
		setFont(font);
		image = new ImageIcon("images/login/" + type + ".png").getImage();
		setBounds(35, 190, 360, 30);
		setForeground(Color.gray);

		if (type == "people") {
			setText("Username");
		}
		new LoginTextListener(this);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 330, 5, null);
	}

	public boolean hadFocused() {
		// TODO Auto-generated method stub
		return hadFocused;
	}

	@Override
	public void setFocused(boolean focused) {
		// TODO Auto-generated method stub
		hadFocused = focused;
		if (!focused) {
			setForeground(Color.gray);
			setText("Username");
		}
	}

}
