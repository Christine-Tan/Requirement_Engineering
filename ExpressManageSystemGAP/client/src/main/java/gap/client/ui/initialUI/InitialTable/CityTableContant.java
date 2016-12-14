package gap.client.ui.initialUI.InitialTable;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.common.po.InitialPeoplePO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;

public class CityTableContant extends PanelWithGrid{

	List<InitialPeoplePO> peoplePOs;
	
	GAPLabel insInformation;
	GAPLabel peopleInformation;
	JLabel rightLabel;
	
	public static Font bigFont = ColorAndFonts.getChinese(20);
	
	
	public CityTableContant(List<InitialPeoplePO> peoplePOs){
		this.peoplePOs = peoplePOs;
		initial();
	}
	
	private void initial(){

		
		insInformation = new GAPLabel("机构信息");
		peopleInformation = new GAPLabel("人员信息");
		
		insInformation.setFont(bigFont);
		peopleInformation.setFont(bigFont);
		
		insInformation.setPreferredSize(new Dimension(CityTableHeader.classIdle, 100));
		peopleInformation.setPreferredSize(new Dimension(CityTableHeader.classIdle, 300));
		
		insInformation.setHorizontalAlignment(JLabel.CENTER);
		peopleInformation.setHorizontalAlignment(JLabel.CENTER);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		gridBagConstraints.insets = new Insets(0, 0, 5, 0);
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, insInformation, 0, 0, 1, 2, 0, 0);
		
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, peopleInformation, 0, 2, 1, 4, 0, 0);
		
		gridBagConstraints.insets = new Insets(0,0,5,0);
				
		Font normalFont = ColorAndFonts.getChinese(16);
		
		String[] itemNames = {"营业厅","中转中心","快递员","营业厅业务员","中心业务员","仓库管理员"}; 
		GAPLabel[] itemLabels = new GAPLabel[6];
		for(int i=0;i<itemLabels.length;i++){
			itemLabels[i] = new GAPLabel(itemNames[i]);
			itemLabels[i].setPreferredSize
				(new Dimension(CityTableHeader.labelWidth, CityTableHeader.height));
			itemLabels[i].setFont(normalFont);
			SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, itemLabels[i], 1, i, 1, 1, 0, 1);
		}
		
		int[] size = addNums();
		rightLabel = new JLabel();
		SwingConsole.addComponent
		(gridBagLayout, gridBagConstraints, this, rightLabel, size[0]+2, 0, 1, size[1], 1, 1);
		
	}

	//int[0]是列数，int[1]是行数
	private int[] addNums() {
		Font normalFont = ColorAndFonts.getEnglish(15);
		
		int[] totalNums = {0,0,0,0,0,0};
		
		int i=0;
		for(i=0;i<peoplePOs.size();i++){
			int[] nums = peoplePOs.get(i).getNums();
			
			for(int j=0;j<nums.length;j++){
				totalNums[j] += nums[j];
			}
			
			for(int j=0;j<nums.length;j++){
				GAPLabel gapLabel = new GAPLabel();
				gapLabel.setFont(normalFont);
				gapLabel.setPreferredSize
					(new Dimension(CityTableHeader.labelWidth, CityTableHeader.height));
				
				gapLabel.setForeground(CityTableHeader.normalColor);
				gapLabel.setHorizontalAlignment(JLabel.CENTER);
				gapLabel.setText(nums[j]+"");

				SwingConsole.addComponent
					(gridBagLayout, gridBagConstraints, this, gapLabel, 2+i, j, 1, 1, 0, 1);
			}
			
		}
		
		//合计
		for(int j=0;j<totalNums.length;j++){
			GAPLabel gapLabel = new GAPLabel();
			gapLabel.setFont(normalFont);
			gapLabel.setPreferredSize
				(new Dimension(CityTableHeader.labelWidth, CityTableHeader.height));
			
			gapLabel.setForeground(CityTableHeader.totalColor);
			gapLabel.setHorizontalAlignment(JLabel.CENTER);
			
			gapLabel.setText(totalNums[j]+"");

			SwingConsole.addComponent
				(gridBagLayout, gridBagConstraints, this, gapLabel, 2+i, j, 1, 1, 0, 1);
		}
		
		
		int[] size = {i+1,totalNums.length};
		return size;
	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int insLine = insInformation.getHeight()+2;
		
		g.setColor(Color.gray);
		g.drawLine(0, insLine, getWidth(), insLine);
		
		int bottomLine = getHeight()-2;
		
		g.drawLine(0, bottomLine, getWidth(), bottomLine);
		
	}
}
