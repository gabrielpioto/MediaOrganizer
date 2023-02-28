package com.mediazer.imdb.model;

import java.util.Map;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Title extends AbstractJsonMapping {

	private String id;
	@JsonProperty("year_end")
	private String yearEnd;
	@JsonProperty("title")
	private String title;
	@JsonProperty("type")
	private String type;
	@JsonProperty("year")
	private String year;
	@JsonProperty("image")
	private Image image;
	private LocalDate releaseDate;

	public String getId() {
		return id;
	}

	public String getYearEnd() {
		return yearEnd;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public String getYear() {
		return year;
	}

	public Image getImage() {
		return image;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	@JsonProperty("release_date")
	private void setReleaseDate(Map<String, String> map) {
		for (String s : map.values()) {
			releaseDate = LocalDate.parse(s);
			return;
		}
	}

	@JsonProperty("tconst")
	private void setId(String id) {
		this.id = id;
	}

	@JsonProperty("title_id")
	private void setTitleId(String id) {
		this.id = id;
	}
}
