package gap.client.ui.LoginUI;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class LoginTextListener implements FocusListener {
	JTextField textField;

	public LoginTextListener(JTextField textField) {
		// TODO Auto-generated constructor stub
		this.textField = textField;
		textField.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		TextInterface text = (TextInterface) textField;
		if (!text.hadFocused()) {
			textField.setText("");
			text.setFocused(true);
			textField.setForeground(Color.black);
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (textField.getText().equals("")) {
			TextInterface text = (TextInterface) textField;
			text.setFocused(false);
		}

	}

}
