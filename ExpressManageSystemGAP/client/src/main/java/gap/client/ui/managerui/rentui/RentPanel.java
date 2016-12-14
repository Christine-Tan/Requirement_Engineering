package gap.client.ui.managerui.rentui;

import gap.client.blcontroller.RentController;
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

/**
 * 总经理制定租金界面
 * 
 * @author seven
 * 
 */
public class RentPanel extends MainPanel {
	RentQueryPanel queryPanel;
	RentTitlePanel titlePanel;
	RentListItemPanel listItem;
	ButtonArea buttonArea;
	GridBagLayout gb;
	GridBagConstraints gcons;

	public RentPanel(MainFrame frame) {
		super(frame);
		setBackground(Color.WHITE);
		queryPanel = new RentQueryPanel();
		titlePanel = new RentTitlePanel();
		listItem = new RentListItemPanel(frame);
		buttonArea = new ButtonArea();
		buttonArea.submit.setText("提交修改");
		buttonArea.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				RentController.flush();
			}

		});

		// 布局
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		JPanel jp = new JPanel();
		jp.setOpaque(false);
		setLayout(gb);
		SwingConsole
				.addComponent(gb, gcons, this, queryPanel, 0, 0, 1, 1, 1, 0);
		SwingConsole
				.addComponent(gb, gcons, this, titlePanel, 0, 1, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, listItem, 0, 2, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, jp, 0, 3, 1, 1, 1, 1);
		SwingConsole
				.addComponent(gb, gcons, this, buttonArea, 0, 4, 1, 1, 1, 0);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
