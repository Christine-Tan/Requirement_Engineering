package gap.client.ui.paymentUI.paymentDetail;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.client.ui.paymentUI.PaymentTableHeader;
import gap.client.vo.PayeeVO;
import gap.common.po.PayeePO;
import gap.common.po.PaymentListPO;
import gap.common.util.PaymentType;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class PaymentDetailPanel extends PanelWithGrid{

		PaymentListPO paymentListPO;
		ArrayList<DetailClassPanel> panels;
		//用来储存不同类型的payee的数组
		ArrayList<PayeeVO> salaryVOs = new ArrayList<>();
		ArrayList<PayeeVO> transFareVOs = new ArrayList<>();
		ArrayList<PayeeVO> rentVOs = new ArrayList<>();
		ArrayList<PayeeVO> rewardVOs = new ArrayList<>();
		
		PaymentTableHeader header;
		
		HashMap<PaymentType,ArrayList<PayeeVO> > typeMap;
		
		public PaymentDetailPanel(PaymentListPO paymentListPO){
			
			setBackground(Color.white);
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.header = new PaymentTableHeader();
			header.removeColumn();
			this.paymentListPO = paymentListPO;
			
			typeMap = new HashMap<>();
			
			typeMap.put(PaymentType.DELIVERY, salaryVOs);
			typeMap.put(PaymentType.BUSSINESSCLERK, salaryVOs);
			typeMap.put(PaymentType.CENTERCLERK, salaryVOs);
			typeMap.put(PaymentType.INVENTORY, salaryVOs);
			typeMap.put(PaymentType.RENT, rentVOs);
			typeMap.put(PaymentType.TRANSFARE, transFareVOs);
			typeMap.put(PaymentType.REWARD, rewardVOs);
			
			ArrayList<PayeeVO> payeeVOs = new ArrayList<>(paymentListPO.paymentList.size());

			initial();
			
		}
		
		public void initial(){
			ArrayList<PayeeVO> payeeVOs = new ArrayList<>(paymentListPO.paymentList.size());
			
			for(PayeePO po:paymentListPO.paymentList){
				payeeVOs.add(new PayeeVO(po));
			}
			
			for(ArrayList<PayeeVO> payeeList:typeMap.values()){
				payeeList.clear();
			}
			
			//将不同类型的收款人分拣到不同的组里边
			for(PayeeVO aPayee:payeeVOs){
				ArrayList<PayeeVO> aList = typeMap.get(aPayee.getType());
				aList.add(aPayee);
			}
			
			
			if(panels==null){
				panels = new ArrayList<>();
			}else{
				panels.clear();
			}
			
			DetailClassPanel salaryPanel = new DetailClassPanel(this,salaryVOs, "工资");
			
			DetailClassPanel transPanel =new DetailClassPanel(this,transFareVOs, "运费");
			
			DetailClassPanel rentPanel =new DetailClassPanel(this,rentVOs, "租金");
			
			DetailClassPanel rewardPanel = new DetailClassPanel(this,rewardVOs, "奖金");
			
			panels.add(salaryPanel);
			panels.add(transPanel);
			panels.add(rentPanel);
			
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			
			SwingConsole.addComponent
				(gridBagLayout, gridBagConstraints, this, header, 0, 0, 1, 1, 1, 0.2);
			
			int i=0;
			for(i=0;i<panels.size();i++){
				DetailClassPanel aPanel = panels.get(i);
				SwingConsole.addComponent
					(gridBagLayout, gridBagConstraints, this, aPanel, 0, i+1, 1, 1, 1, 1);
			}
			
			
		}
		
		public static void main(String[] args) {
		
		
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout(null);
		frame.setSize(300, 300);
		
		PayeeVO vo = new PayeeVO(PaymentType.BUSSINESSCLERK, "00110011",
					"小花", null, 1000, "主账户", "营业厅业务员工资", "12月工资");
		
		PaymentListPO po = new PaymentListPO
				("1010101001", "小明", 1233, Calendar.getInstance());
		ArrayList<PayeePO> payeePOs = new ArrayList<>();
		for(int i=0;i<3;i++){
			PayeePO payeePO = new PayeePO
					(PaymentType.BUSSINESSCLERK, "1010011"+i,
							"小花", null, 111111, 100, "主账户", "工资");
			payeePOs.add(payeePO);
			PayeePO payeePOA = new PayeePO
					(PaymentType.TRANSFARE, "1010011"+i,
							"小花", null, 111111, 100, "主账户", "工资");
			payeePOs.add(payeePOA);
			PayeePO payeePOB = new PayeePO
					(PaymentType.RENT, "1010011"+i,
							"小花", null, 111111, 100, "主账户", "工资");
			payeePOs.add(payeePOB);
		}
		po.paymentList = payeePOs;
		
		PaymentDetailPanel detailPanel = new PaymentDetailPanel(po);
		frame.add(detailPanel);
		
		frame.setVisible(true);
	
	
	}
		

}
