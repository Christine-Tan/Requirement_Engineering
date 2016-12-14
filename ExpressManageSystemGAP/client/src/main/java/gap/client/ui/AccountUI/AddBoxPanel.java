package gap.client.ui.AccountUI;

import javax.swing.JPanel;

/**
 * 
 * 添加框被点开后的面板，他的组件在addBox里已经加好，它用来管理两个按钮的状态
 * @author 申彬
 *
 */
public class AddBoxPanel extends JPanel{
	ConfirmButton confirmButton;
	CancelButton cancelButton;
	
	public AddBoxPanel(ConfirmButton confirmButton,CancelButton cancelButton){
		this.confirmButton = confirmButton;
		this.cancelButton = cancelButton;
	}
	
	public void reset(){
		confirmButton.reset();
		cancelButton.reset();
	}
}
