package gap.client.ui.paymentUI;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.RichLable;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeadPanel extends JPanel{
	GridBagLayout gridBagLayout = new GridBagLayout();
	GridBagConstraints constraints = new GridBagConstraints();
	
	Font labelFont = ColorAndFonts.getEnglish(20,Font.BOLD);
	
	JLabel timeLabel = new JLabel();
	JLabel emptyLabel = new JLabel();
	RichLable title;
	
	public HeadPanel(){
		setBackground(Color.white);
		setLayout(gridBagLayout);
		Calendar calendar = Calendar.getInstance();
		String time = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		
		timeLabel.setFont(labelFont);
		timeLabel.setText("<html>"+time+"</html>");
		
		emptyLabel.setFont(labelFont);
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<time.length();i++){
			builder.append(' ');
		}
		emptyLabel.setText(builder.toString());
		
		title = new RichLable("付款单", 35);
		

		SwingConsole.addComponent
			(gridBagLayout, constraints, this, emptyLabel, 0, 0, 1, 1, 0.5, 1);
		
		SwingConsole.addComponent
			(gridBagLayout, constraints, this, title, 1, 0, 1, 1, 1, 1);
		
		constraints.anchor = GridBagConstraints.SOUTH;
		SwingConsole.addComponent
			(gridBagLayout, constraints, this, timeLabel, 2, 0, 1, 1, 0.5, 1);
		
	}
	
//	public static void main(String[] args) {
//
//		JFrame frame = new JFrame("test");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(100, 100);
//		frame.add(new HeadPanel());
//		frame.setVisible(true);
//
//	}
	
}
