package com.mediazer.imdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cast extends AbstractJsonMapping {
	
	@JsonProperty("as")
	private String roleAs;
	@JsonProperty("char")
	private String character;
	@JsonProperty("name")
	private Name name;
	@JsonProperty("attr")
	private String attr;
	
	public String getRoleAs() {
		return roleAs;
	}
	public String getCharacter() {
		return character;
	}
	public Name getName() {
		return name;
	}
	public String getAttr() {
		return attr;
	}
	
	
	

}
