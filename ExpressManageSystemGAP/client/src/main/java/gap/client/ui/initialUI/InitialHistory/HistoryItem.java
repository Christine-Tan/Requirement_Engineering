package gap.client.ui.initialUI.InitialHistory;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.ConvertString;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.PanelWithGrid;
import gap.client.ui.initialUI.InitialMainPanel;
import gap.client.ui.initialUI.InitialTablePanel;
import gap.common.po.InitialHistoryPO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class HistoryItem extends PanelWithGrid{
	InitialMainPanel mainPanel;
	InitialTablePanel tablePanel;
	InitialHistoryPO historyPO;
	
	GAPLabel[] labels;
	GAPLabel detailButton;
	
	Font font = ColorAndFonts.getEnglish(17);
	
	DetailListener listener;
	
	public HistoryItem(InitialMainPanel mainPanel,InitialHistoryPO historyPO){
		this.mainPanel = mainPanel;
		this.historyPO = historyPO;
		this.tablePanel = new InitialTablePanel(mainPanel, historyPO);
		
		
		
		labels = new GAPLabel[HistoryTableHeader.widths.length];
		String[] texts = {
				">",   //下拉按钮
				ConvertString.getString(historyPO.getDate()),
				historyPO.getTotalInstitution()+"",
				historyPO.getTotalPeopleNum()+"",
				historyPO.getTotalWarehouse()+"",
				historyPO.getTotalStock()+"",
				historyPO.getTotalAccount()+"",
				ConvertString.getString(historyPO.getTotalBalance())
		};
//		"日期",
//		"机构数",
//		"员工数",
//		"仓库数",
//		"库存数",
//		"账户数",
//		"余额"
		
		
		for(int i=0;i<labels.length;i++){
			labels[i] = new GAPLabel(texts[i]);
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			labels[i].setFont(font);
			labels[i].setPreferredSize(new Dimension
					(HistoryTableHeader.widths[i], HistoryTableHeader.height));
		}
		
		detailButton = labels[0];
		detailButton.setForeground(ColorAndFonts.blue);
		listener = new DetailListener();
		detailButton.addMouseListener(listener);
		
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		
		for(int i=0;i<labels.length;i++){
			SwingConsole.addComponent
				(gridBagLayout, gridBagConstraints, this, labels[i], i, 0, 1, 1, 1, 0);
		}
		
		tablePanel = new InitialTablePanel(mainPanel, historyPO);
		tablePanel.setVisible(false);
		
		SwingConsole.addComponent
			(gridBagLayout, gridBagConstraints, this, tablePanel, 0, 1, labels.length, 1, 1, 1);
		
		
	}
	
	class DetailListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			detailButton.setForeground(ColorAndFonts.darkBlue);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			detailButton.setForeground(ColorAndFonts.blue);
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if(detailButton.getText().equals(">")){
				tablePanel.setVisible(true);
				detailButton.setText("V");
				
				for(int i=1;i<labels.length;i++){
					labels[i].setForeground(ColorAndFonts.blue);
				}
				
				mainPanel.revalidate();
				mainPanel.repaint();
			}else if(detailButton.getText().equals("V")){
				tablePanel.setVisible(false);
				detailButton.setText(">");
				for(int i=1;i<labels.length;i++){
					labels[i].setForeground(Color.BLACK);
				}
				
				mainPanel.revalidate();
				mainPanel.repaint();
			}
			
		}
		
	}

}
