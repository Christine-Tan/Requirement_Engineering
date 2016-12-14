package gap.client.ui.inventoryui.distributesector;

import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.SwingConsole;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class DistributeSectorPanel extends MainPanel {
	DistributeButtonArea buttonArea;
	ChoosePanel choose;
	ListPanel listPanel;

	public DistributeSectorPanel(MainFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
		listPanel = new ListPanel(10);
		buttonArea = new DistributeButtonArea();
		choose = new ChoosePanel();

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		JPanel panel = new JPanel();
		panel.setBackground(Color.white);

		SwingConsole.addComponent(gb, gcons, this, choose, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, listPanel, 0, 1, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, panel, 0, 2, 1, 1, 1, 1);
		gcons.fill = GridBagConstraints.HORIZONTAL;
		SwingConsole
				.addComponent(gb, gcons, this, buttonArea, 0, 3, 1, 1, 1, 0);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
