package gap.client.ui.bussinessui.deliveryorder;

import gap.client.blcontroller.DeliveryOrderController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.util.MessageType;
import gap.client.vo.DeliveryOrderVO;
import gap.common.util.ResultMessage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 快递员选择列表
 * @author YangYanfei
 *
 */
public class DeliveryOrderPanel extends MainPanel {
	MainContentPanel mainContentPanel;
	DeliverySelectPanel deliverySelect;
	ButtonArea buttonArea;

	public DeliveryOrderPanel(MainFrame frame) {
		super(frame);
		setBackground(Color.red);
		mainContentPanel = new MainContentPanel(frame);
		deliverySelect = new DeliverySelectPanel(frame);
		mainContentPanel.setDeleverySelectPanel(deliverySelect);
		deliverySelect.setMainContentPanel(mainContentPanel);

		deliverySelect.refresh();

		buttonArea = new ButtonArea();
		buttonArea.submit.setText("生成派件单");
		buttonArea.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				mainFrame.load(new Runnable() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						DeliveryOrderVO delivery = getDeliveryOrder();
						if (delivery.deliveryInfo.isEmpty()) {
							MainFrame.setMessage("请选择快递员和订单",
									MessageType.alram, 2000);
						} else {
							ResultMessage re = DeliveryOrderController
									.save(delivery);
							if (re.equals(ResultMessage.SUCCEED))
								MainFrame.setMessage("提交派件单成功",
										MessageType.succeed, 2000);
							else
								MainFrame.setMessage("提交派件单失败",
										MessageType.alram, 2000);
							refresh();
						}
					}
				});
			}
		});

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		gcons.fill = GridBagConstraints.BOTH;
		setLayout(gb);
		// gcons.ipadx = 200;
		SwingConsole.addComponent(gb, gcons, this, deliverySelect, 0, 0, 1, 1,
				0, 1);
		gcons.ipadx = 0;
		SwingConsole.addComponent(gb, gcons, this, mainContentPanel, 1, 0, 1,
				1, 1, 1);
		SwingConsole
				.addComponent(gb, gcons, this, buttonArea, 0, 1, 2, 1, 1, 0);
	}

	public void refresh() {
		deliverySelect.refresh();
		mainContentPanel.refresh();
		mainFrame.validate();
		
	}

	DeliveryOrderVO getDeliveryOrder() {
		DeliveryOrderVO vo = new DeliveryOrderVO();
		vo.deliveryInfo = mainContentPanel.getDeliveryInfo();
		return vo;
	}

}
