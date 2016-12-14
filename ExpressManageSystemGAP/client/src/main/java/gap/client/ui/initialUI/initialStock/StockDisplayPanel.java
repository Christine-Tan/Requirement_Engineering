package gap.client.ui.initialUI.initialStock;

import gap.client.ui.initialUI.InitialMainPanel;
import gap.client.ui.initialUI.InitialTable.CityTableHeader;
import gap.common.po.InitialStockPO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class StockDisplayPanel extends JPanel{

	ArrayList<InitialStockPO> stockPOs;

	JComponent viewport;
	FlowLayout flow;

	InitialMainPanel panel;
	int currentAccountNum = 0;
	
	final int hGarp = 50;//水平间隙
	final int vGarp = 10;//垂直间隙
	
	HashMap<InitialStockPO, StockBox> stockMap;
	public StockDisplayPanel
	(InitialMainPanel mainPanel,ArrayList<InitialStockPO> stockPOs,JComponent viewport){
	
		this.panel = mainPanel;
		
		setBackground(Color.white);
		this.stockPOs = stockPOs;		
		currentAccountNum = stockPOs.size();

		stockMap = new HashMap<>(stockPOs.size());
		
		this.viewport = viewport;
		setOpaque(false);
		viewport.addComponentListener(new MyResizeListener());
		
		flow = new FlowLayout(FlowLayout.LEFT, 50, 10);
		setLayout(flow);
		repaint();
		addStockBox();
		reSize();

	}
	
	/**
	 * 根据账户数量重新设定大小
	 */
	public void reSize(){
		
		if(viewport==null){
			return;
		}
		int containerWidth = viewport.getWidth() - CityTableHeader.classIdle;
		int containerHeight = viewport.getHeight();


		
		if(stockMap.keySet().isEmpty()){
			return;
		}
		//面板格式不对，还没有被完全new出来
		if(containerWidth<=0 || containerHeight<=0){
			return;
		}
		
		reSizeByNum(stockPOs.size());
		
	}
	
	
	
	/**
	 * 按数量重新算大小
	 * @param boxNum
	 */
	private void reSizeByNum(int boxNum){
		
		int containerWidth = viewport.getWidth() - CityTableHeader.classIdle;

		
		int boxWidth = StockBox.width;
		int boxHeight = StockBox.height;
		//一行最多放几个box
		int numberInRow = containerWidth/(boxWidth +  hGarp);
		//有多少行
		//box的数量是账户数加一个添加图标
		int rowNumber = boxNum/numberInRow;
	
		if(boxNum%numberInRow>0){
			rowNumber++;
		}
		
		int width = numberInRow * (boxWidth +  hGarp);
		int height = rowNumber * (boxHeight +  vGarp);
		
		if(height<100){
			height = 100;
		}
		
		setPreferredSize(new Dimension(width, height));
		
		flow.layoutContainer(this);

		panel.validate();
		panel.repaint();
		
	}
	
	private void addStockBox(){
		for(InitialStockPO po : stockPOs){
			StockBox box = new StockBox(po);
			stockMap.put(po,box);
			add(box);
		}
	}
	
	
	class MyResizeListener implements ComponentListener{

		@Override
		public void componentHidden(ComponentEvent e) {

			
		}

		@Override
		public void componentMoved(ComponentEvent e) {

			
		}

		@Override
		public void componentResized(ComponentEvent e) {
			reSizeByNum(currentAccountNum);
		}

		@Override
		public void componentShown(ComponentEvent e) {

			
		}
		
	}
	
}
