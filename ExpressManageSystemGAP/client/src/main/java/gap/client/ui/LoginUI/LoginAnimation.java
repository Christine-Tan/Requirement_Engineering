package gap.client.ui.LoginUI;

import gap.client.ui.BaseListener.MoveListener;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.RenderSetter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JLabel;

/**
 * @author 申彬
 *
 */
public class LoginAnimation extends JLabel {
	ArrayList<BaseLine> lines;
	ArrayList<MoveShape> shapes;
	Thread animation;
	boolean isStarted = false;
	LoginFrame frame;
	MoveListener listener;
	
	public LoginAnimation(LoginFrame frame) {
		this.setBounds(4, 4, 423, 172);
		this.setLayout(null);
		this.frame = frame;
		// this.setOpaque(false);
		listener = new MoveListener(frame);
		
		setBackground(ColorAndFonts.lightBlue);
		int[] height = { -20, 50, 130, 200 };
		int[] pointNums = { 10, 10, 10, 10 };

		lines = new ArrayList<>();
		shapes = new ArrayList<>();

		for (int i = 0; i < height.length; i++) {
			lines.add(new BaseLine(height[i], pointNums[i]));
		}

		/*
		 * 下面我要做的是，依次取出相邻两条线，每次随机推进1�?2个点，将上下两条线取出的点存入MoveShape中，
		 * 若一条线结束，则剩下�?条线全部点进入同�?个MoveShape
		 */
		for (int i = 0; i < lines.size() - 1; i++) {
			ArrayList<MovePoint> upLine = lines.get(i).points;
			ArrayList<MovePoint> downLine = lines.get(i + 1).points;
			int upLength = upLine.size();
			int downLength = downLine.size();
			// 先做两个1,2随机分布，但总和与线上点数相同的list
			ArrayList<Integer> upDivision = new ArrayList<>();
			ArrayList<Integer> downDivision = new ArrayList<>();
			// list的尾设为�?后一个点的index
			upDivision.add(upLength - 1);
			downDivision.add(downLength - 1);

			@SuppressWarnings("unchecked")
			ArrayList<Integer>[] lists = new ArrayList[2];
			lists[0] = upDivision;
			lists[1] = downDivision;

			// 分割
			for (int listNum = 0; listNum < lists.length; listNum++) {
				ArrayList<Integer> current = lists[listNum];
				int size = current.get(current.size() - 1);
				int index = 0;
				while (index < size) {

					int last = current.size() - 1;

					current.add(last, index);

					int step = 0;
					if (Math.random() >= 0.35) {
						step++;
					}
					index += step;

				}
			}

			// 根据分割结果添加形状
			makeShape(upLine, downLine, upDivision, downDivision);
		}

	}

