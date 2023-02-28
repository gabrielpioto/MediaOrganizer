package com.mediazer.imdb.model;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo extends AbstractJsonMapping {
	
	@JsonProperty("link")
	private URL link;
	@JsonProperty("image")
	private Image image;
	public URL getLink() {
		return link;
	}
	public Image getImage() {
		return image;
	}

}
