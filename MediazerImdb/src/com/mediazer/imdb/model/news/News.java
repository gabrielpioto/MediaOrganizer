package com.mediazer.imdb.model.news;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class News {
	@JsonProperty("channel")
	private String channel;
	@JsonProperty("total")
	private long total = -1;
	@JsonProperty("sources")
	private Map<String, Source> sources;
	@JsonProperty("markup")
	private String markup;
	@JsonProperty("label")
	private String label;
	@JsonProperty("limit")
	private int limit = -1;
	@JsonProperty("start")
	private int start = -1;
	@JsonProperty("items")
	private List<Item> items;
	@JsonProperty("@type")
	private String type;
	public String getChannel() {
		return channel;
	}
	public long getTotal() {
		return total;
	}
	public Map<String, Source> getSources() {
		return sources;
	}
	public String getMarkup() {
		return markup;
	}
	public String getLabel() {
		return label;
	}
	public int getLimit() {
		return limit;
	}
	public int getStart() {
		return start;
	}
	public List<Item> getItems() {
		return items;
	}
	public String getType() {
		return type;
	}
	
}
