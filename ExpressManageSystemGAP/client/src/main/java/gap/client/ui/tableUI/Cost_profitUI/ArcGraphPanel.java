package gap.client.ui.tableUI.Cost_profitUI;

import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.RenderSetter;
import gap.client.ui.gapcomponents.ComponentStyle;
import gap.client.util.MessageType;
import gap.client.vo.Cost_ProfitListVO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class ArcGraphPanel extends JPanel{
	
	Cost_ProfitListVO vo;
	AffineTransform translate = new AffineTransform();
	AffineTransform scale = new AffineTransform();
	
	AffineTransform save;
	
	Area incomeArc = new Area();
	Area paymentArc = new Area();
	//画出扇形后，中间所减去的圆形,半径是扇形半径减去扇形基础宽度
	Ellipse2D subEllipse;
	
	//字体
	Font Chinese;
	Font English;
	
	String centerText = "";
	String leftText = "";
	String rightText = "";
	
	//展开的程度
	double extendRate =0;
	
	double baseWidth = 800;
	double baseHeight = 500;
	
	//扇形的基础半径
	double baseRadius = 200;
	//扇形的基础宽度
	double baseArcWidth = 50;
	//长宽比
	double width_height_ratio = 0;
	
	boolean isStop = true;
	Object lockObject = new Object();
	
	//中心文字
	int centerTextX;
	int centerTextY;
	int centerTextLength;
	int centerTextHeight;
	
	//左边文字
	int leftTextX;
	int leftTextY;
	int leftTextLength;
	int leftTextHeight;
		
	//右边文字
	int rightTextX;
	int rightTextY;
	int rightTextLength;
	int rightTextHeight;
	
	public ArcGraphPanel(Cost_ProfitListVO vo){
		this.vo = vo;
		width_height_ratio = baseWidth/baseHeight;
		setBackground(Color.white);
		Chinese = ColorAndFonts.getChinese(30);
		English = ColorAndFonts.getEnglish(50, Font.BOLD);
		
		SelectListener l = new SelectListener();
		addMouseMotionListener(l);
		addMouseListener(l);
	
	}
	
	public void setVO(Cost_ProfitListVO vo){
		this.vo = vo;
	}
	
	public void startAnimation(){
		isStop = true;
		
		synchronized (lockObject) {
			isStop = false;
			extendRate = 0;
			new ExtendThread().start();
		}
	}
	
	/**
	 * 获得变化后的area
	 * @return
	 */
	private void transGraphics(Graphics2D graphics2d){
		save = graphics2d.getTransform();
		
		double realWidth = getWidth();
		double realHeight = getHeight();
		double Wradio = realWidth/baseWidth;
		double Hradio = realHeight/baseHeight;
		
		double radio = Math.min(Wradio, Hradio);
		
		translate.setToIdentity();
		scale.setToIdentity();
		
		//缩放至相应倍率
		scale.scale(radio, radio);
		//平移至中心
		translate.translate(realWidth/2, realHeight/2.2);
		
		//不知道对不对
		//我去,居然是对的23333
		graphics2d.transform(translate);
		graphics2d.transform(scale);

	}
	
	private Point2D transPoint(Point p){
		AffineTransform inverseTranslate = null;
		try {
			inverseTranslate = translate.createInverse();
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AffineTransform inverseScale = null;
		try {
			inverseScale = scale.createInverse();
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Point2D disP = new Point();

		inverseTranslate.transform(p, disP);
		inverseScale.transform(disP, p);
		
		return p;
	}
	
	
	private void resetGraphics(Graphics2D graphics2d){
		graphics2d.setTransform(save);
	}
	
	private void extendArc(){
		double income = vo.getIncome();
		double payment = vo.getPayment();
		
		double total = income + payment;
		double incomeRate = income/total;
		double paymentRate = payment/total;
		

		incomeArc = getArc(incomeRate, 135);
		paymentArc = getArc(paymentRate, -45);
		
	}
	
	private Area getArc(double arcRate,double rotate){
		//扇面的一半所占的角度
		double arcHalf = 360*arcRate/2;
		//现在所展开的一半的角度
		double currentHalf = arcHalf*extendRate;	
		
		//System.out.println("current half is"+ currentHalf);
		
		double baseRectX = -baseRadius;
		double baseRectWidth = baseRadius*2;
				
		Rectangle2D rectangle2d = new Rectangle2D.Double(baseRectX, baseRectX,
						baseRectWidth, baseRectWidth);
				
		Arc2D arc = new Arc2D.Double
				(rectangle2d, rotate-currentHalf, 2*currentHalf, Arc2D.PIE);
		
		double ellipseX = baseRectX + baseArcWidth;
		double ellipseWidth = baseRectWidth- 2*baseArcWidth;
		
	//	System.out.println(elli);
		subEllipse = new Ellipse2D.Double
				(ellipseX, ellipseX,ellipseWidth, ellipseWidth);
		
		Area area = new Area(arc);
		Area ellipse = new Area(subEllipse);
		
		//扇形减圆形
		area.subtract(ellipse);
		
		return area;
		
	}
	

	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D graphics2d = RenderSetter.OpenRender(g);
		transGraphics(graphics2d);
		
		graphics2d.setColor(Color.green);
		graphics2d.fill(incomeArc);
		
		graphics2d.setColor(ComponentStyle.red);
		graphics2d.fill(paymentArc);
		
		graphics2d.setColor(Color.darkGray);
		graphics2d.setFont(English);
		graphics2d.drawString(centerText, centerTextX, centerTextY);
		
		graphics2d.setColor(Color.black);
		graphics2d.setFont(Chinese);
		graphics2d.drawString(leftText, leftTextX, leftTextY);
		
		graphics2d.drawString(rightText, rightTextX, rightTextY);
		
		resetGraphics(graphics2d);
	}
	
	/**
	 * 计算文字位置<br/>
	 * 这段代码非常丑陋<br/>
	 * 非常<br/>
	 * 丑陋
	 */
	private void computeTextLocation(){
		//centerText = String.format("%.2f", vo.getRate() * extendRate);
		//centerText += "%";
		double realRate = extendRate;
		if(realRate>0.9){
			realRate = 1;
		}
		centerText = String.format("%.2f", vo.getRate() * realRate * 100);
		centerText += "%";
		
		
		centerTextLength = centerText.length() * English.getSize();
		centerTextHeight = English.getSize();
		centerTextX = -centerTextLength/2;
		centerTextY = -centerTextHeight/2;
		centerTextX+=(int)(centerTextLength*1.0/4);
		centerTextY+=(int)(centerTextHeight*3.0/4);
		
		leftText = String.format("%.2f", vo.getIncome() * realRate);
		leftText = "+"+leftText+"元";
		
		leftTextLength = leftText.length() * Chinese.getSize();
		leftTextHeight = Chinese.getSize();
		leftTextX = (int)(-leftTextLength/2 - baseRadius*1.5);
		leftTextY = (int)(-leftTextHeight/2 - baseRadius*0.6);
		leftTextX+=(int)(leftTextLength*1.0/4);
		leftTextY+=(int)(leftTextHeight*3.0/4);
		
		rightText = String.format("%.2f", vo.getPayment() * realRate);
		rightText = "-"+rightText+"元";
		rightTextLength = rightText.length() * Chinese.getSize();
		rightTextHeight = Chinese.getSize();
		rightTextX = (int)(-rightTextLength/2 + baseRadius*1.4);
		rightTextY = (int)(-rightTextHeight/2 + baseRadius*0.6);
		rightTextX+=(int)(rightTextLength*1.0/4);
		rightTextY+=(int)(rightTextHeight*3.0/4);
		
	}
	
	public enum State{
		normal,enter,exit;
	}
	
	class SelectListener implements MouseMotionListener,MouseListener{

		AffineTransform bigger;
		AffineTransform smaller;
		
		
		boolean incomeBigger = false;
		boolean paymentBigger = false;
	
		public SelectListener() {
			bigger = new AffineTransform();
			//放大一点
			bigger.scale(1.05, 1.05);
			
			try {
				//放大的逆变换
				smaller = bigger.createInverse();
			} catch (NoninvertibleTransformException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
			State incomeState = getState(incomeArc, incomeBigger, e);
			switch(incomeState){
				case normal:
					break;
				case enter:
					incomeArc = new Area(bigger.createTransformedShape(incomeArc));
					incomeBigger = true;
					repaint();
					break;
				case exit:
					incomeArc = new Area(smaller.createTransformedShape(incomeArc));
					incomeBigger = false;
					repaint();
					break;
			}
			
			State paymentState = getState(paymentArc, paymentBigger, e);
			
			switch(paymentState){
				case normal:
					break;
				case enter:
					paymentArc = new Area(bigger.createTransformedShape(paymentArc));
					paymentBigger = true;
					repaint();
					break;
				case exit:
					paymentArc = new Area(smaller.createTransformedShape(paymentArc));
					paymentBigger  = false;
					repaint();
					break;
			}
			
			
		}
		
	
		
		public State getState(Area area,boolean isBigger,MouseEvent e){
			Point2D p = transPoint(e.getPoint());
			
			if(area.contains(p) && !isBigger){
				
				return State.enter;
			}else if(!area.contains(p) && isBigger){
				
				return State.exit;
			}else{
				
				return State.normal;
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(incomeBigger){
				String s = "共收入"+leftText.substring(1);
				MainFrame.setMessage(s, MessageType.succeed, 1000);
			}else if(paymentBigger){
				String s = "共支出"+rightText.substring(1);
				MainFrame.setMessage(s, MessageType.alram, 1000);	
			}
			
			Point2D p = transPoint(e.getPoint());
			if(subEllipse.contains(p)){
				String s = "净收入"+String.format("%.2f", vo.getNetIncome())+"元";	
				MainFrame.setMessage(s, MessageType.succeed, 2000);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
	
	
	class ExtendThread extends Thread{
		
		public void run() {
			
			synchronized (lockObject) {
				while (extendRate<0.97 && !isStop) {
					
					extendRate+=0.03;
					
					if(extendRate>1){
						extendRate = 0.97;
					}
					
					extendArc();
					computeTextLocation();
					repaint();
					
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
		}
	}

}
