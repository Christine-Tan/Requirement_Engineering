package gap.client.ui.paymentUI.Listener;

import gap.client.ui.paymentUI.PaymentMainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentSubmitListener implements ActionListener{

	PaymentMainPanel mainPanel;
	public PaymentSubmitListener(PaymentMainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mainPanel.submit();
	}

}
