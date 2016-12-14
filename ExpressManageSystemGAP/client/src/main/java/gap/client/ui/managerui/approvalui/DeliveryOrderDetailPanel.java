package gap.client.ui.managerui.approvalui;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.common.po.DeliveryOrderPO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeliveryOrderDetailPanel extends JPanel {
	// 派件快递员和对应的派件快递
    Map<String, List<String>> deliveryInfo;
	List<ItemPanel> items;
	// 布局
	GridBagLayout gb1;
	GridBagConstraints gcons;

	public DeliveryOrderDetailPanel(DeliveryOrderPO deliveryOrder) {
		// TODO Auto-generated constructor stub
		setBackground(Color.WHITE);
//		setBorder(BorderFactory.createLineBorder(ComponentStyle.gray));
		gb1=new GridBagLayout();
		gcons=new GridBagConstraints();
		items=new ArrayList<>();
		deliveryInfo=deliveryOrder.getDeliveryInfo();
		setLayout(gb1);
		for(Map.Entry<String,List<String>> info:deliveryInfo.entrySet()){
			  items.add(new ItemPanel(info.getKey(),info.getValue()));
		}
		
		gcons.insets=new Insets(5, 5, 5, 5);
		for(int i=0;i<items.size();i++){
			SwingConsole.addComponent(gb1, gcons, this, items.get(i), 0, i, 1, 1, 0, 0);
		}
	}

	class ItemPanel extends JPanel{
		JLabel courierid_jl, orderid_jl;
		List<GAPTextField> ordersid_tf;
		GAPTextField courierid_tf;
		GAPTextField orderid_tf;
		// 布局
		GridBagLayout gb2;

		ItemPanel(String courierid, List<String> ordersid) {
			setBackground(Color.WHITE);
			setBorder(BorderFactory.createLineBorder(ComponentStyle.gray));
            ordersid_tf=new ArrayList<>();
			courierid_jl = new GAPLabel("快递员编号：");
			orderid_jl = new GAPLabel("派件快递单号：");
			courierid_tf=new GAPTextField(9);
			courierid_tf.setText(courierid);
			courierid_tf.closeEdit();
            for(String orderid:ordersid){
            	orderid_tf=new GAPTextField(10);
            	orderid_tf.setText(orderid);
            	orderid_tf.closeEdit();
            	ordersid_tf.add(orderid_tf);            
            }
            
            gb2=new GridBagLayout();
            setLayout(gb2);
            gcons.insets=new Insets(5,5,5,5);
            SwingConsole.addComponent(gb2, gcons, this, courierid_jl, 0, 0, 1, 1, 0, 0);
            SwingConsole.addComponent(gb2, gcons, this, courierid_tf, 1, 0, 1, 1, 0, 0);
            SwingConsole.addComponent(gb2, gcons, this, orderid_jl, 2, 0, 1, 1, 0, 0);
            for(int i=0;i<ordersid_tf.size();i++){
            	SwingConsole.addComponent(gb2, gcons, this, orderid_tf, 3, i, 1, 1, 0, 0);
            }
            
		}
	}
}
