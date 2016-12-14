package gap.client.ui.gapcomponents;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class GAPCellRender<E> extends JLabel implements ListCellRenderer<E> {

	public GAPCellRender() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends E> arg0,
			E arg1, int arg2, boolean arg3, boolean arg4) {
		// TODO 自动生成的方法存根
	    if(arg1 == null){
	        return this;
	    }
		setText(arg1.toString());

		Color background, foreground;
		JList.DropLocation dropLocation = arg0.getDropLocation();
		if (dropLocation != null && !dropLocation.isInsert()
				&& dropLocation.getIndex() == arg2) {
			background = Color.white;
			foreground = ComponentStyle.dark_gray;
			// check if this cell is selected
		} else if (arg3) {
			background = ComponentStyle.blue;
			foreground = Color.white;
			// unselected, and not the DnD drop location
		} else {
			background = Color.WHITE;
			foreground = ComponentStyle.dark_gray;
		}

		setBackground(background);
		setForeground(foreground);

		return this;
	}

	// public void paintComponent(Graphics g) {
	// Graphics2D g2d = RenderSetter.OpenRender(g);
	// }

}
