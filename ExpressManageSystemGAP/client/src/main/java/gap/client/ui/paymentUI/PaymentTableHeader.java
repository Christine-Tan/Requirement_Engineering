package gap.client.ui.paymentUI;

import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JLabel;

public class PaymentTableHeader extends PanelWithGrid{
	
	GAPLabel payDate;
	GAPLabel recevier;
	GAPLabel account;
	//条目，如快递员工资，业务员工资
	GAPLabel item;
	GAPLabel money;
	//备注，如12月工资，运单号
	GAPLabel comment;
	//放在最后的占位label
	JLabel empty;
	JLabel[] components;
	
	public static final int leftGap = 100;
	
	public static final int height = 40;
	public static final int payDateWidth = 100;
	public static final int recevierWidth = 140;
	public static final int accountWidth = 120;
	public static final int itemWidth = 100;
	public static final int moneyWidth = 100;
	public static final int commentWidth = 100;
	public static final int emptyWidth = 40;
	
	public static int[] widthArgs;
	static{
		widthArgs = new int[7];
		widthArgs[0] = payDateWidth;
		widthArgs[1] = recevierWidth;
		widthArgs[2] = accountWidth;
		widthArgs[3] = itemWidth;
		widthArgs[4] = moneyWidth;
		widthArgs[5] = commentWidth;
		widthArgs[6] = emptyWidth;
	}
	
	public void removeColumn(){
		remove(item);
		remove(comment);
		remove(payDate);
		JLabel label = new JLabel();
		gridBagConstraints.insets = new Insets(20, 50, 20, 0);
//		label.setPreferredSize
//		(new Dimension(PaymentTableHeader.leftGap-10,20));
		SwingConsole.addComponent(gridBagLayout, gridBagConstraints, 
				this, label, 0, 0, 1, 1, 0.1, 0.5);
	}
	
	public PaymentTableHeader(){
		
		
		payDate = new GAPLabel("付款日期");
		recevier = new GAPLabel("收款方");
		account = new GAPLabel("付款账号");
		item = new GAPLabel("条目");
		money = new GAPLabel("付款金额");
		comment = new GAPLabel("备注");
		empty = new JLabel();
		
		components = new JLabel[7];
		components[0] = payDate;
		components[1] = recevier;
		components[2] = account;
		components[3] = item;
		components[4] = money;
		components[5] = comment;
		components[6] = empty;
		
		setBackground(Color.white);
		
		for(int i=0;i<components.length;i++){
			
//			components[i].setBackground
//			(new Color((int)(Math.random()*255), (int)(Math.random()*255),(int)(Math.random()*255)));
//		components[i].setOpaque(true);
			
			components[i].setHorizontalAlignment(JLabel.CENTER);
			components[i].setPreferredSize(new Dimension(widthArgs[i], 20));
		}
		
		for(int i=0;i<components.length;i++){
			if(i==0){
				gridBagConstraints.insets = new Insets(20, 100, 20, 0);
			}else{
				gridBagConstraints.insets = new Insets(20, 0, 20, 0);
			}
			
			SwingConsole.addComponent(gridBagLayout, gridBagConstraints, 
					this, components[i], i, 0, 1, 1, 0.5, 0.5);
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setStroke(new BasicStroke(1.0f));
		g2d.setColor(Color.gray);
		int width = getWidth();
		g2d.drawLine(5, getHeight()-5, width, getHeight()-5);
	}
	
//	public static void main(String[] args) {
//	
//	
//	JFrame frame = new JFrame("test");
//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	//frame.setLayout(null);
//	frame.setSize(800, 600);
//	
////	PayeeVO vo = new PayeeVO(PaymentType.BUSSINESSCLERK, "00110011",
////				"小花", null, 1000, "主账户", "营业厅业务员工资", "12月工资");
////	ArrayList<AccountVO> accountVOs = new ArrayList<>();
////	accountVOs.add(new AccountVO("账户111111111",8000));
////	accountVOs.add(new AccountVO("账户2",8000));
////	accountVOs.add(new AccountVO("账户3",8000));
////	PayeeItem payeeItem = new PayeeItem(vo, accountVOs);
//	frame.add(new PaymentTableHeader());
//	
//	frame.setVisible(true);
//
//
//}
	
}
