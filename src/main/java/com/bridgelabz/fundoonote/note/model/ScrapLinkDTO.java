package com.bridgelabz.fundoonote.note.model;

import java.util.List;


import org.jsoup.select.Elements;

public class ScrapLinkDTO {

	private List<String> title;
	private List<String> links;
	private List<String> imageLink;

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public List<String> getImageLink() {
		return imageLink;
	}

	public void setImageLink(List<String> imageLink) {
		this.imageLink = imageLink;
	}
	
	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

	
	
}
