package gap.client.ui.AccountUI.ComponentBehave;

import gap.client.ui.AccountUI.AccountManagePanel;
import gap.client.ui.AccountUI.EditableLable;
import gap.client.vo.AccountVO;

public class NameChangeBehave implements ComponentBehave{
	AccountManagePanel managePanel;
	EditableLable label;
	AccountVO accountVO;
	
	public NameChangeBehave(AccountManagePanel 
			managePanel,EditableLable label,AccountVO vo) {
		this.managePanel = managePanel;
		this.label = label;
		this.accountVO = vo;
	}
	@Override
	public void behave() {

		String newName = label.getText();
		
		//没有改变名称
		if(accountVO.getName().equals(newName)){
			return;
		}
		
		accountVO.setNewName(newName);
		managePanel.modifyAccount(accountVO, this);
	}
	
	/**
	 * 账户名修改成功，重设label的text，重设vo的名字
	 * @param newName
	 */
	public void setNewName(String newName) {
		// TODO Auto-generated method stub
		label.setText(newName);
		accountVO.setName(newName);
		accountVO.setNewName("");
		label.repaint();
	}
	
	
	/**
	 * 账户名已经存在，就设回原来的名字
	 */
	public void revertOldName() {
		// TODO Auto-generated method stub
		accountVO.setNewName("");
		label.setText(accountVO.getName());
		label.repaint();
	}

}
