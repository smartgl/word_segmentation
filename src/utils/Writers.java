package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.Map;

public class Writers {
	public static void hashTableWritter(Hashtable<String, Integer> key_value,
			String fileName) {

		String s = new String();
		String s1 = new String();
		try {
			File f = new File(fileName);
			if (f.exists()) {
				System.out.println("file exist");
			} else {
				System.out.println("creating ....");
				if (f.createNewFile()) {
					System.out.println("successful created");
				} else {
					System.out.println("fail to created");
				}
			}
			BufferedReader input = new BufferedReader(new FileReader(f));
			while ((s = input.readLine()) != null) {
				s1 += s + "\n";
			}
			input.close();
			String content = "";
			BufferedWriter output = new BufferedWriter(new FileWriter(f));

			Map.Entry[] set = Utils.getSortedHashtableByValue(key_value);
			for (int i = 0; i < set.length; i++) {
				// if ((Integer) set[i].getValue() < 5)
				// continue;
				if (i == 0)
					// content += set[i].getKey().toString();
					content += set[i].getKey().toString() + ":"
							+ set[i].getValue().toString();
				else
					content += "\n" + set[i].getKey().toString() + ":"
							+ set[i].getValue().toString();
				// content += "\n" + set[i].getKey().toString();

			}
			s1 += content;
			output.write(s1);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}