	public void startAnimation() {
		if (isStarted) {
			System.out.println("绘画线程已经启动");
			return;
		}

		isStarted = true;
		animation = new Thread() {
			double idle = 5;

			public void run() {
				while (isStarted) {
					if (idle < 30) {
						idle += 0.5;
					}
					for (BaseLine line : lines) {
						line.move();
					}

					repaint();

					try {
						Thread.sleep((int) idle);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
			}

		};

		animation.start();
	}

	public void stop() {
		isStarted = false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

		Graphics2D graphics2d = RenderSetter.OpenRender(g);

		for (int i = 0; i < shapes.size(); i++) {
			MoveShape moveShape = shapes.get(i);
			graphics2d.setColor(moveShape.getColor());
			graphics2d.fill(moveShape.getShape());
		}
	}

	/**
	 * 这个方法传入两列点和两列点的分割法，形状添加点的顺序是左�?->右上->右下->左下
	 * @param upPoints
	 * @param downPoints
	 * @param upDivision
	 * @param downDivision
	 */
	private void makeShape(ArrayList<MovePoint> upPoints,
			ArrayList<MovePoint> downPoints, ArrayList<Integer> upDivision,
			ArrayList<Integer> downDivision) {

		int lessSize = Math.min(upDivision.size(), downDivision.size());

		for (int i = 0; i < lessSize - 1; i++) {
			MoveShape shape = new MoveShape();
			for (int j = upDivision.get(i); j <= upDivision.get(i + 1); j++) {
				shape.addPoint(upPoints.get(j));
			}

			for (int j = downDivision.get(i + 1); j >= downDivision.get(i); j--) {
				shape.addPoint(downPoints.get(j));
			}

			shapes.add(shape);

		}

		// 处理�?后没被添加的点，全部添加至一个形状中
		MoveShape shape = new MoveShape();
		for (int j = upDivision.get(lessSize - 1); j < upPoints.size(); j++) {
			shape.addPoint(upPoints.get(j));
		}

		for (int j = downPoints.size() - 1; j >= downDivision.get(lessSize - 1); j--) {
			shape.addPoint(downPoints.get(j));
		}
		shapes.add(shape);

	}

	/**
	 * 基线指的是一列点的基准线，一列点在一条基线上随机上下波动
	 * @author 申彬
	 */
	class BaseLine {
		double lineLength = 800;
		int lineHeight;
		int pointNum;
		ArrayList<MovePoint> points;

		public BaseLine(int lineHeight, int pointNum) {
			this.lineHeight = lineHeight;
			this.pointNum = pointNum;
			points = new ArrayList<>(pointNum);

			for (int i = 0; i < pointNum; i++) {
				// double diffX = 20*(Math.random()-0.5);
				double diffX = 0;
				double diffY = 20 * (Math.random() - 0.5);
				double x = 4+(i + 0.5) * (lineLength / pointNum) + diffX - 200;
				double y = 4+lineHeight + diffY;

				points.add(new MovePoint(x, y));
			}

		}

		public void move() {
			for (MovePoint point : points) {
				point.move();
			}
		}

	}

	/**
	 * 移动的形状�?�是由一组移动点确定出来的多边形，保存颜色和�?组点。颜色以随机速度在深蓝和浅蓝之间�?复运动，形状跟随点变�?
	 * @author 申彬
	 */
	class MoveShape {
		ArrayList<MovePoint> points = new ArrayList<>();
		int sign = 1;
		double[] rgb;
		double[] diffs;
		double[] upBounds;
		double[] downBounds;

		public MoveShape() {
			double changeRate = 0.01 + 0.008 * Math.random();
			// if(Math.random()>=0.5){
			// sign = -1;
			// }

			Color startColor = ColorAndFonts.darkBlue;
			Color endColor = ColorAndFonts.lightBlue;

			double[] upBounds = { endColor.getRed(), endColor.getGreen(),
					endColor.getBlue() };
			double[] downBounds = { startColor.getRed(), startColor.getGreen(),
					startColor.getBlue() };

			this.upBounds = upBounds;
			this.downBounds = downBounds;

			double r = startColor.getRed();
			double g = startColor.getGreen();
			double b = startColor.getBlue();

			double[] rgb = { r, g, b };
			this.rgb = rgb;

			double rDiff = (endColor.getRed() - startColor.getRed())
					* changeRate;
			double gDiff = (endColor.getGreen() - startColor.getGreen())
					* changeRate;
			double bDiff = (endColor.getBlue() - startColor.getBlue())
					* changeRate;

			double[] diffs = { rDiff, gDiff, bDiff };
			this.diffs = diffs;

		}

		public void addPoint(MovePoint onePoint) {
			points.add(onePoint);
		}

		public Shape getShape() {
			int size = points.size();
			int[] xLoc = new int[size];
			int[] yLoc = new int[size];

			for (int i = 0; i < size; i++) {
				double[] loc = points.get(i).getLocation();
				xLoc[i] = (int) loc[0];
				yLoc[i] = (int) loc[1];
			}

			Polygon polygon = new Polygon(xLoc, yLoc, size);
			return polygon;
		}

		public Color getColor() {

			int length = rgb.length;

			for (int i = 0; i < length; i++) {
				rgb[i] += sign * diffs[i];
			}

			for (int i = 1; i < length; i++) {
				if (rgb[i] >= upBounds[i] || rgb[i] <= downBounds[i]) {
					sign = -sign;
					break;
				}
			}

			int[] intRgb = new int[3];

			for (int i = 0; i < 3; i++) {
				intRgb[i] = (int) rgb[i];
			}

			Color color = new Color(intRgb[0], intRgb[1], intRgb[2]);

			return color;
		}

	}

	/**
	 * 移动点，�?个点以随机�?�度在一条随机直线上做往复运�?
	 * @author 申彬
	 */
	class MovePoint {
		Point2D.Double point;
		double startX;
		double startY;

		double vx = 0.2 + Math.random() * 0.3;
		double vy = Math.random() * 0.25;
		double distanceX = 30 + Math.random() * 50;
		double distanceY = 20 + Math.random() * 10;

		public MovePoint(double x, double y) {
			this.startX = x;
			this.startY = y;
			point = new Point2D.Double(x, y);
			if (Math.random() >= 0.5) {
				vx = -vx;
			}
			if (Math.random() >= 0.5) {
				vy = -vy;
			}

		}

		public double[] getLocation() {
			double[] location = { point.getX(), point.getY() };
			return location;
		}

		public void move() {

			double nextX = point.getX() + vx;
			if (Math.abs(startX - nextX) >= distanceX) {
				vx = -vx;
			}

			double nextY = point.getY() + vy;
			if (Math.abs(startY - nextY) >= distanceY) {
				vy = -vy;
			}

			point.setLocation(nextX, nextY);

		}

	}

}
