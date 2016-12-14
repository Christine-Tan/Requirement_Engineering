package gap.client.ui.BaseComponents;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.RenderSetter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class NavigateBar extends JPanel {
	static int height=60;
	ArrayList<NavigateButton> buttons = new ArrayList<>();
	UserBox userBox;
	MainFrame mainFrame;

	public NavigateBar(MainFrame frame) {
		mainFrame = frame;
		setLayout(null);
		setBounds(0, 40, 220, 610);
		setBackground(Color.white);
	}

	/**
	 * @param
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 选择导航项
	 * 
	 * @param navButton
	 */
	public void setSelect(NavigateButton navButton) {
		for (NavigateButton button : buttons) {
			if (button == navButton) {
				button.select();
			} else {
				button.deSelect();
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = RenderSetter.OpenRender(g);
		graphics2d.setColor(ColorAndFonts.gray);
		int lineWidth = 3;
		graphics2d.fillRect(this.getWidth() - lineWidth, 0, lineWidth, this.getHeight());
	}

	public void addButton(NavigateButton button) {
		int size = buttons.size();
		add(button);
		buttons.add(button);
		button.setBounds(0, 135 + (10+height) * size, 220, height);
	}

	public boolean isUpper(MainPanel panelA, MainPanel panelB) {

		for (NavigateButton button : buttons) {
			// 先遇到panelA
			if (button.getMainPanel() == panelA) {
				return true;
			}
			// 先遇到panelB
			else if (button.getMainPanel() == panelB) {
				return false;
			}
		}

		return false;
	}

}
