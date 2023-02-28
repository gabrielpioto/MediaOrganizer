package com.mediazer.imdb.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteLine extends AbstractJsonMapping {
	
	@JsonProperty("stage")
	private String stage;
	@JsonProperty("chars")
	private List<Name> chars;
	@JsonProperty("quote")
	private String quote;
	public String getStage() {
		return stage;
	}
	public List<Name> getChars() {
		return chars;
	}
	public String getQuote() {
		return quote;
	}
	

}
