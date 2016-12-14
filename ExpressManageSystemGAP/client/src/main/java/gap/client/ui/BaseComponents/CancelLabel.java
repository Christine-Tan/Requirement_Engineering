package gap.client.ui.BaseComponents;

import gap.client.ui.UITools.RenderSetter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CancelLabel extends JLabel{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	static String URL = "images/cancelAnimation/cancel";
	static Image[] confirmAnimation = new Image[12];

	boolean isExit = true;
	private int gap = 10;

	Object lockObject = new Object();

	//0号是正常图，之后1到12是选中动画
	private int index = 0;
	static{
		for(int i=1;i<=12;i++){
			String oneURL = URL+i+".png";		 
			Image image = new ImageIcon(oneURL).getImage();
			confirmAnimation[i-1] = image;
//			confirmAnimation[i-1] = image
//					.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//			System.out.println("OK");
		}
	}
	


	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D graphics2d = RenderSetter.OpenRender(g);

//		Image image = confirmAnimation[index]
//				.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);

		
		graphics2d.drawImage
		(confirmAnimation[index],0, 0,this.getWidth(), this.getHeight(),null);
	}

	public void mouseExited(){
		isExit = true;
		//System.out.println();
		new Thread(){
			public void run() {
				synchronized (lockObject) {
					for(;index>=0;index--){
						repaint();

						try {
							Thread.sleep(gap);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					index = 0;
				}
			}
		}.start();

	}

	public void mouseEntered(){
		isExit = false;
		new Thread(){
			@Override
			public void run() {
				index = 0;
				// TODO Auto-generated method stub
				synchronized (lockObject) {
					for(index = 0;index<confirmAnimation.length
							&& !isExit;index++){
						repaint();

						try {
							Thread.sleep(gap);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(index>=confirmAnimation.length){
						index = confirmAnimation.length-1;
					}
				}
			}
		}.start();

	}

	public static void main(String[] args) {
	JFrame frame = new JFrame("test");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(300, 300);

	final CancelLabel label = new CancelLabel();
	label.setPreferredSize(new Dimension(100, 100));

	//frame.add(new AddBox());


	JButton button = new JButton("");
	button.addActionListener(new ActionListener() {
		int time = 0;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			time++;
			if(time%2==0){
				label.mouseExited();
			}else{
				label.mouseEntered();
			}
		}
	});

	frame.add(label,BorderLayout.CENTER);
	frame.add(button,BorderLayout.SOUTH);
	frame.setVisible(true);
}

}
