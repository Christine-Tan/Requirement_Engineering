package gap.client.ui.paymentUI;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.client.vo.AccountVO;
import gap.client.vo.PayeeVO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class PayeeItem extends PanelWithGrid{
	
	PayeeVO vo;
	
	GAPLabel payDate;
	GAPLabel recevier;
	GAPComboBox<AccountVO> accountCombox;
	//条目，如快递员工资，业务员工资
	GAPLabel item;
	GAPLabel money;
	//备注，如12月工资，运单号
	GAPLabel comment;
	
	JLabel empty;
	
	ArrayList<AccountVO> accountVOs;
	
	JComponent[] components;
	
	private static String dateString=null; 
	
	public PayeeItem(PayeeVO vo,ArrayList<AccountVO> accountVOs){
		this.vo = vo;
		
		this.accountVOs = accountVOs;
		setBackground(Color.white);
		if(dateString==null){
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			dateString = format.format(calendar.getTime());
		}
		
		payDate = new GAPLabel(dateString);
		recevier = new GAPLabel(vo.getUserName());
		
		accountCombox = new GAPComboBox<>();
		
		for(AccountVO oneAccount:accountVOs){
			accountCombox.addItem(oneAccount);
		}
		
		for(int i=0;i<accountVOs.size();i++){
			//把payee中的账户名设为默认选择项
			if(accountVOs.get(i).getName().equals(vo.getAccountName())){
				accountCombox.setSelectedIndex(i);
			}
		}
		
		
		//(微笑脸)这个地方差点坑死我，这个itemchange第一次加进去就会调用，以后用的时候要记得，
		//组件加完了再加监听
		accountCombox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				AccountVO accountVO = (AccountVO) e.getItem();
				setPayeeAccount(accountVO.getName());
			}
		});
		
		
		item = new GAPLabel(vo.getEntry());
		
		String moneyString = String.format("%.2f", vo.getMoney());
		money = new GAPLabel(moneyString+"元");
		comment = new GAPLabel(vo.getNote());
		
		empty = new JLabel();
		
		components = new JComponent[7];
		components[0] = payDate;
		components[1] = recevier;
		components[2] = accountCombox;
		components[3] = item;
		components[4] = money;
		components[5] = comment;
		components[6] = empty;
		
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		for(int i=0;i<components.length;i++){
			
			if(components[i]==accountCombox){
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
	
	private void setPayeeAccount(String accountName){
		vo.setAccountName(accountName);
	}
	
	public static String getDateString(){
		if(dateString==null){
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			dateString = format.format(calendar.getTime());
		}
		return dateString;
		
	}
	
//	public static void main(String[] args) {
//		
//		try {
//			UIManager
//			.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//				| UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		JFrame frame = new JFrame("test");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		//frame.setLayout(null);
//		frame.setSize(300, 300);
//		
//		PayeeVO vo = new PayeeVO(PaymentType.BUSSINESSCLERK, "00110011",
//					"小花", null, 1000, "主账户", "营业厅业务员工资", "12月工资");
//		ArrayList<AccountVO> accountVOs = new ArrayList<>();
//		accountVOs.add(new AccountVO("账户111111111",8000));
//		accountVOs.add(new AccountVO("账户2",8000));
//		accountVOs.add(new AccountVO("账户3",8000));
//		PayeeItem payeeItem = new PayeeItem(vo, accountVOs);
//		frame.add(payeeItem);
//		
//		frame.setVisible(true);
//	
//	
//	}
}
