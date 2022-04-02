package webSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSearch {

	static Scanner sc = new Scanner(System.in);

	// get occurence and position of word
	public static int searchGivenWord(File filePath, String s1) throws IOException {
		int counter = 0;
		String data = "";
		try {
			BufferedReader bf = new BufferedReader(new FileReader(filePath));
			String line = null;

			while ((line = bf.readLine()) != null) {

				data = data + line;
			}
			bf.close();

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		String txt = data;
		processing.BoyerMoore offset1 = new processing.BoyerMoore(s1);

		int offset = 0;

		for (int loc = 0; loc <= txt.length(); loc += offset + s1.length()) {

			offset = offset1.search(s1, txt.substring(loc));
			if ((offset + loc) < txt.length()) {
				counter++;
				System.out.println(s1 + " is at position " + (offset + loc)); // printing position of word
			}
		}
		if (counter != 0) {
			System.out.println("\nIn file: " + filePath.getName());
			System.out.println("-----------------------------------------------------\n");

		}
		return counter;
	}

	public static void websearch() {

		Hashtable<String, Integer> htable = new Hashtable<String, Integer>();
		Scanner s = new Scanner(System.in);

		System.out.println("Enter Y/y to search and N/n to exit : ");
		String choice = s.nextLine();
		String p;

		while (choice.equals("Y") | choice.equals("y")) {
			System.out.println("Enter your search : ");
			p = s.nextLine();

			long fileNumber = 0;
			int frequency = 0;
			int rep = 0; // No. of files that contains the Searched word
			try {
				// long startTime = System.currentTimeMillis();
				long startTimesearch = System.currentTimeMillis();
				File dir = new File("Resources/Text");

				File[] fileArray = dir.listFiles();
				for (int i = 0; i < fileArray.length; i++)

				{
					frequency = searchGivenWord(fileArray[i], p);
					htable.put(fileArray[i].getName(), frequency);
					if (frequency != 0) {
						rep++;
						System.out.println("\nFile containing " + p + " word is= " + fileArray[i]);
					}
					fileNumber++;
				}

				System.out.println("\nNumber of time the word " + p + " repeated is= " + rep);
				long endTimesearch = System.currentTimeMillis();
				System.out.println(
						"\nThe search took  " + (endTimesearch - startTimesearch) + " Milli Seconds to complete");
				System.out.println("Enter Y/y to search and N/n to exit : ");
				choice = s.nextLine();

			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}
	}

}
