package com.mediazer.imdb.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote extends AbstractJsonMapping {
	
	@JsonProperty("lines")
	private List<QuoteLine> lines;
	@JsonProperty("qconst")
	private String id;
	public List<QuoteLine> getLines() {
		return lines;
	}
	public String getId() {
		return id;
	}
	

}
