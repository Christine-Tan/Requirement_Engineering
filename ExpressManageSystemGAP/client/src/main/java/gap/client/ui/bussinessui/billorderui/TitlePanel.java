package gap.client.ui.bussinessui.billorderui;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPTextField;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class TitlePanel extends JPanel {
	GAPTextField id, delivery_id, name, money, date;

	public TitlePanel() {
		// setBackground(Color.white);
		setOpaque(false);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 80));

		id = new GAPTextField("编号", 3);
		delivery_id = new GAPTextField("快递员编号", 7);
		name = new GAPTextField("快递员", 5);
		money = new GAPTextField("收款金额", 5);
		date = new GAPTextField("收款日期", 7);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.anchor = GridBagConstraints.EAST;
		gcons.insets = new Insets(40, 0, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, id, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(40, 55, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, delivery_id, 1, 0, 1, 1, 0,
				0);
		SwingConsole.addComponent(gb, gcons, this, name, 2, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, money, 3, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, date, 4, 0, 1, 1, 0, 0);
		closeEdit();
	}

	void closeEdit() {
		id.closeEdit();
		delivery_id.closeEdit();
		name.closeEdit();
		money.closeEdit();
		date.closeEdit();

		id.setHorizontalAlignment(JTextField.CENTER);
		delivery_id.setHorizontalAlignment(JTextField.CENTER);
		name.setHorizontalAlignment(JTextField.CENTER);
		money.setHorizontalAlignment(JTextField.CENTER);
		date.setHorizontalAlignment(JTextField.CENTER);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
