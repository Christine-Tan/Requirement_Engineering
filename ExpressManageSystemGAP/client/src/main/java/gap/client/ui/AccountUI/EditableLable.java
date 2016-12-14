package gap.client.ui.AccountUI;

import gap.client.ui.AccountUI.ComponentBehave.ComponentBehave;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPTextField;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * 
 * 双击可编辑，平时是label
 * @author 申彬
 *
 */
public class EditableLable extends GAPTextField{

	//该组件的行为
	ComponentBehave behave;
	
	public EditableLable(){
		super();
		init("");
	}
	
	public EditableLable(String text){
		super();
		init(text);
	}
	
	private void init(String text){
		
		//移除父类的mouseListener
		removeMouseListener(getTextListener());
		
		setEditable(false);
		setForeground(Color.BLACK);
		setHorizontalAlignment(JTextField.CENTER);
		setFont(ColorAndFonts.getChinese(15));
		setText(text);
		MyListener listener = new MyListener();
		
		setControl("\\.+", 1, 25);
		
		setBorder(BorderFactory.createEmptyBorder());		
		addMouseListener(listener);
		addFocusListener(listener);
		addKeyListener(listener);
	}
	
	public void setBehave(ComponentBehave behave){
		this.behave = behave;
	}
	
	class MyListener implements MouseListener,KeyListener,FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			
			setEditable(false);
			setForeground(Color.BLACK);
			setBorder(BorderFactory.createEmptyBorder());
			getCaret().setVisible(false);
			
			//启动行为
			//behave.behave();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			if(isEditable() && e.getKeyChar() == KeyEvent.VK_ENTER){
				setEditable(false);
				setBorder(BorderFactory.createEmptyBorder());
				getCaret().setVisible(false);
				
				//启动行为
				behave.behave();
			}
			setForeground(Color.BLACK);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getClickCount()>=1){
				requestFocusInWindow();
				setForeground(Color.BLACK);
				
				setBorder(ComponentStyle.focus_border);
				setOpaque(true);
				setEditable(true);
				getCaret().setVisible(true);
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			setForeground(Color.gray);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if(!isEditable()){
				setForeground(Color.BLACK);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("test");
//		frame.setSize(100, 100);
//		frame.add(new EditableLable("aaaaaa"));
//		frame.setVisible(true);
//		
//	}
}
