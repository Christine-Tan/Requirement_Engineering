package gap.client.ui.paymentUI.paymentDetail;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.client.ui.paymentUI.PaymentTableHeader;
import gap.client.vo.PayeeVO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class DetailItem extends PanelWithGrid{
	
	PayeeVO vo;
	

	GAPLabel recevier;
	GAPLabel accountLabel;
	//条目，如快递员工资，业务员工资

	GAPLabel money;
	//备注，如12月工资，运单号
	
	JLabel empty;
	
	
	JComponent[] components;
	
	private static String dateString=null; 
	
	public DetailItem(PayeeVO vo){
		this.vo = vo;

		setBackground(Color.white);
		if(dateString==null){
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			dateString = format.format(calendar.getTime());
		}
		

		recevier = new GAPLabel(vo.getUserName());
		
		accountLabel = new GAPLabel(vo.getAccountName());
		
		String moneyString = String.format("%.2f", vo.getMoney());
		money = new GAPLabel(moneyString+"元");
		
		empty = new JLabel();
		
		components = new JComponent[4];
		components[0] = recevier;
		components[1] = accountLabel;
		components[2] = money;
		components[3] = empty;
		
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		for(int i=0;i<components.length;i++){
			
			if(components[i]==accountLabel){
				components[i].setPreferredSize
				(new Dimension(PaymentTableHeader.widthArgs[i], 25));
			}
			if(components[i] instanceof JLabel){
				JLabel label = (JLabel) components[i];
				label.setHorizontalAlignment(JLabel.CENTER);
				components[i].setPreferredSize
				(new Dimension(PaymentTableHeader.widthArgs[i], PaymentTableHeader.height));
			}
			
			SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this,components[i], i, 0, 1, 1,1, 1);
			
		}
		
		
	}
	
}
