package gap.client.ui.tableUI.OperationUI;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.client.ui.paymentUI.PaymentTableHeader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TableTotalMoneyPanel extends PanelWithGrid{


	GAPLabel incomeLabel;
	GAPLabel paymentLabel;
	GAPLabel totalLabel;
	GAPLabel empty;
	GAPLabel[] labels;
	
	public TableTotalMoneyPanel(){

		
		empty = new GAPLabel();
		int emptyLength = Default.PANEL_WIDTH-600;
		int height = PaymentTableHeader.height;
		empty.setPreferredSize(new Dimension(emptyLength, height));
		
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, empty, 0, 0, 1, 1, 1, 0);
		
		
		incomeLabel = new GAPLabel();
		paymentLabel = new GAPLabel();
		totalLabel = new GAPLabel();
		
		labels = new GAPLabel[3];
		labels[0] = incomeLabel;
		labels[1] = paymentLabel;
		labels[2] = totalLabel;
		
		for(GAPLabel label:labels){
			setLabel(label);
		}
		
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(0, 20, 0, 20);
		for(int i=0;i<labels.length;i++){
			SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, labels[i], i+1, 0, 1, 1, 1, 0);
		}

		
	}
	
	private void setLabel(GAPLabel label){
		label.setForeground(Color.black);
		label.setFont(ColorAndFonts.getChinese(20));
	}
	

	public void setMoney(double income,double payment,double total){
		
		setLabelText(income, "共收入", incomeLabel);
		setLabelText(payment, "共支出", paymentLabel);
		setLabelText(total, "净收入", totalLabel);
	}
	
	private void setLabelText(double money,String text,GAPLabel label){
		String moneyString = String.format("%.2f", money);
		String labelText = text+moneyString +"元";
		label.setText(labelText);
	}
	
//	protected void paintComponent(Graphics g) {
//		// TODO Auto-generated method stub
//		super.paintComponent(g);
//		Graphics2D graphics2d = RenderSetter.OpenRender(g);
//		g.setColor(Color.gray);
//
//		graphics2d.drawLine(0, getHeight()-2, getWidth(), getHeight()-2);
//	}
}
