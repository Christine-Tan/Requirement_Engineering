package gap.client.ui.inventoryui.stockinorderinput;

import gap.client.blcontroller.ExpressorderController;
import gap.client.blcontroller.InventoryController;
import gap.client.blcontroller.StockinOrderController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.FlushButton;
import gap.client.ui.gapcomponents.GAPJScrollPane;
import gap.client.ui.gapcomponents.GAPScrollBarUI;
import gap.client.util.LocalInfo;
import gap.client.util.MessageType;
import gap.client.vo.ExpressOrderVO;
import gap.client.vo.GoodsVO;
import gap.client.vo.StockinOrderVO;
import gap.common.po.ArrivedOrderPO;
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
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StockinOrderInputPanel extends MainPanel {
	StockinInfoUI stockinInfo;
	TitlePanel title;
	ButtonArea confirm;
	ListPanel list;
	List<ExpressOrderVO> orders;
	MainFrame mainFrame;
	GAPScrollBarUI scrollBar;
	GridBagLayout gb;
	GridBagConstraints gcons;
	List<ArrivedOrderPO> arrivedOrders;
	String id;
	JButton flushButton;

	public StockinOrderInputPanel(MainFrame frame) {
		super(frame);
		mainFrame = frame;

		flushButton = new FlushButton();
		confirm = new ButtonArea();
		confirm.submit.setText("生成入库单");
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
				MainFrame.setMessage("刷新成功", MessageType.succeed, 3000);
			}

		});

		confirm.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				mainFrame.load(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						StockinOrderVO vo = getStockinOrderVO();
						ResultMessage re = StockinOrderController.save(vo);
						if (re.equals(ResultMessage.SUCCEED)) {
							MainFrame.setMessage("入库单生成成功",
									MessageType.succeed, 3000);
//							System.out.println("设置入库" + id);
							StockinOrderController.setOrderStockin(id);
							refresh();
							String ins_id = LocalInfo.getIns_ID();
							checkAlarm(InventoryController.alarm(ins_id+"1",ins_id));
							checkAlarm(InventoryController.alarm(ins_id+"2",ins_id));
							checkAlarm(InventoryController.alarm(ins_id+"3",ins_id));
							checkAlarm(InventoryController.alarm(ins_id+"0",ins_id));

						} else {
							MainFrame.setMessage("入库单为空", MessageType.alram,
									3000);
						}
					}
				});
				
			}
		});

	}
	
	public void checkAlarm(String s){
		
		if(s!=null){
			String[] splits = s.split(" ");
			AlarmDialog alarm = new AlarmDialog(mainFrame, splits[0], Double.parseDouble(splits[1]));
		}
		
		
	}

	public void setSelected(Boolean bool) {
		for (ListItem item : list.items) {
			item.setSelected(bool);
		}
	}

	public StockinOrderVO getStockinOrderVO() {

		List<GoodsVO> goods = list.getGoodsVOs();
		String inDate = stockinInfo.getInDate();
		String id = stockinInfo.getId();
		String ins_id = LocalInfo.ins_id;

		StockinOrderVO vo = new StockinOrderVO(goods, inDate, id, ins_id);
		return vo;
	}

	public void initial() {
		stockinInfo = new StockinInfoUI();
		title = new TitlePanel();
		title.box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int state = e.getStateChange();
				if (state == ItemEvent.SELECTED) {
					setSelected(true);
				} else {
					setSelected(false);
				}

			}
		});

		orders = new ArrayList<ExpressOrderVO>();
		arrivedOrders = new ArrayList<ArrivedOrderPO>();
		arrivedOrders = StockinOrderController.getArrivedOrderPO(LocalInfo
				.getIns_ID());
		if (arrivedOrders != null && arrivedOrders.size() > 0) {
			id = arrivedOrders.get(0).getID();
//			System.out.println("初始化：" + id);
			Set<String> ids = arrivedOrders.get(0).getOrders().keySet();
			if (ids != null && ids.size() > 0) {
				orders = ExpressorderController
						.getByOrderIdList(new ArrayList<>(ids));
			}
		} else {
			MainFrame.setMessage("所有到达单已安排入库", MessageType.succeed, 2000);
		}

		list = new ListPanel(orders);
	}

	public void reLayout() {
		removeAll();
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		
		gcons.insets = new Insets(10, 740, 0, -20);
		SwingConsole.addComponent(gb, gcons, this, flushButton, 0, 0, 1, 1, 1,
				0);
		
		gcons.insets = new Insets(0, 0, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, stockinInfo, 0, 1, 1, 1, 1,
				0);
		SwingConsole.addComponent(gb, gcons, this, title, 0, 2, 1, 1, 1, 0);

		GAPJScrollPane js = new GAPJScrollPane(list);
		js.setPreferredSize(new Dimension(Default.PANEL_WIDTH, Math.min(
				list.items.size(), 8) * 50 + 20));

		SwingConsole.addComponent(gb, gcons, this, js, 0, 3, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, panel, 0, 4, 1, 1, 1, 1);
		SwingConsole.addComponent(gb, gcons, this, confirm, 0, 5, 1, 1, 1, 0);
		title.box.setSelected(true);
		mainFrame.validate();
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		initial();
		reLayout();
	}
}
