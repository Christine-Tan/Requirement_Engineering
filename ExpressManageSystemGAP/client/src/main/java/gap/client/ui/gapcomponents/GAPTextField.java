package gap.client.ui.gapcomponents;

import gap.client.ui.UITools.GapTextControll;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GAPTextField extends JTextField {
	TextListener listener;
	boolean edited;
	Font font = ComponentStyle.defaultFont;

	public GAPTextField() {
		super();
		normal();
	}

	public GAPTextField(int column) {
		super(column);
		normal();
	}

	public GAPTextField(String str, int columns) {
		super(str);
		setColumns(columns);
		normal();
	}

	public GAPTextField(int column, Font font) {
		super(column);
		this.font = font;
		normal();
	}

	/**
	 * 设置字符框内的内容应该满足的格式
	 * @param regex
	 * @param length
	 */
	public void setControl(String regex, final int minLength,
			final int maxLength) {
		setDocument(new GapTextControll(regex, maxLength));
		listener.setControl(regex, maxLength, minLength);
	}

	public void setCenter() {
		setHorizontalAlignment(JTextField.CENTER);
	}

	// 警告样式
	public void alarm() {
		setBackground(ComponentStyle.red);
		setFont(font);
		setBorder(ComponentStyle.text_border);
		Container con = getParent();
		while (!(con instanceof JFrame)) {
			con = con.getParent();
		}
		con.validate();
		con.repaint();

	}

	// 获得焦点样式
	private void gainFocus() {
		setBackground(Color.white);
		// setFont(ComponentStyle.defaultFont);
		setFont(font);
		setBorder(ComponentStyle.focus_border);
	}

	// 普通样式
	private void normal() {
		if (listener == null) {
			listener = new TextListener();
			addFocusListener(listener);
			addMouseListener(listener);
		}
		edited = true;
		setBackground(Color.white);
		setBorder(ComponentStyle.text_border);
		setFont(font);
		// setHorizontalAlignment(JTextField.CENTER);
		validate();
	}

	public void toNormal() {
		normal();
	}

	public void toAlarm() {
		alarm();
	}

	// 关闭编辑样式
	public void closeEdit() {
		edited = false;
		setEditable(false);
		setFocusable(false);
		setBorder(BorderFactory.createEmptyBorder());
	}

	// 打开编辑样式
	public void openEdit() {
		edited = true;
		setEditable(true);
		setFocusable(true);
		normal();
	}
	
	protected TextListener getTextListener(){
		return listener;
	}

	class TextListener implements FocusListener, MouseListener {
		boolean controled;
		String regex;
		int maxLen, minLen;

		/**
		 * 默认为输入无要求
		 */
		public TextListener() {
			// TODO 自动生成的构造函数存根
			controled = false;
			maxLen = -1;
			minLen = -1;
			regex = null;
		}

		public void setControl(String regex, int maxLen, int minLen) {
			this.regex = regex;
			this.maxLen = maxLen;
			this.minLen = minLen;
			controled = true;
		}

		@Override
		public void focusGained(FocusEvent e) {
			// TODO 自动生成的方法存根
			gainFocus();
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO 自动生成的方法存根
			String text = getText();
			if (controled
					&& (minLen != -1 && maxLen != -1 && (text.length() < minLen || text
							.length() > maxLen))) {
				alarm();
			} else if (edited) {
				normal();
			} else {
				closeEdit();
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO 自动生成的方法存根

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO 自动生成的方法存根

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO 自动生成的方法存根

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO 自动生成的方法存根
			if (edited && !isFocusOwner()) {
				gainFocus();
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO 自动生成的方法存根
			if (edited && !isFocusOwner())
				normal();
		}
	}
}
