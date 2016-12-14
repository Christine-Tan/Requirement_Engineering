package gap.client.ui.inventoryui.stockoutorderinput;

import gap.client.blcontroller.InstitutionController;
import gap.client.blcontroller.InventoryController;
import gap.client.blcontroller.StockoutOrderController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.FlushButton;
import gap.client.ui.gapcomponents.GAPJScrollPane;
import gap.client.util.LocalInfo;
import gap.client.util.MessageType;
import gap.client.util.Transport;
import gap.client.vo.GoodsVO;
import gap.client.vo.StockoutOrderVO;
import gap.common.util.ResultMessage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StockoutOrderInputPanel extends MainPanel {
	StockoutInfoPanel stockoutInfo;
	ListPanel list;
	ButtonArea confirm;
	TitlePanel title;
	List<GoodsVO> voList;
	ChoosePanel choose;
	MainFrame mainFrame;
	GridBagLayout gb;
	GridBagConstraints gcons;
	JButton flushButton;

	public StockoutOrderInputPanel(MainFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
		this.mainFrame = frame;
		flushButton = new FlushButton();
		choose = new ChoosePanel();
		stockoutInfo = new StockoutInfoPanel();
		confirm = new ButtonArea();
		confirm.submit.setText("生成出库单");
		title = new TitlePanel();
		initial();

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		gcons.fill = GridBagConstraints.BOTH;
		setLayout(gb);

		reLayout();
		
		flushButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				mainFrame.load(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						refresh();
					}
				});
				MainFrame.setMessage("刷新成功", MessageType.succeed, 2000);
			}

		});
		
		title.box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int state = e.getStateChange();
				if(state == ItemEvent.SELECTED){
					setSelected(true);
				}else{
					setSelected(false);
				}
			}
		});
		
		choose.car.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				initialList(LocalInfo.ins_id+"1");
				list = new ListPanel(voList);
				choose.plane.toNomal();
				choose.train.toNomal();
				reLayout();
			}
		});
		
		choose.train.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				initialList(LocalInfo.ins_id+"2");
				list = new ListPanel(voList);
				choose.car.toNomal();
				choose.plane.toNomal();
				reLayout();
			}
		});

		choose.plane.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				initialList(LocalInfo.ins_id+"3");
				list = new ListPanel(voList);
				choose.car.toNomal();
				choose.train.toNomal();
				reLayout();
			}
		});
		
		confirm.submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				mainFrame.load(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						StockoutOrderVO vo = getStockoutOrderVO();
						ResultMessage re = StockoutOrderController.save(vo);
						if(re.equals(ResultMessage.SUCCEED)){
							MainFrame.setMessage("出库单生成成功", MessageType.succeed, 3000);
							refresh();
						}else{
							MainFrame.setMessage("出库单为空", MessageType.alram, 3000);
						}
					}
					
				});
				
			}
		});
	}
	
	public void initialList(String sector_id){
		stockoutInfo = new StockoutInfoPanel();
		voList = InventoryController.getOneSectorExisted(sector_id, LocalInfo.ins_id);
		if(voList.size()==0){
			MainFrame.setMessage("该分区快递为空", MessageType.normal, 3000);
		}else{
			MainFrame.setMessage("请选择出库快递", MessageType.normal, 3000);
		}
	}
	
	public void reLayout(){
		removeAll();
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		
		gcons.insets = new Insets(10, 740, 0, -20);
		SwingConsole.addComponent(gb, gcons, this, flushButton, 0, 0, 1, 1, 1,
				0);
		
		gcons.insets = new Insets(0, 0, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, stockoutInfo, 0, 1, 1, 1, 1,
				0);
		SwingConsole.addComponent(gb, gcons, this, choose, 0, 2, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, title, 0, 3, 1, 1, 1, 0);
		
		GAPJScrollPane js = new GAPJScrollPane(list);
		js.setPreferredSize(new Dimension(Default.PANEL_WIDTH,Math.min(list.items.size(),8)*50+15));
		
		
		SwingConsole.addComponent(gb, gcons, this, js, 0, 4, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, panel, 0, 5, 1, 1, 1, 1);
		SwingConsole.addComponent(gb, gcons, this, confirm, 0, 6, 1, 1, 1, 0);
		mainFrame.validate();
	}
	
	public void setSelected(boolean bool){
		for(ListItem item:list.items){
			item.setSelected(bool);
		}
	}
	
	public StockoutOrderVO getStockoutOrderVO(){
		
		List<String> ids = list.getOrderIds();
		String date = stockoutInfo.getOutDate();
		String target_insName = stockoutInfo.getTargetIns();
		String target_ins = InstitutionController.findByName(target_insName).getInsId();
		String id = stockoutInfo.getId();
		String transport = Transport.getTransportByName(stockoutInfo.getTransport()).toString();
		StockoutOrderVO vo = new StockoutOrderVO(ids, date, target_ins, id, transport, LocalInfo.ins_id);
		return vo;
		
	}
	
	public void initial(){
		choose.car.setSelected();
		choose.train.toNomal();
		choose.plane.toNomal();
		voList = new ArrayList<GoodsVO>();
		initialList(LocalInfo.ins_id+"1");
		
		
		list = new ListPanel(voList);
		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		initial();
		reLayout();
	}

}
