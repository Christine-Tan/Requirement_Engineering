package gap.client.ui.BaseComponents;

import gap.client.ui.BaseComponents.TitleButton.Type;
import gap.client.ui.UITools.SwingConsole;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel {

	MainFrame mainFrame;
	GridBagLayout gridBagLayout = new GridBagLayout();
	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	private static final long serialVersionUID = 1L;

	TitleButton exitButton;
	TitleButton iconButton;

	public TitlePanel(MainFrame frame) {
		mainFrame = frame;
		setLayout(gridBagLayout);
		setSize(1000, 30);

		exitButton = new TitleButton(mainFrame, Type.close);
		iconButton = new TitleButton(mainFrame, Type.iconified);

		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		gridBagConstraints.ipadx = 25;
		// gridBagConstraints.ipady = 5;
		gridBagConstraints.fill = gridBagConstraints.BOTH;

		SwingConsole.addComponent(gridBagLayout, gridBagConstraints, this,
				label, 0, 0, 1, 2, 1, 1);

		SwingConsole.addComponent(gridBagLayout, gridBagConstraints, this,
				label2, 1, 0, 1, 1, 0, 1);

		SwingConsole.addComponent(gridBagLayout, gridBagConstraints, this,
				iconButton, 1, 1, 1, 1, 0.01, 2.5);

		SwingConsole.addComponent(gridBagLayout, gridBagConstraints, this,
				exitButton, 2, 1, 1, 1, 0.01, 2.5);

	}

}
