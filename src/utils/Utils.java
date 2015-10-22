package utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Utils {

	public static String trim(String source, String reg) {
		String result = Pattern.compile(reg).matcher(source).replaceAll("");
		return result;
	}

	public static Map.Entry[] getSortedHashtableByValue(
			Hashtable<String, Integer> h) {
		Set set = h.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
				.size()]);

		Arrays.sort(entries, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				int key1 = Integer.parseInt(((Map.Entry) arg0).getValue()
						.toString());
				int key2 = Integer.parseInt(((Map.Entry) arg1).getValue()
						.toString());
				return ((Comparable) key2).compareTo(key1);
			}
		});

		return entries;
	}
}
