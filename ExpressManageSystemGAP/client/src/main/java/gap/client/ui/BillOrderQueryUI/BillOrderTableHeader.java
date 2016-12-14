package gap.client.ui.BillOrderQueryUI;

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

public class BillOrderTableHeader extends JPanel {
	// 单据编号，类型，日期,金额
	JLabel id, type, date,money;
	// 全选


	boolean isSelected;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;
	public BillOrderTableHeader() {
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 40));
		setBackground(Color.WHITE);
        isSelected=false;
		id = new GAPLabel("单据编号");
		type = new GAPLabel("单据类型");
		date = new GAPLabel("生成日期");
		money = new GAPLabel("金额");

		
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.insets = new Insets(10, 100, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, id, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 173, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, type, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 110, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, date, 2, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 110, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, money, 3, 0, 1, 1, 0, 0);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
