package gap.client.ui.BillOrderQueryUI;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.client.ui.paymentUI.PaymentTableHeader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class TotalMoneyPanel extends PanelWithGrid{

	double money;
	double totalMoney = 0;
	String totalName;
	GAPLabel label;
	GAPLabel empty;
	
	public TotalMoneyPanel(double money,String totalName){
		this.money = money;

		this.totalName = totalName;
		
		empty = new GAPLabel();
		int emptyLength = Default.PANEL_WIDTH-200;
		int height = PaymentTableHeader.height;
		empty.setPreferredSize(new Dimension(emptyLength, height));
		
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, empty, 0, 0, 1, 1, 1, 0);
		
		
		String moneyString = String.format("%.2f", totalMoney);
		String labelText = totalName+"总计 "+moneyString +"元";
		label = new GAPLabel(labelText);
		label.setForeground(Color.black);
		label.setFont(ColorAndFonts.getChinese(20));
		
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, label, 1, 0, 1, 1, 1, 0);
		
	}
	

	public void setMoney(double money){
		totalMoney = money;
		String moneyString = String.format("%.2f", totalMoney);
		String labelText = totalName+"总计 "+moneyString +"元";
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
