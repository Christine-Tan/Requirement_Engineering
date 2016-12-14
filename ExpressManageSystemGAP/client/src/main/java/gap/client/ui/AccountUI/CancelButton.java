package gap.client.ui.AccountUI;

import gap.client.ui.AccountUI.ComponentBehave.BoxCancelBehave;
import gap.client.ui.AccountUI.ComponentBehave.ComponentBehave;
import gap.client.ui.BaseComponents.CancelLabel;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.gapcomponents.GAPButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class CancelButton extends JPanel{

	private CancelLabel label;
	private GAPButton button;
	private AddBox box;		
	private String text = "取消";
	private MouseListener lis = new CancelListener();
	
	private ComponentBehave behave;
		
	public CancelButton(AddBox box){
		this.box = box;
		setBackground(Color.white);
		setLayout(null);
		label = new CancelLabel();
		
		behave = new BoxCancelBehave(box);
			
		setSize(new Dimension(80, 30));
		label.setBounds(5, 0, 30, 30);
			
		add(label);
		addMouseListener(lis);
			
	}
	
	public void reset() {
		// TODO Auto-generated method stub
		label.mouseExited();
	}
	
	public void setBehave(ComponentBehave behave){
		this.behave = behave;
	}
	
		
		@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// TODO Auto-generated method stub
		Graphics2D graphics2d = RenderSetter.OpenRender(g);
		graphics2d.setColor(ColorAndFonts.darkBlue);
		
		graphics2d.setFont(ColorAndFonts.getChinese(18));
		graphics2d.drawString(text, 40, 20);
		graphics2d.setColor(Color.white);
			
	}
		
	class CancelListener implements MouseListener{

			@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(behave!=null){
				behave.behave();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			label.mouseEntered();
		}

			@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			label.mouseExited();
		}

			@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
				
		}

			@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
			
	}
	
}
