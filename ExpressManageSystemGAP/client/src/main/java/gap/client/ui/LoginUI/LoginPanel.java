package gap.client.ui.LoginUI;

import gap.client.blcontroller.LoginController;
import gap.client.datacontroller.NetModule;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.WhiteExitButton;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.gapcomponents.GAPDialog;
import gap.client.vo.LoginVO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	private int border_width = 4;
	private int low = 0, high = 40;

	private JTextField userName = new LoginTextField("people");
	private LoginButton signInButton;
	private JPasswordField passwordField;
	// private LoginButton goBackButton = new LoginButton("goBack");
	private WhiteExitButton exitButton = new WhiteExitButton();
	private LoginAnimation animation;
	private LogoPanel logoPanel;
	private LoginFrame frame;
	// 渐变
	LinearGradientPaint paint;

	public LoginPanel(LoginFrame frame) {
		setLayout(null);
		setBounds(0, 0, 430, 330);
		// setOpaque(false);
		this.frame = frame;
		animation = new LoginAnimation(frame);
		logoPanel = new LogoPanel(frame);
		signInButton = new LoginButton("signIn", this);
		passwordField = new LoginPassword(signInButton, this);

		JLayeredPane layer = new JLayeredPane();
		layer.setBounds(0, 0, this.getWidth(), this.getHeight());
		add(layer);
		layer.add(userName);
		layer.add(passwordField);
		layer.add(animation, Integer.valueOf(10));
		animation.startAnimation();

		layer.add(logoPanel, Integer.valueOf(15));
		logoPanel.startAnimation();

		int signInX = getWidth() / 2 - 200 / 2;
		signInButton.setBounds(signInX, 280, 200, 35);
		exitButton.setBounds(400, 15, 15, 15);

		layer.add(signInButton);
		layer.add(exitButton, new Integer(20));
	}

	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = RenderSetter.OpenRender(g);

		g2d.clearRect(0, 0, getWidth(), getHeight());
		Color backColor = new Color(248, 248, 248);
		g2d.setColor(backColor);
		g2d.fillRoundRect(4, 4, getWidth() - 8, getHeight() - 8, 4, 4);

		g2d.setColor(ColorAndFonts.darkBlue);
		g2d.fillRect(4, 4, this.getWidth() - 8, 56);
		g2d.setColor(Color.white);
		g2d.setFont(ColorAndFonts.Chinese);
		g2d.drawString("GAP 快�?�物流系�?", 20, 40);

		int step = (high - low) / (border_width - 1);
		for (int i = 0; i < border_width; i++) {
			Color co = new Color(0, 0, 0, low + i * step);
			g2d.setColor(co);
			g2d.drawRoundRect(i, i, getWidth() - 2 * i, getHeight() - 2 * i, 4,
					4);
		}

	}

	public void closeEye() {
		// TODO Auto-generated method stub
		logoPanel.closeEye();
	}

	public void openEye() {
		// TODO Auto-generated method stub
		logoPanel.openEye();
	}

	public void login() {
		// TODO Auto-generated method stub
		String username_text = userName.getText();
		String password_text = new String(passwordField.getPassword());
		LoginVO log = LoginController.login(username_text, password_text);
		System.out.println(log.isSucceed());
		if (log.isSucceed()) {
			MainFrame mainFrame = new MainFrame();
			mainFrame.initial(log.getUserType());
			LoginFrame.setVi(false);
			NetModule.initial(mainFrame);
		} else {
			GAPDialog gapDia = new GAPDialog(frame);
			gapDia.setBounds(frame.getLocation().x + (frame.getWidth() - 400)
					/ 2, frame.getLocation().y + (frame.getHeight() - 250) / 2,
					400, 250);
			gapDia.showMessage("登录失败", "用户名或密码错误");
			userName.setText("");
			passwordField.setText("");
			userName.grabFocus();
		}

	}

}
