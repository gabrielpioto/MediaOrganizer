package br.com.seriezer.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Episode {
	@JsonProperty("tvdb_id")
	private String tvdbId;
	@JsonProperty("season")
	private int season;
	@JsonProperty("episode")
	private int episode;
	@JsonProperty("title")
	private String title;
	@JsonProperty("overview")
	private String overview;
	@JsonProperty("date_based")
	private boolean dateBased;
	@JsonProperty("first_aired")
	private long firstAired;
	@JsonProperty("torrents")
	private Map<String,Torrent> torrents;
	public String getTvdbId() {
		return tvdbId;
	}
	public int getSeason() {
		return season;
	}
	public int getEpisode() {
		return episode;
	}
	public String getTitle() {
		return title;
	}
	public String getOverview() {
		return overview;
	}
	public boolean isDateBased() {
		return dateBased;
	}
	public long getFirstAired() {
		return firstAired;
	}
	public Map<String, Torrent> getTorrents() {
		return torrents;
	}
	
	
	
}
