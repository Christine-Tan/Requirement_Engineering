package gap.client.ui.managerui.logui;

import gap.client.blcontroller.LogController;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.vo.LogVO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogListItemPanel extends JPanel {
	Map<String, List<LogVO>> logMap;
	List<ItemPanel> items;
	GridBagLayout gb;
	GridBagConstraints gcons;

	public LogListItemPanel() {
		// TODO Auto-generated constructor stub
		setBackground(Color.WHITE);
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		items = new ArrayList<>();

		refresh();

	}

	public void refresh() {
		removeAll();
		items.clear();
		logMap = LogController.getLogByDate();
		Iterator<?> it = logMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<LogVO>> entry = (Map.Entry<String, List<LogVO>>) it.next();
			String date = entry.getKey();
			List<LogVO> logs = entry.getValue();
			items.add(new ItemPanel(date, logs));
		}
		reLayout();
	}

	private void reLayout() {
		setLayout(gb);
		gcons.insets = new Insets(10, 50, 5, 300);
		for (int i = 0; i < items.size(); i++) {
			SwingConsole.addComponent(gb, gcons, this, items.get(i), 0, i, 1, 1, 1, 0);
		}
	}

	class ItemPanel extends JPanel {
		JLabel date_jl;
		GAPButton detail_b;
		JPanel detailPanel;
		boolean detailed;
		// 布局
		GridBagLayout gbi,gbl;
		GridBagConstraints gcons;

		public ItemPanel(String date, List<LogVO> logs) {
			setBackground(Color.WHITE);
			date_jl = new GAPLabel(date);
			detail_b = new GAPButton(">");
			
			// 对detail按钮添加监听
			detail_b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (detailed) {
						closeDetail();
					} else {
						openDetail();
					}
				}
			});
			detailPanel = new LogDetailPanel(logs);
			// 布局
			gbi = new GridBagLayout();
			gcons = new GridBagConstraints();
			gbl=new GridBagLayout();
			JPanel jp=new JPanel();
//			JLabel empty=new JLabel();
			jp.setBackground(Color.WHITE);
			jp.setOpaque(true);
			jp.setLayout(gbl);
		
//			gcons.anchor = GridBagConstraints.WEST;
			gcons.insets = new Insets(5, 10, 5, 10);
			SwingConsole.addComponent(gbl, gcons, jp, detail_b, 0, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 10, 5, 350);
			SwingConsole.addComponent(gbl, gcons, jp, date_jl, 1, 0, 1, 1, 0, 0);
//			gcons.insets=new Insets(0, 0, 0, 0);
//			SwingConsole.addComponent(gbl,gcons, jp, empty, 2, 0, 1, 1, 1, 0);
			setLayout(gbi);	
			gcons.insets = new Insets(5, 10, 5, 10);
//			gcons.fill = GridBagConstraints.BOTH;
			SwingConsole.addComponent(gbi, gcons, this, jp, 0, 0, 1, 1, 1, 0);
			gcons.insets = new Insets(5, 10, 5, 0);
			SwingConsole.addComponent(gbi, gcons, this, detailPanel, 0, 1, 2, 1, 1, 0);
			closeDetail();
		}

		private void closeDetail() {
			detailed = false;
			detail_b.setText(">");
			detailPanel.setVisible(false);
		}

		private void openDetail() {
			detailed = true;
			detail_b.setText("v");
			detailPanel.setVisible(true);
		}
	}

}
