package webSearchEngine;

import java.util.Scanner;

public class Engine {
	public static void main(String args[]) {
		System.out.println("*************Welcome to Web Search Engine!!*************\n");

		boolean flag = true;
		Scanner sc = new Scanner(System.in);

		do {
			showMenu();
			int choice = sc.nextInt();
			if (choice == 0) {
				Crawler crawler = new Crawler();
				crawler.startCrawling("https://www.w3schools.com/", 0);
			} else if (choice == 1) {
				WordSearch wordSearch = new WordSearch();
				wordSearch.search();
			} else if(choice == 2) {
				flag = false;
				System.out.println("Thanks for using Web Search Engine!!");
				
			}
			
			else {
				System.out.println("Please enter the valid choice");
			}

		} while (flag);
	}

	private static void showMenu() {
		String[] options = { "Crawl the data","Word Search", "Exit" };
		System.out.println("Please Enter the choice: ");

		for (int i = 0; i < options.length; i++) {
			System.out.println(i + ": " + options[i]);
		}
	}
}
