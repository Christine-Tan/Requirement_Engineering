package gap.client.ui.initialUI.InitialHistory;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

public class HistoryTableHeader extends PanelWithGrid{

	
	private GAPLabel[] labels;
	public static int[] widths;
	public static String[] texts;
	public static int height = 50;
	public static Font font;
	
	static{
		font = ColorAndFonts.getChinese(18);
		
		String[] temStrings = {
				"",   //空出一个位置给下拉按钮
				"日期",
				"机构数",
				"员工数",
				"仓库数",
				"库存数",
				"账户数",
				"余额"
		};
		texts = temStrings;
		
		int[] tempWidth = {
				50,100,100,100,100,100,100,150
		};
		
		widths = tempWidth;
		
	}
	
	public HistoryTableHeader(){
		
		labels = new GAPLabel[texts.length];
		
		for(int i=0;i<labels.length;i++){
			labels[i] = new GAPLabel(texts[i]);
			labels[i].setPreferredSize(new Dimension(widths[i],height));
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			labels[i].setFont(font);
		}
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		
		for(int i=0;i<labels.length;i++){
			SwingConsole.addComponent
				(gridBagLayout, gridBagConstraints, this, labels[i], i, 0, 1, 1, 1, 1);
		}
	}
	
}
