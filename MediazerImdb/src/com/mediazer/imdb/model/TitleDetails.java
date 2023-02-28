package com.mediazer.imdb.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mediazer.imdb.model.news.News;

public class TitleDetails extends Title {
	
	@JsonProperty("photos")
	private List<Photo> photos;
	@JsonProperty("seasons")
	private List<String> seasons;
	@JsonProperty("directors_summary")
	private List<Cast> directors;
	@JsonProperty("user_comment")
	private UserComment userComment;
	@JsonProperty("certificate")
	private Certificate certificate;
	@JsonProperty("has")
	private List<String> has;
	@JsonProperty("series")
	private Title serie;
	@JsonProperty("writers_summary")
	private List<Cast> writers;
	@JsonProperty("rating")
	private double rating = -1;
	@JsonProperty("num_votes")
	private long numVotes = -1;
	@JsonProperty("genres")
	private List<String> genres;
	@JsonProperty("best_plot")
	private Plot bestPlot;
	@JsonProperty("quote")
	private Quote quote;
	@JsonProperty("can_rate")
	private boolean canRate;
	@JsonProperty("trailer")
	private Trailer trailer;
	@JsonProperty("trivium")
	private String trivium;
	@JsonProperty("goof")
	private String goof;
	@JsonProperty("tagline")
	private String tagline;
	@JsonProperty("runtime")
	private Runtime runtime;
	@JsonProperty("cast_summary")
	private List<Cast> cast;
	@JsonProperty("plot")
	private Plot plot;
	@JsonProperty("production_status")
	private String status;
	@JsonProperty("news")
	private News news;
	
	
	public List<Photo> getPhotos() {
		return photos;
	}
	public List<String> getSeasons() {
		return seasons;
	}
	public List<Cast> getDirectors() {
		return directors;
	}
	public UserComment getUserComment() {
		return userComment;
	}
	public Certificate getCertificate() {
		return certificate;
	}
	public List<String> getHas() {
		return has;
	}
	public Title getSerie() {
		return serie;
	}
	public List<Cast> getWriters() {
		return writers;
	}
	public double getRating() {
		return rating;
	}
	public long getNumVotes() {
		return numVotes;
	}
	public List<String> getGenres() {
		return genres;
	}
	public Plot getBestPlot() {
		return bestPlot;
	}
	public Quote getQuote() {
		return quote;
	}
	public boolean isCanRate() {
		return canRate;
	}
	public Trailer getTrailer() {
		return trailer;
	}
	public String getTrivium() {
		return trivium;
	}
	public String getGoof() {
		return goof;
	}
	public String getTagline() {
		return tagline;
	}
	public Runtime getRuntime() {
		return runtime;
	}
	public List<Cast> getCast() {
		return cast;
	}
	public Plot getPlot() {
		return plot;
	}
	public String getStatus() {
		return status;
	}
	public News getNews() {
		return news;
	}
	
	
	
	
	
	
	
}
