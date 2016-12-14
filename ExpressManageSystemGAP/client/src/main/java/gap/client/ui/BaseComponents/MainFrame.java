package gap.client.ui.BaseComponents;

import gap.client.ui.BaseComponents.FrameInitialler.FrameInitialler;
import gap.client.ui.BaseComponents.FrameInitialler.InitiallerFactory;
import gap.client.ui.BaseComponents.SliderPanel.Direction;
import gap.client.ui.BaseListener.MoveListener;
import gap.client.ui.BaseListener.ResizeListener;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.LoadPanel;
import gap.client.ui.inventoryui.checkstock.CheckStockPanel;
import gap.client.ui.inventoryui.checkstock.ListItem;
import gap.client.ui.inventoryui.checkstock.Unit;
import gap.client.util.MessageType;
import gap.common.util.UserType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
	TitlePanel titlePanel;
	NavigateBar navigateBar;
	MainPanel mainPanel;
	LoadPanel loadPanel;

	SliderPanel sliderPanel;

	public static MessagePanel messagePanel;

	MoveListener moveListener;
	ResizeListener resizeListener;

	private GridBagLayout grid;
	private GridBagConstraints gcons;

	Image icon;
	String iconPath="images/pikachu.png";

	public MainFrame() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
            icon=ImageIO.read(new File(iconPath));
            setIconImage(icon);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

		loadPanel = new LoadPanel(this);

		setSize(Default.WIDTH, Default.HEIGHT);
		setUndecorated(true);

		JPanel contentPanel = (JPanel) this.getContentPane();
		contentPanel.setBackground(Color.white);
		contentPanel.setBorder(BorderFactory
				.createLineBorder(ColorAndFonts.gray));

		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		sliderPanel = new SliderPanel(contentPanel);

		grid = new GridBagLayout();
		gcons = new GridBagConstraints();

		gcons.ipadx = Default.NAV_WIDTH;
		gcons.ipady = Default.TITLE_HEIGHT;
		gcons.fill = GridBagConstraints.BOTH;
		contentPanel.setLayout(grid);

		titlePanel = new TitlePanel(this);
		navigateBar = new NavigateBar(this);
		messagePanel = new MessagePanel(this);

		moveListener = new MoveListener(this);
		resizeListener = new ResizeListener(this);
		addMouseListener(moveListener);
		addMouseMotionListener(moveListener);
		addMouseListener(resizeListener);
		addMouseMotionListener(resizeListener);

		SwingConsole.addComponent(grid, gcons, contentPanel, titlePanel, 0, 0,
				2, 1, 1, 0);
		SwingConsole.addComponent(grid, gcons, contentPanel, navigateBar, 0, 1,
				1, 2, 0, 1);

		 SwingConsole.addComponent(grid, gcons, contentPanel,
				 sliderPanel, 1, 1, 1, 1, 1, 1);
		SwingConsole.addComponent(grid, gcons, contentPanel, messagePanel, 1,
				2, 1, 1, 1, 0);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public NavigateBar getNavigateBar() {
		return navigateBar;
	}

	public void initial(UserType user) {
		UserBox userBox = new UserBox();
		navigateBar.add(userBox);


		FrameInitialler initialler = InitiallerFactory.getInitialler(user);
		initialler.initialFrame(this);

	}

	public void load(final Runnable runnable) {
		Thread loadThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// // TODO 自动生成的方法存根
				loadPanel.load(runnable);
			}
		});
		loadThread.start();
	}

	public void setMainPanel(MainPanel panel) {
//		if (mainPanel != null){
//			remove(mainPanel.getJsPanel());
//		}
//		this.mainPanel = panel;
//		SwingConsole.addComponent(grid, gcons, (JPanel) getContentPane(),
//				mainPanel.getJsPanel(), 1, 1, 1, 1, 1, 1);
//		validate();

		if(mainPanel == null){
			mainPanel = panel;
			sliderPanel.setBeginPanel(panel.getJsPanel());
			validate();
		}
		else if(mainPanel == panel){
			return;
		}
		else{


			Direction direction = null;
			if(navigateBar.isUpper(mainPanel, panel)){
				direction = Direction.UP;
			}else{
				direction = Direction.DOWN;
			}

			this.mainPanel = panel;

			sliderPanel.slide(panel.getJsPanel(), direction);

		}
	}

	public JScrollPane getMainJsPanel(){
		return mainPanel.getJsPanel();
	}

	public static void setMessage(String message, MessageType type, long time) {
		messagePanel.setMessage(message, type, time);
	}

	public static void paint(CheckStockPanel panel) {
		for (int i = 0; i < panel.list.items.size(); i++) {
			ListItem item = panel.list.items.get(i);
			for (int j = 0; j < item.shelf.length; j++) {
				Unit unit = item.shelf[j];
				unit.icon.paint();
			}
		}
	}

}
