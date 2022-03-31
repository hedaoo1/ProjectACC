package webSearchEngine;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.lang.model.element.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawler {

	public List<String> visitedLinks = new ArrayList<String>();
	public List<String> fetchedLinks = new LinkedList<String>();
	private Document currentDocument;

	public void htmlToText(String baseUrl) {
		try {
			final String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0";
			Document document = Jsoup.connect(baseUrl).userAgent(userAgent).timeout(10 * 1000).ignoreHttpErrors(true)
					.get();
			this.currentDocument = document;
			String txtDestination = "Resources/Text/" + document.title() + ".txt";
			FileWriter fileWriter = new FileWriter(txtDestination);
			fileWriter.write(document.body().text());
			fileWriter.close();

			String htmDestination = "Resources/Html/" + document.title() + ".htm";
			fileWriter = new FileWriter(htmDestination);

			fileWriter.write(document.html());
			fileWriter.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Exception Occured While Converting Html to Text");
		}
	}

	public boolean filter(String baseUrl) {
		if (baseUrl.contains("ads") || baseUrl.contains("linkedin") || baseUrl.contains("facebook")
				|| baseUrl.contains("instagram") || baseUrl.contains("twitter")) {
			return false;
		} else if (visitedLinks.contains(baseUrl)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean startCrawling(String baseUrl, int depth) {
		if (depth == 0) {
			if (filter(baseUrl) == false) {
				return false;
			} else {
				htmlToText(baseUrl);
				visitedLinks.add(baseUrl);
				return true;
			}
		}
		Elements fetchedLinks = currentDocument.select("a[href]");

		for (org.jsoup.nodes.Element link : fetchedLinks) {
			if (filter(link.toString())) {
				this.fetchedLinks.add(link.absUrl("href"));
			}
		}
		

		return true;
	}
}
