package gap.client.ui.gapcomponents;

import gap.client.ui.UITools.ColorAndFonts;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;

public class GAPButton extends JButton {
	private Color defautBackGround, defautFont, pressFont, enterFont;

	public MouseListener listener;

	public GAPButton() {
		super();
		initial();
	}

	public GAPButton(String mess) {
		super(mess);
		initial();
	}

	public GAPButton(Icon icon) {
		super(icon);
		initial();
	}

	public GAPButton(String str, Icon icon) {
		super(str, icon);
		initial();
	}

	public void setDefautBackGroundColor(Color color) {
		defautBackGround = color;
	}

	public void setDefautFontColor(Color color) {
		defautFont = color;
	}

	public void setEnterFontColor(Color color) {
		enterFont = color;
	}

	public void setPressFontColor(Color color) {
		pressFont = color;
	}

	private void initial() {
		defautBackGround = Color.white;
		defautFont = ColorAndFonts.blue;
		pressFont = ColorAndFonts.otherDarkBulue;
		enterFont = ColorAndFonts.blue.darker();

		setFont(ComponentStyle.buttonFont);
		setBackground(defautBackGround);
		setForeground(defautFont);
		setBorder(BorderFactory.createEmptyBorder());
		setFocusable(false);
		setUI(new GAPButtonUI());

		listener = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				setBackground(defautBackGround);
				setForeground(defautFont);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				setForeground(pressFont);
				// setBackground(ColorAndFonts.blue);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				setForeground(defautFont);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				setForeground(enterFont);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
			}
		};
		addMouseListener(listener);
	}
}
