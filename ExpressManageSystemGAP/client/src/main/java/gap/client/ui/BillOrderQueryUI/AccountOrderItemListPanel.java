package gap.client.ui.BillOrderQueryUI;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.ui.paymentUI.paymentDetail.PaymentDetailPanel;
import gap.common.po.BillOrderPO;
import gap.common.po.PaymentListPO;
import gap.common.util.ReceiptType;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountOrderItemListPanel extends JPanel {
	// 列表中所有的项
	List<ItemPanel> items;
	List<Object> orders;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;
	AccountorBillQueryMainPanel queryMainPanel;
	JLabel emptyLabel;
	JLabel textLabel;

	public AccountOrderItemListPanel
	(AccountorBillQueryMainPanel queryMainPanel) {

		this.queryMainPanel = queryMainPanel;
		orders = new ArrayList<>();
		setBackground(Color.WHITE);
//		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 900));
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		items = new ArrayList<>();
		
		textLabel = new JLabel("还没有查询收款单");
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setForeground(Color.gray);
		textLabel.setFont(ColorAndFonts.getChinese(30));
		
		emptyLabel = new JLabel();
		
		gcons.fill = GridBagConstraints.BOTH;
		SwingConsole.addComponent(gb, gcons, this, textLabel, 0, 0, 1, 1, 1, 1);
		

	}
	
	public void setDefualtText(String text){
		textLabel.setText(text);
	}
	
	
	public void refresh(ArrayList<Object> orderPOs){
		removeAll();
		items.clear();
		this.orders = orderPOs;
		if(orders.size()==0){
			gcons.fill = GridBagConstraints.BOTH;
			SwingConsole.addComponent(gb, gcons, this, textLabel, 0, 0, 1, 1, 1, 1);
			return;
		}
		
		for (Object order : orders) {
			items.add(new ItemPanel(order));
		}
		
		showItems();
		//queryMainPanel.validate();
	}
	
	public void cancel(){
		removeAll();
		items.clear();
		orders.clear();
		gcons.fill = GridBagConstraints.BOTH;
		SwingConsole.addComponent(gb, gcons, this, textLabel, 0, 0, 1, 1, 1, 1);
	}
   
	private void showItems() {
		for (int i = 0; i < items.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1, 1, 1, 0);
		}
		SwingConsole.addComponent(gb, gcons, this, emptyLabel, 0, items.size(), 1, 1, 1, 1);
	}

	class ItemPanel extends JPanel {
		GAPTextField order_id, type, date;
		GAPLabel money;
		JButton detail;

		// 详细信息面板
		JPanel detailPanel;
		// 布局
		GridBagLayout gbl;
		// 是否显示详细信息
		boolean detailed;
		Object order;
		
		ReceiptType receiptType;
		

		ItemPanel(Object ob) {
			setBackground(Color.white);
			this.order = ob;
			// 组件初始化
			order_id = new GAPTextField(15);
			type = new GAPTextField(13);
			date = new GAPTextField(10);
			this.setLayout(gbl);
			order_id.setHorizontalAlignment(JTextField.CENTER);
			type.setHorizontalAlignment(JTextField.CENTER);
			date.setHorizontalAlignment(JTextField.CENTER);
			order_id.closeEdit();
			type.closeEdit();
			date.closeEdit();
			
			order_id.setOpaque(false);
			type.setOpaque(false);
			date.setOpaque(false);
			
		
			money = new GAPLabel();
			money.setForeground(Color.black);
			double total = 0;
			
			if (order instanceof BillOrderPO) {
				BillOrderPO billOrder = (BillOrderPO) order;
				order_id.setText(billOrder.getID());
				type.setText("收款单");
				date.setText(billOrder.getBillDate().toString());
				total = billOrder.getTotal();
				receiptType = ReceiptType.BILL;
			} else if (order instanceof PaymentListPO) {
				PaymentListPO paymentList = (PaymentListPO) order;
				order_id.setText(paymentList.getID());
				type.setText("付款单");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = format.format(paymentList.getDate().getTime());
				date.setText(dateString);
				total = paymentList.getTotal();
				receiptType = ReceiptType.PAYMENT;
			} else {
				System.out.println("no corresponding ordertype");
			}
			
			String moneyString = String.format("%.2f", total) + "元";
			money.setText(moneyString);
			

			// 对显示详细信息的按钮添加监听
			detail = new GAPButton(">");
			detail.setFont(ComponentStyle.defaultFont);
			detail.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (detailed) {
						closeDetail();
					} else {
						showDetail();
					}
				}
			});
			
			// 确定订单的类型
			if (order instanceof BillOrderPO) {
				detailPanel = new BillOrderDetailPanel((BillOrderPO) order);
			} else if (order instanceof PaymentListPO) {
                detailPanel=new PaymentDetailPanel((PaymentListPO)order);
			}
			
			detailPanel.setVisible(false);
			// 布局
			gbl = new GridBagLayout();
			setLayout(gbl);
			gcons.insets = new Insets(5, 20, 20, 10);
			SwingConsole.addComponent(gbl, gcons, this, detail, 0, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 10, 10, 0);
			SwingConsole.addComponent(gbl, gcons, this, order_id, 1, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 35, 10, 7);
			gcons.anchor = GridBagConstraints.CENTER;
			SwingConsole.addComponent(gbl, gcons, this, type, 2, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 5, 10, 15);
			SwingConsole.addComponent(gbl, gcons, this, date, 3, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 40, 10, 5);
			SwingConsole.addComponent(gbl, gcons, this, money, 4, 0, 1, 1, 0, 0);

			gcons.insets = new Insets(0, 0, 0, 0);
			gcons.anchor = GridBagConstraints.CENTER;
			SwingConsole.addComponent(gbl, gcons, this, detailPanel, 0, 1, 5, 1, 0, 0);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int width = 30;
			int widthGarp = 20;
			int height = getHeight();
			int garp = 10;
			if(receiptType==null){
				return;
			}
			
			switch (receiptType) {
			case BILL:
				g.setColor(new Color(189, 252, 167));
				break;
			case PAYMENT:
				g.setColor(new Color(247,133,138));
				break;
			}
			
			g.fillRect(widthGarp, garp/2, width, height-garp*2);
			
		}



		void showDetail() {
			order_id.setForeground(ColorAndFonts.blue);
			type.setForeground(ColorAndFonts.blue);
			date.setForeground(ColorAndFonts.blue);
			money.setForeground(ColorAndFonts.blue);
			detailPanel.setVisible(true);
			detailed = true;
			detail.setText("v");
		}

		void closeDetail() {
			order_id.setForeground(Color.BLACK);
			type.setForeground(Color.BLACK);
			date.setForeground(Color.BLACK);
			money.setForeground(Color.BLACK);
			detailPanel.setVisible(false);
			detailed = false;
			detail.setText(">");
		}
	}
}
