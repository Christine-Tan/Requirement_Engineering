package gap.client.ui.BaseComponents;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.ColorChanger;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigateButton extends GAPButton {
	MainFrame mainFrame;
	MainPanel mainPanel;
	NavigateBar bar;
	String name;
	boolean isSelect;

	public NavigateButton(MainFrame mainFrame, MainPanel mainPanel,
			NavigateBar bar, String name) {
		super();
		setText(name);
		setFont(ComponentStyle.plainFont);
		this.mainFrame = mainFrame;
		this.mainPanel = mainPanel;
		this.bar = bar;
		this.name = name;
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if (!isSelect)
					NavigateButton.this.bar.setSelect(NavigateButton.this);
			}
		});
	}

	/**
	 * 设置被选择
	 */
	void select() {
		isSelect = true;

//		setDefautFontColor(Color.white);
		setEnterFontColor(Color.white);
		setPressFontColor(Color.white);
//		setDefautBackGroundColor(ColorAndFonts.blue);
//		setBackground(ColorAndFonts.blue);
//		setForeground(Color.white);

		AnimationThread backThread = new AnimationThread(getBackground(),
				ColorAndFonts.blue, Type.BACK);
		AnimationThread foreThread = new AnimationThread(getForeground(),
				Color.white, Type.FORE);
		backThread.start();
		foreThread.start();
//	
//		setBackground(ColorAndFonts.blue);
//		setForeground(Color.white);
		
		mainFrame.load(new Runnable() {

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				mainPanel.refresh();
				mainFrame.setMainPanel(mainPanel);
				mainFrame.validate();
				mainFrame.repaint();
			}
		});

		// }
		// });

	}

	/**
	 * 设置未被选择
	 */
	void deSelect() {
		isSelect = false;
	//	setDefautBackGroundColor(Color.white);
	//	setDefautFontColor(Color.BLACK);
		setEnterFontColor(ColorAndFonts.blue.darker());
		setPressFontColor(ColorAndFonts.otherDarkBulue);
		// setBackground(Color.white);
		// setForeground(Color.BLACK);

		AnimationThread backThread = new AnimationThread(getBackground(),
				Color.white, Type.BACK);
		AnimationThread foreThread = new AnimationThread(getForeground(),
				Color.black, Type.FORE);
		backThread.start();
		foreThread.start();

		repaint();
		mainFrame.validate();
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	private enum Type {
		BACK, FORE
	}

	class AnimationThread extends Thread {

		ColorChanger changer;
		Type type;

		public AnimationThread(Color formerColor, Color targetColor, Type type) {
			this.type = type;
			changer = new ColorChanger(formerColor, targetColor, 0.15);
		}

		public void run() {
			while (!changer.isFinish()) {
				Color currentColor = changer.change();

				switch (type) {
				case BACK:
					setBackground(currentColor);
					setDefautBackGroundColor(currentColor);
					break;

				case FORE:
					setForeground(currentColor);
					setDefautFontColor(currentColor);
					break;
				}

				repaint();

				try {
					Thread.sleep(50);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}
}
