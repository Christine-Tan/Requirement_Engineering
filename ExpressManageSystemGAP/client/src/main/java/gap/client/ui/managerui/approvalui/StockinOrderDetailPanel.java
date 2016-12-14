package gap.client.ui.managerui.approvalui;

import gap.client.blcontroller.InstitutionController;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.common.po.GoodsPO;
import gap.common.po.StockinOrderPO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StockinOrderDetailPanel extends JPanel {
	JLabel ins_jl, order_jl;
	GAPTextField ins_tf, order_tf;
	List<GAPTextField> orders_tf;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	public StockinOrderDetailPanel(StockinOrderPO stockinOrder) {
		// TODO Auto-generated constructor stub
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(ComponentStyle.gray));
		
        orders_tf=new ArrayList<>();
		ins_jl=new GAPLabel("所属中转中心：");
		order_jl=new GAPLabel("待入库快递编号：");
	 	
		ins_tf=new GAPTextField(15);
	 	ins_tf.setText(InstitutionController.findById(stockinOrder.getIns_id()).getInsName());
	 	ins_tf.setHorizontalAlignment(JTextField.CENTER);
	 	ins_tf.closeEdit();
	 	
	 	for(GoodsPO good:stockinOrder.getGoods()){
	 	     order_tf=new GAPTextField(10);
	 	     order_tf.setText(good.getExpressorder_id());
	 	     order_tf.setHorizontalAlignment(JTextField.CENTER);
	 	     order_tf.closeEdit();
	 	     orders_tf.add(order_tf);
	 	}
	 	
		gb=new GridBagLayout();
		gcons=new GridBagConstraints();
		setLayout(gb);
		SwingConsole.addComponent(gb, gcons, this, ins_jl, 0, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, ins_tf, 1, 0, 1, 1, 0, 0);
		SwingConsole.addComponent(gb, gcons,this, order_jl, 2, 0, 1, 1, 0, 0);
		for(int i=0;i<orders_tf.size();i++){
			SwingConsole.addComponent(gb, gcons, this, orders_tf.get(i), 3, i, 1, 1, 0, 0);
		}
		
	}

}
