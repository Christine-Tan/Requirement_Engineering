package gap.client.ui.inventoryui.alarmvalueset;

import gap.client.blcontroller.InventoryController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ButtonArea;
import gap.client.util.LocalInfo;
import gap.client.util.MessageType;
import gap.common.util.ResultMessage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AlarmValueSetPanel extends MainPanel {

	ButtonArea buttonArea;
	AlarmValueUI alarmValue;
	JFrame frame;
	GridBagLayout gb;
	GridBagConstraints gcons;

	public AlarmValueSetPanel(MainFrame frame) {
		super(frame);
		this.frame = frame;
		// TODO Auto-generated constructor stub

		buttonArea = new ButtonArea();
		buttonArea.submit.setText("确认");
		alarmValue = new AlarmValueUI();

		buttonArea.submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				double value = alarmValue.getAlarmValue();
				ResultMessage re = InventoryController.setAlarmValue(value,
						LocalInfo.getIns_ID());
				
				if(re.equals(ResultMessage.SUCCEED)){
					alarmValue.icon.initial(value);
					alarmValue.icon.startAnimation();
					alarmValue.alarmValue.closeEdit();
					MainFrame.setMessage("警戒值设置成功", MessageType.succeed, 2000);
				}else{
					MainFrame.setMessage("警戒值设置错误  (70%~100%)", MessageType.alram, 2000);
				}
				
				
			}

		});

		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		setLayout(gb);
		
		
		reLayout();
		
	}
	
	public void initial(){
		alarmValue = new AlarmValueUI();
	}
	
	public void reLayout(){
		removeAll();
		gcons.fill = GridBagConstraints.HORIZONTAL;
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.white);
		panel1.setPreferredSize(new Dimension(Default.PANEL_WIDTH,10));
		
		
		SwingConsole.addComponent(gb, gcons, this, panel, 0, 0, 1, 1, 1, 1);
		SwingConsole
				.addComponent(gb, gcons, this, alarmValue, 0, 1, 1, 1, 1, 0);
		SwingConsole.addComponent(gb, gcons, this, panel1, 0, 2, 1, 1, 1, 0);
		SwingConsole
				.addComponent(gb, gcons, this, buttonArea, 0, 3, 1, 1, 1, 0);
		frame.validate();

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		initial();
		reLayout();
		
	}

}
