package gap.client.ui.deliveryui.expressorderinput;

import gap.client.blcontroller.ExpressorderController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.GAPDialog;
import gap.client.util.MessageType;
import gap.client.vo.ExpressOrderVO;
import gap.common.util.ResultMessage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ExpressorderInputPanel extends MainPanel {
	PeopleInfoUI sender, receiver;
	CargoInfoUI cargo;
	ExpressInfoUI express;
	ButtonArea buttonArea;

	public ExpressorderInputPanel(MainFrame frame) {
		super(frame);

		// 初始化组件
		sender = new PeopleInfoUI("寄件人信息：");
		receiver = new PeopleInfoUI("收件人信息：");
		cargo = new CargoInfoUI();
		express = new ExpressInfoUI();
		buttonArea = new ButtonArea();
		buttonArea.submit.setText("提交订单");
		express.price_get.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				ExpressOrderVO vo = getExpressOrderVO();
				if (vo != null && vo.cargoInfo != null) {
					vo = ExpressorderController.createOrder(vo);
					express.setPrice(vo.price);
					express.setTime(ExpressorderController.getDeliveryTime(
							vo.sender_info.getAddress().getCity_name(),
							vo.receiver_info.getAddress().getCity_name(),
							vo.expressType));
				}
			}
		});

		buttonArea.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				mainFrame.load(new Runnable() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						ExpressOrderVO vo = getExpressOrderVO();
						if (vo != null) {
							vo = ExpressorderController.createOrder(vo);
							ResultMessage re = ExpressorderController.save(vo);
							if (re.equals(ResultMessage.SUCCEED)) {
								MainFrame.setMessage("订单添加成功",
										MessageType.succeed, 2000);
								GAPDialog gapDia = new GAPDialog("添加成功",
										mainFrame);
								gapDia.setBounds(
										mainFrame.getLocation().x
												+ (mainFrame.getWidth() - 400)
												/ 2,
										mainFrame.getLocation().y
												+ (mainFrame.getHeight() - 250)
												/ 2, 400, 250);
								gapDia.showMessage("订单添加成功", "您的订单号是:",
										vo.order_id);
								refresh();
							} else {
								MainFrame.setMessage("订单提交失败",
										MessageType.alram, 2000);
							}
						}
					}
				});

			}
		});

		// 布局
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.fill = GridBagConstraints.HORIZONTAL;
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.white);
		panel1.setPreferredSize(new Dimension(850, 20));
		// sender.setBackground(Color.red);
		SwingConsole.addComponent(gb, gcons, this, panel1, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, sender, 0, 1, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, receiver, 0, 2, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, cargo, 0, 3, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, express, 0, 4, 1, 1, 1, 0);
		gcons.fill = GridBagConstraints.BOTH;
		SwingConsole.addComponent(gb, gcons, this, panel, 0, 5, 1, 1, 1, 1);
		gcons.fill = GridBagConstraints.HORIZONTAL;
		SwingConsole
				.addComponent(gb, gcons, this, buttonArea, 0, 7, 1, 1, 1, 0);
	}

	/**
	 * 获得订单VO
	 * @return
	 */
	public ExpressOrderVO getExpressOrderVO() {
		ExpressOrderVO vo = new ExpressOrderVO();
		if ((vo.sender_info = sender.getInfo()) == null)
			return null;
		if ((vo.receiver_info = receiver.getInfo()) == null)
			return null;
		if ((vo.cargoInfo = cargo.getCargo()) == null)
			return null;
		vo.expressType = express.getType();
		return vo;
	}

	@Override
	public void refresh() {
		// TODO 自动生成的方法存根
		cargo.reSet();
		sender.reSet();
		receiver.reSet();
		express.reSet();
	}
}
