package com.mediazer.imdb.model;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserComment extends AbstractJsonMapping {
	@JsonProperty("user_score")
	private int userScore = -1;
	@JsonProperty("summary")
	private String summary;
	@JsonProperty("user_location")
	private String userLocation;
	@JsonProperty("text")
	private String text;
	private LocalDate date;
	@JsonProperty("status")
	private String status;
	@JsonProperty("user_score_count")
	private int userScoreCount = -1;
	@JsonProperty("user_name")
	private String userName;
	@JsonProperty("user_rating")
	private int userRating = -1;

	public int getUserScore() {
		return userScore;
	}

	public String getSummary() {
		return summary;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public String getText() {
		return text;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getStatus() {
		return status;
	}

	public int getUserScoreCount() {
		return userScoreCount;
	}

	public String getUserName() {
		return userName;
	}

	public int getUserRating() {
		return userRating;
	}
	
	@JsonProperty("date")
	private void setLocalDate(String date){
		this.date = LocalDate.parse(date);
	}

}
