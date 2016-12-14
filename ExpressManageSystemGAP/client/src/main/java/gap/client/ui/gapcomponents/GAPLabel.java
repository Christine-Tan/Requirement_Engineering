package gap.client.ui.gapcomponents;

import java.awt.Color;

import javax.swing.JLabel;

public class GAPLabel extends JLabel {
	public GAPLabel() {
		super();
		initial();
	}

	public GAPLabel(String str) {
		super(str);
		initial();
	}

	private void initial() {
		setOpaque(true);
		setFont(ComponentStyle.defaultFont);
//		setFont(new Font("微软雅黑",Font.BOLD,15));
		setBackground(Color.white);
		setForeground(ComponentStyle.dark_gray.brighter());
	}
}
