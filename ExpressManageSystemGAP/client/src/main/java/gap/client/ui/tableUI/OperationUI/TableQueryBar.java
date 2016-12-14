package gap.client.ui.tableUI.OperationUI;

import gap.client.ui.AccountUI.DefaultText_Field;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TableQueryBar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	OperationMainPanel mainPanel;
	
	DefaultText_Field beginDateField;
	DefaultText_Field endDateField;
	JButton search;

	Component currentComponent;
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints gcons = new GridBagConstraints();
	
	TableSearchListener searchListener;
	

	public TableQueryBar
		(OperationMainPanel mainPanel) {
		
		this.mainPanel = mainPanel;
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 80));


		setLayout(gb);

		beginDateField = new DefaultText_Field("开始日期");
		beginDateField.setPreferredSize(new Dimension(170, 24));
		endDateField = new DefaultText_Field("结束日期");
		endDateField.setPreferredSize(new Dimension(170, 24));
		search = new GAPButton(" 搜索", new ImageIcon(
				"images\\deliveryIcon\\search.png"));
		search.setFont(ComponentStyle.plainFont);
			

		
		//加监听
		searchListener = new TableSearchListener(this, mainPanel);

		search.addMouseListener(searchListener);
		//cancel.addMouseListener(cancelListener);
		
		currentComponent = search;
		
		gcons.insets = new Insets(10, 0, 0, 30);
		SwingConsole.addComponent(gb, gcons, this, beginDateField, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 0, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, endDateField, 1, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, currentComponent, 2, 0, 1, 1, 0, 0);
		
	}
	
	public Component getSearchButton(){
		return currentComponent;
	}
	
	
	public void clear() {
		beginDateField.setText("");
		endDateField.setText("");
	}

	public void setAlarm() {
		endDateField.toAlarm();
	}

	public String getBegin() {
		return beginDateField.getText();
	}

	public String getEnd() {
		// TODO Auto-generated method stub
		return endDateField.getText();
	}
	
}

