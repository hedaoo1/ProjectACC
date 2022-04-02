package webSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

public class WordSearch {

	static Scanner sc = new Scanner(System.in);

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
				System.out.println(s1 + " is at position " + (offset + loc));
			}
		}
		if (counter != 0) {
			System.out.println("\nIn file: " + filePath.getName());
			System.out.println("-----------------------------------------------------\n");

		}
		return counter;
	}

	public static void search() {

		Hashtable<String, Integer> htable = new Hashtable<String, Integer>();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Y/y to search and N/n to exit : ");
		String choice = s.nextLine();
		String word;

		while (choice.equals("Y") | choice.equals("y")) {
			System.out.println("Enter the word to search : ");
			word = s.nextLine();
			long fileNumber = 0;
			int wordFrequency = 0;
			int wordInFiles = 0; 
			try {
				long startSearchTime = System.currentTimeMillis();
				File directory = new File("Resources/Text");
				File[] fileList = directory.listFiles();
				for (int i = 0; i < fileList.length; i++)
				{
					wordFrequency = searchGivenWord(fileList[i], word);
					htable.put(fileList[i].getName(), wordFrequency);
					if (wordFrequency != 0) {
						wordInFiles++;
						System.out.println("\nFile containing " + word + " word is= " + fileList[i]);
					}
					fileNumber++;
				}
				System.out.println("\nNumber of time the word " + word + " repeated is= " + wordInFiles);
				long endSearchTime = System.currentTimeMillis();
				System.out.println(
						"\nThe search took  " + (endSearchTime - startSearchTime) + " Milli Seconds to complete");
				System.out.println("Enter Y/y to search and N/n to exit : ");
				choice = s.nextLine();

			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}
	}
}
