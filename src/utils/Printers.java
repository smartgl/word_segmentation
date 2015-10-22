package utils;

import java.util.Hashtable;
import java.util.Map;

public class Printers {
	public static void hashTablePrinter(Hashtable<String, Integer> key_value) { // perportyTable
		Map.Entry[] set = Utils.getSortedHashtableByValue(key_value);
		for (int i = 0; i < set.length; i++) {
			System.out.println(set[i].getKey().toString() + ":"
					+ set[i].getValue().toString());
		}
	}

}
