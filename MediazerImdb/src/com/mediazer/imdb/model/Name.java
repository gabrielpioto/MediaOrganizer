package com.mediazer.imdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Name extends AbstractJsonMapping {
	
	@JsonProperty("nconst")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("char")
	private String character;
	@JsonProperty("image")
	private Image image;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCharacter() {
		return character;
	}
	public Image getImage() {
		return image;
	}
	
	

}
