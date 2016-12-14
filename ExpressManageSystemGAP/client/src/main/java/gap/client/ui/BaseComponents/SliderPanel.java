package gap.client.ui.BaseComponents;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * 完成滚动特效的panel
 * @author 申彬
 *
 */
public class SliderPanel extends JPanel{
	JComponent beforePanel;
	JComponent afterPanel;
	JComponent container;
	ResizeListener listener;
	SlideThread slideThread;

	// 两面板之间间隔
	int idle = 50;
	
	public SliderPanel(JComponent container){
		setBackground(Color.white);
		setLayout(null);
		this.container = container;
	}
	
	public void setBeginPanel(JComponent panel){
		beforePanel = panel;
		removeAll();
		panel.setBounds(0, 0, getWidth(), getHeight());
		add(panel);
		
		listener = new ResizeListener();
		addComponentListener(listener);
		
		validate();
	}
	
	public enum Direction{
		UP,DOWN,LEFT,RIGHT
	}
	
	public void slide(JComponent afterPanel,Direction direction){
		
		
		if(slideThread==null){
			this.afterPanel = afterPanel;
			slideThread = new SlideThread(direction);
		}else{
			synchronized (slideThread) {
				this.afterPanel = afterPanel;
				slideThread = new SlideThread(direction);
			}	
		}
		slideThread.start();
		
	}
	
	class SlideThread extends Thread{
		
		int width;
		int height;

		
		Direction direction;
		
		int slideSpeed = 40;
		
		public SlideThread(Direction direction){

			this.direction = direction;
			
			if(direction.equals(Direction.UP)){
				slideSpeed = -slideSpeed;
			}
			
			width = getWidth();
			height = getHeight();
			
			//向上滑就加到下边，向下滑就加到上边
			if(direction.equals(Direction.UP)){
				afterPanel.setBounds(0, height+idle, width, height);
			}else{
				afterPanel.setBounds(0,-height-idle, width, height);
			}
			
			
		}
		
		public synchronized void run() {
			double middle = height*(0.4);
			//目前移动的距离
			boolean hasSlowed = false;
			
			while (true) {
				
				removeAll();
				
				//距离过标志速度变慢
				if( !hasSlowed){
					double absY = Math.abs(afterPanel.getY());
					if(absY<middle){
						slideSpeed = slideSpeed/2;
						hasSlowed = true;
					}
				}
				
				if(Math.abs(afterPanel.getY())<=Math.abs(slideSpeed)){
					//距离小到一定程度，结束
					afterPanel.setBounds(0, 0, width, height);
					add(afterPanel);
					
					beforePanel = afterPanel;
					afterPanel = null;
					
					break;
				}
				//还没到位，继续移动
				else{
					
					int beforeY = beforePanel.getY();
					beforeY += slideSpeed;
					beforePanel.setLocation(beforePanel.getX(), beforeY);
					
					int afterY = afterPanel.getY();
					afterY += slideSpeed;
					afterPanel.setLocation(afterPanel.getX(), afterY);

					add(beforePanel);
					add(afterPanel);
					
				}
				
				try{
					Thread.sleep(30);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				container.repaint();
				container.revalidate();
				
			}
		}
		
	}
	
	class ResizeListener implements ComponentListener{

		@Override
		public void componentHidden(ComponentEvent e) {
			
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			
		}

		@Override
		public void componentResized(ComponentEvent e) {
			int width = getWidth();
			int height = getHeight();
			beforePanel.setBounds(0,0,width,height);
			validate();
		}

		@Override
		public void componentShown(ComponentEvent e) {
			
		}
		
	}
	
}
