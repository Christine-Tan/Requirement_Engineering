package gap.client.ui.managerui.logui;

import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPTextField;
import gap.client.vo.LogVO;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class LogDetailPanel extends JPanel {
	List<DetailItem> detailItems;
	// 布局
	GridBagLayout gbd;
	GridBagConstraints gcons;

	public LogDetailPanel(List<LogVO> logs) {
		// TODO Auto-generated constructor stub
		setBackground(Color.white);
		gbd = new GridBagLayout();
		gcons = new GridBagConstraints();
		detailItems = new ArrayList<>();
		for (LogVO log : logs) {
			detailItems.add(new DetailItem(log));
		}
		reLayout();
	}

	private void reLayout() {
		setLayout(gbd);
		gcons.insets = new Insets(5, 0, 5, 0);
		for (int i = 0; i < detailItems.size(); i++) {
			SwingConsole.addComponent(gbd, gcons, this, detailItems.get(i), 0, i, 1, 1, 1, 0);
		}
	}

	class DetailItem extends JPanel {
		GAPTextField time_f,user_f,operation_f;
 //		JLabel time_jl, user_jl, operation_jl;
		String time, user, operation;
		// 布局
		GridBagLayout gbdi;

		public DetailItem(LogVO log) {
			setBackground(Color.WHITE);

			// setPreferredSize(new Dimension(Default.PANEL_WIDTH - 100, 40));
			time = log.getDate().substring(10, log.getDate().length() - 1);
			user = log.getUser().getUserName();
			operation = log.getOperate();
//			time_jl = new GAPLabel(time);
//			user_jl = new GAPLabel(user);
//			operation_jl = new GAPLabel(operation);
            time_f=new GAPTextField(6);
            time_f.setText(time);
            time_f.closeEdit();
            user_f=new GAPTextField(7);
            user_f.setText(user);
            user_f.closeEdit();
            operation_f=new GAPTextField(23);
            operation_f.setText(operation);
            operation_f.closeEdit();
            
			gbdi = new GridBagLayout();
//			gcons = new GridBagConstraints();
			setLayout(gbdi);
			gcons.insets = new Insets(5, 20, 5, 0);
			SwingConsole.addComponent(gbdi, gcons, this, time_f, 0, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 20, 5, 0);
			SwingConsole.addComponent(gbdi, gcons, this, user_f, 1, 0, 1, 1, 0, 0);
			gcons.insets = new Insets(5, 20, 5, 0);
			SwingConsole.addComponent(gbdi, gcons, this, operation_f, 2, 0, 1, 1, 0, 0);
		}

	}
}
