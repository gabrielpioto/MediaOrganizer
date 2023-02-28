package br.com.seriezer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Serie {
	@JsonProperty("air_day")
	private String airDay; //TODO change to weekday
	@JsonProperty("airTime")
	private String airTime; //TODO change to Time
	@JsonProperty("country")
	private String country;
	@JsonProperty("images")
	private Images images;
	@JsonProperty("imdb_id")
	private String imdbId;
	@JsonProperty("last_updated")
	private String lastUpdated;
	@JsonProperty("network")
	private String network;
	@JsonProperty("num_seasons")
	private int numSeasons;
	@JsonProperty("runtime")
	private long runtime;
	@JsonProperty("slug")
	private String slug;
	@JsonProperty("status")
	private String status;
	@JsonProperty("synopsis")
	private String synopsis;
	@JsonProperty("title")
	private String title;
	@JsonProperty("tvdb_id")
	private String tvdbId;
	@JsonProperty("year")
	private int year;
	@JsonProperty("episodes")
	private List<Episode> episodes;
	@JsonProperty("genres")
	private List<String> genres;
	public String getAirDay() {
		return airDay;
	}
	public String getAirTime() {
		return airTime;
	}
	public String getCountry() {
		return country;
	}
	public Images getImages() {
		return images;
	}
	public String getImdbId() {
		return imdbId;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public String getNetwork() {
		return network;
	}
	public int getNumSeasons() {
		return numSeasons;
	}
	public long getRuntime() {
		return runtime;
	}
	public String getSlug() {
		return slug;
	}
	public String getStatus() {
		return status;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public String getTitle() {
		return title;
	}
	public String getTvdbId() {
		return tvdbId;
	}
	public int getYear() {
		return year;
	}
	public List<Episode> getEpisodes() {
		return episodes;
	}
	public List<String> getGenres() {
		return genres;
	}
	
	
}
