package gap.client.ui.managerui.approvalui;

import gap.client.blcontroller.InstitutionController;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.common.po.ArrivedOrderPO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArrivedOrderDetailPanel extends JPanel {
	// 出发机构，到达机构，订单列表，订单编号，订单状态
	JLabel fromIns_jl, toIns_jl, orders_jl;
	// 到达订单的信息
	GAPTextField fromIns, toIns, orderId, state, orderId_jt, state_jt;
	// 到达订单的列表项
	List<ItemPanel> items;
	// 显示详细信息
	JButton detail;
	JPanel detailPanel;
	boolean isDetailed;
	// 布局
	GridBagLayout gb, gb1;
	GridBagConstraints gcons;
	ArrivedOrderPO po;

	public ArrivedOrderDetailPanel(ArrivedOrderPO arrivedOrder) {
		// TODO Auto-generated constructor stub
		this.po = arrivedOrder;
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(ComponentStyle.gray));
		fromIns_jl = new GAPLabel("出发机构");
		toIns_jl = new GAPLabel("到达机构");
		
		fromIns = new GAPTextField(12);
		fromIns.setHorizontalAlignment(JTextField.CENTER);
		fromIns.setText(InstitutionController.findById(po.getFrom_ins_id()).getInsName());
		fromIns.closeEdit();
		
		toIns = new GAPTextField(12);
		toIns.setHorizontalAlignment(JTextField.CENTER);
		toIns.setText(InstitutionController.findById(po.getDes_ins_id()).getInsName());
		toIns.closeEdit();

		detail = new GAPButton(">");
		detail.setFont(ComponentStyle.defaultFont);
		// 对显示到达的订单条目按钮添加监听
		detail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (isDetailed) {
					closeDetail();
				} else {
					showDetail();
				}
			}
		});
		orders_jl = new GAPLabel("显示已到达的订单信息");

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		// 初始化订单列表
		detailPanel = new JPanel();
		detailPanel.setBackground(Color.white);
//		detailPanel.setBorder(BorderFactory.createLineBorder(ComponentStyle.gray));

		// 表头
		orderId_jt = new GAPTextField(10);
		orderId_jt.setText("订单编号");
		orderId_jt.setHorizontalAlignment(JTextField.CENTER);
		orderId_jt.closeEdit();
		state_jt = new GAPTextField(5);
		state_jt.setText("订单状态");
		state_jt.setHorizontalAlignment(JTextField.CENTER);
		state_jt.closeEdit();
		items = new ArrayList<>();
		for (Map.Entry<String, String> orders : po.getOrders().entrySet()) {
			items.add(new ItemPanel(orders.getKey(), orders.getValue()));
		}
		reLayout();
		detailPanel.setVisible(false);

		// 整个到达单的布局
		setLayout(gb);
		gcons.insets = new Insets(5, 10, 5, 0);
		SwingConsole.addComponent(gb, gcons, this, fromIns_jl, 0, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, fromIns, 1, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, toIns_jl, 2, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, toIns, 3, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, detail, 0, 1, 1, 1, 0, 0);
		gcons.insets = new Insets(5, 10, 10, 100);
		SwingConsole.addComponent(gb, gcons, this, orders_jl, 1, 1, 1, 1, 0, 0);
		gcons.insets = new Insets(5, 10, 5, 10);
		SwingConsole.addComponent(gb, gcons, this, detailPanel, 0, 2, 2, 1, 0, 0);

	}

	private void reLayout() {
		// 布局		
		gb1 = new GridBagLayout();
		detailPanel.setLayout(gb1);
		JPanel jp=new JPanel();
		jp.setBackground(Color.WHITE);
		jp.setLayout(gb1);
		gcons.insets = new Insets(5, 10, 5, 0);
		SwingConsole.addComponent(gb1, gcons, jp,  orderId_jt, 1, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(5, 20, 5, 10);
		SwingConsole.addComponent(gb1, gcons, jp, state_jt, 2, 0, 1, 1, 0, 0);
		gcons.insets = new Insets(5, 0, 5, 10);
		SwingConsole.addComponent(gb1, gcons, this.detailPanel, jp, 0, 0, 1, 1, 0, 0);
		for (int i = 1; i <= items.size(); i++) {
			SwingConsole.addComponent(gb1, gcons, this.detailPanel, items.get(i - 1), 0, i, 1, 1, 1, 0);
		}
	}

	private void showDetail() {
		detailPanel.setVisible(true);
		detail.setText("v");
		isDetailed = true;
	}

	private void closeDetail() {
		detailPanel.setVisible(false);
		detail.setText(">");
		isDetailed = false;
	}

	class ItemPanel extends JPanel {
		GAPTextField id_f, state_f;
		GridBagLayout gb2;

		ItemPanel(String id, String state) {
			setBackground(Color.WHITE);
			id_f = new GAPTextField(10);
			id_f.setText(id);
			id_f.setHorizontalAlignment(JTextField.CENTER);

			state_f = new GAPTextField(5);
			state_f.setText(state);
			state_f.setHorizontalAlignment(JTextField.CENTER);

			// 布局
			gb2 = new GridBagLayout();
			setLayout(gb2);
			gcons.insets = new Insets(5, 10, 5, 0);
			SwingConsole.addComponent(gb2, gcons, this, id_f, 1, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 20, 5, 10);
			SwingConsole.addComponent(gb2, gcons, this, state_f, 2, 0, 1, 1, 0, 0);
			 closeEdit();
		}

		void closeEdit() {
			id_f.closeEdit();
			state_f.closeEdit();
		}
	}
}
