package com.mediazer.imdb.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trailer extends AbstractJsonMapping {
	@JsonProperty("encodings")
	private Map<String, Video> videos;
	@JsonProperty("description")
	private String description;
	@JsonProperty("relatedTitle")
	private Title relatedTitle;
	@JsonProperty("duration_seconds")
	private int durationSeconds = -1;
	@JsonProperty("slates")
	private List<Image> slates;
	@JsonProperty("content_type")
	private String contentType;
	@JsonProperty("relatedName")
	private Name relatedName;
	@JsonProperty("id")
	private String id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("@type")
	private String type;

	public Map<String, Video> getVideos() {
		return videos;
	}

	public String getDescription() {
		return description;
	}

	public Title getRelatedTitle() {
		return relatedTitle;
	}

	public int getDurationSeconds() {
		return durationSeconds;
	}

	public List<Image> getSlates() {
		return slates;
	}

	public String getContentType() {
		return contentType;
	}

	public Name getRelatedName() {
		return relatedName;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

}
