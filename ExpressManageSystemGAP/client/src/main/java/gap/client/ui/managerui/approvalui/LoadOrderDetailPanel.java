package gap.client.ui.managerui.approvalui;

import gap.client.blcontroller.InstitutionController;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.common.po.LoadOrderPO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoadOrderDetailPanel extends JPanel {
	JLabel from_jl, to_jl, car_jl, driver_jl, looker_jl;
	GAPTextField from_tf, to_tf, car_tf, driver_tf, looker_tf;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	public LoadOrderDetailPanel(LoadOrderPO loadOrder) {
		// TODO Auto-generated constructor stub
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(ComponentStyle.gray));

		from_jl = new GAPLabel("离开机构：");
		to_jl = new GAPLabel("到达机构：");
		car_jl = new GAPLabel("车辆编号：");
		driver_jl = new GAPLabel("司机编号：");
		looker_jl = new GAPLabel("监装员编号：");

		from_tf = new GAPTextField(15);
		from_tf.setText(InstitutionController.findById(loadOrder.getDepartureins_id()).getInsName());
		to_tf = new GAPTextField(15);
		to_tf.setText(InstitutionController.findById(loadOrder.getTargetins_id()).getInsName());
		car_tf = new GAPTextField(10);
		car_tf.setText(loadOrder.getCar_number());
		driver_tf = new GAPTextField(10);
		driver_tf.setText(loadOrder.getDriver_id());
		looker_tf = new GAPTextField(9);
		looker_tf.setText(loadOrder.getGuard_id());
		setStyle();
		closeEdit();

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		gcons.insets = new Insets(5, 5, 5, 5);
		SwingConsole.addComponent(gb, gcons, this, from_jl, 0, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, from_tf, 1, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, to_jl, 2, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, to_tf, 3, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, car_jl, 0, 1, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, car_tf, 1, 1, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, driver_jl, 2, 1, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, driver_tf, 3, 1, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, looker_jl, 0, 2, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, looker_tf, 1, 2, 1, 1, 0, 0);
	}

	private void setStyle() {
		from_tf.setHorizontalAlignment(JTextField.CENTER);
		to_tf.setHorizontalAlignment(JTextField.CENTER);
		car_tf.setHorizontalAlignment(JTextField.CENTER);
		driver_tf.setHorizontalAlignment(JTextField.CENTER);
		looker_tf.setHorizontalAlignment(JTextField.CENTER);
	}

	private void closeEdit() {
		from_tf.closeEdit();
		to_tf.closeEdit();
		car_tf.closeEdit();
		driver_tf.closeEdit();
		looker_tf.closeEdit();
	}
}
