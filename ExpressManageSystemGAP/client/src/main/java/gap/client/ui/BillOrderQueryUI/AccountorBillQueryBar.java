package gap.client.ui.BillOrderQueryUI;

import gap.client.ui.AccountUI.DefaultText_Field;
import gap.client.ui.BillOrderQueryUI.Listener.BillCancelListener;
import gap.client.ui.BillOrderQueryUI.Listener.BillSearchListener;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.vo.InstitutionVO;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AccountorBillQueryBar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	GAPComboBox<InstitutionVO> instituteBox;
	DefaultText_Field dateField;
	JButton search;
	//CancelButton cancel;
	Component currentComponent;
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints gcons = new GridBagConstraints();
	
	BillSearchListener searchListener;
	BillCancelListener cancelListener;
	

	public AccountorBillQueryBar
		(AccountorBillQueryMainPanel mainPanel,List<InstitutionVO> institutionVOs) {
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 80));


		setLayout(gb);

		
		instituteBox = new GAPComboBox<>();
		for(InstitutionVO vo:institutionVOs){
			instituteBox.addItem(vo);
		}
		
		
		dateField = new DefaultText_Field("日期格式：2015-12-21");
		dateField.setPreferredSize(new Dimension(170, 24));
		search = new GAPButton(" 搜索", new ImageIcon(
				"images\\deliveryIcon\\search.png"));
		search.setFont(ComponentStyle.plainFont);
		
	//	cancel = new CancelButton(null);		

		
		//加监听
		searchListener = new BillSearchListener(this, mainPanel);
		cancelListener = new BillCancelListener(this, mainPanel);
		search.addMouseListener(searchListener);
		//cancel.addMouseListener(cancelListener);
		
		currentComponent = search;
		
		gcons.insets = new Insets(10, 0, 0, 30);
		SwingConsole.addComponent(gb, gcons, this, instituteBox, 0, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(10, 0, 0, 10);
		SwingConsole.addComponent(gb, gcons, this, dateField, 1, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, currentComponent, 2, 0, 1, 1, 0, 0);
		
	}
	
	public Component getSearchButton(){
		return currentComponent;
	}
	
	/**
	 * 把界面上的button设为Cancelbutton
	 */
//	public void setCancelButton(){
//		remove(currentComponent);
//		currentComponent = cancel;
//		cancel.setPreferredSize(new Dimension(80, 30));
//		SwingConsole.addComponent(gb, gcons, this, currentComponent, 1, 0, 1, 1, 0, 0);
//	}
	
	/**
	 * 把界面上的button设为SearchButton
	 */
	public void setSearchButton(){
		dateField.setText("");
		remove(currentComponent);
		currentComponent = search;
		SwingConsole.addComponent(gb, gcons, this, currentComponent, 1, 0, 1, 1, 0, 0);
	}

	public String getInstitute(){
		InstitutionVO institutionVO = (InstitutionVO) instituteBox.getSelectedItem();
		return institutionVO.getInsId();
	}
	
	public String getDate(){
		String text = dateField.getText();
		//输入为空视为null,之后逻辑将null视为所有日期
		if(text.equals("")){
			return null;
		}else{
			return text;
		}
	}
	
	public void clear() {
		instituteBox.setSelectedItem(null);
		dateField.setText("");
	}

	public void setAlarm() {
		// TODO Auto-generated method stub
		dateField.toAlarm();
	}
}

