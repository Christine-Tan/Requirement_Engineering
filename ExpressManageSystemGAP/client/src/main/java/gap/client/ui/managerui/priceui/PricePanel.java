package gap.client.ui.managerui.priceui;

import gap.client.blcontroller.PriceController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.ui.gapcomponents.GAPJScrollPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 * 总经理制定价格常量界面
 * 
 * @author seven
 *
 */
public class PricePanel extends MainPanel {
	PriceTitlePanel titlePanel;
	PriceListItemPanel listItem;
	ButtonArea buttonArea;
	GridBagLayout gb;
	GridBagConstraints gcons;

	public PricePanel(MainFrame frame) {
		super(frame);
		titlePanel = new PriceTitlePanel();
		listItem = new PriceListItemPanel(frame);
		buttonArea = new ButtonArea();
		buttonArea.submit.setText("提交修改");
		buttonArea.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PriceController.flush();
			}

		});

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);

		// 布局

		JPanel jp = new JPanel();
		jp.setOpaque(false);
		SwingConsole
				.addComponent(gb, gcons, this, titlePanel, 0, 0, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, listItem, 0, 1, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, jp, 0, 2, 1, 1, 1, 1);
		SwingConsole
				.addComponent(gb, gcons, this, buttonArea, 0, 3, 1, 1, 1, 0);
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		PricePanel ip = new PricePanel(null);
		JScrollPane js = new GAPJScrollPane(ip);

		jf.setContentPane(js);
		jf.setSize(1024, 768);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
