package gap.client.ui.managerui.approvalui;

import gap.client.blcontroller.ApprovalController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.GAPJScrollPane;
import gap.client.ui.managerui.approvalui.OrderItemListPanel.ItemPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author seven
 * 
 *         总经理审批单据界面
 */
public class ApprovalPanel extends MainPanel {
	TotalPanel totalPanel;
	ApprovalTitlePanel titlePanel;
	OrderItemListPanel listItemPanel;
	ButtonArea buttonArea;
	GridBagLayout gb;
	GridBagConstraints gcons;
	Timer timer;
	final MainFrame frame;

	public ApprovalPanel(MainFrame frame) {
		// TODO Auto-generated constructor stub
		super(frame);
		setBackground(Color.white);
		this.frame = frame;
		MyTask task = new MyTask();
		timer = new Timer(true);
		timer.schedule(task, 1000, 20000);

	}

	public synchronized void refresh() {
		ApprovalPanel.this.removeAll();
		totalPanel = new TotalPanel();
		titlePanel = new ApprovalTitlePanel();
		listItemPanel = new OrderItemListPanel(frame);
		buttonArea = new ButtonArea();
		buttonArea.submit.setText("确认审批");
		// 给自动刷新按钮添加监听
		totalPanel.refresh_b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ApprovalPanel.this.refresh();
			}
		});
		// 给全选按钮添加监听
		titlePanel.select.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				int state = arg0.getStateChange();
				if (state == ItemEvent.SELECTED) {
					for (ItemPanel item : listItemPanel.items) {
						item.setSelected(true);
					}
				} else {
					for (ItemPanel item : listItemPanel.items) {
						item.setSelected(false);
					}
				}
			}
		});

		// 审批按钮添加监听
		buttonArea.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				List<Object> orders = new ArrayList<>();
				for (ItemPanel item : listItemPanel.items) {
					if (item.select.isSelected()) {
						orders.add(item.order);
					}
				}
				ApprovalController.approve(orders);
				refresh();
			}
		});

		// 布局
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		JPanel jp = new JPanel();
		jp.setOpaque(false);
		JPanel jp1=new JPanel();
		jp1.setLayout(gb);
//		jp1.setOpaque(false);
		jp1.setBackground(Color.white);
		SwingConsole.addComponent(gb, gcons, jp1, listItemPanel, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, jp1, jp, 0, 1, 1, 1, 1, 1);
//		GAPJScrollPane js = new GAPJScrollPane(listItemPanel);
		GAPJScrollPane js = new GAPJScrollPane(jp1);
		js.setBackground(Color.white);
		js.setBorder(BorderFactory.createEmptyBorder());
		js.setPreferredSize(new Dimension(Default.PANEL_WIDTH, 485));
		SwingConsole.addComponent(gb, gcons, this, totalPanel, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, titlePanel, 0, 1, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, js, 0, 2, 1, 1, 1, 0);
//		SwingConsole.addComponent(gb, gcons, this, jp, 0, 3, 1, 1, 1, 1);
		SwingConsole.addComponent(gb, gcons, this, buttonArea, 0, 3, 1, 1, 1, 0);
		frame.validate();
	}

	class MyTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			frame.load(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ApprovalPanel.this.refresh();
				}
			});
		}
	}
}
