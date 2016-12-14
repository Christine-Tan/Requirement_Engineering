package gap.server.ui.tools;

import java.awt.Color;
import java.awt.Font;

public class ColorAndFonts {

	final public static Color purple = new Color(174, 93, 161);
	final public static Color orange = new Color(241, 145, 73);

	final public static Color lightBlue = new Color(0, 223, 248);
	final public static Color blue = new Color(0, 163, 209);
	final public static Color darkBlue = new Color(0, 109, 162);
	
	final public static Color otherDarkBulue = new Color(63, 70, 128);

	final public static Color gray = new Color(235, 235, 235);
	final public static Color darkGray = new Color(180, 180, 180);
	
	final public static Color redOrange = new Color(253,68,0);

	final public static Font Chinese = new Font("方正等线", Font.PLAIN, 16);
	final public static Font English = new Font("Courier New", Font.PLAIN, 16);

	public static Font getChinese(int size) {
		return new Font("方正等线", Font.PLAIN, size);

	}

	public static Font getEnglish(int size) {
		return new Font("Courier New", Font.PLAIN, size);
	}
	
	public static Font getEnglish(int size,int style) {
		return new Font("Courier New", style, size);
	}
	
	public static Font getChinese(int size,int style) {
		return new Font("方正等线", style, size);
	}

}
