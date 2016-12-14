package gap.client.ui.bussinessui.loadorder;

import gap.client.blcontroller.LoadOrderController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.FlushButton;
import gap.client.util.LocalInfo;
import gap.client.util.MessageType;
import gap.client.vo.LoadOrderVO;
import gap.common.util.ResultMessage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LoadOrderPanel extends MainPanel {
	JButton flushButton;
	CarAndDriverPanel carAndDriver;
	OrderPanel orderPanel;
	ButtonArea buttonArea;
	DesAddressPanel desAddressPanel;

	public LoadOrderPanel(MainFrame frame) {
		super(frame);
		flushButton = new FlushButton();
		carAndDriver = new CarAndDriverPanel();
		orderPanel = new OrderPanel(frame);
		buttonArea = new ButtonArea();
		desAddressPanel = new DesAddressPanel();
		buttonArea.submit.setText("生成装车单");
		buttonArea.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				mainFrame.load(new Runnable() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						LoadOrderVO vo = getLoadOrder();
						if (vo.orders.size() == 0) {
							MainFrame.setMessage("请选择订单", MessageType.alram,
									2000);
						} else {
							LoadOrderController.setPrice(vo);
							ResultMessage re = LoadOrderController.save(vo);
							System.out.println("装车单的价格为：" + vo.price);
							if (re.equals(ResultMessage.SUCCEED)) {
								MainFrame.setMessage("生成成功",
										MessageType.succeed, 2000);
							}
							orderPanel.refresh();
						}
					}
				});

			}
		});

		mainFrame.load(new Runnable() {

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				refresh();
			}
		});

		flushButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				mainFrame.load(new Runnable() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						refresh();
					}
				});
			}
		});

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		JPanel jp = new JPanel();
		jp.setOpaque(false);
		// gcons.anchor = GridBagConstraints.EAST;
		gcons.insets = new Insets(10, 700, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, flushButton, 0, 0, 1, 1, 0,
				0);
		gcons.insets = new Insets(0, 0, 0, 0);
		SwingConsole.addComponent(gb, gcons, this, carAndDriver, 0, 1, 1, 1, 1,
				0);
		SwingConsole
				.addComponent(gb, gcons, this, orderPanel, 0, 2, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, jp, 0, 3, 1, 1, 1, 1);
		SwingConsole.addComponent(gb, gcons, this, desAddressPanel, 0, 4, 1, 1,
				1, 0);
		SwingConsole
				.addComponent(gb, gcons, this, buttonArea, 0, 5, 1, 1, 1, 0);
	}

	public LoadOrderVO getLoadOrder() {
		LoadOrderVO loadOrder = new LoadOrderVO();
		loadOrder.car_id = carAndDriver.getCar().getCar_id();
		loadOrder.driver_id = carAndDriver.getDriver().getId();
		loadOrder.departureins_id = LocalInfo.ins_id;
		loadOrder.guard_id = LocalInfo.getUserID();
		loadOrder.orders = orderPanel.getOrders();
		loadOrder.date = (new Date(System.currentTimeMillis())).toString();
		loadOrder.comment = desAddressPanel.getComment();
		loadOrder.targetins_id = desAddressPanel.getTargetIns_id();
		return loadOrder;
	}

	@Override
	public void refresh() {
		// TODO 自动生成的方法存根
		carAndDriver.refresh();
		orderPanel.refresh();
		mainFrame.validate();
	}

}
