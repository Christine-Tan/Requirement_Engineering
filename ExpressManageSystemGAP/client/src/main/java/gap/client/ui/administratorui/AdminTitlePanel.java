package gap.client.ui.administratorui;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminTitlePanel extends JPanel {
	JLabel user_id, user_name, password, name, user_type, gender, ins_name;

	public AdminTitlePanel() {
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));
		setBackground(Color.WHITE);
		user_id = new GAPLabel("用户ID");
		user_name = new GAPLabel("用户名");
		password = new GAPLabel("密码");

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(10, 20, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, user_id, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 130, 10, 10);
		SwingConsole.addComponent(gb, gcons, this, user_name, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 140, 10, 130);
		SwingConsole.addComponent(gb, gcons, this, password, 2, 0, 1, 1, 0, 0);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
