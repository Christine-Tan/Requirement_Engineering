package gap.client.ui.managerui.logui;

import gap.client.blcontroller.LogController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.vo.LogVO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogQueryPanel extends JPanel {
	JLabel to_jl, total_jl, result_jl;
	GAPTextField fromdate_jl, todate_jl, num_jl;
	List<LogVO> logs;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	public LogQueryPanel() {
		// TODO Auto-generated constructor stub
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 80));
		logs = LogController.getLogList();
		to_jl = new GAPLabel("至");
		total_jl = new GAPLabel("共有");
		result_jl = new GAPLabel("条记录");
		fromdate_jl = new GAPTextField();
		fromdate_jl.setText(logs.get(logs.size() - 1).getDate().substring(0, 11));
		todate_jl = new GAPTextField();
		todate_jl.setText(logs.get(0).getDate().substring(0, 11));
		num_jl = new GAPTextField();
		num_jl.setText(String.valueOf(logs.size()));
		setStyle();

		// 布局
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.insets = new Insets(10, 100, 5, 10);
		SwingConsole.addComponent(gb, gcons, this, fromdate_jl, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 35, 5, 5);
		SwingConsole.addComponent(gb, gcons, this, to_jl, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 30, 5, 100);
		SwingConsole.addComponent(gb, gcons, this, todate_jl, 2, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(5, 300, 5, 0);
		SwingConsole.addComponent(gb, gcons, this, total_jl, 2, 1, 1, 1, 0, 0);
		gcons.insets = new Insets(5, 5, 5, 0);
		SwingConsole.addComponent(gb, gcons, this, num_jl, 3, 1, 1, 1, 0, 0);
		gcons.insets = new Insets(5, 5, 5, 100);
		SwingConsole.addComponent(gb, gcons, this, result_jl, 4, 1, 1, 1, 0, 0);
	}

	private void setStyle() {
		Font font = new Font("微软雅黑", 0, 23);
		to_jl.setFont(font);
		todate_jl.setFont(font);
		fromdate_jl.setFont(font);
		fromdate_jl.setHorizontalAlignment(JTextField.CENTER);
		todate_jl.setHorizontalAlignment(JTextField.CENTER);
		num_jl.setHorizontalAlignment(JTextField.CENTER);
		fromdate_jl.closeEdit();
		todate_jl.closeEdit();
		num_jl.closeEdit();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);
		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
}
