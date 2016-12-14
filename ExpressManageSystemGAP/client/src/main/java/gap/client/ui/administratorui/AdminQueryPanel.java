package gap.client.ui.administratorui;

import gap.client.blcontroller.UserController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.common.util.UserType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminQueryPanel extends JPanel {
	
	JLabel userid_jl;
	protected static JTextField userid_jt;
	protected static JButton search;
	protected static JLabel total;
	int resultNum;
	public AdminQueryPanel(UserType userType) {
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 80));
		setBackground(Color.WHITE);
		userid_jl = new GAPLabel("用户ID：");
		userid_jt = new GAPTextField(9);
		search = new GAPButton("搜索");
        search.setFont(ComponentStyle.defaultFont);
		resultNum = UserController.getAll(userType).size();
		total = new JLabel("一共有" + resultNum + "条结果");
	
		// 布局
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.insets = new Insets(40, 0, 5, 100);
		SwingConsole.addComponent(gb, gcons, this, total, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 300, 35, 0);
		SwingConsole.addComponent(gb, gcons, this, userid_jl, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 5, 35, 0);
		SwingConsole.addComponent(gb, gcons, this, userid_jt, 2, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 5, 35, 10);
		SwingConsole.addComponent(gb, gcons, this, search, 3, 0, 1, 1, 0, 0);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
