package gap.client.ui.gapcomponents;

import gap.client.ui.UITools.ColorAndFonts;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ChooseButton extends JButton {

	public MouseListener listener;
	public boolean clicked;
	
	private JPanel panel;
	public ChooseButton(String text,JPanel panel){
		super(text);
		this.setPanel(panel);
		initial();
	}
	
	public ChooseButton() {
		super();
		initial();
	}

	public ChooseButton(String mess) {
		super(mess);
		initial();
	}

	public ChooseButton(Icon icon) {
		super(icon);
		initial();
	}

	public ChooseButton(String str, Icon icon) {
		super(str, icon);
		initial();
	}

	private void initial() {
//		clicked = false;
//		setFont(ComponentStyle.buttonFont);
		setFont(ColorAndFonts.getChinese(20));
		
		setBackground(Color.white);
		setForeground(ColorAndFonts.blue);
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		setFocusable(false);
		setUI(new GAPButtonUI());

		listener = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				setSelected();
				clicked = true;
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(!clicked){
					setForeground(ColorAndFonts.otherDarkBulue);
				}
				//setBackground(ColorAndFonts.blue);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(!clicked){
					setForeground(ColorAndFonts.blue);
				}else{
					setForeground(Color.white);
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(!clicked){
					setForeground(ColorAndFonts.blue.darker());
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
			}
		};
		addMouseListener(listener);
	}
	
	public void toNomal(){
		setBackground(Color.white);
		setForeground(ColorAndFonts.blue);
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		clicked = false;
		
	}
	
	public void setSelected(){
		setBackground(ColorAndFonts.blue);
		setForeground(Color.white);
		setBorder(BorderFactory.createEmptyBorder());
		clicked = true;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
