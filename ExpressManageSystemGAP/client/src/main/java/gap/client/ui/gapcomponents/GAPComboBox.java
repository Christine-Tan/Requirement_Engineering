package gap.client.ui.gapcomponents;

import gap.client.ui.UITools.ColorAndFonts;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class GAPComboBox<E> extends JComboBox<E> {
	public GAPComboBox() {
		super();
		setRenderer(new GAPCellRender<E>());
		setBackground(Color.white);
		// setFocusable(false);
		setFont(ComponentStyle.defaultFont);
		setMaximumRowCount(6);
		setBorder(BorderFactory.createLineBorder(ColorAndFonts.gray.darker()));
		setUI(new GAPComboBoxUI<>(this));
	}

}
