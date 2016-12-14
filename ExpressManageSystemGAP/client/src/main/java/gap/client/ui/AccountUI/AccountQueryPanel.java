package gap.client.ui.AccountUI;

import gap.client.ui.AccountUI.ComponentBehave.SearchCancel;
import gap.client.ui.AccountUI.Listener.SearchListener;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AccountQueryPanel extends JPanel {
	GAPTextField id;
	JButton search;
	CancelButton cancel;
	Component currentComponent;
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints gcons = new GridBagConstraints();
	
	AccountManagePanel managePanel;
	AccountDisplayPanel displayPanel;
	SearchListener searchListener;
	

	public AccountQueryPanel(AccountManagePanel managePanel,
			AccountDisplayPanel displayPanel) {
		
		this.managePanel = managePanel;
		this.displayPanel = displayPanel;
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 80));


		setLayout(gb);

		id = new GAPTextField(10);
		search = new GAPButton(" 搜索", new ImageIcon(
				"images\\deliveryIcon\\search.png"));
		search.setFont(ComponentStyle.plainFont);
		
		//改变取消按钮行为
		cancel = new CancelButton(null);
		cancel.setBehave(new SearchCancel(this, displayPanel));
		
		//给搜索按钮加监听
		searchListener = new SearchListener(managePanel, this);
		search.addMouseListener(searchListener);
		
		currentComponent = search;
		
		gcons.insets = new Insets(10, 0, 0, 2);
		SwingConsole.addComponent(gb, gcons, this, id, 0, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, currentComponent, 1, 0, 1, 1, 0, 0);
		
	}
	
	public String getKey(){
		return id.getText();
	}
	
	public Component getSearchButton(){
		return currentComponent;
	}
	
	public void setCancelButton(){
		remove(currentComponent);
		currentComponent = cancel;
		cancel.setPreferredSize(new Dimension(80, 30));
		SwingConsole.addComponent(gb, gcons, this, currentComponent, 1, 0, 1, 1, 0, 0);
	}
	
	public void setSearchButton(){
		id.setText("");
		remove(currentComponent);
		currentComponent = search;
		SwingConsole.addComponent(gb, gcons, this, currentComponent, 1, 0, 1, 1, 0, 0);
	}

	public void clear() {
		id.setText("");
	}

	public void setAlarm() {
		// TODO Auto-generated method stub
		id.toAlarm();
	}
}
