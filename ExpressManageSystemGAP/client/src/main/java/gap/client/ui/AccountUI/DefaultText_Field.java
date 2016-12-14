package gap.client.ui.AccountUI;

import gap.client.ui.LoginUI.LoginTextListener;
import gap.client.ui.LoginUI.TextInterface;
import gap.client.ui.gapcomponents.GAPTextField;

import java.awt.Color;

/**
 * 
 * 带默认文字的textField
 * @author 申彬
 *
 */
public class DefaultText_Field extends GAPTextField implements TextInterface {

	boolean hadFocused = false;
	String defaultText;
	
	public DefaultText_Field(String defaultText) {
		super();
		this.defaultText = defaultText;
		setForeground(Color.gray);
		setText(defaultText);
		new LoginTextListener(this);
	}
	@Override
	public boolean hadFocused() {
		// TODO Auto-generated method stub
		return hadFocused;
	}
	
//	public void setText(String t) {
//		setForeground(Color.gray);
//		super.setText(t);
//	};

	@Override
	public void setFocused(boolean focused) {
		// TODO Auto-generated method stub
		hadFocused = focused;
		if (!focused) {
			setForeground(Color.darkGray);
			setText(defaultText);
		}
	}
	
	public String getText(){
		String realText = super.getText();
		
		//排除用户未输入的情况
		if(defaultText==null || realText.equals(defaultText)){
			return "";
		}else{
			return realText;
		}
	}

}
