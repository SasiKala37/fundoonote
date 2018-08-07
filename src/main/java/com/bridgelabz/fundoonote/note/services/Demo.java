/*package com.bridgelabz.fundoonote.note.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Demo {

	public static String getDomainName(String url) {

		String pattern = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,15}";
		Pattern patrn = Pattern.compile(pattern);

		String domainName = "";
		Matcher matcher = patrn.matcher(url);

		if (matcher.find()) {
			domainName = matcher.group(0).toLowerCase().trim();
		}

		return domainName;
	}

	public static void main(String[] args) throws IOException {

		String query = "Devoxx Morocco";

		String url = "https://www.google.com/search?q=" + query + "&num=10";

		Document doc = Jsoup.connect(url).userAgent("Jsoup client").timeout(5000).get();

		Elements links = doc.select("a[href]");

		Set<String> result = new HashSet<>();

		for (Element link : links) {

			String attr1 = link.attr("href");
			String attr2 = link.attr("class");

			if (!attr2.startsWith("_Zkb") && attr1.startsWith("/url?q=")) {

			result.add(getDomainName(attr1));
			 }
		}

		for (String el : result) {
			System.out.println(el);
		}

	}

}
*/