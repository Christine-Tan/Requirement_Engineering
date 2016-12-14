package gap.client.ui.managerui.approvalui;

import gap.client.blcontroller.ApprovalController;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.ui.paymentUI.paymentDetail.PaymentDetailPanel;
import gap.common.po.ArrivedOrderPO;
import gap.common.po.BillOrderPO;
import gap.common.po.DeliveryOrderPO;
import gap.common.po.ExpressOrderPO;
import gap.common.po.LoadOrderPO;
import gap.common.po.PaymentListPO;
import gap.common.po.StockinOrderPO;
import gap.common.po.StockoutOrderPO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OrderItemListPanel extends JPanel {
	// 列表中所有的项
	List<ItemPanel> items;
	List<Object> orders;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;
	JFrame frame;
	JPanel detailPanel;

	public OrderItemListPanel(JFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		setBackground(Color.WHITE);
//		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 900));
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		items = new ArrayList<>();
		orders = ApprovalController.getUnpassedOrder();
		for (Object order : orders) {
			items.add(new ItemPanel(order));
		}
		showItems();
	}
   
	private void showItems() {
		for (int i = 0; i < items.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1, 1, 1, 0);
		}
	}

	class ItemPanel extends JPanel {
		GAPTextField order_id, type, date;
		JButton detail;
		JCheckBox select;
		// 详细信息面板
		JPanel detailPanel;
		// 布局
		GridBagLayout gbl;
		// 是否显示详细信息
		boolean detailed;
		Object order;

		ItemPanel(Object ob) {
			setBackground(Color.WHITE);
			this.order = ob;
			// 组件初始化
			order_id = new GAPTextField(20);
			type = new GAPTextField(13);
			date = new GAPTextField(10);
			this.setLayout(gbl);
			order_id.setHorizontalAlignment(JTextField.CENTER);
			type.setHorizontalAlignment(JTextField.CENTER);
			date.setHorizontalAlignment(JTextField.CENTER);
			order_id.closeEdit();
			type.closeEdit();
			date.closeEdit();
			if (order instanceof ArrivedOrderPO) {
				ArrivedOrderPO arrivedOrder = (ArrivedOrderPO) order;
				order_id.setText(arrivedOrder.getID());
				type.setText("到达单");
				date.setText(arrivedOrder.getTime());
			} else if (order instanceof BillOrderPO) {
				BillOrderPO billOrder = (BillOrderPO) order;
				order_id.setText(billOrder.getID());
				type.setText("收款单");
				date.setText(billOrder.getBillDate().toString());
			} else if (order instanceof DeliveryOrderPO) {
				DeliveryOrderPO deliveryOrder = (DeliveryOrderPO) order;
				order_id.setText(deliveryOrder.getID());
				type.setText("派件单");
				date.setText(deliveryOrder.getTime());
			} else if (order instanceof ExpressOrderPO) {
				ExpressOrderPO expressOrder = (ExpressOrderPO) order;
				order_id.setText(expressOrder.getID());
				type.setText("寄件单");
				date.setText(expressOrder.getTime());
			} else if (order instanceof LoadOrderPO) {
				LoadOrderPO loadOrder = (LoadOrderPO) order;
				order_id.setText(loadOrder.getID());
				type.setText("装车单");
				date.setText(loadOrder.getDate());
			} else if (order instanceof StockinOrderPO) {
				StockinOrderPO stockinOrder = (StockinOrderPO) order;
				order_id.setText(stockinOrder.getID());
				type.setText("入库单");
				date.setText(stockinOrder.getInDate());
			} else if (order instanceof StockoutOrderPO) {
				StockoutOrderPO stockoutOrder = (StockoutOrderPO) order;
				order_id.setText(stockoutOrder.getID());
				type.setText("出库单");
				date.setText(stockoutOrder.getOutDate());
			} else if (order instanceof PaymentListPO) {
				PaymentListPO paymentList = (PaymentListPO) order;
				order_id.setText(paymentList.getID());
				type.setText("付款单");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = format.format(paymentList.getDate().getTime());
				date.setText(dateString);
			} else {
				System.out.println("no corresponding ordertype");
			}

			select = new JCheckBox();
			select.setBackground(Color.WHITE);
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
			if (order instanceof ArrivedOrderPO) {
				detailPanel = new ArrivedOrderDetailPanel((ArrivedOrderPO) order);
			} else if (order instanceof BillOrderPO) {
				detailPanel = new BillOrderDetailPanel((BillOrderPO) order);
			} else if (order instanceof DeliveryOrderPO) {
				detailPanel = new DeliveryOrderDetailPanel((DeliveryOrderPO) order);
			} else if (order instanceof ExpressOrderPO) {
				detailPanel = new ExpressOrderDetailPanel((ExpressOrderPO) order);
			} else if (order instanceof LoadOrderPO) {
				detailPanel = new LoadOrderDetailPanel((LoadOrderPO) order);
			} else if (order instanceof StockinOrderPO) {
				detailPanel = new StockinOrderDetailPanel((StockinOrderPO) order);
			} else if (order instanceof StockoutOrderPO) {
				detailPanel = new StockoutOrderDetailPanel((StockoutOrderPO) order);
			} else if (order instanceof PaymentListPO) {
                detailPanel=new PaymentDetailPanel((PaymentListPO)order);
			}
			detailPanel.setVisible(false);
			// 布局
			gbl = new GridBagLayout();
			setLayout(gbl);
			gcons.insets = new Insets(5, 55, 10, 20);
			SwingConsole.addComponent(gbl, gcons, this, detail, 0, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 0, 10, 0);
			SwingConsole.addComponent(gbl, gcons, this, order_id, 1, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 10, 10, 0);
			SwingConsole.addComponent(gbl, gcons, this, type, 2, 0, 1, 1, 0, 0);
			SwingConsole.addComponent(gbl, gcons, this, date, 3, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 55, 10, 30);
			SwingConsole.addComponent(gbl, gcons, this, select, 4, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 10, 5, 10);
			SwingConsole.addComponent(gbl, gcons, this, detailPanel, 0, 1, 6, 1, 0, 0);
		}

		void setSelected(boolean bool) {
			this.select.setSelected(bool);
		}

		void showDetail() {
			order_id.setForeground(ColorAndFonts.blue);
			type.setForeground(ColorAndFonts.blue);
			date.setForeground(ColorAndFonts.blue);
			detailPanel.setVisible(true);
			detailed = true;
			detail.setText("v");
		}

		void closeDetail() {
			order_id.setForeground(Color.BLACK);
			type.setForeground(Color.BLACK);
			date.setForeground(Color.BLACK);
			detailPanel.setVisible(false);
			detailed = false;
			detail.setText(">");
		}
	}
}
