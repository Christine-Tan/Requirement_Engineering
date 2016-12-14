package gap.client.ui.BaseComponents;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * 带GridBagLayout的MainPanel,不需要在子类里new了
 *
 */
public abstract class MainPanelWithGird extends MainPanel {

	protected GridBagLayout gb;
	protected GridBagConstraints gcons;

	public MainPanelWithGird(MainFrame frame) {
		super(frame);
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
	}

}
