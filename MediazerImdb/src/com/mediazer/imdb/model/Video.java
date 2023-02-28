package com.mediazer.imdb.model;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Video extends AbstractJsonMapping {
	@JsonProperty("format")
	private String format;
	@JsonProperty("url")
	private URL url;
	public String getFormat() {
		return format;
	}
	public URL getUrl() {
		return url;
	}
	
	
}
