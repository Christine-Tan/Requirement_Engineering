package gap.client.ui.UITools;

import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class AreaMaker {

	public static Area getRoundRect(double width, double height, double radius) {
		RoundRectangle2D.Double roundRect = new RoundRectangle2D.Double(0, 0,
				width, height, radius, radius);
		return new Area(roundRect);
	}

}
