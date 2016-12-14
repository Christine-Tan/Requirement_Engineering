package gap.client.ui.gapcomponents;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.RenderSetter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

/**
 * 
 * 本来想做一个带阴影效果的label，但是发现不带阴影更好看
 * @author 申彬
 *
 */
public class RichLable extends JLabel{

	String text;
	Color textColor = Color.black;
	Color shadowColor = Color.gray;
	Color backGround = new Color(232, 255, 232);
	int fontSize = 0;
	Font font;
	//阴影距离
	int shadowDistance = 2;
	
	int textLength = 0;
	int textHeight = 0;
	
	boolean isButtom = false;
	
	public RichLable(String text,int fontSize){
		this.text = text;
		this.fontSize = fontSize;
		
		font = ColorAndFonts.getChinese(fontSize);
		textLength = this.text.length()*fontSize;
		textHeight = fontSize;
		setPreferredSize(new Dimension(textLength, textHeight+10));
	}
	
	public void setTextBottom(){
		isButtom = true;
	}
	
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D graphics2d = RenderSetter.OpenRender(g);
		
		//计算位置
		int width = getWidth();
		int height = getHeight();
		int x = (width-textLength)/2;
		int y = (height-textHeight)/2;
		y+=(int)(textHeight*3.0/4);
		
		if(isButtom){
			y+=(int)(textHeight*1.0/4);
		}
		
		graphics2d.setColor(Color.white);
		graphics2d.fillRect(0, 0, width, height);
		
		graphics2d.setFont(font);
		
//		graphics2d.setColor(shadowColor);
//		graphics2d.drawString(text, x+shadowDistance, y+shadowDistance);
		
		graphics2d.setColor(textColor);
		graphics2d.drawString(text, x, y);
		
		
	}
	
//	public static void main(String[] args) {
//
//		JFrame frame = new JFrame("test");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(100, 100);
//		frame.add(new TitleLable("付款单",40));
//		frame.setVisible(true);
//
//	}
	
	
}
