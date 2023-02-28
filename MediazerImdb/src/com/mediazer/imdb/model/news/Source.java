package com.mediazer.imdb.model.news;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Source {
	@JsonProperty("logo")
	private URL logo;
	@JsonProperty("url")
	private URL url;
	@JsonProperty("label")
	private String label;

	public URL getLogo() {
		return logo;
	}

	public URL getUrl() {
		return url;
	}

	public String getLabel() {
		return label;
	}

}
