package gap.client.ui.bussinessui.carmanage;

import gap.client.blcontroller.CarManageController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.util.MessageType;
import gap.common.util.ResultMessage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class CarManagePanel extends MainPanel {
	CarQueryPanel queryPanel;
	TitlePanel titlePanel;
	CarListItemPanel listItem;
	ButtonArea buttonArea;

	public CarManagePanel(MainFrame frame) {
		super(frame);
		queryPanel = new CarQueryPanel();
		titlePanel = new TitlePanel();
		listItem = new CarListItemPanel(frame);
		buttonArea = new ButtonArea();
		buttonArea.submit.setText("提交修改");
		// buttonArea.submit.setIcon(new ImageIcon(
		// "images\\deliveryIcon\\submit.png"));
		buttonArea.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				mainFrame.load(new Runnable() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						ResultMessage re = CarManageController.flush();
						if (re.equals(ResultMessage.SUCCEED)) {
							MainFrame.setMessage("修改成功", MessageType.succeed,
									2000);
							refresh();
						} else {
							MainFrame.setMessage("修改失败", MessageType.alram,
									2000);
						}
					}
				});
			}
		});

		queryPanel.search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				mainFrame.load(new Runnable() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						listItem.refresh(queryPanel.id.getText());

					}
				});
			}
		});

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		JPanel jp = new JPanel();
		// jp.setBackground(Color.red);
		jp.setOpaque(false);
		SwingConsole
				.addComponent(gb, gcons, this, queryPanel, 0, 0, 1, 1, 1, 0);
		SwingConsole
				.addComponent(gb, gcons, this, titlePanel, 0, 1, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, listItem, 0, 2, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, jp, 0, 3, 1, 1, 1, 1);
		SwingConsole
				.addComponent(gb, gcons, this, buttonArea, 0, 4, 1, 1, 1, 0);
	}

	public static void main(String[] args) {
		// JFrame jf = new JFrame();
		// CarManagePanel pa = new CarManagePanel(null);
		// JScrollPane js = new GAPJScrollPane(pa);
		//
		// jf.setContentPane(js);
		// // jf.getContentPane().add(pa);
		// jf.setSize(1024, 768);
		// jf.setVisible(true);
		// jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void refresh() {
		// TODO 自动生成的方法存根
		listItem.refresh("");
		queryPanel.id.setText("");
		mainFrame.validate();
	}

}
