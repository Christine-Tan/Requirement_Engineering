package gap.client.ui.inventoryui.observestock;

import gap.client.blcontroller.InventoryController;
import gap.client.blcontroller.StockinOrderController;
import gap.client.blcontroller.StockoutOrderController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.GAPJScrollPane;
import gap.client.util.LocalInfo;
import gap.client.util.MessageType;
import gap.client.vo.StockinOrderVO;
import gap.client.vo.StockoutOrderVO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ObserveStockPanel extends MainPanel {
	ButtonArea confirm;
	TitlePanel stockinTitle, stockoutTitle;
	ListPanel stockinList, stockoutList;
	TotalNumPanel stockinTotal, stockoutTotal, totalNum;
	PeriodPanel period;
	String beginDate, endDate;
	GridBagLayout gb;
	GridBagConstraints gcons;
	MainFrame mainFrame;

	public ObserveStockPanel(MainFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
		// List<StockinOrderVO> inList = new ArrayList<StockinOrderVO>();
		// List<StockoutOrderVO> outList = new ArrayList<StockoutOrderVO>();
		// List<InventoryOrder> InList =
		// InventoryOrder.transformInOrder(inList);
		// List<InventoryOrder> OutList =
		// InventoryOrder.transformOutOrder(outList);
		// stockinList = new ListPanel(InList);
		// stockoutList = new ListPanel(OutList);
		this.mainFrame = frame;

		confirm = new ButtonArea();
		confirm.submit.setText("确认");
		stockinTitle = new TitlePanel("入库");
		stockoutTitle = new TitlePanel("出库");
		String total = InventoryController.getTotalNum(LocalInfo.getIns_ID())
				+ "";
		totalNum = new TotalNumPanel("库存", total);
		period = new PeriodPanel();

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		gcons.fill = GridBagConstraints.BOTH;
		setLayout(gb);
		firstLayout();
		
//		mainFrame.load(new Runnable(){
//			public void run(){
//				refresh();
//			}
//		});
		

		period.confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				mainFrame.load(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						initialListPanel();
					}
					
				});
				
				MainFrame.setMessage("出入库数量统计结束", MessageType.succeed, 2000);
			}
		});
		
		confirm.submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainFrame.setMessage("不要走啊，亲，再看一遍呀~~~~", MessageType.succeed, 2000);
			}
		});

	}

	void initialListPanel() {
		// frame.removeAll();
		beginDate = period.getBeginDate();
		endDate = period.getEndDate();
		List<StockinOrderVO> inList = StockinOrderController.getRequired(
				beginDate, endDate, LocalInfo.getIns_ID());
		List<StockoutOrderVO> outList = StockoutOrderController.getRequired(
				beginDate, endDate, LocalInfo.getIns_ID());

		if (inList == null) {
			System.out.println("inList NULL");
			inList = new ArrayList<StockinOrderVO>();
		}
		List<InventoryOrder> InList = InventoryOrder.transformInOrder(inList);
		
		if (outList == null) {
			System.out.println("outList NULL");
			outList = new ArrayList<StockoutOrderVO>();
		}
		List<InventoryOrder> OutList = InventoryOrder
				.transformOutOrder(outList);
		stockinList = new ListPanel(InList);
		stockoutList = new ListPanel(OutList);

		String inTotal = StockinOrderController.getTotalNum(inList) + "";
		stockinTotal = new TotalNumPanel("入库", inTotal);

		String outTotal = StockoutOrderController.getTotalNum(outList) + "";
		stockoutTotal = new TotalNumPanel("出库", outTotal);
		
//		String totoal = InventoryController.getTotalNum(LocalInfo.getIns_ID())+"";
//		totalNum = ne
		reLayout();
	}

	void reLayout() {
		removeAll();
		JPanel panel0 = new JPanel();
		panel0.setBackground(Color.white);
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.white);
//		panel1.setPreferredSize(new Dimension(Default.PANEL_WIDTH, 60));
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.white);
		panel2.setPreferredSize(new Dimension(Default.PANEL_WIDTH, 30));
		
		SwingConsole.addComponent(gb, gcons, this, period, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, stockinTitle, 0, 1, 1, 1, 1,
				0);
		GAPJScrollPane js1 = new GAPJScrollPane(stockinList);
		js1.setPreferredSize(new Dimension(Default.PANEL_WIDTH, Math.min(stockinList.items.size(),5)*50+20));
		
		SwingConsole.addComponent(gb, gcons, this, js1, 0, 2, 1, 1, 1,
				1);
		SwingConsole.addComponent(gb, gcons, this, stockinTotal, 0, 3, 1, 1, 1,
				0);
		SwingConsole.addComponent(gb, gcons, this, panel1, 0, 4, 1, 1, 1, 0.1);;
		SwingConsole.addComponent(gb, gcons, this, stockoutTitle, 0, 5, 1, 1,
				1, 0);
		GAPJScrollPane js2 = new GAPJScrollPane(stockoutList);
		js2.setPreferredSize(new Dimension(Default.PANEL_WIDTH, Math.min(stockoutList.items.size(),5)*50+20));
		SwingConsole.addComponent(gb, gcons, this, js2, 0, 6, 1, 1, 1,
				1);
		SwingConsole.addComponent(gb, gcons, this, stockoutTotal, 0, 7, 1, 1,
				1, 0);
		SwingConsole.addComponent(gb, gcons, this, panel0, 0, 8, 1, 1, 1, 0.1);
		SwingConsole.addComponent(gb, gcons, this, totalNum, 0, 9, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, panel2, 0, 10, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, confirm, 0, 11, 1, 1, 1, 0);

		mainFrame.validate();

	}
	
	public void initial(){
		period.beginDate.setText(null);
		period.endDate.setText(null);
	}
	
	public void firstLayout(){
		removeAll();
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.white);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.white);
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.white);
		
		panel3.setPreferredSize(new Dimension(Default.PANEL_WIDTH,30));

		SwingConsole.addComponent(gb, gcons, this, period, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, stockinTitle, 0, 1, 1, 1, 1,
				0);
		SwingConsole.addComponent(gb, gcons, this, panel1, 0, 2, 1, 1, 1, 1);
		SwingConsole.addComponent(gb, gcons, this, stockoutTitle, 0, 4, 1, 1,
				1, 0);
		SwingConsole.addComponent(gb, gcons, this, panel2, 0, 5, 1, 1, 1, 1);
		SwingConsole.addComponent(gb, gcons, this, totalNum, 0, 8, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, panel3, 0, 9, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, confirm, 0, 11, 1, 1, 1, 0);
		mainFrame.validate();
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		initial();
		firstLayout();
		
	}

}
