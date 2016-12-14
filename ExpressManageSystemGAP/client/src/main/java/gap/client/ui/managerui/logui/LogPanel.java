package gap.client.ui.managerui.logui;

import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.SwingConsole;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class LogPanel extends MainPanel {
	LogQueryPanel queryPanel;
	LogListItemPanel listItemPanel;
	// 布局
	GridBagLayout gb;
	GridBagConstraints gcons;

	public LogPanel(MainFrame frame) {
		// TODO Auto-generated constructor stub
		super(frame);
		queryPanel = new LogQueryPanel();
		listItemPanel = new LogListItemPanel();
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		JPanel jp = new JPanel();
		jp.setOpaque(false);
		setLayout(gb);
		gcons.insets = new Insets(5, 0, 5, 0);
		SwingConsole.addComponent(gb, gcons, this, queryPanel, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, listItemPanel, 0, 1, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, jp, 0, 2, 1, 1, 1, 1);

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		listItemPanel.refresh();
	}

}
