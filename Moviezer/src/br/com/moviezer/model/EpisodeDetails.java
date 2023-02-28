package br.com.moviezer.model;

import java.time.LocalDate;

public class EpisodeDetails extends VideoDetails{
	
	private String serieName;
	private String seasonName;
	private int seasonNumber;
	private int episodeNumber;
	
	public EpisodeDetails(String id, String title, LocalDate releaseDate) {
		super(id, title, releaseDate);
	}

	public String getSerieName() {
		return serieName;
	}

	public void setSerieName(String serieName) {
		this.serieName = serieName;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}
	
	public String getPrettyEpisodeNumber(){
		String prefix="";
		if(episodeNumber<10) prefix = "0";
		return prefix+episodeNumber;
	}
	
	public String getPrettySeasonNumber(){
		String prefix="";
		if(seasonNumber<10) prefix = "0";
		return prefix+seasonNumber;
	}

}
