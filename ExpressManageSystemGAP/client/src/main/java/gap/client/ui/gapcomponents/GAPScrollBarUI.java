package gap.client.ui.gapcomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * 用于改变滚动条的外观
 * 主要需要重写四个组件的方法：Track,Thumb,DecreaseButton,IncreaseButton
 * @author YangYanfei
 *
 */
public class GAPScrollBarUI extends BasicScrollBarUI {

	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		g.setColor(Color.white);
		g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width,
				trackBounds.height);
		g.setColor(ComponentStyle.light_gray.brighter());
		g.drawRect(trackBounds.x, trackBounds.y, trackBounds.width,
				trackBounds.height);
	}

	protected void paintThumb(Graphics g, JComponent c, Rectangle trackBounds) {
		g.setColor(ComponentStyle.gray);
		g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width,
				trackBounds.height);

	}

	protected JButton createDecreaseButton(int orientation) {
		JButton jb = new JButton();
		jb.setBorder(BorderFactory.createEmptyBorder());
		return jb;
	}

	protected JButton createIncreaseButton(int orientation) {
		JButton jb = new JButton();
		jb.setBorder(BorderFactory.createEmptyBorder());
		return jb;
	}
}
