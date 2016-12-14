package gap.client.ui.gapcomponents;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class PanelWithGrid extends JPanel{
	protected GridBagLayout gridBagLayout;
	protected GridBagConstraints gridBagConstraints;
	
	public PanelWithGrid() {
		gridBagConstraints = new GridBagConstraints();
		gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		setBackground(Color.white);
	}
}
