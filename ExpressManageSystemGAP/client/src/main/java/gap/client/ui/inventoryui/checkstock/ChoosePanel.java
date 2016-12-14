package gap.client.ui.inventoryui.checkstock;

import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ChooseButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class ChoosePanel extends JPanel {
	ChooseButton car, train, plane,flex;

	public ChoosePanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));
//		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
//				ComponentStyle.light_gray));

		car = new ChooseButton("汽运区");
		car.setSize(80, 50);
		train = new ChooseButton("铁运区");
		train.setSize(80, 50);
		plane = new ChooseButton("航运区");
		plane.setSize(80, 50);
		flex = new ChooseButton("机动区");
		flex.setSize(80, 50);


		JPanel panel1 = new JPanel();
		panel1.setBackground(null);
		panel1.setOpaque(false);

	
		JPanel panel2 = new JPanel();
		panel2.setBackground(null);
		panel2.setOpaque(false);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gcons = new GridBagConstraints();
		setLayout(gb);

		gcons.insets = new Insets(0, 0, 0, 0);
		gcons.fill = GridBagConstraints.HORIZONTAL;
		// gcons.anchor = GridBagConstraints.CENTER;
		SwingConsole.addComponent(gb, gcons, this, panel1, 0, 0, 1, 1, 1, 1);
		SwingConsole.addComponent(gb, gcons, this, car, 1, 0, 1, 1, 0.5, 1);
		SwingConsole.addComponent(gb, gcons, this, train, 2, 0, 1, 1, 0.5, 1);
		SwingConsole.addComponent(gb, gcons, this, plane, 3, 0, 1, 1, 0.5, 1);
		SwingConsole.addComponent(gb, gcons, this, flex, 4, 0, 1, 1, 0.5, 1);
		SwingConsole.addComponent(gb, gcons, this, panel2, 5, 0, 1, 1, 1, 1);

	}

}
