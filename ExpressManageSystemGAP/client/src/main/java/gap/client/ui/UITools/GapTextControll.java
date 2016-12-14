package gap.client.ui.UITools;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class GapTextControll extends PlainDocument {
	// 允许输入的字符串的长度
	private int length;
	// 允许输入的字符的正则表达式
	public String regex;

	// 获得匹配数字的文本
	public static PlainDocument getNumberDocument(int length) {
		return new GapTextControll("[\\D]", length);
	}

	public GapTextControll(int length) {
		regex = "[\\D&&[^a-zA-Z]]";
		this.length = length;
	}

	public GapTextControll(String regex, int length) {
		this.regex = regex;
		this.length = length;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if ((length < 0 || this.getLength() >= length) || str.matches(regex)) {
			str = "";
		}
		super.insertString(offs, str, a);
	}
}
