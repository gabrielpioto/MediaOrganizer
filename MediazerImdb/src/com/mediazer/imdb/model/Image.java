package com.mediazer.imdb.model;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image extends AbstractJsonMapping {
	
	@JsonProperty("width")
	private int width;
	@JsonProperty("height")
	private int height;
	@JsonProperty("url")
	private URL url;
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public URL getUrl() {
		return url;
	}
	
	
}
