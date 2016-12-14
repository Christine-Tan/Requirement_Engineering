package gap.client.ui.paymentUI;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.client.vo.PayeeVO;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

public class PaymentTotalPanel extends PanelWithGrid{

	ArrayList<PayeeVO> payeeVOs;
	double totalMoney = 0;
	String totalName;
	GAPLabel label;
	GAPLabel empty;
	
	public PaymentTotalPanel(ArrayList<PayeeVO> payeeVOs,String totalName){
		this.payeeVOs = payeeVOs;
		calculateTotal();
		this.totalName = totalName;
		String moneyString = String.format("%.2f", totalMoney);
		String labelText = totalName+"总计 "+moneyString +"元";
		label = new GAPLabel(labelText);
		
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, label, 0, 0, 1, 1, 1, 0);
		
		empty = new GAPLabel();
		int emptyLength = PaymentTableHeader.emptyWidth+30;
		int height = PaymentTableHeader.height;
		empty.setPreferredSize(new Dimension(emptyLength, height));
		
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, empty, 1, 0, 1, 1, 0, 0);
	}
	
	private void calculateTotal(){
		double total=0;
		if(payeeVOs!=null){
			for(PayeeVO vo:payeeVOs){
				total+=vo.getMoney();
			}
		}
		totalMoney = total;
	}
	
	public void setMoney(double money){
		totalMoney = money;
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
