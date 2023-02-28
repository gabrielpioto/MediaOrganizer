package br.com.seriezer.model;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Images {
	@JsonProperty("poster")
	private URL poster;
	@JsonProperty("fanart")
	private URL fanart;
	@JsonProperty("banner")
	private URL banner;
	public URL getPoster() {
		return poster;
	}
	public URL getFanart() {
		return fanart;
	}
	public URL getBanner() {
		return banner;
	}
	
	
	
}
