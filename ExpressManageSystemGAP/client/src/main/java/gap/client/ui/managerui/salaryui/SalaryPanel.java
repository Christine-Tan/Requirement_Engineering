package gap.client.ui.managerui.salaryui;

import gap.client.blcontroller.SalaryController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SalaryPanel extends MainPanel {
	SalaryTitlePanel titlePanel;
	SalaryListItemPanel listItem;
	ButtonArea buttonArea;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	public SalaryPanel(MainFrame frame) {
		super(frame);
		setBackground(Color.WHITE);
		titlePanel = new SalaryTitlePanel();
		listItem = new SalaryListItemPanel(frame);
		buttonArea = new ButtonArea();
		buttonArea.submit.setText("提交修改");
		buttonArea.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SalaryController.flush();
			}

		});

		// 布局
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		JPanel jp = new JPanel();
		jp.setOpaque(false);
		setLayout(gb);
		SwingConsole
				.addComponent(gb, gcons, this, titlePanel, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, listItem, 0, 1, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, jp, 0, 2, 1, 1, 1, 1);
		SwingConsole
				.addComponent(gb, gcons, this, buttonArea, 0, 3, 1, 1, 1, 0);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
