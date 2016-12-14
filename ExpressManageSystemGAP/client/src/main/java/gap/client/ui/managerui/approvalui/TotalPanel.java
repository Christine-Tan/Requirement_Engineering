package gap.client.ui.managerui.approvalui;

import gap.client.blcontroller.ApprovalController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.FlushButton;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TotalPanel extends JPanel {
	JLabel total, result;
	FlushButton refresh_b;
	protected static GAPTextField num_f;
	GridBagLayout gb;
	GridBagConstraints gcons;

	public TotalPanel() {
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));
		setBackground(Color.WHITE);
		total = new GAPLabel("一共有");
		result = new GAPLabel("张待审批单据");
		num_f = new GAPTextField(2);
		// 这里要自动刷新数据
		// 获得所有待审批单据
		List<Object> orders = ApprovalController.getUnpassedOrder();
		num_f.setText(String.valueOf(orders.size()));
		num_f.setHorizontalAlignment(JTextField.CENTER);
        num_f.closeEdit();
        
        refresh_b=new FlushButton();
        
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.insets = new Insets(20, 0, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, total, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(20, 0, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, num_f, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(20, 5, 10, 400);
		SwingConsole.addComponent(gb, gcons, this, result, 2, 0, 1, 1, 0, 0);
		gcons.insets=new Insets(20, 100, 10, 0);
		SwingConsole.addComponent(gb, gcons, this, refresh_b, 3, 0, 1, 1, 0, 0);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
