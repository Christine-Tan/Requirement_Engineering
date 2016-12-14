package gap.client.util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * 这是一个模糊查找字符串的类，需要用的地方调用静态方法fuzzyQuery。<br/>
 * 参数是一个字符串组或者一个arraylist
 * 返回的SearchResult中属性有：被匹配的字符串，关键字，被匹配字符的位置数组,和该字符串在原来list中的index
 * @author 申彬
 *
 */

public class FuzzyQuery {

	private static FuzzyQuery fuzzyQuery = new FuzzyQuery();

	private FuzzyQuery() {
	}

	/**
	 * 模糊查找的算法：<p/>
	 * 第一个位置有一个基础权值100，第二个位置99，第三个位置98，以此类推。<br/>
	 * 从keyword的第一个字符开始，从头开始遍历一个字符串，每找到一个对应字符，<br/>
	 * 检查 连续匹配的boolean是否为true，是则累加器加200，若不是，累加器加基础权值<br/>
	 * 	，并且设置连续位为true，keyword的index++，接着找。<p/>
	 * 该算法会将连续出现keyword中连续字符的串排在前边，假如连续情况相同，追加一个判断，短的排前边<br>
	 * @param source
	 * @param keyword
	 * @return
	 */
	public static ArrayList<SearchResult> fuzzyQuery(String[] source,
			String keyword) {
		return fuzzyQuery.query(source, keyword);
	}

	public static ArrayList<SearchResult> fuzzyQuery(ArrayList<String> source,
			String keyword) {
		String[] strings = new String[source.size()];
		source.toArray(strings);
		return fuzzyQuery.query(strings, keyword);
	}

	public ArrayList<SearchResult> query(String[] source, String keyword) {
		ArrayList<Power> powers = new ArrayList<>(source.length);
		for (int i = 0; i < source.length; i++) {
			powers.add(new Power(source[i], i));
		}

		for (Power onePower : powers) {

			Boolean isHit = Boolean.FALSE;
			int keyIndex = 0;
			while (true) {
				if (keyIndex == keyword.length()) {
					break;
				}
				char keyChar = keyword.charAt(keyIndex);
				isHit = onePower.testHit(keyChar);

				if (isHit == null) {
					break;
				} else if (isHit.equals(Boolean.TRUE)) {
					keyIndex++;
				}

			}

		}

		// java默认是从小到大排的，这里反转一下变成从大到小
		powers.sort(null);
		Collections.reverse(powers);

		ArrayList<SearchResult> results = new ArrayList<>(powers.size() / 2);
		for (Power onePower : powers) {
			if (onePower.getPower() > 0) {
				SearchResult result = new SearchResult(onePower.getString(),
						keyword, onePower.getHitPos(),
						onePower.getFormerIndex());
				results.add(result);
			}
		}
		results.trimToSize();
		return results;

	}

	private class Power implements Comparable<Power> {
		private int power = 0;
		private String originalString;
		private String LowcaseString;
		private boolean isContinue = false;
		private int formerIndex = 0;
		private int myIndex = 0;

		ArrayList<Integer> hitPos = new ArrayList<>(10);

		Integer value1 = new Integer(0);
		Integer value2 = new Integer(0);

		private Power(String string, int formerIndex) {
			this.originalString = string;
			this.LowcaseString = string.toLowerCase();
			this.formerIndex = formerIndex;
		}

		/**
		 * 
		 * true代表匹配，false代表不匹配，null代表string结束
		 * @param target
		 * @return
		 */
		private Boolean testHit(char target) {
			// 如果匹配
			if (LowcaseString.length() == 0) {
				return null;
			}

			if (target == LowcaseString.charAt(myIndex)) {

				if (isContinue) {
					power += 200;
				} else {
					power += (100 - myIndex);
				}

				hitPos.add(myIndex);
				isContinue = true;
			} else {
				isContinue = false;
			}

			myIndex++;
			if (myIndex == LowcaseString.length()) {
				return null;
			} else {
				if (isContinue) {
					return true;
				} else {
					return false;
				}

			}
		}

		public int getPower() {
			return power;
		}

		public String getString() {
			return originalString;
		}

		public int[] getHitPos() {
			int[] pos = new int[hitPos.size()];
			for (int i = 0; i < hitPos.size(); i++) {
				pos[i] = hitPos.get(i);
			}
			return pos;
		}

		public int getFormerIndex() {
			return formerIndex;
		}

		@Override
		public int compareTo(Power o) {
			// TODO Auto-generated method stub
			value1 = power;
			value2 = o.power;

			int result = value1.compareTo(value2);
			if (result != 0) {
				return result;
			} else {
				value1 = LowcaseString.length();
				value2 = o.LowcaseString.length();
				// 这里结果取反，是为了让短的串排在前边
				result = -value1.compareTo(value2);
				return result;
			}

		}

	}

}
