package gap.common.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * 实现文本输入控制的类
 * @author YangYanfei
 *
 */
public class TestControlDocument extends PlainDocument {
	// 允许输入的字符串的长度,length为0时长度无限制
	private int length;
	// 允许输入的字符的正则表达式，默认为数字和字母
	public String regex = "[\\D&&[^a-zA-Z]]";

	public TestControlDocument(int length) {
		this.length = length;
	}

	public TestControlDocument() {
		this(0);
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if ((length != 0 && this.getLength() >= length) || str.matches(regex)) {
			str = "";
		}
		super.insertString(offs, str, a);
	}
}
