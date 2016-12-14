package gap.client.ui.gapcomponents;

import gap.client.ui.UITools.ColorAndFonts;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicComboPopup;

public class GAPComboPoupUI<E> extends BasicComboPopup {

	public GAPComboPoupUI(JComboBox<E> combo) {
		super(combo);
		// setBorderPainted(false);
		setBorder(BorderFactory.createLineBorder(ColorAndFonts.gray.darker()));
		// TODO 自动生成的构造函数存根
	}

	protected JScrollPane createScroller() {
		return new GAPJScrollPane(getList());
	}

}
