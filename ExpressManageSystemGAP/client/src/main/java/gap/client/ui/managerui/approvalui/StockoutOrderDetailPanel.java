package gap.client.ui.managerui.approvalui;

import gap.client.blcontroller.InstitutionController;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.common.po.StockoutOrderPO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StockoutOrderDetailPanel extends JPanel {
	JLabel current_jl, target_jl, transtype_jl, order_jl;
	GAPTextField current_tf, target_tf, transtype_tf, order_tf;
	List<GAPTextField> orders_tf;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	public StockoutOrderDetailPanel(StockoutOrderPO stockoutOrder) {
		// TODO Auto-generated constructor stub
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(ComponentStyle.gray));

		current_jl = new GAPLabel("当前机构：");
		target_jl = new GAPLabel("目标机构：");
		transtype_jl = new GAPLabel("货运方式：");
		order_jl = new GAPLabel("待出库快递编号：");

		current_tf = new GAPTextField(15);
		current_tf.setText(InstitutionController.findById(stockoutOrder.getIns_id()).getInsName());
		current_tf.setHorizontalAlignment(JTextField.CENTER);
		current_tf.closeEdit();

		target_tf = new GAPTextField(15);
		target_tf.setText(InstitutionController.findById(stockoutOrder.getTarget_ins()).getInsName());
		target_tf.setHorizontalAlignment(JTextField.CENTER);
		target_tf.closeEdit();
		String type = "";
		transtype_tf = new GAPTextField(5);
		switch (stockoutOrder.getTransport()) {
		case "CAR":
			type = "汽车";
			break;
		case "PLANE":
			type = "飞机";
			break;
		case "TRAIN":
			type = "火车";
			break;
		}
		transtype_tf.setText(type);
		transtype_tf.setHorizontalAlignment(JTextField.CENTER);
		transtype_tf.closeEdit();
		orders_tf = new ArrayList<>();
		for (String order : stockoutOrder.getExpressorder_ids()) {
			order_tf = new GAPTextField(10);
			order_tf.setText(order);
			order_tf.setHorizontalAlignment(JTextField.CENTER);
			order_tf.closeEdit();
			orders_tf.add(order_tf);
		}

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		SwingConsole.addComponent(gb, gcons, this, current_jl, 0, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, current_tf, 1, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, target_jl, 2, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, target_tf, 3, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, transtype_jl, 0, 1, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, transtype_tf, 1, 1, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, order_jl, 2, 1, 1, 1, 0, 0);
		for (int i = 0; i < orders_tf.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, orders_tf.get(i), 3, i + 1, 1, 1, 0, 0);
		}
	}

}
