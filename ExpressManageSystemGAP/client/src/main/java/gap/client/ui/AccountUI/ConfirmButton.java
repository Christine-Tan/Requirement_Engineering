package gap.client.ui.AccountUI;

import gap.client.ui.BaseComponents.ConfirmLabel;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.RenderSetter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class ConfirmButton extends JPanel{
	private ConfirmLabel label;
	private String text = "确定";
	private AddBox box;
	private MouseListener lis = new ConfirmListener();
	
	public ConfirmButton(AddBox box){
		this.box = box;
		setBackground(Color.white);
		setLayout(null);
		label = new ConfirmLabel();
		
		
		setSize(new Dimension(80, 30));
		label.setBounds(5, 0, 30, 30);
		
		add(label);
		addMouseListener(lis);
		
	}
	
	public void reset() {
		// TODO Auto-generated method stub
		label.mouseExited();
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
	
	class ConfirmListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			box.confirm();
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
