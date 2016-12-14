package gap.client.ui.gapcomponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GAPCheckBox extends JButton {
	boolean selected;

	public GAPCheckBox() {
		super();
		initial();
	}

	void initial() {
		setPreferredSize(new Dimension(17, 17));
		setForeground(Color.white);
		setFont(new Font("黑体", Font.BOLD, 15));
		setFocusPainted(false);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				focus();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				pressed();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				normal();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				focus();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				if (selected) {
					selected = false;
				} else {
					selected = true;
				}
				normal();
				focus();
			}
		});
		normal();
	}

	// 正常样式
	void normal() {
		if (selected) {
			setText("√");
			setBackground(ComponentStyle.blue);
			setBorder(BorderFactory.createEmptyBorder());
		} else {
			setText("");
			setBorder(BorderFactory.createLineBorder(ComponentStyle.gray
					.brighter()));
			setBackground(Color.white);
		}
	}

	// 取得焦点样式
	void focus() {
		if (selected) {
			setBackground(ComponentStyle.blue.brighter());
			setBorder(BorderFactory.createEmptyBorder());
		} else {
			setBorder(ComponentStyle.focus_border);
			setBackground(Color.white);
		}
	}

	// 按下样式
	void pressed() {
		if (selected) {
			setBackground(ComponentStyle.blue);
			setBorder(BorderFactory.createEmptyBorder());
		} else {
			// setBorder(BorderFactory.createLoweredBevelBorder());
			setBackground(Color.white);
		}
	}

	public void setSelected(boolean bool) {
		selected = bool;
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.add(new GAPCheckBox());
		jf.getContentPane().setLayout(new FlowLayout());
		jf.setBounds(500, 500, 200, 100);
		jf.setVisible(true);
	}
}
