package gap.client.ui.inventoryui.observestock;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PeriodPanel extends JPanel {
	JLabel from, to,title;
	JButton confirm;
	GAPTextField beginDate, endDate;

	public PeriodPanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 80));
//		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ColorAndFonts.blue));
		
		title = new GAPLabel("日期：");
		
		from = new GAPLabel("from");
		to = new GAPLabel("to");
		confirm = new GAPButton("确认");
		confirm.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		beginDate = new GAPTextField(10);
		beginDate.setControl("\\d\\d\\d\\d-\\d\\d-\\d\\d", 10, 10);
		endDate = new GAPTextField(10);
		endDate.setControl("\\d\\d\\d\\d-\\d\\d-\\d\\d", 10, 10);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(50, 50));
		panel1.setBackground(null);
		panel1.setOpaque(false);

		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(50, 50));
		panel2.setBackground(null);
		panel2.setOpaque(false);
		
		gcons.insets = new Insets(10,-10,0,10);
		gcons.anchor = GridBagConstraints.EAST;
//		SwingConsole.addComponent(gb, gcons, this, panel1, 1, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, title, 0, 0, 1, 1, 1, 0);
		
		gcons.anchor = GridBagConstraints.EAST;
		gcons.insets = new Insets(10,-10,0,10);
		SwingConsole.addComponent(gb, gcons, this, from, 2, 0, 1, 1, 0.5, 0);
		gcons.anchor = GridBagConstraints.WEST;
		gcons.insets = new Insets(10,10,0,10);
		SwingConsole.addComponent(gb, gcons, this, beginDate, 3, 0, 1, 1, 0.5, 0);
		gcons.anchor = GridBagConstraints.EAST;
		gcons.insets = new Insets(10,-10,0,10);
		SwingConsole.addComponent(gb, gcons, this, to, 4, 0, 1, 1, 0.5, 0);
		gcons.anchor = GridBagConstraints.WEST;
		gcons.insets = new Insets(10,10,0,10);
		SwingConsole.addComponent(gb, gcons, this, endDate, 5, 0, 1, 1, 0.5, 0);
		gcons.anchor = GridBagConstraints.CENTER;
		gcons.insets = new Insets(10,-15,0,15);
		SwingConsole.addComponent(gb, gcons, this, confirm, 6, 0, 1, 1, 1, 0);
//		SwingConsole.addComponent(gb, gcons, this, panel2, 7, 0, 1, 1, 1, 0);
	}

	public String getBeginDate() {
		return beginDate.getText();
	}

	public String getEndDate() {
		return endDate.getText();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}

}
