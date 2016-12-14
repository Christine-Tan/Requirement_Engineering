//package gap.client.util;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class FuzzyQueryTest {
//
//	ArrayList<String> strings;
//	String keyword;
//
//	@Before
//	public void setUp() throws Exception {
//
//		File file = new File("src/test/java/gap/client/testQuery.txt");
//		BufferedReader reader = new BufferedReader(new FileReader(file));
//		String oneLine = "";
//		strings = new ArrayList<>(300);
//		String regex = "\\p{Punct}|\\s|\\pP";
//
//		while ((oneLine = reader.readLine()) != null) {
//			String[] subString = oneLine.split(regex);
//			for (String s : subString) {
//				strings.add(s);
//			}
//		}
//		reader.close();
//
//		Scanner scanner = new Scanner(System.in);
//		keyword = scanner.nextLine();
//		scanner.close();
//
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void testFuzzyQuery() {
//		String[] stringArray = new String[strings.size()];
//		strings.toArray(stringArray);
//		ArrayList<SearchResult> results = FuzzyQuery.fuzzyQuery(stringArray,
//				keyword);
//		StringBuilder builder = new StringBuilder();
//
//		for (SearchResult result : results) {
//			String s = result.getString();
//			int[] targets = result.getMatchIndex();
//
//			int targetIndex = 0;
//			for (int i = 0; i < s.length(); i++) {
//				if (i == targets[targetIndex]) {
//					builder.append('(');
//					builder.append(s.charAt(i));
//					builder.append(')');
//					if (targetIndex < targets.length - 1) {
//						targetIndex++;
//					}
//				} else {
//					builder.append(s.charAt(i));
//				}
//			}
//			System.out.println(builder.toString());
//			builder.delete(0, builder.length());
//		}
//
//	}
//
//}
