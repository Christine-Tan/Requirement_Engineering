package gap.client.ui.inventoryui.stockinorderinput;

import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.ui.gapcomponents.GAPButton;
import gap.client.ui.gapcomponents.GAPLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlarmDialog extends JDialog {
	private static final int WIDTH = 400, HEIGHT = 300;
	GAPLabel alarm, ratio;
	public AlarmDialog dialog;
	public JButton confirm;

	public AlarmDialog(JFrame jframe, String sectorName, double used) {
		super(jframe, "库存报警",true);
		// TODO Auto-generated constructor stub
		dialog = this;
		setBackground(Color.white);

		confirm = new GAPButton("确认");
		confirm.setFont(ComponentStyle.defaultFont);
		alarm = new GAPLabel(sectorName + "报警！");
		alarm.setPreferredSize(new Dimension(500, 60));
		alarm.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		alarm.setForeground(Color.red);
		alarm.setHorizontalAlignment(JLabel.CENTER);
		alarm.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, ComponentStyle.light_gray));

		ratio = new GAPLabel("分区占用比为" + used + "%");
		ratio.setPreferredSize(new Dimension(500, 60));
		ratio.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		ratio.setForeground(Color.red);
		ratio.setHorizontalAlignment(JLabel.CENTER);
		ratio.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, ComponentStyle.light_gray));

		JPanel blank1 = new JPanel();
		blank1.setBackground(Color.white);
		blank1.setSize(80, 100);
		blank1.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, ComponentStyle.light_gray));

		JPanel blank2 = new JPanel();
		blank2.setBackground(Color.white);
		blank2.setSize(80, 100);
		blank2.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, ComponentStyle.light_gray));

		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.setVisible(false);
			}
		});
		JPanel jp1 = new JPanel();
		jp1.setBackground(Color.white);
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		jp1.add(confirm);
		jp1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, ComponentStyle.light_gray));
		
		JPanel jp2 = new JPanel();
		jp2.setBackground(Color.white);
		jp2.setLayout(new GridLayout(4, 1));

		jp2.add(blank1);
		jp2.add(alarm);
		jp2.add(ratio);
		jp2.add(blank2);
		
//		getContentPane()

		getContentPane().add(jp2, BorderLayout.CENTER);
		getContentPane().add(jp1, BorderLayout.SOUTH);
		
		
		int x = jframe.getX() + (jframe.getWidth() - WIDTH) / 2, y = jframe.getY()
				+ (jframe.getHeight() - HEIGHT) / 2;
		setLocation(x, y);
//		setLocationRelativeTo(jframe);
		setUndecorated(true);
		setUndecorated(true);
//		setBorder(BorderFactory.createLineBorder(ComponentStyle.light_gray));
		setSize(WIDTH, HEIGHT);
		setVisible(true);

		// cancel = new GAPButton("取消");
		// cancel.setFont(ComponentStyle.defaultFont);
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AlarmDialog dialog = new AlarmDialog(jf, "汽运区", 90);
	}

}
