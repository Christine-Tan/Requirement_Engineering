package gap.client.ui.bussinessui.drivermanage;

import gap.client.blcontroller.DriverManageController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.GAPJScrollPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DriverManagePanel extends MainPanel {
	DriverQueryPanel queryPanel;
	TitlePanel titlePanel;
	DriverListItemPanel listItem;
	ButtonArea buttonArea;

	public DriverManagePanel(MainFrame frame) {
		super(frame);
		queryPanel = new DriverQueryPanel();
		titlePanel = new TitlePanel();
		listItem = new DriverListItemPanel(frame);
		buttonArea = new ButtonArea();
		buttonArea.submit.setText("提交修改");

		buttonArea.submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				mainFrame.load(new Runnable() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						DriverManageController.flush();
					}
				});
			}
		});

		queryPanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				mainFrame.load(new Runnable() {
					@Override
					public void run() {
						// TODO 自动生成的方法存根
						listItem.refresh(queryPanel.getId());
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
		JFrame jf = new JFrame();
		DriverManagePanel pa = new DriverManagePanel(null);
		JScrollPane js = new GAPJScrollPane(pa);

		jf.setContentPane(js);
		// jf.getContentPane().add(pa);
		jf.setSize(1024, 768);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void refresh() {
		// TODO 自动生成的方法存根
		listItem.refresh("");
		queryPanel.id.setText("");
	}
}
