package gap.client.ui.inventoryui.stockoutorderinput;

import gap.client.blcontroller.InstitutionController;
import gap.client.blcontroller.StockoutOrderController;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPComboBox;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.util.LocalInfo;
import gap.client.vo.InstitutionVO;
import gap.common.util.InstitutionType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StockoutInfoPanel extends JPanel {
	JLabel title, outDate, targetIns, id, transport;
	GAPTextField outDate_text, id_text;
	JComboBox<String> transport_list, targetIns_list;
	String managerins = "0000000";

	public StockoutInfoPanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 120));

		title = new GAPLabel("出库单信息：");

		outDate = new GAPLabel("出库日期：");
		outDate_text = new GAPTextField(10);
		outDate_text.setText((new Date(System.currentTimeMillis())).toString());
		outDate_text.closeEdit();

		targetIns = new GAPLabel("目的地：");
		initialTransList();
//		targetIns_list.setRenderer(new MyCellRenderer());

		id = new GAPLabel("中转单编号：");
		id_text = new GAPTextField(20);
		id_text.setText(getNextId());
		id_text.closeEdit();

		transport = new GAPLabel("货运方式：");
		transport_list = new GAPComboBox<String>();
		transport_list.setPreferredSize(new Dimension(185, 25));
		transport_list.addItem("汽车运输");
		transport_list.addItem("铁路运输");
		transport_list.addItem("航空运输");
//		transport_list.setRenderer(new MyCellRenderer());
		

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(10, 10, 10, 10);
		gcons.anchor = GridBagConstraints.EAST;
		SwingConsole.addComponent(gb, gcons, this, title, 0, 0, 1, 1, 1, 0);
		gcons.anchor = GridBagConstraints.EAST;
		SwingConsole.addComponent(gb, gcons, this, outDate, 1, 0, 1, 1, 1, 0);
		gcons.anchor = GridBagConstraints.WEST;
		SwingConsole.addComponent(gb, gcons, this, outDate_text, 2, 0, 1, 1, 1,
				0);
		gcons.anchor = GridBagConstraints.EAST;
		gcons.insets = new Insets(10, 0, 10, 10);
		SwingConsole.addComponent(gb, gcons, this, targetIns, 3, 0, 1, 1, 1, 0);
		gcons.insets = new Insets(10, 10, 10, 10);
		gcons.anchor = GridBagConstraints.WEST;
		SwingConsole.addComponent(gb, gcons, this, targetIns_list, 4, 0, 1, 1,
				1, 0);
		gcons.anchor = GridBagConstraints.EAST;
		SwingConsole.addComponent(gb, gcons, this, id, 1, 1, 1, 1, 1, 0);
		gcons.anchor = GridBagConstraints.WEST;
		SwingConsole.addComponent(gb, gcons, this, id_text, 2, 1, 1, 1, 1, 0);
		gcons.anchor = GridBagConstraints.EAST;
		gcons.insets = new Insets(10, 0, 10, 10);
		SwingConsole.addComponent(gb, gcons, this, transport, 3, 1, 1, 1, 1, 0);
		gcons.insets = new Insets(10, 10, 10, 10);
		gcons.anchor = GridBagConstraints.WEST;
		SwingConsole.addComponent(gb, gcons, this, transport_list, 4, 1, 1, 1,
				1, 0);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = RenderSetter.OpenRender(g);
		g2d.setColor(ComponentStyle.light_gray);

		int width = getWidth(), height = getHeight();
		g2d.drawLine(10, height - 5, width - 20, height - 5);
	}
	
	public void initialTransList(){
		targetIns_list = new GAPComboBox<String>();
		targetIns_list.setPreferredSize(new Dimension(185, 25));
		InstitutionVO ins = InstitutionController.findById(LocalInfo.ins_id);
		List<InstitutionVO> insList = new ArrayList<InstitutionVO>();
		String insName = ins.getInsName();
		String localCity = ins.getInsCity();
		
		insList = InstitutionController.getAll();
		
		for(InstitutionVO vo:insList){
			String ins_id = vo.getInsId();
			if(!ins_id.equals(managerins)){
				if(vo.getInsCity().equals(localCity)){
					if(!vo.getInsName().equals(insName)){
						targetIns_list.addItem(vo.getInsName());
					}
				}else{
					if(isCenter(ins_id)){
						targetIns_list.addItem(vo.getInsName());
					}
					
				}
			}
			
//			if(!isCenter(ins_id)){
//				if(!vo.getInsCity().equals(localCity)){
//					targetIns_list.addItem(vo.getInsName());
//				}
//				
//			}else{
//				if(!vo.getInsName().equals(insName)){
//					targetIns_list.addItem(vo.getInsName());
//				}
//			}
			
		}
		
		
		
	}
	
	public boolean isCenter(String ins_id){
		if(InstitutionType.getInsType(ins_id).equals(InstitutionType.CENTER)){
			return true;
		}else{
			return false;
		}
	}
	
//	class MyCellRenderer extends JLabel implements ListCellRenderer {     
//		public MyCellRenderer() {         
//			setOpaque(true);     
//		}     
//		@Override
//		public java.awt.Component getListCellRendererComponent(JList list,
//				Object value, int index, boolean isSelected,
//				boolean cellHasFocus) {
//			// TODO Auto-generated method stub
//			setBackground(Color.white);
//			setHorizontalAlignment(SwingConstants.CENTER);
//			setText(value.toString());
//			return this;
//		}
//		
//	}
	
	public String getNextId(){
		String date = outDate_text.getText();
		date = date.replaceAll("-", "");
		String cons = LocalInfo.getIns_ID()+date;
		return StockoutOrderController.getNextId(cons);
		
	}
	
	public String getTargetIns(){
		return (String)targetIns_list.getSelectedItem();
	}
	
	public String getTransport(){
		return (String)transport_list.getSelectedItem();
	}
	
	public String getOutDate(){
		return this.outDate_text.getText();
	}
	
	public String getId(){
		return this.id_text.getText();
	}
	
	
}
